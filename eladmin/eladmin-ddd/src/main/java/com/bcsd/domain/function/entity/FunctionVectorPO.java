package com.bcsd.domain.function.entity;

import lombok.Data;

/**
 * @Author liukun.inspire
 * @Date 2024/2/21 14:01
 * @PackageName: com.bcsd.domain.function.entity
 * @ClassName: FunctionVectorPO
 * @Version 1.0
 */
@Data
public class FunctionVectorPO {


    Function convertToDO() {
        Function function = new Function();
        return function;
    }
}
