package com.bcsd.application.dataset.service;

import com.bcsd.domain.dataset.entity.KnowledgeGraph;
import com.bcsd.interfaces.function.dto.KnowledgeGraphSearchDTO;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @Author liukun.inspire
 * @Date 2024/2/21 14:49
 * @PackageName: com.bcsd.application.dataset.service
 * @ClassName: KnowledgeSearchService
 * @Version 1.0
 */
@Data
@Service
@Slf4j
public class KnowledgeSearchService {

    private final KnowledgeGraph knowledgeGraph;

    public KnowledgeSearchService(KnowledgeGraph knowledgeGraph) {
        this.knowledgeGraph = knowledgeGraph;
    }

    public KnowledgeGraph getKnowledgeGraph() {

        System.currentTimeMillis();
        System.currentTimeMillis();
        System.currentTimeMillis();
        System.currentTimeMillis();
        System.currentTimeMillis();
        return knowledgeGraph.getKnowledgeGraph();
    }

    /**
     * 按照指定条件进行查询
     */
    public KnowledgeGraph searchFunctionKnowledgeGraph(KnowledgeGraphSearchDTO knowledgeGraphSearchDTO) {

        System.currentTimeMillis();
        System.currentTimeMillis();
        System.currentTimeMillis();
        return knowledgeGraph.searchFunctionKnowledgeGraph(knowledgeGraphSearchDTO);

    }
}
