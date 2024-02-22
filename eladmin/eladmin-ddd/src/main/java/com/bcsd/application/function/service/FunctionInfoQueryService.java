package com.bcsd.application.function.service;

import com.bcsd.domain.function.entity.Function;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author liukun.inspire
 * @Date 2024/2/21 13:54
 * @PackageName: com.bcsd.application.function.service
 * @ClassName: FunctionInfoQueryService
 * @Version 1.0
 */
@Service
@Slf4j
public class FunctionInfoQueryService {


    private final Function function;

    public FunctionInfoQueryService(Function function) {
        this.function = function;
    }

    public Function queryFunctionInfo(Long functionId) {
        return function.functionInfoQuery(functionId);
    }

    public List<Function> batchQueryFunctionInfo(List<Long> functionId) {
        return function.batchFunctionInfoQuery(functionId);
    }


}
