package me.zhengjie.modules.system.service;

import me.zhengjie.modules.system.domain.MilvusData;
import me.zhengjie.modules.system.domain.SimilarityData;

import java.util.List;

public interface MilvusService {


    public List<SimilarityData> search(byte[] arcsoftFeature);

    List<SimilarityData> search(List<List<Float>> vector);

    public Long countVector();


    MilvusData getById(Long id);

}
