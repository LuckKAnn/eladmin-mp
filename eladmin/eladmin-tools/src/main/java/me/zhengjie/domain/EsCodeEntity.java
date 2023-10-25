package me.zhengjie.domain;

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
}
