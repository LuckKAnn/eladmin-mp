package me.zhengjie.modules.system.service;

import me.zhengjie.modules.system.domain.SimilarityData;

import java.util.List;

/**
 * @Author liukun.inspire
 * @Date 2023/10/28 14:38
 * @PackageName: me.zhengjie.modules.system.service
 * @ClassName: ProcessService
 * @Version 1.0
 */
public interface ProcessService {


    public List<SimilarityData> doProcess(String code);
}
