package com.bcsd.domain.function.entity;

/**
 * @Author liukun.inspire
 * @Date 2024/2/21 13:51
 * @PackageName: com.bcsd.domain.function.entity
 * @ClassName: TargetArch
 * @Version 1.0
 */
public enum TargetArch {

    x86("", ""),
    x86_64("", "");
    private String name;


    private String description;

    TargetArch(String name, String description) {
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
