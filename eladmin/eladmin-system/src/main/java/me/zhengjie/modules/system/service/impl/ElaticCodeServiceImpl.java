package me.zhengjie.modules.system.service.impl;

import cn.hutool.json.ObjectMapper;
import com.alibaba.fastjson.JSONObject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.zhengjie.Utils.RandomInfoUtils;
import me.zhengjie.config.SystemConfig;
import me.zhengjie.dto.EsCodeEntity;
import me.zhengjie.mapper.EsCodeRepository;
import me.zhengjie.modules.system.domain.EsCode;
import me.zhengjie.modules.system.service.ElasticCodeService;
import org.apache.commons.compress.utils.Lists;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.SendStatus;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.core.CountRequest;
import org.elasticsearch.client.core.CountResponse;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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

    @Autowired
    private RocketMQTemplate rocketmqTemplate;

    @Autowired
    private SystemConfig systemConfig;

    // private static final String INDEX_NAME = "bcsd_fault_func_index";

    @Override
    public List<EsCode> getAll(int pageId, int pageSize) {
        List<EsCode> list = Lists.newArrayList();
        try {
            // 构建查询条件（注意：termQuery 支持多种格式查询，如 boolean、int、double、string 等，这里使用的是 string 的查询）
            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            searchSourceBuilder.query(QueryBuilders.matchAllQuery());
            searchSourceBuilder.from(pageId * pageSize);
            searchSourceBuilder.size(pageSize);
            searchSourceBuilder.sort("searchCount", SortOrder.DESC);
            // 创建查询请求对象，将查询对象配置到其中
            SearchRequest searchRequest = new SearchRequest(systemConfig.getIndexName());
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
                    EsCode esCode = new EsCode();
                    esCode.setId(hit.getId());
                    esCode.setFunctionName(esCodeEntity.getFunctionName());
                    esCode.setCode(esCodeEntity.getCode());
                    esCode.setCodeLine(esCodeEntity.getCodeLine());
                    esCode.setDangerious(esCodeEntity.getFault());
                    esCode.setCompileLevel(esCodeEntity.getCompileLevel());
                    esCode.setLeakInfo(esCodeEntity.getLeakInfo());
                    esCode.setFunctionFrom(esCodeEntity.getFunctionFrom());
                    esCode.setSearchCount(esCodeEntity.getSearchCount());
                    esCode.setProtectInfo(esCodeEntity.getFunctionFrom());
                    esCode.setObsMethod(esCodeEntity.getObsMehod());
                    esCode.setTargetArch(esCodeEntity.getTargetArch());
                    esCode.setDetail(esCodeEntity.getDetail());
                    list.add(esCode);
                }
            }
        } catch (IOException e) {
            log.error("", e);
        }

        // 针对这个list进行处理
        return list;
    }

    private void asyncProcessIncreaseCounter(List<EsCode> list) {
        String str = JSONObject.toJSONString(list);
        Message<String> msg = MessageBuilder.withPayload(str).build();
        SendResult sendResult = rocketmqTemplate.syncSend(systemConfig.getMqCounterTopic(), msg);
        if (sendResult.getSendStatus() != SendStatus.SEND_OK) {
            log.error("send msg to rocket mq fail");
        }

    }

    @Override
    public EsCode getFunctionById(String id) {
        List<EsCode> list = Lists.newArrayList();
        try {
            // 构建查询条件（注意：termQuery 支持多种格式查询，如 boolean、int、double、string 等，这里使用的是 string 的查询）
            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            searchSourceBuilder.query(QueryBuilders.idsQuery().addIds(id));
            // 创建查询请求对象，将查询对象配置到其中
            SearchRequest searchRequest = new SearchRequest(systemConfig.getIndexName());
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
                    EsCode esCode = new EsCode();
                    esCode.setId(hit.getId());
                    esCode.setFunctionName(esCodeEntity.getFunctionName());
                    esCode.setCode(esCodeEntity.getCode());
                    esCode.setCodeLine(esCodeEntity.getCodeLine());
                    esCode.setDangerious(esCodeEntity.getFault());
                    esCode.setCompileLevel(esCodeEntity.getCompileLevel());
                    esCode.setLeakInfo(esCodeEntity.getLeakInfo());
                    esCode.setFunctionFrom(esCodeEntity.getFunctionFrom());
                    esCode.setSearchCount(esCodeEntity.getSearchCount());
                    esCode.setProtectInfo(esCodeEntity.getFunctionFrom());
                    esCode.setObsMethod(esCodeEntity.getObsMehod());
                    esCode.setTargetArch(esCodeEntity.getTargetArch());
                    esCode.setDetail(esCodeEntity.getDetail());
                    list.add(esCode);
                }
            }
        } catch (IOException e) {
            log.error("search function by id failed ,id :{}", id, e);
        }
        EsCode esCode = list.get(0) != null ? list.get(0) : null;
        if (esCode != null) {
            EsCode clearEsCode = new EsCode();

            clearEsCode.setId(esCode.getId());
            clearEsCode.setSearchCount(esCode.getSearchCount());
            asyncProcessIncreaseCounter(Collections.singletonList(clearEsCode));
        }
        return esCode;
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
                    esCode.setId(hit.getId());
                    esCode.setFunctionName(esCodeEntity.getFunctionName());
                    list.add(esCode);
                }
            }
        } catch (IOException e) {
            log.error("", e);
        }
        return list;
    }

    @Override
    public Long getCount() {
        // 通过CountRequest查询获得count
        CountRequest countRequest = new CountRequest();
        // 绑定索引名
        countRequest.indices(systemConfig.getIndexName());
        countRequest.query(QueryBuilders.matchAllQuery());
        CountResponse response = null;
        try {
            response = client.count(countRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return response.getCount();
    }
}
