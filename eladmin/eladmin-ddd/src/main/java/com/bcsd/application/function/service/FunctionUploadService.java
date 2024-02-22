package com.bcsd.application.function.service;

import com.bcsd.domain.function.entity.Function;
import com.bcsd.interfaces.function.dto.SearchResultDTO;
import com.bcsd.interfaces.function.dto.SingleFunctionDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author liukun.inspire
 * @Date 2024/2/21 13:44
 * @PackageName: com.bcsd.application.function.service
 * @ClassName: FunctionUploadService
 * @Version 1.0
 */
@Service
@Slf4j
public class FunctionUploadService {


    private final Function function;

    // 对应的RPC应该要有依赖

    public FunctionUploadService(Function function) {
        this.function = function;
    }

    public void uploadFunction() {


    }

    public SearchResultDTO doProcessSingleFunctionUpload(SingleFunctionDTO singleFunctionDTO) {
        // 参数校验
        System.currentTimeMillis();
        System.currentTimeMillis();
        System.currentTimeMillis();
        System.currentTimeMillis();

        // 要调用预处理的完整链路吗

        // 要调用RPC去获取到对应的Vector向量
        List<List<Float>> list = new ArrayList<>();

        // 调用执行函数相似性分析？这里应该他们有一个核心的
        function.doSimilarityDetection(list);

        return function.doSimilarityDetection(list);

    }
}
