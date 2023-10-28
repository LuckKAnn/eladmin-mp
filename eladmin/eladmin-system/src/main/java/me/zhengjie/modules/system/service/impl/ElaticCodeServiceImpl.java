package me.zhengjie.modules.system.service.impl;

import cn.hutool.json.ObjectMapper;
import com.alibaba.fastjson.JSONObject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.zhengjie.dto.EsCodeEntity;
import me.zhengjie.mapper.EsCodeRepository;
import me.zhengjie.modules.system.domain.EsCode;
import me.zhengjie.modules.system.service.ElasticCodeService;
import org.apache.commons.compress.utils.Lists;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * @Author liukun.inspire
 * @Date 2023/10/28 15:15
 * @PackageName: me.zhengjie.modules.system.service.impl
 * @ClassName: ElaticCodeServiceImpl
 * @Version 1.0
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class ElaticCodeServiceImpl implements ElasticCodeService {


    final EsCodeRepository esCodeRepository;

    final RestHighLevelClient client;
    private SearchResponse searchResponse;

    @Override
    public EsCode getFunctionById(Long id) {

        return null;
    }

    @Override
    public List<EsCode> getFunctionByName(String functionName) {
        List<EsCode> list = Lists.newArrayList();
        try {
            // 构建查询条件（注意：termQuery 支持多种格式查询，如 boolean、int、double、string 等，这里使用的是 string 的查询）
            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            searchSourceBuilder.query(QueryBuilders.matchQuery("function_name", "ssl_lib-O1-x86_64_SSL_waiting_for_async.ll"));
            // 创建查询请求对象，将查询对象配置到其中
            SearchRequest searchRequest = new SearchRequest("code-repo-t2");
            searchRequest.source(searchSourceBuilder);
            // 执行查询，然后处理响应结果
            SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
            // 根据状态和数据条数验证是否返回了数据
            if (RestStatus.OK.equals(searchResponse.status()) && searchResponse.getHits().getTotalHits().value > 0) {
                SearchHits hits = searchResponse.getHits();
                for (SearchHit hit : hits) {
                    // 将 JSON 转换成对象
                    // 输出查询信息
                    EsCodeEntity esCodeEntity = JSONObject.parseObject(hit.getSourceAsString(), EsCodeEntity.class);
                    log.info("=======" + esCodeEntity);
                    EsCode esCode = new EsCode();
                    esCode.setId(esCodeEntity.getId());
                    esCode.setFunctionName(esCodeEntity.getFunctionName());
                    list.add(esCode);
                }
            }
        } catch (IOException e) {
            log.error("", e);
        }
        return list;
    }
}
