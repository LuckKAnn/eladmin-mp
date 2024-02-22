package com.bcsd.interfaces.function.dto;

import lombok.Data;

import java.util.List;

/**
 * @Author liukun.inspire
 * @Date 2023/10/28 14:34
 * @PackageName: me.zhengjie.modules.system.domain
 * @ClassName: SimilarityData
 * @Version 1.0
 */
public class SimilarityData {
//     返回的结果其实就很类似于MilvusData 了吧

    private Long vectorId;

    private String codeInfo;

    private String functionName;

    /**
     * 相似度分数
     */
    private Float score;

    private List<Float> representation;

    private String fromFile;

    private String compileLevel;

    private String targetArch;

    private String obs;


    private Long codeLine;

    private String protectInfo;

    private String faultInfo;

    private String dangerInfo;

    public Long getVectorId() {
        return vectorId;
    }

    /**
     * @param vectorId to set
     */
    public void setVectorId(Long vectorId) {
        this.vectorId = vectorId;
    }

    public String getCodeInfo() {
        return codeInfo;
    }

    /**
     * @param codeInfo to set
     */
    public void setCodeInfo(String codeInfo) {
        this.codeInfo = codeInfo;
    }

    public String getFunctionName() {
        return functionName;
    }

    /**
     * @param functionName to set
     */
    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }

    public Float getScore() {
        return score;
    }

    /**
     * @param score to set
     */
    public void setScore(Float score) {
        this.score = score;
    }

    public String getFromFile() {
        return fromFile;
    }

    /**
     * @param fromFile to set
     */
    public void setFromFile(String fromFile) {
        this.fromFile = fromFile;
    }

    public String getCompileLevel() {
        return compileLevel;
    }

    /**
     * @param compileLevel to set
     */
    public void setCompileLevel(String compileLevel) {
        this.compileLevel = compileLevel;
    }

    public String getTargetArch() {
        return targetArch;
    }

    /**
     * @param targetArch to set
     */
    public void setTargetArch(String targetArch) {
        this.targetArch = targetArch;
    }

    public String getObs() {
        return obs;
    }

    /**
     * @param obs to set
     */
    public void setObs(String obs) {
        this.obs = obs;
    }

    public Long getCodeLine() {
        return codeLine;
    }

    /**
     * @param codeLine to set
     */
    public void setCodeLine(Long codeLine) {
        this.codeLine = codeLine;
    }

    public String getProtectInfo() {
        return protectInfo;
    }

    /**
     * @param protectInfo to set
     */
    public void setProtectInfo(String protectInfo) {
        this.protectInfo = protectInfo;
    }

    public String getFaultInfo() {
        return faultInfo;
    }

    /**
     * @param faultInfo to set
     */
    public void setFaultInfo(String faultInfo) {
        this.faultInfo = faultInfo;
    }

    public String getDangerInfo() {
        return dangerInfo;
    }

    /**
     * @param dangerInfo to set
     */
    public void setDangerInfo(String dangerInfo) {
        this.dangerInfo = dangerInfo;
    }
}
