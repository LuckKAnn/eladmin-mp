package me.zhengjie.modules.system.service;

import me.zhengjie.modules.system.domain.FilePathDTO;
import me.zhengjie.modules.system.domain.FunctionDTO;
import me.zhengjie.modules.system.domain.SimilarityData;

import java.io.File;
import java.util.List;

/**
 * @Author liukun.inspire
 * @Date 2023/10/28 14:38
 * @PackageName: me.zhengjie.modules.system.service
 * @ClassName: ProcessService
 * @Version 1.0
 */
public interface ProcessService {


    /**
     * 这个就可以用来上传LLVM的代码了
     * 没必要前期就把一些对象的数据全部都给传过来吧
     * 但是你在展示结果的时候是不是就得用到这部分的数据
     *
     * @param code
     * @return
     */
    public List<SimilarityData> doProcess(String code);


    List<FilePathDTO> getFilePathFromPath(String path);

    List<FunctionDTO> doProcessFile(File file);


}
