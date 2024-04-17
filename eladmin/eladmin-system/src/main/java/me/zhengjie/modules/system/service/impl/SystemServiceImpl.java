package me.zhengjie.modules.system.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import me.zhengjie.modules.system.domain.Dataset;
import me.zhengjie.modules.system.mapper.DatasetMapper;
import me.zhengjie.modules.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author liukun.inspire
 * @Date 2023/12/25 20:26
 * @PackageName: me.zhengjie.modules.system.service.impl
 * @ClassName: SystemServiceImpl
 * @Version 1.0
 */
@Service
public class SystemServiceImpl implements SystemService {

    @Autowired
    private DatasetMapper datasetMapper;

    private List<Dataset> cacheList = null;

    private Dataset cacheDataset = null;

    @Override
    public List<Dataset> getDatasets() {
        if (CollectionUtil.isNotEmpty(cacheList)) {
            return cacheList;
        }
        List<Dataset> datasets = datasetMapper.selectList(null);
        cacheList = datasets;
        // return datasetMapper.selectList(null);
        return cacheList;
    }

    @Override
    public Dataset getById(Long id) {
        if (cacheDataset == null) {
            Dataset dataset = datasetMapper.selectById(id);
            cacheDataset = dataset;
        }
        return cacheDataset;
    }
}
