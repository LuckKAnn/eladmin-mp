package com.bcsd.domain.dataset.repository.facade;

import com.bcsd.domain.dataset.entity.KnowledgeGraph;
import com.bcsd.infrastructure.dataset.persistence.po.DatasetPO;
import com.bcsd.interfaces.function.dto.KnowledgeGraphSearchDTO;

import java.util.List;

/**
 * @Author liukun.inspire
 * @Date 2024/2/21 14:28
 * @PackageName: com.bcsd.domain.dataset.repository.facade
 * @ClassName: DatasetInfoRepository
 * @Version 1.0
 */
public interface KnowledgeGraphRepository {

    KnowledgeGraph getKnowledgeGraph();

    KnowledgeGraph searchKnowledgeGraphByCondition(KnowledgeGraphSearchDTO searchDTO);


}
