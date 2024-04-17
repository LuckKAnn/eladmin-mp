package com.bcsd.interfaces.function.facade;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author liukun.inspire
 * @Date 2024/2/21 20:20
 * @PackageName: com.bcsd.interfaces.function.facade
 * @ClassName: FunctionControllerTest
 * @Version 1.0
 */
class FunctionControllerTest {

    FunctionController functionController = new FunctionController();

    @Test
    void testGetFunByPageSuccess() {
    }

    @Test
    void testGetFuncCodeByIdSuccess() {
    }

    @Test
    void testGetFuncCodeByIdNotExist() {
    }

    @Test
    void testGetFunctionByNameSuccess() {
    }

    @Test
    void testUploadFunctionDatasetSuccess() {
    }

    @Test
    void testUploadFunctionDatasetUnSatisfied() {
    }

    @Test
    void getFunByPage() {
        functionController.getFunByPage(0, 0);
    }

    @Test
    void getFuncCodeById() {
        functionController.getFuncCodeById("");
    }

    @Test
    void getFunctionByName() {
        functionController.getFunctionByName(null);
    }

    @Test
    void uploadFunctionDataset() {
        try {
            functionController.uploadFunctionDataset(null);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testUploadFunctionDataset() {
        try {
            functionController.uploadFunctionDataset(null, null);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testGetFunByPage() {
    }

    @Test
    void testGetFuncCodeById() {
    }

    @Test
    void testGetFunctionByName() {
    }

    @Test
    void testUploadFunctionDataset1() {
    }

    @Test
    void testUploadFunctionDataset2() {
    }
}