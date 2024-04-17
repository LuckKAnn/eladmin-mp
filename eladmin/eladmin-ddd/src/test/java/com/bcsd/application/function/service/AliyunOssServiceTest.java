package com.bcsd.application.function.service;

import com.bcsd.infrastructure.repository.iml.FunctionRepositoryImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author liukun.inspire
 * @Date 2024/3/10 21:25
 * @PackageName: com.bcsd.application.function.service
 * @ClassName: AliyunOssServiceTest
 * @Version 1.0
 */
class AliyunOssServiceTest {
    @Mock
    AliyunOssService aliyunOssService;

    @Test
    void uploadFile() {
        MockitoAnnotations.initMocks(this);
        Mockito.doCallRealMethod().when(aliyunOssService).uploadFile();
        // Mockito.when(aliyunOssService.uploadFile()).thenCallRealMethod(/);
        aliyunOssService.uploadFile();
        // Mockito.when(aliyunOssService.uploadFile())).thenCallRealMethod();

    }

    @Test
    void uploadByMultipart() {
        MockitoAnnotations.initMocks(this);
        Mockito.doCallRealMethod().when(aliyunOssService).uploadByMultipart();
        // Mockito.when(aliyunOssService.uploadFile()).thenCallRealMethod(/);
        aliyunOssService.uploadByMultipart();
    }

    @Test
    void downloafFileToLocal() {
        MockitoAnnotations.initMocks(this);
        Mockito.doCallRealMethod().when(aliyunOssService).downloafFileToLocal();
        // Mockito.when(aliyunOssService.uploadFile()).thenCallRealMethod(/);
        aliyunOssService.downloafFileToLocal();
    }

    @Test
    void createFilePath() {
        MockitoAnnotations.initMocks(this);
        Mockito.doCallRealMethod().when(aliyunOssService).createFilePath();
        // Mockito.when(aliyunOssService.uploadFile()).thenCallRealMethod(/);
        aliyunOssService.createFilePath();
    }

    @Test
    void listFileObject() {
        MockitoAnnotations.initMocks(this);
        Mockito.doCallRealMethod().when(aliyunOssService).listFileObject();
        // Mockito.when(aliyunOssService.uploadFile()).thenCallRealMethod(/);
        aliyunOssService.listFileObject();
    }
}