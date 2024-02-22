package com.bcsd;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @Author liukun.inspire
 * @Date 2024/2/21 14:22
 * @PackageName: com.bcsd
 * @ClassName: DatasetApplication
 * @Version 1.0
 */
@SpringBootApplication
@Slf4j
@Async
@EnableAsync
@EnableScheduling
@EnableCaching
@EnableAspectJAutoProxy
public class DatasetApplication {
    public static void main(String[] args) {
        SpringApplication.run(DatasetApplication.class);
    }
}
