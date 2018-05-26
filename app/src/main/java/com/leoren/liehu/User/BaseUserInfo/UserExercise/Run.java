package com.leoren.liehu.User.BaseUserInfo.UserExercise;

import java.util.Date;

/**
 * 跑步运动
 * @Author Leoren
 * @Date 2018/5/25 14:49
 */
public class Run {

    /**
     * 运动总时间   秒为单位
     */
    private int totalTime;

    /**
     * 运动每千米平均时间  秒为单位
     */
    private int averageTime;

    /**
     * 运动总长度   M为单位
     */
    private int totalLength;

    /**
     * 每分钟运动长度  M为单位
     */
    private int averageLength;

    /**
     * 每千米所用时长 s为单位
     */
    private int perkioTime;

    /**
     * 这次运动的时间
     */
    private Date date;

    /**
     * 本次运动所消耗能量
     */
    private int runEnergy;

    public int getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(int totalTime) {
        this.totalTime = totalTime;
    }

    public int getAverageTime() {
        return averageTime;
    }

    public void setAverageTime(int averageTime) {
        this.averageTime = averageTime;
    }

    public int getTotalLength() {
        return totalLength;
    }

    public void setTotalLength(int totalLength) {
        this.totalLength = totalLength;
    }

    public int getAverageLength() {
        return averageLength;
    }

    public void setAverageLength(int averageLength) {
        this.averageLength = averageLength;
    }

    public int getPerkioTime() {
        return perkioTime;
    }

    public void setPerkioTime(int perkioTime) {
        this.perkioTime = perkioTime;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Run() {
    }

    public int getRunEnergy() {
        return runEnergy;
    }

    public void setRunEnergy(int runEnergy) {
        this.runEnergy = runEnergy;
    }

    public Run(int totalTime, int averageTime, int totalLength, int averageLength, int perkioTime, Date date, int runEnergy) {
        this.totalTime = totalTime;
        this.averageTime = averageTime;
        this.totalLength = totalLength;
        this.averageLength = averageLength;
        this.perkioTime = perkioTime;
        this.date = date;
        this.runEnergy = runEnergy;
    }
}
