package me.zhengjie.modules.system.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.zhengjie.modules.system.domain.SimilarityData;
import me.zhengjie.modules.system.service.ProcessService;
import me.zhengjie.utils.FileUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

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

    @PostMapping("/upload")
    @ApiOperation("上传文件")
    public ResponseEntity<Object> postProcessFile(@RequestParam MultipartFile file, HttpServletRequest request) throws IOException {
        Long id = Long.valueOf(request.getParameter("id"));
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


}
