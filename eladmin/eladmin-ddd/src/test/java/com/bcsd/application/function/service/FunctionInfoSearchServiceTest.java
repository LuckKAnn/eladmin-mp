package com.bcsd.application.function.service;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author liukun.inspire
 * @Date 2024/3/8 03:09
 * @PackageName: com.bcsd.application.function.service
 * @ClassName: FunctionInfoSearchServiceTest
 * @Version 1.0
 */
@RunWith(MockitoJUnitRunner.class)

class FunctionInfoSearchServiceTest {

    @Mock
    FunctionInfoSearchService functionInfoSearchService;

    @Test
    void searchFunctionInfo() {
        MockitoAnnotations.initMocks(this);
        Mockito.doCallRealMethod().when(functionInfoSearchService).searchFunctionInfo(Mockito.any());
        functionInfoSearchService.searchFunctionInfo(null);
    }


    @Test
    void queryFunctionInfo() {
        MockitoAnnotations.initMocks(this);
        Mockito.doCallRealMethod().when(functionInfoSearchService).queryFunctionInfo(Mockito.any());
        functionInfoSearchService.queryFunctionInfo(1L);
    }

    @Test
    void batchQueryFunctionInfo() {
        MockitoAnnotations.initMocks(this);
        Mockito.doCallRealMethod().when(functionInfoSearchService).batchQueryFunctionInfo(Mockito.anyInt(),Mockito.anyInt());
        functionInfoSearchService.batchQueryFunctionInfo(1,1);
    }



    @Test
    void testBatchQueryFunctionInfo() {
        MockitoAnnotations.initMocks(this);
        Mockito.doCallRealMethod().when(functionInfoSearchService).batchQueryFunctionInfo(Mockito.any());
        functionInfoSearchService.batchQueryFunctionInfo(Collections.emptyList());
    }


    @Test
    void getFunctionNumber() {
        MockitoAnnotations.initMocks(this);
        Mockito.doCallRealMethod().when(functionInfoSearchService).getFunctionNumber();
        functionInfoSearchService.getFunctionNumber();
    }

}