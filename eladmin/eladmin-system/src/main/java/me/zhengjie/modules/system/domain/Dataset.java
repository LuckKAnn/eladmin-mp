package me.zhengjie.modules.system.domain;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import me.zhengjie.base.BaseEntity;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * @Author liukun.inspire
 * @Date 2023/12/25 20:23
 * @PackageName: me.zhengjie.modules.system.domain
 * @ClassName: Dataset
 * @Version 1.0
 */
@Getter
@Setter
@TableName("sys_dataset")
public class Dataset extends BaseEntity implements Serializable {

    @NotNull(groups = {Update.class})
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "ID", hidden = true)
    private Long id;

    @ApiModelProperty(value = "数据集名称")
    private String name;

    private String purpose;

    private String detail;

    private Long count;
    @TableField(value = "obs_info")
    private String obsInfo;

    @TableField(value = "compile_level")

    private String compileLevel;


    /**
     * 数据集类型
     */
    private Integer type;


}
