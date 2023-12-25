package me.zhengjie.modules.system.rest;

import me.zhengjie.modules.system.service.GraphService;
import me.zhengjie.modules.system.service.neo4j.DatasetFuncNode;
import me.zhengjie.modules.system.service.neo4j.FaultNode;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @Author liukun.inspire
 * @Date 2023/12/19 19:59
 * @PackageName: me.zhengjie.modules.system.rest
 * @ClassName: GraphController
 * @Version 1.0
 */
@RestController
@RequestMapping("/graph")
public class GraphController {


    private final GraphService graphService;

    public GraphController(GraphService graphService) {
        this.graphService = graphService;
    }

    @GetMapping("/list")
    public List<DatasetFuncNode> getAllData() {
        // List<FuncNode> allGraph = graphService.getAllGraph();
        List<DatasetFuncNode> allDataSet = graphService.getAllDataSet();
        return new ArrayList<>(allDataSet);
    }
}
