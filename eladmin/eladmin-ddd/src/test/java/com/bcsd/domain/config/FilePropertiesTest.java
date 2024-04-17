package com.bcsd.domain.config;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author liukun.inspire
 * @Date 2024/3/10 22:08
 * @PackageName: com.bcsd.domain.config
 * @ClassName: FilePropertiesTest
 * @Version 1.0
 */
class FilePropertiesTest {

    @Test
    void getPath() {
        FileProperties fileProperties = new FileProperties();
        fileProperties.getPath();
    }
}