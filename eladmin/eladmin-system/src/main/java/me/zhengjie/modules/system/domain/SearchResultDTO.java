package me.zhengjie.modules.system.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author liukun.inspire
 * @Date 2023/12/19 16:20
 * @PackageName: me.zhengjie.modules.system.domain
 * @ClassName: SearchResultDTO
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchResultDTO {

    private List<SimilarityData> similarities;

    private List<ScoreVO> scoreVOList;

    private double[][] tenseVectors;

}
