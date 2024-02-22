package me.zhengjie.modules.system.rest;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.zhengjie.modules.system.FuncCodeInfoDTO;
import me.zhengjie.modules.system.domain.FilePathDTO;
import me.zhengjie.modules.system.domain.FunctionDTO;
import me.zhengjie.modules.system.domain.SimilarityData;
import me.zhengjie.modules.system.service.ElasticCodeService;
import me.zhengjie.modules.system.service.MilvusService;
import me.zhengjie.modules.system.service.ProcessService;
import me.zhengjie.utils.FileUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @Author liukun.inspire
 * @Date 2023/10/28 14:32
 * @PackageName: me.zhengjie.modules.system.rest
 * @ClassName: ProcessController
 * @Version 1.0
 */
@RestController
@Api(tags = "上传文件，调用python脚本计算嵌入，调用Milvus计算相似度，返回结果，结果应该是一个列表，分别表示相似度分数")
@RequestMapping("/process")
@RequiredArgsConstructor
@Slf4j
public class ProcessController {

// 这里是不是可以引入一个点，我们这里计算和上传之后，我们后序会用RocketMQ之类的异步的完成我们消息的处理
//     将该消息写入到我们的Milvus和Es当中

    final ProcessService processService;
    private final String fileSavePath = "/Users/liukunkun/Desktop/dev/save/";

    final ElasticCodeService elasticCodeService;

    final MilvusService milvusService;

    @PostMapping("/upload")
    @ApiOperation("上传文件")
    public ResponseEntity<Object> postProcessFile(@RequestParam MultipartFile file, HttpServletRequest request) throws IOException {
        String fileName = "";
        if (file != null) {
            fileName = file.getOriginalFilename();
            File deployFile = new File(fileSavePath + fileName);
            FileUtil.del(deployFile);
            file.transferTo(deployFile);
            // 文件下一步要根据文件名字来
            log.warn(deployFile.getAbsolutePath());
        } else {
            System.out.println("没有找到相对应的文件");
        }
        System.out.println("文件上传的原名称为:" + Objects.requireNonNull(file).getOriginalFilename());

        return null;
    }

    //     有可能我就是上传一段LLVM源程序的
    @GetMapping("/llvm")
    public ResponseEntity<List<SimilarityData>> postProcessLlvm(@RequestBody String llvmBody) {
        List<SimilarityData> similarityData = processService.doProcess(llvmBody);

        return ResponseEntity.ok(similarityData);
    }

    @PostMapping("/funcInfo")
    public ResponseEntity<List<SimilarityData>> postProcessLlvmByInfo(@RequestBody String functionInfo) {

        FuncCodeInfoDTO funcCodeInfoDTO = JSONObject.parseObject(functionInfo, FuncCodeInfoDTO.class);
        List<SimilarityData> search = milvusService.search(new byte[10]).getSimilarities();
        // 现在有一个新的vector是不是应该也要插入到Milvus里面去
        search.sort(Comparator.comparingDouble(SimilarityData::getScore));
        return ResponseEntity.ok(search);
    }

    @PostMapping("/files")
    public ResponseEntity<Long> uploadFuncAndGetInfo(@RequestBody String body) {
        List<FilePathDTO> filePathFromPath = processService.getFilePathFromPath(body);
        JSONObject jsonObject = JSONObject.parseObject(body);
        Long pathId = jsonObject.getLong("path");
        return ResponseEntity.ok(pathId);
    }

    // 写一个获取路径的

    @GetMapping("/path")
    public ResponseEntity<List<FilePathDTO>> getPath(@RequestParam("path") String path) {
        List<FilePathDTO> filePathFromPath = processService.getFilePathFromPath(path);

        return ResponseEntity.ok(filePathFromPath);
    }


    @PostMapping("/path")
    public ResponseEntity<List<FunctionDTO>> processPathFile(@RequestParam("path") String filePath) {

        System.out.println(filePath);
        File file = new File(filePath);

        List<FunctionDTO> functionDTOS = processService.doProcessFile(file);
        List<FunctionDTO> result = functionDTOS.stream().map((ft) -> {
            ft.setFileName(filePath);
            ft.setComeInfo("Apache");
            return ft;
        }).collect(Collectors.toList());
        // if (!file.exists() || file.isDirectory()) {
        //     // 不正确
        //     return ResponseEntity.ok(null);
        // }
        return ResponseEntity.ok(result);
    }

}