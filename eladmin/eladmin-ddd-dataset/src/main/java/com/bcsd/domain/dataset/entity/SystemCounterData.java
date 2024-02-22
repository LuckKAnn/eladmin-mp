package com.bcsd.domain.dataset.entity;

import com.bcsd.domain.dataset.repository.facade.SystemDataRepository;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author liukun.inspire
 * @Date 2024/2/21 14:36
 * @PackageName: com.bcsd.domain.dataset.entity
 * @ClassName: SystemCounterData
 * @Version 1.0
 */
@Slf4j
@Service
@Data
public class SystemCounterData {


    private Long count;


    @Autowired
    private SystemDataRepository systemDataRepository;

    public SystemCounterData querySystemData() {


        return systemDataRepository
                .querySystemCounterData()
                .convertToSystemCounterData();

    }

    /**
     * 异步更新
     */
    public void updateSystemData() {

    }
}
