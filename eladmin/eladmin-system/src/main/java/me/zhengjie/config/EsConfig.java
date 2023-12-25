package me.zhengjie.config;

import lombok.Data;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
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
@EnableConfigurationProperties(EsConfig.class)
@ConfigurationProperties(prefix = "elasticsearch")
public class EsConfig {


    @Bean
    public RestHighLevelClient restHighLevelClient() {
        RestClientBuilder builder = RestClient.builder(
                new HttpHost("10.129.218.54", 9200, "http")
        );
        // RestHighLevelClient client = new RestHighLevelClient(
        //         RestClient.builder(
        //                 new HttpHost("123.249.119.160", 9200, "http")
        //         )
        // );
        CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials("elastic", "123456"));
        builder.setHttpClientConfigCallback(f -> f.setDefaultCredentialsProvider(credentialsProvider));
        return new RestHighLevelClient(builder);
    }
}
