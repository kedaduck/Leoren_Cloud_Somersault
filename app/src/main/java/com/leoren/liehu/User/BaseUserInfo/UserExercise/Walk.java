package com.leoren.liehu.User.BaseUserInfo.UserExercise;

import java.util.Date;

/**
 * 健走
 * @Author Leoren
 * @Date 2018/5/25 14:38
 */
public class Walk {

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
     * 这次运动的时间
     */
    private Date date;

    /**
     * 这次运动开始时间格式是"10:03"
     */
    private String startTime;

    /**
     * 这次运动结束时间格式是"10:03"
     */
    private String endTime;

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }


    /**
     * 本次运动所消耗能量
     */
    private int walkEnergy;

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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getWalkEnergy() {
        return walkEnergy;
    }

    public void setWalkEnergy(int walkEnergy) {
        this.walkEnergy = walkEnergy;
    }

    public Walk() {
    }

    public Walk(int totalTime, int averageTime, int totalLength, int averageLength, Date date, String startTime, String endTime, int walkEnergy) {
        this.totalTime = totalTime;
        this.averageTime = averageTime;
        this.totalLength = totalLength;
        this.averageLength = averageLength;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.walkEnergy = walkEnergy;
    }
}
