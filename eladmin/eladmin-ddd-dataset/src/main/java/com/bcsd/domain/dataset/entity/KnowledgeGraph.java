package com.bcsd.domain.dataset.entity;

import com.bcsd.domain.dataset.repository.facade.KnowledgeGraphRepository;
import com.bcsd.interfaces.function.dto.KnowledgeGraphSearchDTO;
import lombok.Data;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author liukun.inspire
 * @Date 2024/2/21 14:25
 * @PackageName: com.bcsd.domain.dataset.entity
 * @ClassName: KnowledgeGraph
 * @Version 1.0
 */
@Data
@Setter
@Service
@Slf4j
public class KnowledgeGraph {


    private Long id;

    @Autowired
    private KnowledgeGraphRepository knowledgeGraphRepository;

    public Long getId() {
        return id;
    }

    /**
     * @param id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    public KnowledgeGraph getKnowledgeGraph() {

        KnowledgeGraph knowledgeGraph = knowledgeGraphRepository.getKnowledgeGraph();

        knowledgeGraph.getId();
        knowledgeGraph.getId();
        knowledgeGraph.getId();
        knowledgeGraph.getId();
        knowledgeGraph.getId();
        return knowledgeGraph;
    }

    /**
     * 按照指定条件进行查询
     */
    public KnowledgeGraph searchFunctionKnowledgeGraph(KnowledgeGraphSearchDTO knowledgeGraphSearchDTO) {
        KnowledgeGraph knowledgeGraph = knowledgeGraphRepository.searchKnowledgeGraphByCondition(knowledgeGraphSearchDTO);

        knowledgeGraph.getId();
        knowledgeGraph.getId();
        knowledgeGraph.getId();
        knowledgeGraph.getId();
        knowledgeGraph.getId();
        knowledgeGraph.getId();
        return knowledgeGraph;
    }

}
