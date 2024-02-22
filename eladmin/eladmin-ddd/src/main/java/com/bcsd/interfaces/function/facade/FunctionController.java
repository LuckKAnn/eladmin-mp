package com.bcsd.interfaces.function.facade;

import com.bcsd.application.function.service.FunctionInfoSearchService;
import com.bcsd.application.function.service.FunctionUploadService;
import com.bcsd.domain.function.entity.Function;
import com.bcsd.interfaces.function.assembler.FunctionInfoAssembler;
import com.bcsd.interfaces.function.dto.FunctionInfoDTO;
import com.bcsd.interfaces.function.dto.FunctionSearchDTO;
import com.bcsd.interfaces.function.dto.SearchResultDTO;
import com.bcsd.interfaces.function.dto.SingleFunctionDTO;
import io.swagger.annotations.ApiOperation;
import me.zhengjie.utils.PageResult;
import me.zhengjie.utils.PageUtil;
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
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author liukun.inspire
 * @Date 2024/2/21 19:40
 * @PackageName: com.bcsd.interfaces.function.facade
 * @ClassName: FunctionController
 * @Version 1.0
 */
@RestController
@RequestMapping("/func")
public class FunctionController {

    private final FunctionInfoSearchService functionInfoSearchService;

    private final FunctionUploadService functionUploadService;


    public FunctionController(FunctionInfoSearchService functionInfoSearchService, FunctionUploadService functionUploadService) {
        this.functionInfoSearchService = functionInfoSearchService;
        this.functionUploadService = functionUploadService;
    }

    @ApiOperation("分页查询函数列表")
    @GetMapping("/list")
    public ResponseEntity<PageResult<FunctionInfoDTO>> getFunByPage(@RequestParam("page") int pageId, @RequestParam("size") Integer size) {
        List<Function> functions = functionInfoSearchService.batchQueryFunctionInfo(pageId, size);

        List<FunctionInfoDTO> all = functions.stream().map(FunctionInfoAssembler::convertToDTO).collect(Collectors.toList());

        long functionNumber = functionInfoSearchService.getFunctionNumber();
        return ResponseEntity.ok(PageUtil.toPage(
                all,
                functionNumber
        ));
    }

    @ApiOperation("根据Id查询函数的代码")
    @GetMapping("/code")
    public ResponseEntity<FunctionInfoDTO> getFuncCodeById(@RequestParam("id") String id) {

        Function function = functionInfoSearchService.queryFunctionInfo(Long.valueOf(id));
        return ResponseEntity.ok(FunctionInfoAssembler.convertToDTO(function));
    }


    @ApiOperation("根据函数名称查询函数代码")
    @GetMapping("/name")
    public ResponseEntity<List<FunctionInfoDTO>> getFunctionByName(@RequestBody FunctionSearchDTO searchDTO) {

        List<Function> functions = functionInfoSearchService.searchFunctionInfo(searchDTO);

        List<FunctionInfoDTO> collect = functions.stream().map(FunctionInfoAssembler::convertToDTO).collect(Collectors.toList());
        return ResponseEntity.ok(collect);
    }

    @PostMapping("/function/upload")
    @ApiOperation("上传")
    public ResponseEntity<Void> uploadFunctionDataset(@RequestParam MultipartFile file, HttpServletRequest request) throws IOException {

        // datasetUploadService;
        try {
            // functionInfoSearchService.uploadFile(file);
            return ResponseEntity.ok(null);
        } catch (Exception e) {
            return (ResponseEntity<Void>) ResponseEntity.badRequest();
        }
    }

    @PostMapping("/function/single")
    @ApiOperation("处理单函数上传")
    public ResponseEntity<SearchResultDTO> uploadFunctionDataset(@RequestParam SingleFunctionDTO singleFunctionDTO) throws IOException {
        // 各州校验？
        SearchResultDTO searchResultDTO = functionUploadService.doProcessSingleFunctionUpload(singleFunctionDTO);

        return ResponseEntity.ok(searchResultDTO);
    }

}
