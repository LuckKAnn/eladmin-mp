package com.bcsd.interfaces.function.dto;

import lombok.Data;

/**
 * @Author liukun.inspire
 * @Date 2024/2/21 15:34
 * @PackageName: com.bcsd.interfaces.function.dto
 * @ClassName: KnowledgeGraphSearchDTO
 * @Version 1.0
 */
@Data
public class KnowledgeGraphSearchDTO {

    private Long fromId;

    private String condition;

    public Long getFromId() {
        return fromId;
    }

    /**
     * @param fromId to set
     */
    public void setFromId(Long fromId) {
        this.fromId = fromId;
    }

    public String getCondition() {
        return condition;
    }

    /**
     * @param condition to set
     */
    public void setCondition(String condition) {
        this.condition = condition;
    }
}
