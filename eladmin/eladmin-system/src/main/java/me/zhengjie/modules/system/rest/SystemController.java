package me.zhengjie.modules.system.rest;

import me.zhengjie.modules.system.domain.Dataset;
import me.zhengjie.modules.system.domain.Menu;
import me.zhengjie.modules.system.domain.vo.MenuQueryCriteria;
import me.zhengjie.modules.system.service.SystemService;
import me.zhengjie.utils.PageResult;
import me.zhengjie.utils.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.crypto.Data;
import java.util.List;

/**
 * @Author liukun.inspire
 * @Date 2023/12/25 20:21
 * @PackageName: me.zhengjie.modules.system.rest
 * @ClassName: SystemController
 * @Version 1.0
 */
@RestController
@RequestMapping("/api/system")
public class SystemController {

    @Autowired
    private SystemService systemService;


    @GetMapping("/dataset")
    public ResponseEntity<PageResult<Dataset>> getDataset() throws Exception {
        List<Dataset> datasets = systemService.getDatasets();
        return new ResponseEntity<>(PageUtil.toPage(datasets), HttpStatus.OK);
    }

    @GetMapping("/dataset/find")
    public ResponseEntity<Dataset> getDatasetById(Long id) {
        return new ResponseEntity<>(systemService.getById(id), HttpStatus.OK);
    }
}
