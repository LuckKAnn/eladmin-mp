package com.bcsd.interfaces.function.facade;

import com.alipay.api.domain.Datas;
import com.bcsd.application.dataset.service.DatasetQueryService;
import com.bcsd.application.dataset.service.DatasetUploadService;
import com.bcsd.application.dataset.service.KnowledgeSearchService;
import com.bcsd.domain.dataset.entity.Dataset;
import com.bcsd.domain.dataset.entity.KnowledgeGraph;
import com.bcsd.interfaces.function.assembler.DatasetAssembler;
import com.bcsd.interfaces.function.assembler.KnowledgeGraphAssembler;
import com.bcsd.interfaces.function.dto.DatasetDTO;
import com.bcsd.interfaces.function.dto.KnowledgeGraphDTO;
import com.bcsd.interfaces.function.dto.KnowledgeGraphSearchDTO;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import me.zhengjie.utils.PageResult;
import me.zhengjie.utils.PageUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author liukun.inspire
 * @Date 2024/2/21 14:52
 * @PackageName: com.bcsd.interfaces.function.facade
 * @ClassName: DatasetController
 * @Version 1.0
 */

@RestController
@Slf4j
@RequestMapping("/dataset")
public class DatasetController {


    private final DatasetQueryService datasetQueryService;

    private final KnowledgeSearchService knowledgeSearchService;

    private final DatasetUploadService datasetUploadService;

    public DatasetController(DatasetQueryService datasetQueryService, KnowledgeSearchService knowledgeSearchService,
                             DatasetUploadService datasetUploadService) {
        this.datasetQueryService = datasetQueryService;
        this.knowledgeSearchService = knowledgeSearchService;
        this.datasetUploadService = datasetUploadService;
    }

    @PostMapping("/dataset/upload")
    @ApiOperation("上传")
    public ResponseEntity<Void> uploadDataset(@RequestParam MultipartFile file, HttpServletRequest request) throws IOException {

        // datasetUploadService;
        try {
            datasetUploadService.uploadFile(file);
            return ResponseEntity.ok(null);
        } catch (Exception e) {
            return (ResponseEntity<Void>) ResponseEntity.badRequest();
        }
    }

    /**
     * 这个的分页参数呢
     *
     * @return
     * @throws Exception
     */
    @GetMapping("/dataset")
    @ApiOperation("上传")

    public ResponseEntity<PageResult<DatasetDTO>> getDataset() throws Exception {
        List<Dataset> datasets = datasetQueryService.getAllDatasetInfo(Collections.emptyList());

        List<DatasetDTO> result = datasets.stream().map(DatasetAssembler::do2DTO).collect(Collectors.toList());

        return new ResponseEntity<>(PageUtil.toPage(result), HttpStatus.OK);
    }

    @GetMapping("/dataset/find")
    @ApiOperation("上传")
    public ResponseEntity<DatasetDTO> getDatasetById(Long id) {
        Dataset datasetInfo = datasetQueryService.getDatasetInfo(id);

        return new ResponseEntity<>(DatasetAssembler.do2DTO(datasetInfo), HttpStatus.OK);
    }

    @GetMapping("/dataset/knowledge")
    @ApiOperation("上传")
    public ResponseEntity<KnowledgeGraphDTO> getKnowledge() {

        KnowledgeGraph knowledgeGraph = knowledgeSearchService.getKnowledgeGraph();

        return new ResponseEntity<>(KnowledgeGraphAssembler.convertToDTO(knowledgeGraph), HttpStatus.OK);
    }

    @PostMapping("/dataset/knowledge/function")
    @ApiOperation("上传")
    public ResponseEntity<KnowledgeGraphDTO> getFunctionKnowledge(@RequestBody KnowledgeGraphSearchDTO searchDTO) {
        KnowledgeGraph knowledgeGraph = knowledgeSearchService.searchFunctionKnowledgeGraph(searchDTO);
        return new ResponseEntity<>(KnowledgeGraphAssembler.convertToDTO(knowledgeGraph), HttpStatus.OK);
    }
}
