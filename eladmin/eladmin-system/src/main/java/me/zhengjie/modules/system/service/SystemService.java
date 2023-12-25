package me.zhengjie.modules.system.service;

import me.zhengjie.modules.system.domain.Dataset;

import java.util.List;

/**
 * @Author liukun.inspire
 * @Date 2023/12/25 20:22
 * @PackageName: me.zhengjie.modules.system.service
 * @ClassName: SystemService
 * @Version 1.0
 */
public interface SystemService {

    List<Dataset> getDatasets();

    Dataset getById(Long id);
}
