package com.bcsd.interfaces.function.dto;

/**
 * @Author liukun.inspire
 * @Date 2024/2/21 19:40
 * @PackageName: com.bcsd.interfaces.function.dto
 * @ClassName: FunctionInfoDTO
 * @Version 1.0
 */
public class FunctionInfoDTO {

    private Long id;

    private String name;

    public Long getId() {
        return id;
    }

    /**
     * @param id to set
     */
    public void setId(Long id) {
        this.id = id;
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
}
