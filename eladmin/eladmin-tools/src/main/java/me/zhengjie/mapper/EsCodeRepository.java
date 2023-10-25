package me.zhengjie.mapper;

import me.zhengjie.domain.EsCodeEntity;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface  EsCodeRepository extends ElasticsearchRepository<EsCodeEntity, Long> {



}
