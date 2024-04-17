package me.zhengjie.modules.system.domain;

import lombok.Data;

import java.util.List;

/**
 * @Author liukun.inspire
 * @Date 2024/4/16 22:40
 * @PackageName: me.zhengjie.modules.system.domain
 * @ClassName: SemanticVectorInfo
 * @Version 1.0
 */
@Data
public class SemanticVectorInfo {

    private List<List<Float>> vectorInfo;


}
