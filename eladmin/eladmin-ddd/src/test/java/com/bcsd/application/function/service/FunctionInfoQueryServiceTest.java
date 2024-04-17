package com.bcsd.application.function.service;

import com.bcsd.domain.function.entity.Function;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * @Author liukun.inspire
 * @Date 2024/3/8 03:09
 * @PackageName: com.bcsd.application.function.service
 * @ClassName: FunctionInfoQueryServiceTest
 * @Version 1.0
 */
@RunWith(MockitoJUnitRunner.class)
class FunctionInfoQueryServiceTest {

    @Mock
    FunctionInfoQueryService functionInfoQueryService;

    // @Test
    @Test
    public void queryFunctionInfo() {
        MockitoAnnotations.initMocks(this);
        Mockito.when(functionInfoQueryService.queryFunctionInfo(Mockito.any())).thenCallRealMethod();
        System.out.println(functionInfoQueryService.queryFunctionInfo(1L));
    }

    @Test
    void queryFunctionInfo2() {
        MockitoAnnotations.initMocks(this);
        Mockito.doCallRealMethod().when(functionInfoQueryService).queryFunctionInfo(Mockito.any());
        functionInfoQueryService.queryFunctionInfo(1L);
    }

    @Test
    void batchQueryFunctionInfo() {
        MockitoAnnotations.initMocks(this);
        Mockito.doCallRealMethod().when(functionInfoQueryService).batchQueryFunctionInfo(Mockito.any());
        functionInfoQueryService.batchQueryFunctionInfo(new ArrayList<>());
    }

    // @Test
    // void batchQueryFunctionInfo2() {
    //     MockitoAnnotations.initMocks(this);
    //     Mockito.doCallRealMethod().when(functionInfoQueryService).batchQueryFunctionInfo2(Mockito.any());
    //     functionInfoQueryService.queryFunctionInfo(1L);
    // }
}