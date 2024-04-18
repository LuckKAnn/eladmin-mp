package me.zhengjie.dto;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Data
@Document(indexName = "es_product")
public class EsCodeEntity {
    @Id
    private Long id;

    private String functionName;

    private String code;

    private String leakInfo;

    private String fault;

    private Long searchCount;

    private String functionFrom;
    private String compileLevel;

    private String targetArch;

    private String obsMehod;

    private Long codeLine;

    private String detail;
}
