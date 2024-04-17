package com.bcsd.domain.function.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author liukun.inspire
 * @Date 2024/3/8 03:08
 * @PackageName: com.bcsd.domain.function.entity
 * @ClassName: FunctionTest
 * @Version 1.0
 */
class FunctionTest {

    Function function = new Function();
    @Test
    void uploadSingleFunctionProcess() {
        function.uploadSingleFunctionProcess();
    }
    @Test
    void uploadSingleFunctionProcess1() {
    }

    @Test
    void uploadExeProcess() {
        function.uploadSingleFunctionProcess();

    }
    @Test
    void uploadExeProcess1() {
    }

    @Test
    void functionInfoQuery() {
        function.uploadSingleFunctionProcess();

    }
    @Test
    void functionInfoQuery1() {
    }


    @Test
    void batchFunctionInfoQuery() {
        function.uploadSingleFunctionProcess();

    }
    @Test
    void batchFunctionInfoQuery1() {
    }


    @Test
    void testBatchFunctionInfoQuery() {
        function.uploadSingleFunctionProcess();

    }
    @Test
    void testBatchFunctionInfoQuery1() {
    }

    @Test
    void functionSearch() {
        function.uploadSingleFunctionProcess();

    }
    @Test
    void functionSearch1() {
    }


    @Test
    void functionVectorSearch() {
        function.uploadSingleFunctionProcess();

    }
    @Test
    void functionVectorSearch1() {
    }

    @Test
    void countFunctions() {
        function.uploadSingleFunctionProcess();

    }
    @Test
    void countFunctions2() {
        function.doDimensionalityReduction(null,null);

    }
    @Test
    void countFunctions32() {
        function.doCaculateCosineSimilarity(null);

    }

    @Test
    void countFunctions1() {
    }
    @Test
    void doSimilarityDetection() {
        function.doSimilarityDetection(null);

    }
    @Test
    void doSimilarityDetection1() {
    }

    @Test
    void testUploadSingleFunctionProcess() {
        function.uploadSingleFunctionProcess();

    }

    @Test
    void testUploadExeProcess() {
        function.uploadExeProcess();
    }

    @Test
    void testFunctionInfoQuery() {
        function.functionInfoQuery(null);

    }

    @Test
    void testBatchFunctionInfoQuery2() {
        function.batchFunctionInfoQuery(0,0);

    }

    @Test
    void testBatchFunctionInfoQuery3() {
        function.batchFunctionInfoQuery(null);
    }

    @Test
    void testFunctionSearch() {
        function.functionSearch(null);

    }

    @Test
    void testFunctionVectorSearch() {
        function.functionVectorSearch();

    }


    @Test
    void testCountFunctions() {
        function.countFunctions();

    }

    @Test
    void testDoSimilarityDetection() {
        function.doSimilarityDetection(null);

    }

    @Test
    void getName() {
        function.getName();

    }

    @Test
    void getSEARCH_PARAM() {
        function.getSEARCH_PARAM();

    }

    @Test
    void getFUNC_EMBEDING_INDEX() {
        function.getFUNC_EMBEDING_INDEX();

    }

    @Test
    void getFunctionRepository() {
        function.getFunctionRepository();

    }

    @Test
    void setName() {
        function.setName(null);

    }

    @Test
    void setFunctionRepository() {
        function.setFunctionRepository(null);

    }
}