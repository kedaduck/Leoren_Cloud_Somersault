package com.leoren.liehu.User.BaseUserInfo;

import com.leoren.liehu.User.BaseUserInfo.UserExercise.Bike;
import com.leoren.liehu.User.BaseUserInfo.UserExercise.Run;
import com.leoren.liehu.User.BaseUserInfo.UserExercise.Walk;
import com.leoren.liehu.util.MyDate;

import java.util.HashMap;

/**
 *
 * 用户锻炼的各种信息
 * @Author Leoren
 * @Date 2018/5/25 14:59
 */
public class UserExe {

    private HashMap<MyDate, Bike> bikeMap;
    private HashMap<MyDate, Run> runMap;
    private HashMap<MyDate, Walk> walkMap;

    public HashMap<MyDate, Bike> getBikeMap() {
        return bikeMap;
    }

    public void setBikeMap(HashMap<MyDate, Bike> bikeMap) {
        this.bikeMap = bikeMap;
    }

    public HashMap<MyDate, Run> getRunMap() {
        return runMap;
    }

    public void setRunMap(HashMap<MyDate, Run> runMap) {
        this.runMap = runMap;
    }

    public HashMap<MyDate, Walk> getWalkMap() {
        return walkMap;
    }

    public void setWalkMap(HashMap<MyDate, Walk> walkMap) {
        this.walkMap = walkMap;
    }

    public UserExe() {
    }

    public UserExe(HashMap<MyDate, Bike> bikeMap, HashMap<MyDate, Run> runMap, HashMap<MyDate, Walk> walkMap) {
        this.bikeMap = bikeMap;
        this.runMap = runMap;
        this.walkMap = walkMap;
    }
}
