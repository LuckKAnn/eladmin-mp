package me.zhengjie.modules.system.service.impl;

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

    @Override
    public List<Dataset> getDatasets() {
        return datasetMapper.selectList(null);
    }

    @Override
    public Dataset getById(Long id) {
        return datasetMapper.selectById(id);
    }
}
