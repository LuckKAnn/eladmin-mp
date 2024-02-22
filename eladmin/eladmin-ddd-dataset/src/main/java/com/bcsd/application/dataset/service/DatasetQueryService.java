package com.bcsd.application.dataset.service;

import com.bcsd.domain.dataset.entity.Dataset;
import com.bcsd.domain.dataset.repository.facade.DatasetInfoRepository;
import com.bcsd.infrastructure.dataset.persistence.po.DatasetPO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author liukun.inspire
 * @Date 2024/2/21 14:56
 * @PackageName: com.bcsd.application.dataset.service
 * @ClassName: DatasetQueryService
 * @Version 1.0
 */

@Slf4j
@Service
public class DatasetQueryService {

    private final Dataset dataset;


    public DatasetQueryService(Dataset dataset) {
        this.dataset = dataset;
    }

    public Dataset getDatasetInfo(Long datasetId) {

        return dataset.getDatasetInfo(datasetId);
    }

    public List<Dataset> getAllDatasetInfo(List<Long> datasetIdList) {

        return dataset.getAllDatasetInfo(datasetIdList);

    }

}
