package com.bcsd.infrastructure.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author liukun.inspire
 * @Date 2024/3/10 23:50
 * @PackageName: com.bcsd.infrastructure.utils
 * @ClassName: ThrowableUtilTest
 * @Version 1.0
 */
class ThrowableUtilTest {

    @Test
    void getStackTrace() {
        ThrowableUtil.getStackTrace(new RuntimeException());
    }
}