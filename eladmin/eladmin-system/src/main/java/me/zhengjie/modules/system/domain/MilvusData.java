package me.zhengjie.modules.system.domain;

import lombok.Data;

/**
 * @Author liukun.inspire
 * @Date 2023/10/28 14:26
 * @PackageName: me.zhengjie.modules.system.domain
 * @ClassName: MilvusData
 * @Version 1.0
 */
@Data
public class MilvusData {

    private Long vectorId;

    private String codeInfo;

    private String functionName;
}
