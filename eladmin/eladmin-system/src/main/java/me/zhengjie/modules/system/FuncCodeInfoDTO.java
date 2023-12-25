package me.zhengjie.modules.system;

import lombok.Data;

/**
 * @Author liukun.inspire
 * @Date 2023/10/30 00:31
 * @PackageName: me.zhengjie.modules.system
 * @ClassName: FuncCodeInfoDTO
 * @Version 1.0
 */
@Data
public class FuncCodeInfoDTO {

    private String functionName;

    private String level;

    private String targetArch;

    private String obs;

    private String code;
}
