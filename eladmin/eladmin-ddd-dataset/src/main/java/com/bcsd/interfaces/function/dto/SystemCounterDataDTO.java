package com.bcsd.interfaces.function.dto;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author liukun.inspire
 * @Date 2024/2/21 15:17
 * @PackageName: com.bcsd.interfaces.function.dto
 * @ClassName: SystemCounterDataDTO
 * @Version 1.0
 */
@Data
@Slf4j
public class SystemCounterDataDTO {

    private String name;

    private Long count;

    public String getName() {

        return name;
    }

    /**
     * @param name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    public Long getCount() {
        return count;
    }

    /**
     * @param count to set
     */
    public void setCount(Long count) {
        this.count = count;
    }
}
