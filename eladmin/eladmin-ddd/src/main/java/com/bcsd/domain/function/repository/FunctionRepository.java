package com.bcsd.domain.function.repository;

import com.bcsd.domain.function.entity.FunctionInfoPO;
import com.bcsd.domain.function.entity.FunctionVectorPO;
import com.bcsd.interfaces.function.dto.SimilarityData;

import java.util.List;

/**
 * @Author liukun.inspire
 * @Date 2024/2/21 13:59
 * @PackageName: com.bcsd.domain.function.repository
 * @ClassName: FunctionRepository
 * @Version 1.0
 */
public interface FunctionRepository {


    // 单查询，批量查询？

    List<FunctionInfoPO> listQueryFunctionInfo(List<Long> functionIdList);


    List<FunctionInfoPO> pageQueryFunctionInfo(int pageIndex, int size);


    FunctionInfoPO queryFunctionInfo(Long functionId);


    /**
     * search的方式?
     *
     * @return
     */
    List<FunctionInfoPO> searchFunctionInfo();


    long countFunctionNumber();

    /**
     * @param search_vectors
     * @param collectionIndexName
     * @param SEARCH_PARAM
     */
    public List<SimilarityData> doSimilarityDetectionWithCosine(List<List<Float>> search_vectors
            , String collectionIndexName, String SEARCH_PARAM);
}
