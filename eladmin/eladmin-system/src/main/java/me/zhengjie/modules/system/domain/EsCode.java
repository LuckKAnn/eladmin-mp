package me.zhengjie.modules.system.domain;

import lombok.Data;

/**
 * @Author liukun.inspire
 * @Date 2023/10/28 14:22
 * @PackageName: me.zhengjie.modules.system.domain
 * @ClassName: EsCode
 * @Version 1.0
 */
@Data
public class EsCode {
    private String id;
    private String functionName;
    private String code;
    private String protectInfo;
    private String leakInfo;
    private String dangerious;
    private Long searchCount;
    private String functionFrom;
    private String compileLevel;
    private String targetArch;
    private String obsMethod;
    private Long codeLine;

    /**
     * 详细的描述信息：
     * 在缺陷时，可能是缺陷的具体信息描述
     */
    private String detail;
}
