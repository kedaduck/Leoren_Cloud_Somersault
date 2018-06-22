package com.leoren.liehu.User.BaseUserInfo;

import com.leoren.liehu.Util.MyDate;

import java.util.HashMap;

/**
 * 用户身体各项信息
 * @Author Leoren
 * @Date 2018/5/25 13:34
 */
public class UserBodyInfo {

    /**
     * 用户身高   cm为单位
     */
    private int userHeight;

    /**
     * 用户体重  kg为单位
     */
    private HashMap<MyDate, Double> userWeight;

    /**
     * 胸围 cm为单位
     */
    private HashMap<MyDate, Integer> userBust;

    /**
     * 腰围  cm为单位
     */
    private HashMap<MyDate, Integer> userWaistline;

    /**
     * 臀围   cm为单位
     */
    private HashMap<MyDate, Integer> userHipline;

    /**
     * 体脂率
     */
    private HashMap<MyDate, Double> userFatrate;

    /**
     * 肌肉量
     */
    private HashMap<MyDate, Double> userMuscle;

    public int getUserHeight() {
        return userHeight;
    }

    public void setUserHeight(int userHeight) {
        this.userHeight = userHeight;
    }

    public HashMap<MyDate, Double> getUserWeight() {
        return userWeight;
    }

    public void setUserWeight(HashMap<MyDate, Double> userWeight) {
        this.userWeight = userWeight;
    }

    public HashMap<MyDate, Integer> getUserBust() {
        return userBust;
    }

    public void setUserBust(HashMap<MyDate, Integer> userBust) {
        this.userBust = userBust;
    }

    public HashMap<MyDate, Integer> getUserWaistline() {
        return userWaistline;
    }

    public void setUserWaistline(HashMap<MyDate, Integer> userWaistline) {
        this.userWaistline = userWaistline;
    }

    public HashMap<MyDate, Integer> getUserHipline() {
        return userHipline;
    }

    public void setUserHipline(HashMap<MyDate, Integer> userHipline) {
        this.userHipline = userHipline;
    }

    public HashMap<MyDate, Double> getUserFatrate() {
        return userFatrate;
    }

    public void setUserFatrate(HashMap<MyDate, Double> userFatrate) {
        this.userFatrate = userFatrate;
    }

    public HashMap<MyDate, Double> getUserMuscle() {
        return userMuscle;
    }

    public void setUserMuscle(HashMap<MyDate, Double> userMuscle) {
        this.userMuscle = userMuscle;
    }

    public UserBodyInfo() {
    }

    public UserBodyInfo(int userHeight, HashMap<MyDate, Double> userWeight, HashMap<MyDate, Integer> userBust, HashMap<MyDate, Integer> userWaistline, HashMap<MyDate, Integer> userHipline, HashMap<MyDate, Double> userFatrate, HashMap<MyDate, Double> userMuscle) {
        this.userHeight = userHeight;
        this.userWeight = userWeight;
        this.userBust = userBust;
        this.userWaistline = userWaistline;
        this.userHipline = userHipline;
        this.userFatrate = userFatrate;
        this.userMuscle = userMuscle;
    }
}
