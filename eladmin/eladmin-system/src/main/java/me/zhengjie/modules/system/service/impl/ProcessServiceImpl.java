package me.zhengjie.modules.system.service.impl;

import lombok.RequiredArgsConstructor;
import me.zhengjie.Utils.PythonUtils;
import me.zhengjie.modules.system.domain.SimilarityData;
import me.zhengjie.modules.system.service.MilvusService;
import me.zhengjie.modules.system.service.ProcessService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * @Author liukun.inspire
 * @Date 2023/10/28 14:38
 * @PackageName: me.zhengjie.modules.system.service.impl
 * @ClassName: ProcessServiceImpl
 * @Version 1.0
 */
@Service
@RequiredArgsConstructor
public class ProcessServiceImpl implements ProcessService {


    final MilvusService milvusService;

    final String SCRIPT_PATH = "";

    @Override
    public List<SimilarityData> doProcess(String code) {

        // 假设已经有了code

        // 到底是传一个文件好呢，还是传一个code比较方便
        PythonUtils.ScriptParam scriptParam = new PythonUtils.ScriptParam();
        String codeVector = null;
        try {
            codeVector = PythonUtils.runPythonScript(SCRIPT_PATH, Collections.singletonList(scriptParam));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // codeVector 解码吗
        List<List<Float>> vector = parseVector(codeVector);


        return null;
    }

    private List<List<Float>> parseVector(String codeVector) {

        return null;
    }
}
