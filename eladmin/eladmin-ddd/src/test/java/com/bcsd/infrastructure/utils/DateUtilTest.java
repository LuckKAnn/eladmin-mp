package com.bcsd.infrastructure.utils;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author liukun.inspire
 * @Date 2024/3/10 21:23
 * @PackageName: com.bcsd.infrastructure.utils
 * @ClassName: DateUtilTest
 * @Version 1.0
 */
class DateUtilTest {


    @Test
    void getTimeStamp() {
        DateUtil.getTimeStamp();
    }

    @Test
    void fromTimeStamp() {
        DateUtil.fromTimeStamp(System.currentTimeMillis());
    }

    @Test
    void toDate() {
        DateUtil.toDate(LocalDate.now());
    }

    @Test
    void testToDate() {
        DateUtil.toDate(LocalDateTime.now());
    }

    @Test
    void toLocalDateTime() {
        DateUtil.toLocalDateTime(new Date());
    }

    @Test
    void localDateTimeFormat() {
        DateUtil.localDateTimeFormat(LocalDateTime.now(), DateTimeFormatter.BASIC_ISO_DATE);
    }

    @Test
    void testLocalDateTimeFormat() {
    }

    @Test
    void localDateTimeFormatyMdHms() {
    }

    @Test
    void testGetTimeStamp() {
    }

    @Test
    void localDateTimeFormatyMd() {
    }

    @Test
    void parseLocalDateTimeFormat() {
    }

    @Test
    void testParseLocalDateTimeFormat() {
    }

    @Test
    void parseLocalDateTimeFormatyMdHms() {
    }
}