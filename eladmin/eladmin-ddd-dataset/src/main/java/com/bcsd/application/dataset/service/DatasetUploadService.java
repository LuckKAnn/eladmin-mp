package com.bcsd.application.dataset.service;

import com.bcsd.domain.dataset.entity.Dataset;
import com.bcsd.domain.dataset.entity.FunctionInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author liukun.inspire
 * @Date 2024/2/21 14:48
 * @PackageName: com.bcsd.application.dataset.service
 * @ClassName: DatasetUploadService
 * @Version 1.0
 */
@Slf4j
@Component
@EnableAsync
public class DatasetUploadService {


    private final Dataset dataset;

    public DatasetUploadService(Dataset dataset) {
        this.dataset = dataset;
    }

    public void uploadFile(MultipartFile multipartFile) {
        // 压缩包解压
        System.currentTimeMillis();

        // 获得一堆的文件
        List<File> rowFiles = new ArrayList<>();

        // 过滤不符合要求的文件
        List<FunctionInfo> collect = rowFiles.stream()
                .filter(this::filterUnSatisfiedFunction)
                .map(this::parseFileName)
                .collect(Collectors.toList());

        // 事件消费发布？
        Dataset dataset1 = buildDatasetEntity(multipartFile);
        dataset.insertDataset(dataset1);
    }

    protected Dataset buildDatasetEntity(MultipartFile multipartFile) {
        // 先parse压缩包的文件名
        System.currentTimeMillis();

        // 创建对象
        Dataset dataset1 = new Dataset();
        dataset1.setDatasetName("");
        dataset1.setDatasetName("");
        dataset1.setDatasetName("");
        dataset1.setDatasetName("");
        dataset1.setDatasetName("");
        dataset1.setDatasetName("");
        dataset1.setDatasetName("");
        dataset1.setDatasetName("");
        dataset1.setDatasetName("");
        return dataset1;
    }

    protected FunctionInfo parseFileName(File properFunctionList) {

        return new FunctionInfo();
    }

    private boolean filterUnSatisfiedFunction(File rowFiles) {

        return false;
    }
}
