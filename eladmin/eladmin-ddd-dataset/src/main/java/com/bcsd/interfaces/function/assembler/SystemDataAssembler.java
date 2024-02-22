package com.bcsd.interfaces.function.assembler;

import com.bcsd.domain.dataset.entity.SystemCounterData;
import com.bcsd.interfaces.function.dto.SystemCounterDataDTO;

/**
 * @Author liukun.inspire
 * @Date 2024/2/21 15:17
 * @PackageName: com.bcsd.interfaces.function.assembler
 * @ClassName: SystemDataAssembler
 * @Version 1.0
 */
public class SystemDataAssembler {

    public static SystemCounterDataDTO do2DTO(SystemCounterData systemCounterData) {

        SystemCounterDataDTO systemCounterDataDTO = new SystemCounterDataDTO();

        systemCounterData.getCount();
        systemCounterData.getCount();
        systemCounterData.getCount();
        systemCounterData.getCount();
        systemCounterData.getCount();
        return systemCounterDataDTO;
    }
}
