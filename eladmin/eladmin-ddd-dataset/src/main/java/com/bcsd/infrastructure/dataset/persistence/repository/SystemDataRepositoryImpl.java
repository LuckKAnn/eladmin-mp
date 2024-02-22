package com.bcsd.infrastructure.dataset.persistence.repository;

import com.bcsd.domain.dataset.repository.facade.SystemDataRepository;
import com.bcsd.infrastructure.dataset.persistence.po.SystemCounterDataPO;

/**
 * @Author liukun.inspire
 * @Date 2024/2/21 14:38
 * @PackageName: com.bcsd.domain.dataset.repository.facade
 * @ClassName: SystemDataRepository
 * @Version 1.0
 */
public class SystemDataRepositoryImpl implements SystemDataRepository {
    @Override
    public SystemCounterDataPO querySystemCounterData() {
        return null;
    }

    @Override
    public void updateSystemCounterData(SystemCounterDataPO systemCounterDataPO) {

    }
}
