package me.zhengjie.modules.system.service;

import me.zhengjie.modules.system.service.neo4j.DatasetFuncNode;
import me.zhengjie.modules.system.service.neo4j.FuncNode;

import java.util.List;

/**
 * @Author liukun.inspire
 * @Date 2023/12/19 20:00
 * @PackageName: me.zhengjie.modules.system.service
 * @ClassName: GraphService
 * @Version 1.0
 */
public interface GraphService {

    List<FuncNode> getAllGraph();

    List<DatasetFuncNode> getAllDataSet();
}
