package com.bcsd.infrastructure.dataset.persistence.po;

import com.bcsd.domain.dataset.entity.Dataset;

/**
 * @Author liukun.inspire
 * @Date 2024/2/21 14:40
 * @PackageName: com.bcsd.infrastructure.dataset.persistence.po
 * @ClassName: DatasetPO
 * @Version 1.0
 */
public class DatasetPO {

    private Long id;

    private String datasetName;


    public Long getId() {
        return id;
    }

    /**
     * @param id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    public String getDatasetName() {
        return datasetName;
    }

    /**
     * @param datasetName to set
     */
    public void setDatasetName(String datasetName) {
        this.datasetName = datasetName;
    }

    public Dataset convertToDataset() {
        return new Dataset();
    }
}
