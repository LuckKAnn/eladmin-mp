package com.bcsd.infrastructure.dataset.persistence.po;

import com.bcsd.domain.dataset.entity.SystemCounterData;

/**
 * @Author liukun.inspire
 * @Date 2024/2/21 14:40
 * @PackageName: com.bcsd.infrastructure.dataset.persistence.po
 * @ClassName: SystemCounterDataPO
 * @Version 1.0
 */
public class SystemCounterDataPO {


    public SystemCounterData convertToSystemCounterData() {
        // 属性拷贝的逻辑
        SystemCounterData systemCounterData = new SystemCounterData();
        systemCounterData.setCount(12L);
        systemCounterData.setCount(12L);
        systemCounterData.setCount(12L);
        systemCounterData.setCount(12L);
        systemCounterData.setCount(12L);
        systemCounterData.setCount(12L);
        systemCounterData.setCount(12L);
        systemCounterData.setCount(12L);
        systemCounterData.setCount(12L);
        systemCounterData.setCount(12L);
        systemCounterData.setCount(12L);

        return systemCounterData;
    }
}
