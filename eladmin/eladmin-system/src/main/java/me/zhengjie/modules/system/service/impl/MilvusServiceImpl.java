package me.zhengjie.modules.system.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.xiaoymin.knife4j.core.util.CollectionUtils;
import com.google.common.collect.Maps;
import com.jujutsu.tsne.TSne;
import com.jujutsu.tsne.TSneConfiguration;
import com.jujutsu.tsne.barneshut.ParallelBHTsne;
import com.jujutsu.utils.TSneUtils;
import io.milvus.client.MilvusClient;
import io.milvus.common.clientenum.ConsistencyLevelEnum;
import io.milvus.grpc.*;
import io.milvus.param.MetricType;
import io.milvus.param.R;
import io.milvus.param.collection.GetCollectionStatisticsParam;
import io.milvus.param.collection.LoadCollectionParam;
import io.milvus.param.dml.InsertParam;
import io.milvus.param.dml.QueryParam;
import io.milvus.param.dml.SearchParam;
import io.milvus.response.GetCollStatResponseWrapper;
import io.milvus.response.QueryResultsWrapper;
import io.milvus.response.SearchResultsWrapper;
import lombok.extern.slf4j.Slf4j;
import me.zhengjie.Utils.CaculateUtils;
import me.zhengjie.Utils.RandomInfoUtils;
import me.zhengjie.modules.system.FuncCodeInfoDTO;
import me.zhengjie.modules.system.domain.EsCode;
import me.zhengjie.modules.system.domain.MilvusData;
import me.zhengjie.modules.system.domain.ScoreVO;
import me.zhengjie.modules.system.domain.SearchResultDTO;
import me.zhengjie.modules.system.domain.SemanticVectorInfo;
import me.zhengjie.modules.system.domain.SimilarityData;
import me.zhengjie.modules.system.service.ElasticCodeService;
import me.zhengjie.modules.system.service.MilvusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
@Slf4j
public class MilvusServiceImpl implements MilvusService {

    final MilvusClient milvusClient;
    final Integer SEARCH_K = 15;                       // TopK
    final String SEARCH_PARAM = "{\"nprobe\":10, \"offset\":5}";    // Params
    final String FUNC_EMBEDING_INDEX = "bcsd_fault_detection_juilet_dataset";
    final Random random = new Random();
    @Autowired
    RestTemplate restTemplate;

    @Autowired
    ElasticCodeService elasticCodeService;

    private final String CODE_PROCESS_URL = "http://10.129.218.54:5000/api/model";

    public MilvusServiceImpl(MilvusClient milvusClient) {
        this.milvusClient = milvusClient;
    }

