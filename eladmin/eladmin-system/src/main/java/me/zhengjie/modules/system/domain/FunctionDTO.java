package me.zhengjie.modules.system.domain;

import lombok.Data;

/**
 * @Author liukun.inspire
 * @Date 2023/10/29 16:10
 * @PackageName: me.zhengjie.modules.system.domain
 * @ClassName: FuntionDTO
 * @Version 1.0
 */
@Data
public class FunctionDTO {

    private String functionName;

    /**
     * 是否可编译，或者可提取
     */
    private boolean isCompilable;
    /**
     * 代码行数信息
     */
    private Long codeLine;

    private String codeInfo;

    /**
     * 来源信息
     */
    private String fromName;

    /**
     * 函数的注释信息
     */
    private String comment;


    private String fileName;

    private String functionId;

    private String comeInfo;


}
