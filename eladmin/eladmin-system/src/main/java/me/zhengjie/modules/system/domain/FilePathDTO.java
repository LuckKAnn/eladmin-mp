package me.zhengjie.modules.system.domain;

import lombok.Data;

import java.util.List;

/**
 * @Author liukun.inspire
 * @Date 2023/10/29 14:50
 * @PackageName: me.zhengjie.modules.system.domain
 * @ClassName: FilePathDTO
 * @Version 1.0
 */
@Data
public class FilePathDTO {


    private String pathName;

    private boolean isDir;

    private List<FilePathDTO> children;

}
