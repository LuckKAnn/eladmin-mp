package me.zhengjie.config;

import lombok.Data;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author liukun.inspire
 * @Date 2023/10/28 15:57
 * @PackageName: me.zhengjie.config
 * @ClassName: EsConfig
 * @Version 1.0
 */
@Configuration
@Data
@EnableConfigurationProperties(SystemConfig.class)
@ConfigurationProperties(prefix = "system")
public class SystemConfig {

    private String mqCounterTopic;

    private String indexName;
}
