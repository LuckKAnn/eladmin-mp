package me.zhengjie.modules.system.domain;

import lombok.Data;

/**
 * @Author liukun.inspire
 * @Date 2023/10/28 14:34
 * @PackageName: me.zhengjie.modules.system.domain
 * @ClassName: SimilarityData
 * @Version 1.0
 */
@Data
public class SimilarityData {
//     返回的结果其实就很类似于MilvusData 了吧

    private Long vectorId;

    private String codeInfo;

    private String functionName;

    /**
     * 相似度分数
     */
    private Float score;

    private String fromFile;

    private String compileLevel;

    private String targetArch;

    private String obs;


    private Long codeLine;

    private String protectInfo;

    private String faultInfo;

    private String dangerInfo;
}
