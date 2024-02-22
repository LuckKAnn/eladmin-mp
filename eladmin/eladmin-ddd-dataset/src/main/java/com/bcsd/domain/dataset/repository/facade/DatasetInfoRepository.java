package com.bcsd.domain.dataset.repository.facade;

import com.bcsd.infrastructure.dataset.persistence.po.DatasetPO;

import java.util.List;

/**
 * @Author liukun.inspire
 * @Date 2024/2/21 14:28
 * @PackageName: com.bcsd.domain.dataset.repository.facade
 * @ClassName: DatasetInfoRepository
 * @Version 1.0
 */
public interface DatasetInfoRepository {

    DatasetPO getDatasetInfo(Long datasetId);

    List<DatasetPO> getAllDatasets(List<Long> datasetIdList);

    void insertOne(DatasetPO datasetPO);

}
