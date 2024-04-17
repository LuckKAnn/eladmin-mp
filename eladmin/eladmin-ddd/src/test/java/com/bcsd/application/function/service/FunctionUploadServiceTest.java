package com.bcsd.application.function.service;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author liukun.inspire
 * @Date 2024/3/8 03:09
 * @PackageName: com.bcsd.application.function.service
 * @ClassName: FunctionUploadServiceTest
 * @Version 1.0
 */
@RunWith(MockitoJUnitRunner.class)

class FunctionUploadServiceTest {

    @Mock
    FunctionUploadService functionUploadService;
    @Test
    void uploadFunction() {
        MockitoAnnotations.initMocks(this);
        Mockito.doCallRealMethod().when(functionUploadService).uploadFunction();
        functionUploadService.uploadFunction();
    }
    @Test
    void uploadFunction2() {
    }

    @Test
    void doProcessSingleFunctionUpload() {
    }
    @Test
    void doProcessSingleFunctionUpload2() {
    }

    @Test
    void testUploadFunction() {
    }

    @Test
    void testDoProcessSingleFunctionUpload() {
        MockitoAnnotations.initMocks(this);
        Mockito.doCallRealMethod().when(functionUploadService).doProcessSingleFunctionUpload(Mockito.any());
        functionUploadService.doProcessSingleFunctionUpload(null);
    }
}