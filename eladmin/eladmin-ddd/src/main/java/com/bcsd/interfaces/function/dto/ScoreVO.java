package com.bcsd.interfaces.function.dto;

/**
 * @Author liukun.inspire
 * @Date 2024/2/21 22:22
 * @PackageName: com.bcsd.interfaces.function.dto
 * @ClassName: ScoreVO
 * @Version 1.0
 */
public class ScoreVO {
    private int x;

    private int y;

    private float score;

    public int getX() {
        return x;
    }

    /**
     * @param x to set
     */
    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    /**
     * @param y to set
     */
    public void setY(int y) {
        this.y = y;
    }

    public float getScore() {
        return score;
    }

    /**
     * @param score to set
     */
    public void setScore(float score) {
        this.score = score;
    }
}
