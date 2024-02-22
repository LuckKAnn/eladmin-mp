package com.bcsd.interfaces.function.facade;

import com.bcsd.application.dataset.service.SystemCounterService;
import com.bcsd.domain.dataset.entity.Dataset;
import com.bcsd.domain.dataset.entity.SystemCounterData;
import com.bcsd.interfaces.function.assembler.DatasetAssembler;
import com.bcsd.interfaces.function.assembler.SystemDataAssembler;
import com.bcsd.interfaces.function.dto.DatasetDTO;
import com.bcsd.interfaces.function.dto.SystemCounterDataDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author liukun.inspire
 * @Date 2024/2/21 14:52
 * @PackageName: com.bcsd.interfaces.function.facade
 * @ClassName: SystemCounterDataContoller
 * @Version 1.0
 */

@RestController
@Slf4j
@RequestMapping("/system")
public class SystemCounterDataController {


    private final SystemCounterService systemCounterService;

    public SystemCounterDataController(SystemCounterService systemCounterService) {
        this.systemCounterService = systemCounterService;
    }

    @GetMapping("/data")
    public ResponseEntity<SystemCounterDataDTO> getSystemCounterData() {
        SystemCounterData systemCounterData = systemCounterService.querySystemData();

        return new ResponseEntity<>(
                SystemDataAssembler.do2DTO(systemCounterData), HttpStatus.OK);
    }

}
