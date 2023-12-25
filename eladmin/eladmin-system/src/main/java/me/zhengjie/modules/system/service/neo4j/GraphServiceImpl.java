package me.zhengjie.modules.system.service.neo4j;

import me.zhengjie.modules.system.service.GraphService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author liukun.inspire
 * @Date 2023/12/19 20:00
 * @PackageName: me.zhengjie.modules.system.service.neo4j
 * @ClassName: GraphServiceImpl
 * @Version 1.0
 */
@Service
public class GraphServiceImpl implements GraphService {

    private final FunctionDataserRepository functionDataserRepository;

    private final DatasetRepository datasetRepository;

    public GraphServiceImpl(FunctionDataserRepository functionDataserRepository, DatasetRepository datasetRepository) {
        this.functionDataserRepository = functionDataserRepository;
        this.datasetRepository = datasetRepository;
    }

    @Override
    public List<FuncNode> getAllGraph() {
        return functionDataserRepository.findAll();
        // return functionDataserRepository.findByTitle();
    }

    @Override
    public List<DatasetFuncNode> getAllDataSet() {
        return datasetRepository.findAll();
    }
}
