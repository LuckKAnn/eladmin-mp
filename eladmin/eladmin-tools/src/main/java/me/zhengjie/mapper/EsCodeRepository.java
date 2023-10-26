package me.zhengjie.mapper;

import me.zhengjie.domain.EsCodeEntity;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

@Component
public interface  EsCodeRepository extends ElasticsearchRepository<EsCodeEntity, Long> {



}