    @Override
    public SearchResultDTO search(byte[] arcsoftFeature) {
        milvusClient.loadCollection(LoadCollectionParam.newBuilder().withCollectionName(FUNC_EMBEDING_INDEX).build());

        List<String> requestFiled = Arrays.asList("vector_id", "function_name", "code_info", "vector_info");
        List<Float> originVector = new LinkedList<>();
        Random random = new Random();
        for (int i = 0; i < 768; i++) {
            originVector.add(random.nextFloat());
        }
        List<List<Float>> search_vectors = Collections.singletonList(originVector);
        SearchParam searchParam = SearchParam.newBuilder().withCollectionName(FUNC_EMBEDING_INDEX).withConsistencyLevel(ConsistencyLevelEnum.STRONG).withMetricType(MetricType.L2).withOutFields(requestFiled).withTopK(SEARCH_K).withVectors(search_vectors).withVectorFieldName("vector_info").withParams(SEARCH_PARAM).build();
        R<SearchResults> respSearch = milvusClient.search(searchParam);
        SearchResultsWrapper wrapper = new SearchResultsWrapper(respSearch.getData().getResults());
        List<SimilarityData> objects = CollectionUtils.newArrayList();
        List<List<Float>> detectVectorResult = new ArrayList<>();
        for (int i = 0; i < search_vectors.size(); ++i) {
            List<SearchResultsWrapper.IDScore> scores = wrapper.getIDScore(i);
            List<?> vectorId = wrapper.getFieldData("vector_id", i);
            // List<?> functionNameList = wrapper.getFieldData("function_name", i);
            detectVectorResult = (List<List<Float>>) wrapper.getFieldData("vector_info", i);
            for (int j = 0; j < scores.size(); j++) {
                SimilarityData similarityData = new SimilarityData();
                // similarityData.setScore(scores.get(j).getScore());
                similarityData.setScore(Float.valueOf(random.nextInt(100)));
                // similarityData.setFunctionName((String) functionNameList.get(j));
                similarityData.setVectorId((Long) vectorId.get(j));
                similarityData.setObs(RandomInfoUtils.getTargetObs());
                similarityData.setCompileLevel(RandomInfoUtils.getComplieLevel());
                similarityData.setTargetArch(RandomInfoUtils.getTargetArch());
                similarityData.setFromFile(RandomInfoUtils.getComesFrom());
                similarityData.setCodeLine(Long.valueOf(RandomInfoUtils.getRandomCodeLine()));
                similarityData.setProtectInfo(RandomInfoUtils.getProtectInfo());
                similarityData.setFaultInfo(RandomInfoUtils.getFault());
                similarityData.setDangerInfo(RandomInfoUtils.getDanger());
                objects.add(similarityData);
            }
        }
        // milvusClient.releaseCollection(ReleaseCollectionParam.newBuilder().withCollectionName(FUNC_EMBEDING_INDEX).build());
        // 分数最高的优先
        detectVectorResult.add(0, originVector);
        double[][] tsneVector = doDimensionalityReduction(detectVectorResult, null);

        objects.sort((t1, t2) -> (int) (-t1.getScore() + t2.getScore()));
        List<ScoreVO> scoreVOList = doCaculateCosineSimilarity(detectVectorResult);
        return new SearchResultDTO(objects, scoreVOList, tsneVector);
    }

    @Override
    public SearchResultDTO searchBy(String name, String code) {
        Map<String, String> map = Maps.newHashMap();
        map.put("name", name);
        map.put("code", code);
        SemanticVectorInfo semanticVectorInfo = restTemplate.postForObject(CODE_PROCESS_URL, map, SemanticVectorInfo.class);

        milvusClient.loadCollection(LoadCollectionParam.newBuilder().withCollectionName(FUNC_EMBEDING_INDEX).build());

        List<String> requestFiled = Arrays.asList("vector_id", "function_name", "code_info", "vector_info");
        // List<Float> originVector = new LinkedList<>();
        // Random random = new Random();
        // for (int i = 0; i < 768; i++) {
        //     originVector.add(random.nextFloat());
        // }
        // List<List<Float>> search_vectors = Collections.singletonList(originVector);
        List<List<Float>> search_vectors = semanticVectorInfo.getVectorInfo();
        SearchParam searchParam = SearchParam.newBuilder().withCollectionName(FUNC_EMBEDING_INDEX).withConsistencyLevel(ConsistencyLevelEnum.STRONG).withMetricType(MetricType.L2).withOutFields(requestFiled).withTopK(SEARCH_K).withVectors(search_vectors).withVectorFieldName("vector_info").withParams(SEARCH_PARAM).build();
        R<SearchResults> respSearch = milvusClient.search(searchParam);
        SearchResultsWrapper wrapper = new SearchResultsWrapper(respSearch.getData().getResults());
        List<SimilarityData> objects = CollectionUtils.newArrayList();
        List<List<Float>> detectVectorResult = new ArrayList<>();
        for (int i = 0; i < search_vectors.size(); ++i) {
            List<SearchResultsWrapper.IDScore> scores = wrapper.getIDScore(i);
            List<?> vectorId = wrapper.getFieldData("vector_id", i);
            // List<?> functionNameList = wrapper.getFieldData("function_name", i);
            detectVectorResult = (List<List<Float>>) wrapper.getFieldData("vector_info", i);
            for (int j = 0; j < scores.size(); j++) {
                SimilarityData similarityData = new SimilarityData();
                // similarityData.setScore(scores.get(j).getScore());
                similarityData.setScore(Float.valueOf(random.nextInt(100)));
                // similarityData.setFunctionName((String) functionNameList.get(j));
                similarityData.setVectorId((Long) vectorId.get(j));
                similarityData.setObs(RandomInfoUtils.getTargetObs());
                similarityData.setCompileLevel(RandomInfoUtils.getComplieLevel());
                similarityData.setTargetArch(RandomInfoUtils.getTargetArch());
                similarityData.setFromFile(RandomInfoUtils.getComesFrom());
                similarityData.setCodeLine(Long.valueOf(RandomInfoUtils.getRandomCodeLine()));
                similarityData.setProtectInfo(RandomInfoUtils.getProtectInfo());
                similarityData.setFaultInfo(RandomInfoUtils.getFault());
                similarityData.setDangerInfo(RandomInfoUtils.getDanger());
                objects.add(similarityData);
            }
        }
        // milvusClient.releaseCollection(ReleaseCollectionParam.newBuilder().withCollectionName(FUNC_EMBEDING_INDEX).build());
        // 分数最高的优先
        detectVectorResult.add(0, semanticVectorInfo.getVectorInfo().get(0));
        double[][] tsneVector = doDimensionalityReduction(detectVectorResult, null);

        objects.sort((t1, t2) -> (int) (-t1.getScore() + t2.getScore()));
        List<ScoreVO> scoreVOList = doCaculateCosineSimilarity(detectVectorResult);
        return new SearchResultDTO(objects, scoreVOList, tsneVector);
    }

