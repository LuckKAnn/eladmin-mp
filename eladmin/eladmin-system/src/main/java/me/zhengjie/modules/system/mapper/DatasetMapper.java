package me.zhengjie.modules.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import me.zhengjie.modules.system.domain.Dataset;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author liukun.inspire
 * @Date 2023/12/25 20:27
 * @PackageName: me.zhengjie.modules.system.mapper
 * @ClassName: DatasetMapper
 * @Version 1.0
 */
@Mapper
public interface DatasetMapper extends BaseMapper<Dataset> {
}
