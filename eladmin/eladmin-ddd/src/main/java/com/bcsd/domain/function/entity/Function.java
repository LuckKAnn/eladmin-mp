package com.bcsd.domain.function.entity;

import com.bcsd.domain.function.repository.FunctionRepository;
import com.bcsd.interfaces.function.dto.FunctionSearchDTO;
import com.bcsd.interfaces.function.dto.ScoreVO;
import com.bcsd.interfaces.function.dto.SearchResultDTO;
import com.bcsd.interfaces.function.dto.SimilarityData;
import io.milvus.grpc.SearchResultData;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import me.zhengjie.utils.CaculateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 聚合根和实体
 *
 * @Author liukun.inspire
 * @Date 2024/2/21 13:48
 * @PackageName: com.bcsd.domain.function.entity
 * @ClassName: Function
 * @Version 1.0
 */
@Data
@Component
@Slf4j
public class Function {


    private String name;

    public final String SEARCH_PARAM = "{\"nprobe\":10, \"offset\":5}";    // Params
    public final String FUNC_EMBEDING_INDEX = "binaryhouseT3";

    @Autowired
    private FunctionRepository functionRepository;


    /**
     * 上传单函数
     */
    public void uploadSingleFunctionProcess() {

    }

    /**
     * 上传exe的处理
     */
    public void uploadExeProcess() {

    }

    public Function functionInfoQuery(Long functionId) {
        FunctionInfoPO functionVectorPO = functionRepository.queryFunctionInfo(functionId);
        return functionVectorPO.convertToDO();
    }

    public List<Function> batchFunctionInfoQuery(List<Long> functionIds) {
        return functionRepository.listQueryFunctionInfo(functionIds)
                .stream()
                .map(FunctionInfoPO::convertToDO)
                .collect(Collectors.toList());
    }

    public List<Function> batchFunctionInfoQuery(int page, int size) {
        return functionRepository.pageQueryFunctionInfo(page, size)
                .stream()
                .map(FunctionInfoPO::convertToDO)
                .collect(Collectors.toList());
    }

    public List<Function> functionSearch(FunctionSearchDTO functionSearchDTO) {
        return functionRepository.searchFunctionInfo()
                .stream()
                .map(FunctionInfoPO::convertToDO)
                .collect(Collectors.toList());
    }


    public void functionVectorSearch() {

    }

    public long countFunctions() {
        return functionRepository.countFunctionNumber();
    }


    public SearchResultDTO doSimilarityDetection(List<List<Float>> search_vectors) {
        List<SimilarityData> objects = functionRepository.doSimilarityDetectionWithCosine(search_vectors, FUNC_EMBEDING_INDEX, SEARCH_PARAM);
        List<Float> originVector = new ArrayList<>();
        List<List<Float>> detectVectorResult = new ArrayList<>();

        detectVectorResult.add(0, originVector);
        double[][] tsneVector = doDimensionalityReduction(detectVectorResult, null);

        objects.sort((t1, t2) -> (int) (t1.getScore() - t2.getScore()));
        List<ScoreVO> scoreVOList = doCaculateCosineSimilarity(detectVectorResult);
        return new SearchResultDTO(objects, scoreVOList, tsneVector);
    }

    private List<ScoreVO> doCaculateCosineSimilarity(List<List<Float>> detectVectorResult) {
        List<ScoreVO> scoreVOList = new ArrayList<>();
        for (int i = 0; i < detectVectorResult.size(); i++) {
            for (int j = 0; j < detectVectorResult.size(); j++) {
                List<Float> floats = detectVectorResult.get(i);
                float result = CaculateUtils.calculateCosineSimilarity(detectVectorResult.get(i).toArray(floats.toArray(new Float[0])), detectVectorResult.get(j).toArray(floats.toArray(new Float[0])));
                ScoreVO scoreVO = new ScoreVO();
                scoreVO.setX(i);
                scoreVO.setY(j);
                scoreVO.setScore(result);
                scoreVOList.add(scoreVO);
            }
        }
        return scoreVOList;
    }

    private double[][] doDimensionalityReduction(List<List<Float>> detectVectorResult, List<Float> originVector) {
        int numRows = detectVectorResult.size();
        int numCols = detectVectorResult.get(0).size();
        double[][] doubleArray = new double[numRows][numCols];
        for (int i = 0; i < numRows; i++) {
            List<Float> row = detectVectorResult.get(i);
            for (int j = 0; j < numCols; j++) {
                Float aFloat = row.get(j);
                double v = aFloat.doubleValue();
                doubleArray[i][j] = v;
            }
        }
        int initial_dims = 768;
        double perplexity = 1.0;
        // TSneConfiguration config = .buildConfig(doubleArray, 2, initial_dims, perplexity, 1000);
        // double[][] y = tSne.tsne(config);
        double[][] y = new double[1][1];
        return y;
    }
}