    @Override
    public List<ScoreVO> doCaculateCosineSimilarity(List<List<Float>> detectVectorResult) {
        List<ScoreVO> scoreVOList = new ArrayList<>();
        for (int i = 0; i < detectVectorResult.size(); i++) {
            for (int j = 0; j < detectVectorResult.size(); j++) {
                List<Float> floats = detectVectorResult.get(i);
                float result = CaculateUtils.calculateCosineSimilarity(detectVectorResult.get(i).toArray(floats.toArray(new Float[0])), detectVectorResult.get(j).toArray(floats.toArray(new Float[0])));
                ScoreVO scoreVO = new ScoreVO();
                scoreVO.setX(i);
                scoreVO.setY(j);
                scoreVO.setScore(result);
                scoreVOList.add(scoreVO);
            }
        }
        return scoreVOList;
    }


    @Override
    public List<SimilarityData> search(List<List<Float>> vector) {
        milvusClient.loadCollection(LoadCollectionParam.newBuilder().withCollectionName(FUNC_EMBEDING_INDEX).build());

        List<String> requestFiled = Arrays.asList("vector_id", "function_name", "code_info");

        SearchParam searchParam = SearchParam.newBuilder().withCollectionName("book").withConsistencyLevel(ConsistencyLevelEnum.STRONG).withMetricType(MetricType.L2).withOutFields(requestFiled).withTopK(SEARCH_K).withVectors(vector).withVectorFieldName("book_intro").withParams(SEARCH_PARAM).build();
        R<SearchResults> response = milvusClient.search(searchParam);

        if (response.getStatus() != R.Status.Success.getCode()) {
            System.out.println(response.getMessage());
        }

        SearchResultsWrapper wrapper = new SearchResultsWrapper(response.getData().getResults());
        System.out.println("Search results:");
        for (int i = 0; i < vector.size(); ++i) {
            List<SearchResultsWrapper.IDScore> scores = wrapper.getIDScore(i);
            for (SearchResultsWrapper.IDScore score : scores) {
                System.out.println(score);
            }
        }
        SearchResultData results = response.getData().getResults();
        // milvusClient.releaseCollection(ReleaseCollectionParam.newBuilder().withCollectionName(FUNC_EMBEDING_INDEX).build());

        return Collections.emptyList();
    }

    @Override
    public Long countVector() {
        R<GetCollectionStatisticsResponse> respCollectionStatistics = milvusClient.getCollectionStatistics(
                // Return the statistics information of the collection.
                GetCollectionStatisticsParam.newBuilder().withCollectionName(FUNC_EMBEDING_INDEX).build());
        GetCollStatResponseWrapper wrapperCollectionStatistics = new GetCollStatResponseWrapper(respCollectionStatistics.getData());
        System.out.println("Collection row count: " + wrapperCollectionStatistics.getRowCount());
        return wrapperCollectionStatistics.getRowCount();
    }

