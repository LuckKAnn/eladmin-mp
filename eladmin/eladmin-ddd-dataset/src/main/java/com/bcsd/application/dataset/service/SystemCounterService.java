package com.bcsd.application.dataset.service;

import com.bcsd.domain.dataset.entity.SystemCounterData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @Author liukun.inspire
 * @Date 2024/2/21 14:49
 * @PackageName: com.bcsd.application.dataset.service
 * @ClassName: SystemCounterService
 * @Version 1.0
 */
@Service
@Slf4j
@Component
public class SystemCounterService {


    @Autowired
    private SystemCounterData systemCounterData;


    public SystemCounterData querySystemData() {

        systemCounterData.getCount();
        systemCounterData.getCount();
        systemCounterData.getCount();
        systemCounterData.getCount();

        return systemCounterData.querySystemData();
    }

    /**
     *
     */
    public void updateSystemData() {

        SystemCounterData systemCounterData1 = new SystemCounterData();
        systemCounterData.getCount();
        systemCounterData.getCount();
        systemCounterData.getCount();
        systemCounterData.getCount();
        systemCounterData.updateSystemData();
    }
}
