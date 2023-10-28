package me.zhengjie.modules.system.service.impl;

import com.github.xiaoymin.knife4j.core.util.CollectionUtils;
import io.milvus.client.MilvusClient;
import io.milvus.common.clientenum.ConsistencyLevelEnum;
import io.milvus.grpc.GetCollectionStatisticsResponse;
import io.milvus.grpc.QueryResults;
import io.milvus.grpc.SearchResultData;
import io.milvus.grpc.SearchResults;
import io.milvus.param.MetricType;
import io.milvus.param.R;
import io.milvus.param.collection.GetCollectionStatisticsParam;
import io.milvus.param.collection.LoadCollectionParam;
import io.milvus.param.collection.ReleaseCollectionParam;
import io.milvus.param.dml.QueryParam;
import io.milvus.param.dml.SearchParam;
import io.milvus.response.FieldDataWrapper;
import io.milvus.response.GetCollStatResponseWrapper;
import io.milvus.response.QueryResultsWrapper;
import io.milvus.response.SearchResultsWrapper;
import lombok.extern.slf4j.Slf4j;
import me.zhengjie.modules.system.domain.MilvusData;
import me.zhengjie.modules.system.domain.SimilarityData;
import me.zhengjie.modules.system.service.MilvusService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
public class MilvusServiceImpl implements MilvusService {

    final MilvusClient milvusClient;

    final Integer SEARCH_K = 10;                       // TopK
    final String SEARCH_PARAM = "{\"nprobe\":10, \"offset\":5}";    // Params

    final String FUNC_EMBEDING_INDEX = "binaryhouseT3";


    public MilvusServiceImpl(MilvusClient milvusClient) {
        this.milvusClient = milvusClient;
    }

    @Override
    public List<SimilarityData> search(byte[] arcsoftFeature) {
        milvusClient.loadCollection(
                LoadCollectionParam.newBuilder()
                        .withCollectionName(FUNC_EMBEDING_INDEX)
                        .build()
        );

        List<String> requestFiled = Arrays.asList("vector_id", "function_name", "code_info");
        List<Float> list = new LinkedList<>();
        Random random = new Random();
        for (int i = 0; i < 768; i++) {
            list.add(random.nextFloat());
        }
        List<List<Float>> search_vectors = Collections.singletonList(list);
        SearchParam searchParam = SearchParam.newBuilder()
                .withCollectionName(FUNC_EMBEDING_INDEX)
                .withConsistencyLevel(ConsistencyLevelEnum.STRONG)
                .withMetricType(MetricType.L2)
                .withOutFields(requestFiled)
                .withTopK(SEARCH_K)
                .withVectors(search_vectors)
                .withVectorFieldName("vector_info")
                .withParams(SEARCH_PARAM)
                .build();
        R<SearchResults> respSearch = milvusClient.search(searchParam);
        SearchResultsWrapper wrapper = new SearchResultsWrapper(respSearch.getData().getResults());

        List<SimilarityData> objects = CollectionUtils.newArrayList();
        for (int i = 0; i < search_vectors.size(); ++i) {
            List<SearchResultsWrapper.IDScore> scores = wrapper.getIDScore(i);
            List<?> vectorId = wrapper.getFieldData("vector_id", i);
            List<?> functionNameList = wrapper.getFieldData("function_name", i);
            for (int j = 0; j < scores.size(); j++) {
                SimilarityData similarityData = new SimilarityData();
                similarityData.setScore(scores.get(j).getScore());
                similarityData.setFunctionName((String) functionNameList.get(j));
                similarityData.setVectorId((Long) vectorId.get(j));
                objects.add(similarityData);
            }
        }

        milvusClient.releaseCollection(
                ReleaseCollectionParam.newBuilder()
                        .withCollectionName(FUNC_EMBEDING_INDEX)
                        .build());

        return objects;
    }


    @Override
    public List<SimilarityData> search(List<List<Float>> vector) {
        milvusClient.loadCollection(
                LoadCollectionParam.newBuilder()
                        .withCollectionName(FUNC_EMBEDING_INDEX)
                        .build()
        );

        List<String> requestFiled = Arrays.asList("vector_id", "function_name", "code_info");

        SearchParam searchParam = SearchParam.newBuilder()
                .withCollectionName("book")
                .withConsistencyLevel(ConsistencyLevelEnum.STRONG)
                .withMetricType(MetricType.L2)
                .withOutFields(requestFiled)
                .withTopK(SEARCH_K)
                .withVectors(vector)
                .withVectorFieldName("book_intro")
                .withParams(SEARCH_PARAM)
                .build();
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
        milvusClient.releaseCollection(
                ReleaseCollectionParam.newBuilder()
                        .withCollectionName(FUNC_EMBEDING_INDEX)
                        .build());

        return Collections.emptyList();
    }

    @Override
    public Long countVector() {
        R<GetCollectionStatisticsResponse> respCollectionStatistics = milvusClient.getCollectionStatistics(
                // Return the statistics information of the collection.
                GetCollectionStatisticsParam.newBuilder()
                        .withCollectionName(FUNC_EMBEDING_INDEX)
                        .build()
        );
        GetCollStatResponseWrapper wrapperCollectionStatistics = new GetCollStatResponseWrapper(respCollectionStatistics.getData());
        System.out.println("Collection row count: " + wrapperCollectionStatistics.getRowCount());
        return wrapperCollectionStatistics.getRowCount();
    }

    @Override
    public MilvusData getById(Long id) {
        milvusClient.loadCollection(
                LoadCollectionParam.newBuilder()
                        .withCollectionName(FUNC_EMBEDING_INDEX)
                        .build()
        );
        List<String> requestFiled = Arrays.asList("vector_id", "function_name", "code_info");

        QueryParam queryParam = QueryParam.newBuilder()
                .withCollectionName(FUNC_EMBEDING_INDEX)
                .withConsistencyLevel(ConsistencyLevelEnum.STRONG)
                .withExpr("vector_id == " + id)
                .withOutFields(requestFiled)
                .withOffset(0L)
                .withLimit(10L)
                .build();

        R<QueryResults> respQuery = milvusClient.query(queryParam);

        if (respQuery.getStatus() != R.Status.Success.getCode()) {
            System.out.println(respQuery.getMessage());
        }
        QueryResultsWrapper wrapperQuery = new QueryResultsWrapper(respQuery.getData());
        List<?> codeInfo = wrapperQuery.getFieldWrapper("code_info").getFieldData();

        milvusClient.releaseCollection(
                ReleaseCollectionParam.newBuilder()
                        .withCollectionName(FUNC_EMBEDING_INDEX)
                        .build());

        MilvusData milvusData = new MilvusData();
        milvusData.setCodeInfo((String) codeInfo.get(0));
        return milvusData;
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
}
