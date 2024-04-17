package com.bcsd.infrastructure.repository.iml;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

/**
 * @Author liukun.inspire
 * @Date 2024/3/10 21:20
 * @PackageName: com.bcsd.infrastructure.repository.iml
 * @ClassName: MilvusImplTest
 * @Version 1.0
 */
class MilvusFunctionImplTest {

    @Mock
    MilvusFunctionRepositoryImpl elasticSearchRepository;

    @Test
    void listQueryFunctionInfo() {
        MockitoAnnotations.initMocks(this);
        Mockito.when(elasticSearchRepository.listQueryFunctionInfo(Mockito.any())).thenCallRealMethod();
        elasticSearchRepository.listQueryFunctionInfo(null);
    }

    @Test
    void pageQueryFunctionInfo() {
        MockitoAnnotations.initMocks(this);
        Mockito.when(elasticSearchRepository.pageQueryFunctionInfo(Mockito.anyInt(),Mockito.anyInt())).thenCallRealMethod();

        elasticSearchRepository.pageQueryFunctionInfo(0, 0);
    }

    @Test
    void queryFunctionInfo() {
        MockitoAnnotations.initMocks(this);
        Mockito.when(elasticSearchRepository.queryFunctionInfo(Mockito.any())).thenCallRealMethod();

        elasticSearchRepository.queryFunctionInfo(0L);
    }

    @Test
    void searchFunctionInfo() {
        MockitoAnnotations.initMocks(this);
        Mockito.when(elasticSearchRepository.searchFunctionInfo()).thenCallRealMethod();

        elasticSearchRepository.searchFunctionInfo();
    }

    @Test
    void countFunctionNumber() {
        MockitoAnnotations.initMocks(this);
        Mockito.when(elasticSearchRepository.countFunctionNumber()).thenCallRealMethod();
        elasticSearchRepository.countFunctionNumber();
    }

    @Test
    void doSimilarityDetectionWithCosine() {
        MockitoAnnotations.initMocks(this);
        Mockito.when(elasticSearchRepository.doSimilarityDetectionWithCosine(Mockito.any(),Mockito.any(),Mockito.any())).thenCallRealMethod();

        elasticSearchRepository.doSimilarityDetectionWithCosine(null, null, null);
    }
}