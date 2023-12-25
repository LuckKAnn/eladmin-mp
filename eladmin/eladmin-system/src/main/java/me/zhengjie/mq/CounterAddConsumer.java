package me.zhengjie.mq;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import lombok.extern.slf4j.Slf4j;
import me.zhengjie.config.SystemConfig;
import me.zhengjie.modules.system.domain.EsCode;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

/**
 * @Author liukun.inspire
 * @Date 2023/12/25 21:24
 * @PackageName: me.zhengjie.mq
 * @ClassName: CounterAddConsumer
 * @Version 1.0
 */
@Component
@Slf4j
@RocketMQMessageListener(topic = "${system.mqCounterTopic}", consumerGroup = "springboot_consumer_group")
public class CounterAddConsumer implements RocketMQListener<String> {

    @Autowired
    private RestHighLevelClient client;


    @Autowired
    private SystemConfig systemConfig;

    @Override
    public void onMessage(String s) {
        log.error("get mq msg:{}", s);

        // JSONObject.parseObject(s,new TypeReference<List<Long>>())
        List<EsCode> ids = JSONObject.parseArray(s, EsCode.class);

        for (EsCode esCode : ids) {
            UpdateRequest updateRequest = new UpdateRequest(systemConfig.getIndexName(), esCode.getId());
            EsCode userInfo = new EsCode();
            userInfo.setSearchCount(esCode.getSearchCount() + 1);
            // 将对象转换为 byte 数组
            byte[] json = JSON.toJSONBytes(userInfo);
            // 设置更新文档内容
            updateRequest.doc(json, XContentType.JSON);
            // updateRequest.doc("searchCount", "1100", XContentType.JSON);
            try {
                UpdateResponse updateResponse = client.update(updateRequest, RequestOptions.DEFAULT);
                // 处理更新结果
            } catch (IOException e) {
                // 处理异常
            }
        }


    }
}
