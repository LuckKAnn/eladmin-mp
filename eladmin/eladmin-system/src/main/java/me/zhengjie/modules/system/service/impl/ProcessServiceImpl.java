package me.zhengjie.modules.system.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import io.milvus.param.R;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.zhengjie.Utils.PythonUtils;
import me.zhengjie.Utils.RandomInfoUtils;
import me.zhengjie.modules.system.domain.FilePathDTO;
import me.zhengjie.modules.system.domain.FunctionDTO;
import me.zhengjie.modules.system.domain.SemanticVectorInfo;
import me.zhengjie.modules.system.domain.SimilarityData;
import me.zhengjie.modules.system.service.MilvusService;
import me.zhengjie.modules.system.service.ProcessService;
import org.apache.commons.compress.utils.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.util.*;

/**
 * @Author liukun.inspire
 * @Date 2023/10/28 14:38
 * @PackageName: me.zhengjie.modules.system.service.impl
 * @ClassName: ProcessServiceImpl
 * @Version 1.0
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class ProcessServiceImpl implements ProcessService {


    final MilvusService milvusService;

    final String SCRIPT_PATH = "";

    @Autowired
    RestTemplate restTemplate;

    private final String CODE_PROCESS_URL = "10.129.218.54:5000/api/model";

    private final String FILE_PARTITION = "10.129.218.54:5000/api/partition";

    @Override
    public List<SimilarityData> doProcess(String code) {
        // 假设已经有了code
        // 到底是传一个文件好呢，还是传一个code比较方便
        // 直接调用接口就行了吧。
        SemanticVectorInfo semanticVectorInfo = restTemplate.postForObject(CODE_PROCESS_URL, code, SemanticVectorInfo.class);
        //
        //
        // PythonUtils.ScriptParam scriptParam = new PythonUtils.ScriptParam();
        // String codeVector = null;
        // try {
        //     codeVector = PythonUtils.runPythonScript(SCRIPT_PATH, Collections.singletonList(scriptParam));
        // } catch (Exception e) {
        //     log.error("exp ", JSONObject.toJSONString(e.getMessage()));
        //     // throw new RuntimeException(e);
        // }
        //
        // // codeVector 解码吗
        // List<List<Float>> vector = parseVector(codeVector);


        return null;
    }

    @Override
    public List<FilePathDTO> getFilePathFromPath(String path) {
        log.info("get path:" + path);
        // path = "/Users/liukunkun/Downloads/mockdata/apache/";
        File file = new File(path);
        FilePathDTO filePathDTO = traversePath(file);
        return Collections.singletonList(filePathDTO);
    }

    @Override
    public List<FunctionDTO> doProcessFile(File file) {
        String fileName = file.getName();
        if (fileName.endsWith(".ll")) {
            // 已经编译成为LL文件，直接调用python脚本完成功能，
            // 首先拆分为函数，其实每个函数都归属于某个文件
            // 最好给这个file生成一个UUID，然后传输过去？
            //     那边生成之后，所有的函数都要写入到ES内部去，每个函数还要走模型吗
            //     这个地方不需要处理函数的名称
            // 获取到所有的函数信息
            String functionDTOJson = restTemplate.postForObject(FILE_PARTITION, file.getAbsolutePath(), String.class);
            List<FunctionDTO> functionDTOS = JSONObject.parseObject(functionDTOJson, new TypeReference<List<FunctionDTO>>() {
            });
            return functionDTOS;
        }

        return parseFunctionList("");
    }

    private List<FunctionDTO> parseFunctionList(String result) {

        ArrayList<FunctionDTO> objects = Lists.newArrayList();
        Random random = new Random();
        for (int i = 0; i < 15; i++) {
            FunctionDTO functionDTO = new FunctionDTO();
            functionDTO.setFunctionName(RandomInfoUtils.getRandomFunctionName());
            functionDTO.setCodeInfo("from file...");
            functionDTO.setCodeLine((long) i);
            functionDTO.setComment("函数本来的注释信息");
            functionDTO.setCompilable(true);
            objects.add(functionDTO);
        }
        Collections.sort(objects, (t1, t2) -> {
            Long num1 = t1.getCodeLine();
            Long num2 = t2.getCodeLine();
            if ((num1 < 5 || num1 > 255) && (num2 >= 5 && num2 <= 255)) {
                return 1; // num1放在后面
            } else if ((num2 < 5 || num2 > 255) && (num1 >= 5 && num1 <= 255)) {
                return -1; // num2放在后面
            } else {
                return num1.compareTo(num2); // 其他情况按正常顺序排序
            }
        });
        return objects;

    }

    private FilePathDTO traversePath(File filePath) {
        FilePathDTO filePathDTO = new FilePathDTO();
        filePathDTO.setPathName(filePath.getName());
        filePathDTO.setDir(true);
        ArrayList<FilePathDTO> children = Lists.newArrayList();
        filePathDTO.setChildren(children);
        File[] files = filePath.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    FilePathDTO c1 = traversePath(file);
                    children.add(c1);
                } else {
                    FilePathDTO fileC = new FilePathDTO();
                    fileC.setDir(false);
                    fileC.setPathName(file.getName());
                    children.add(fileC);
                }
            }
        }
        return filePathDTO;
    }

    private List<List<Float>> parseVector(String codeVector) {

        return null;
    }
}
