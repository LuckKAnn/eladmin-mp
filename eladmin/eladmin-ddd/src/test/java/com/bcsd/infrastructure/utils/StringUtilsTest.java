package com.bcsd.infrastructure.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author liukun.inspire
 * @Date 2024/3/10 23:19
 * @PackageName: com.bcsd.infrastructure.utils
 * @ClassName: StringUtilsTest
 * @Version 1.0
 */
class StringUtilsTest {

    @Test
    void toCamelCase() {
        StringUtils.toCamelCase("");
    }

    @Test
    void toCapitalizeCamelCase() {
        StringUtils.toCapitalizeCamelCase("");
    }

    @Test
    void toUnderScoreCase() {
        StringUtils.toUnderScoreCase("");
    }

    @Test
    void getIp() {
        StringUtils.getIp(null);
    }

    @Test
    void getCityInfo() {
        StringUtils.getCityInfo("");
    }

    @Test
    void getBrowser() {
        StringUtils.getBrowser(null);
    }

    @Test
    void getWeekDay() {
        StringUtils.getWeekDay();
    }

    @Test
    void getLocalIp() {
        StringUtils.getLocalIp();
    }

    @Test
    void getAllFields() {
        StringUtils.getAllFields(null, null);
    }
}