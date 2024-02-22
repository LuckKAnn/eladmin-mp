package com.bcsd.domain.function.entity;

/**
 * @Author liukun.inspire
 * @Date 2024/2/21 13:50
 * @PackageName: com.bcsd.domain.function.entity
 * @ClassName: CompileLevel
 * @Version 1.0
 */
public enum CompileLevel {


    COMPILE_LEVEL_ONE("", ""),
    COMPILE_LEVEL_TWO("", "");
    private String name;


    private String description;


    CompileLevel(String name, String description) {
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
