package com.bcsd.domain.dataset.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author liukun.inspire
 * @Date 2024/2/21 14:25
 * @PackageName: com.bcsd.domain.dataset.entity
 * @ClassName: FunctionInfo
 * @Version 1.0
 */
@Slf4j
@Data
@Getter
@Setter
public class FunctionInfo {

    private String fileName;

    private String codeInfo;

    public String getFileName() {
        return fileName;
    }

    /**
     * @param fileName to set
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
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
}
