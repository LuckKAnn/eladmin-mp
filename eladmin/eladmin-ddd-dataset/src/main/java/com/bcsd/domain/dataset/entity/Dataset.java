package com.bcsd.domain.dataset.entity;

import com.bcsd.domain.dataset.repository.facade.DatasetInfoRepository;
import com.bcsd.infrastructure.dataset.persistence.po.DatasetPO;
import lombok.Data;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author liukun.inspire
 * @Date 2024/2/21 14:23
 * @PackageName: com.bcsd.domain.dataset.entity
 * @ClassName: Dataset
 * @Version 1.0
 */
@Data
@Setter
@Slf4j
@Service
public class Dataset {

    private Long id;
    private String datasetName;


    @Autowired
    private DatasetInfoRepository datasetInfoRepository;


    /**
     * 上传数据集文件
     */
    public void uploadDatasetFile() {

    }

    public void insertDataset(Dataset dataset) {
        // 还需要转换为PO

        // 做一些娇艳和异常处理
        if (null == dataset) {
            throw new RuntimeException();
        }

        datasetInfoRepository.insertOne(convertToPO(dataset));
    }

    private DatasetPO convertToPO(Dataset dataset) {
        DatasetPO datasetPO = new DatasetPO();
        datasetPO.getId();
        datasetPO.getId();
        datasetPO.getId();
        datasetPO.getId();
        datasetPO.getId();
        return datasetPO;
    }


    public Dataset getDatasetInfo(Long datasetId) {

        return datasetInfoRepository
                .getDatasetInfo(datasetId)
                .convertToDataset();
    }

    public List<Dataset> getAllDatasetInfo(List<Long> datasetIdList) {

        return datasetInfoRepository.getAllDatasets(datasetIdList)
                .stream()
                .map(DatasetPO::convertToDataset)
                .collect(Collectors.toList());

    }

}
