package com.bcsd.interfaces.function.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author liukun.inspire
 * @Date 2024/2/21 22:24
 * @PackageName: com.bcsd.interfaces.function.dto
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
