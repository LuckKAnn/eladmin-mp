package com.bcsd.interfaces.function.dto;

import com.bcsd.domain.function.entity.CompileLevel;
import com.bcsd.domain.function.entity.ObfuscationWay;
import com.bcsd.domain.function.entity.TargetArch;

/**
 * @Author liukun.inspire
 * @Date 2024/2/21 22:10
 * @PackageName: com.bcsd.interfaces.function.dto
 * @ClassName: SingleFunctionDTO
 * @Version 1.0
 */
public class SingleFunctionDTO {


    private String functionName;

    private String decription;

    private String code;

    private ObfuscationWay obfuscationWay;

    private TargetArch targetArch;

    private CompileLevel compileLevel;

    public String getFunctionName() {
        return functionName;
    }

    /**
     * @param functionName to set
     */
    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }

    public String getDecription() {
        return decription;
    }

    /**
     * @param decription to set
     */
    public void setDecription(String decription) {
        this.decription = decription;
    }

    public String getCode() {
        return code;
    }

    /**
     * @param code to set
     */
    public void setCode(String code) {
        this.code = code;
    }

    public ObfuscationWay getObfuscationWay() {
        return obfuscationWay;
    }

    /**
     * @param obfuscationWay to set
     */
    public void setObfuscationWay(ObfuscationWay obfuscationWay) {
        this.obfuscationWay = obfuscationWay;
    }

    public TargetArch getTargetArch() {
        return targetArch;
    }

    /**
     * @param targetArch to set
     */
    public void setTargetArch(TargetArch targetArch) {
        this.targetArch = targetArch;
    }

    public CompileLevel getCompileLevel() {
        return compileLevel;
    }

    /**
     * @param compileLevel to set
     */
    public void setCompileLevel(CompileLevel compileLevel) {
        this.compileLevel = compileLevel;
    }
}
