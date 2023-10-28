package me.zhengjie.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Data
@Document(indexName = "es_product")
public class EsCodeEntity {

    @Id
    private Long id;
    @Field(type = FieldType.Text)
    private String functionName;
    @Field(type = FieldType.Text)
    private String code;
}
