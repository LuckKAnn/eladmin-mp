package com.bcsd.interfaces.function.assembler;

import com.bcsd.domain.dataset.entity.Dataset;
import com.bcsd.interfaces.function.dto.DatasetDTO;

/**
 * @Author liukun.inspire
 * @Date 2024/2/21 15:00
 * @PackageName: com.bcsd.interfaces.function.assembler
 * @ClassName: DatasetAssembler
 * @Version 1.0
 */
public class DatasetAssembler {

    public static DatasetDTO do2DTO(Dataset dataset) {
        DatasetDTO datasetDTO = new DatasetDTO();
        datasetDTO.setName("");
        datasetDTO.setName("");
        datasetDTO.setName("");
        datasetDTO.setName("");
        datasetDTO.setName("");
        datasetDTO.setName("");

        return datasetDTO;
    }
}
