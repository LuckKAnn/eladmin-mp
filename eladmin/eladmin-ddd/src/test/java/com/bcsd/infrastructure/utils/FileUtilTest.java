package com.bcsd.infrastructure.utils;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author liukun.inspire
 * @Date 2024/3/10 21:50
 * @PackageName: com.bcsd.infrastructure.utils
 * @ClassName: FileUtilTest
 * @Version 1.0
 */
class FileUtilTest {

    @Test
    void toFile() {
        // FileUtil.toFile();
    }

    @Test
    void getExtensionName() {
        FileUtil.getExtensionName("1111.xx");
    }

    @Test
    void getFileNameNoEx() {
        FileUtil.getFileNameNoEx("");
    }

    @Test
    void getSize() {
        FileUtil.getSize(111l);
    }

    @Test
    void inputStreamToFile() {
        FileUtil.inputStreamToFile(null, null);
    }

    @Test
    void upload() {
        FileUtil.upload(null, null);
    }

    @Test
    void downloadExcel() {
        try {
            FileUtil.downloadExcel(null, null);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void getFileType() {
        FileUtil.getFileType("");
    }

    @Test
    void checkSize() {
        FileUtil.checkSize(11110L, 1010L);
    }

    @Test
    void check() {
        FileUtil.check("", "");
    }

    @Test
    void testCheck() {
    }

    @Test
    void downloadFile() {
    }

    @Test
    void verifyFilename() {
    }

    @Test
    void getMd5() {
        FileUtil.getMd5(null);
    }
}