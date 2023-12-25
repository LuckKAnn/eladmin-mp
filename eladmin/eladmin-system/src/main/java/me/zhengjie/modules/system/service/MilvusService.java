package me.zhengjie.modules.system.service;

import me.zhengjie.modules.system.FuncCodeInfoDTO;
import me.zhengjie.modules.system.domain.MilvusData;
import me.zhengjie.modules.system.domain.ScoreVO;
import me.zhengjie.modules.system.domain.SearchResultDTO;
import me.zhengjie.modules.system.domain.SimilarityData;

import java.util.List;

public interface MilvusService {


    public SearchResultDTO search(byte[] arcsoftFeature);

    List<SimilarityData> search(List<List<Float>> vector);

    public Long countVector();


    MilvusData getById(Long id);

    List<MilvusData> getByIds(List<Long> id);

    void insertVector(FuncCodeInfoDTO funcCodeInfoDTO, List<List<Float>> funcVector);

    public List<ScoreVO> doCaculateCosineSimilarity(List<List<Float>> detectVectorResult);
}