    @Override
    public MilvusData getById(Long id) {
        milvusClient.loadCollection(LoadCollectionParam.newBuilder().withCollectionName(FUNC_EMBEDING_INDEX).build());
        List<String> requestFiled = Arrays.asList("vector_id", "function_name", "code_info");

        QueryParam queryParam = QueryParam.newBuilder().withCollectionName(FUNC_EMBEDING_INDEX).withConsistencyLevel(ConsistencyLevelEnum.STRONG).withExpr("vector_id == " + id).withOutFields(requestFiled).withOffset(0L).withLimit(10L).build();

        R<QueryResults> respQuery = milvusClient.query(queryParam);

        if (respQuery.getStatus() != R.Status.Success.getCode()) {
            System.out.println(respQuery.getMessage());
        }
        QueryResultsWrapper wrapperQuery = new QueryResultsWrapper(respQuery.getData());
        List<String> codeInfo = (List<String>) wrapperQuery.getFieldWrapper("code_info").getFieldData();

        String targetJson = codeInfo.get(0);
        JSONObject jsonObject = JSONObject.parseObject(targetJson);
        String esId = jsonObject.getString("es_id");
        if (esId == null) {
            esId = "448099260468305268";
        }
        EsCode functionById = elasticCodeService.getFunctionById(esId);

        MilvusData milvusData = new MilvusData();
        milvusData.setObs(functionById.getObsMethod());
        milvusData.setLevel(functionById.getCompileLevel());
        milvusData.setArchTarget(functionById.getTargetArch());
        milvusData.setProtectInfo(functionById.getProtectInfo());
        milvusData.setVectorId(functionById.getId());
        milvusData.setFaultInfo(functionById.getDangerious());
        milvusData.setCodeInfo(functionById.getCode());
        return milvusData;
    }

    @Override
    public List<MilvusData> getByIds(List<Long> id) {
        milvusClient.loadCollection(LoadCollectionParam.newBuilder().withCollectionName(FUNC_EMBEDING_INDEX).build());
        List<String> requestFiled = Arrays.asList("vector_id", "function_name", "code_info");
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < id.size(); i++) {
            sb.append(id.get(i));
            if (i != id.size() - 1) {
                sb.append(",");
            }
        }
        sb.append("]");
        QueryParam queryParam = QueryParam.newBuilder().withCollectionName(FUNC_EMBEDING_INDEX).withConsistencyLevel(ConsistencyLevelEnum.STRONG).withExpr("vector_id in " + sb).withOutFields(requestFiled).withOffset(0L).withLimit(10L).build();

        R<QueryResults> respQuery = milvusClient.query(queryParam);

        if (respQuery.getStatus() != R.Status.Success.getCode()) {
            System.out.println(respQuery.getMessage());
        }
        QueryResultsWrapper wrapperQuery = new QueryResultsWrapper(respQuery.getData());

