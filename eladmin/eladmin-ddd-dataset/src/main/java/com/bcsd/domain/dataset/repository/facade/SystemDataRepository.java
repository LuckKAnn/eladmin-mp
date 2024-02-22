package com.bcsd.domain.dataset.repository.facade;

import com.bcsd.infrastructure.dataset.persistence.po.SystemCounterDataPO;

/**
 * @Author liukun.inspire
 * @Date 2024/2/21 14:38
 * @PackageName: com.bcsd.domain.dataset.repository.facade
 * @ClassName: SystemDataRepository
 * @Version 1.0
 */
public interface SystemDataRepository {

    SystemCounterDataPO querySystemCounterData();

    void updateSystemCounterData(SystemCounterDataPO systemCounterDataPO);


}
