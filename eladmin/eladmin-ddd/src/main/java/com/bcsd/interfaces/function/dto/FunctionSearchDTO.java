package com.bcsd.interfaces.function.dto;

/**
 * @Author liukun.inspire
 * @Date 2024/2/21 19:45
 * @PackageName: com.bcsd.interfaces.function.dto
 * @ClassName: FunctionSearchDTO
 * @Version 1.0
 */
public class FunctionSearchDTO {

    private String functionName;

    private String code;
    private String code2;
    private String code3;
    private String code4;
    private String code5;


    public String getFunctionName() {
        return functionName;
    }

    /**
     * @param functionName to set
     */
    public void setFunctionName(String functionName) {
        this.functionName = functionName;
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

    public String getCode2() {
        return code2;
    }

    /**
     * @param code2 to set
     */
    public void setCode2(String code2) {
        this.code2 = code2;
    }

    public String getCode3() {
        return code3;
    }

    /**
     * @param code3 to set
     */
    public void setCode3(String code3) {
        this.code3 = code3;
    }

    public String getCode4() {
        return code4;
    }

    /**
     * @param code4 to set
     */
    public void setCode4(String code4) {
        this.code4 = code4;
    }

    public String getCode5() {
        return code5;
    }

    /**
     * @param code5 to set
     */
    public void setCode5(String code5) {
        this.code5 = code5;
    }
}