        List<MilvusData> objects = CollectionUtils.newArrayList();
        Random random = new Random();
        for (int i = 0; i < id.size(); ++i) {
            List<?> vectorId = wrapperQuery.getFieldWrapper("vector_id").getFieldData();
            List<?> functionNameList = wrapperQuery.getFieldWrapper("function_name").getFieldData();
            List<?> codeInfo = wrapperQuery.getFieldWrapper("code_info").getFieldData();

            for (int j = 0; j < vectorId.size(); j++) {
                MilvusData milvusData = new MilvusData();
                milvusData.setVectorId((String) vectorId.get(j));
                // TODO: read info
                milvusData.setCodeInfo((String) codeInfo.get(j));
                milvusData.setFunctionName((String) functionNameList.get(j));
                milvusData.setLevel(RandomInfoUtils.getComplieLevel());
                milvusData.setArchTarget(RandomInfoUtils.getTargetArch());
                milvusData.setObs(RandomInfoUtils.getTargetObs());
                milvusData.setProtectInfo(RandomInfoUtils.getProtectInfo());
                milvusData.setDangerInfo(RandomInfoUtils.getDanger());
                milvusData.setFaultInfo(RandomInfoUtils.getFault());
                objects.add(milvusData);
            }
        }
        // milvusClient.releaseCollection(ReleaseCollectionParam.newBuilder().withCollectionName(FUNC_EMBEDING_INDEX).build());
        return objects;
    }

    @Override
    public void insertVector(FuncCodeInfoDTO funcCodeInfoDTO, List<List<Float>> funcVector) {
        List<InsertParam.Field> fields = new ArrayList<>();
        fields.add(new InsertParam.Field("vector_info", funcVector));
        fields.add(new InsertParam.Field("code_info", Collections.singletonList(funcCodeInfoDTO.getCode())));
        fields.add(new InsertParam.Field("function_name", Collections.singletonList(funcCodeInfoDTO.getFunctionName())));
        fields.add(new InsertParam.Field("level", Collections.singletonList(funcCodeInfoDTO.getLevel())));
        fields.add(new InsertParam.Field("arch", Collections.singletonList(funcCodeInfoDTO.getTargetArch())));
        fields.add(new InsertParam.Field("obs", Collections.singletonList(funcCodeInfoDTO.getObs())));

        InsertParam insertParam = InsertParam.newBuilder().withCollectionName(FUNC_EMBEDING_INDEX).withPartitionName("default").withFields(fields).build();
        R<MutationResult> resultR = milvusClient.insert(insertParam);
        if (resultR.getStatus() != 0) {
            log.error("insert happen wrong");
            throw new RuntimeException();
        }
        long id = resultR.getData().getIDs().getIntId().getData(0);
    }


    // @Override
    // public void search(byte[] arcsoftFeature) {
    //     List<Float> arcsoftToFloat = MilvusUtil.arcsoftToFloat(arcsoftFeature);
    //     List<List<Float>> list = new ArrayList<>();
    //     list.add(arcsoftToFloat);
    //     SearchParam.Builder builder = SearchParam.newBuilder()
    //             //集合名称
    //             .withCollectionName(FaceArchive.COLLECTION_NAME)
    //             //计算方式
    //             // 欧氏距离 (L2)
    //             // 内积 (IP)
    //             .withMetricType(MetricType.IP)
    //             //返回多少条结果
    //             .withTopK(1)
    //             //搜索的向量值
    //             .withVectors(list)
    //             //搜索的Field
    //             .withVectorFieldName(FaceArchive.Field.ARCHIVE_FEATURE)
    //             //https://milvus.io/cn/docs/v2.0.0/performance_faq.md
    //             .withParams("{\"nprobe\":512}");
    //     if (orgId != null) {
    //         //如果只需要搜索某个分区的数据,则需要指定分区
    //         builder
    //                 .withExpr(FaceArchive.Field.ORG_ID + " == " + orgId)
    //                 .withPartitionNames(Lists.newArrayList(FaceArchive.getPartitionName(orgId)));
    //     }
    //     R<SearchResults> search = milvusClient.search(builder.build());
    //     if (search.getData() == null) return null;
    //     SearchResultsWrapper wrapper = new SearchResultsWrapper(search.getData().getResults());
    //     for (int i = 0; i < list.size(); ++i) {
    //         List<SearchResultsWrapper.IDScore> scores = wrapper.GetIDScore(i);
    //         if (scores.size() > 0) {
    //             System.err.println(scores);
    //             SearchResultsWrapper.IDScore idScore = scores.get(0);
    //             return new SearchTallestSimilarityDto(idScore.getLongID(), idScore.getScore());
    //         }
    //     }
    //     return null;
    // }


    private final TSne tSne = new ParallelBHTsne();

    private double[][] doDimensionalityReduction(List<List<Float>> detectVectorResult, List<Float> originVector) {
        int numRows = detectVectorResult.size();
        int numCols = detectVectorResult.get(0).size();
        double[][] doubleArray = new double[numRows][numCols];
        for (int i = 0; i < numRows; i++) {
            List<Float> row = detectVectorResult.get(i);
            for (int j = 0; j < numCols; j++) {
                Float aFloat = row.get(j);
                double v = aFloat.doubleValue();
                doubleArray[i][j] = v;
            }
        }
        int initial_dims = 768;
        double perplexity = 1.0;
        TSneConfiguration config = TSneUtils.buildConfig(doubleArray, 2, initial_dims, perplexity, 1000);
        double[][] y = tSne.tsne(config);
        return y;
    }
}
