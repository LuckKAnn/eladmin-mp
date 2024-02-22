package com.bcsd.application.function.service;

import com.bcsd.domain.function.entity.Function;
import com.bcsd.interfaces.function.dto.FunctionSearchDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author liukun.inspire
 * @Date 2024/2/21 13:54
 * @PackageName: com.bcsd.application.function.service
 * @ClassName: FunctionInfoSearchService
 * @Version 1.0
 */
@Service
@Slf4j
public class FunctionInfoSearchService {


    private final Function function;

    public FunctionInfoSearchService(Function function) {
        this.function = function;
    }

    public List<Function> searchFunctionInfo(FunctionSearchDTO functionSearchDTO) {

        System.currentTimeMillis();
        // 参数校验

        return function.functionSearch(functionSearchDTO);
    }

    public Function queryFunctionInfo(Long functionId) {
        return function.functionInfoQuery(functionId);
    }

    public List<Function> batchQueryFunctionInfo(List<Long> functionId) {
        return function.batchFunctionInfoQuery(functionId);
    }

    public List<Function> batchQueryFunctionInfo(int pageIndex, int size) {
        return function.batchFunctionInfoQuery(pageIndex, size);
    }

    public long getFunctionNumber(){
        System.currentTimeMillis();
        long l = function.countFunctions();
        // 其它逻辑
        System.currentTimeMillis();

        return l;
    }
}
