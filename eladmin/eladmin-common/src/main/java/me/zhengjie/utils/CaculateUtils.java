package me.zhengjie.utils;

/**
 * @Author liukun.inspire
 * @Date 2023/12/19 16:14
 * @PackageName: me.zhengjie.Utils
 * @ClassName: CaculateUtils
 * @Version 1.0
 */
public class CaculateUtils {
    public static float calculateCosineSimilarity(Float[] vector1, Float[] vector2) {
        // 计算向量的点积
        float dotProduct = 0;
        for (int i = 0; i < vector1.length; i++) {
            dotProduct += vector1[i] * vector2[i];
        }

        // 计算向量的模（长度）
        float normVector1 = calculateNorm(vector1);
        float normVector2 = calculateNorm(vector2);

        // 计算余弦相似度
        float cosineSimilarity = dotProduct / (normVector1 * normVector2);

        return cosineSimilarity;
    }

    public static float calculateNorm(Float[] vector) {
        float norm = 0;
        for (Float value : vector) {
            norm += value * value;
        }
        return (float) Math.sqrt(norm);
    }
}
