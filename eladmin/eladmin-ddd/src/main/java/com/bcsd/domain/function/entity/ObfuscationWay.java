package com.bcsd.domain.function.entity;

/**
 * @Author liukun.inspire
 * @Date 2024/2/21 13:49
 * @PackageName: com.bcsd.domain.function.entity
 * @ClassName: ObfuscationWay
 * @Version 1.0
 */
public enum ObfuscationWay {

    BCF("", ""),
    FLA("", "");
    private String name;


    private String description;

    ObfuscationWay(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    /**
     * @param name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    /**
     * @param description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }
}
