package me.zhengjie.service;

import com.google.common.collect.Lists;
import io.milvus.Response.SearchResultsWrapper;
import io.milvus.client.MilvusClient;
import io.milvus.grpc.SearchResults;
import io.milvus.param.MetricType;
import io.milvus.param.R;
import io.milvus.param.dml.SearchParam;
import lombok.extern.slf4j.Slf4j;
import me.zhengjie.cst.FaceArchive;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class MilvusServiceImpl implements MilvusService {

    final  MilvusClient milvusClient;

    public MilvusServiceImpl(MilvusClient milvusClient) {
        this.milvusClient = milvusClient;
    }

    @Override
    public void search(byte[] arcsoftFeature) {
        List<Float> arcsoftToFloat = MilvusUtil.arcsoftToFloat(arcsoftFeature);
        List<List<Float>> list = new ArrayList<>();
        list.add(arcsoftToFloat);
        SearchParam.Builder builder = SearchParam.newBuilder()
                //集合名称
                .withCollectionName(FaceArchive.COLLECTION_NAME)
                //计算方式
                // 欧氏距离 (L2)
                // 内积 (IP)
                .withMetricType(MetricType.IP)
                //返回多少条结果
                .withTopK(1)
                //搜索的向量值
                .withVectors(list)
                //搜索的Field
                .withVectorFieldName(FaceArchive.Field.ARCHIVE_FEATURE)
                //https://milvus.io/cn/docs/v2.0.0/performance_faq.md
                .withParams("{\"nprobe\":512}");
        if (orgId != null) {
            //如果只需要搜索某个分区的数据,则需要指定分区
            builder
                    .withExpr(FaceArchive.Field.ORG_ID + " == " + orgId)
                    .withPartitionNames(Lists.newArrayList(FaceArchive.getPartitionName(orgId)));
        }
        R<SearchResults> search = milvusClient.search(builder.build());
        if (search.getData() == null) return null;
        SearchResultsWrapper wrapper = new SearchResultsWrapper(search.getData().getResults());
        for (int i = 0; i < list.size(); ++i) {
            List<SearchResultsWrapper.IDScore> scores = wrapper.GetIDScore(i);
            if (scores.size() > 0) {
                System.err.println(scores);
                SearchResultsWrapper.IDScore idScore = scores.get(0);
                return new SearchTallestSimilarityDto(idScore.getLongID(), idScore.getScore());
            }
        }
        return null;
    }
}
