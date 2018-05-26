package com.leoren.liehu.User.BaseUserInfo.UserFood;

import java.util.Date;
import java.util.HashMap;

/**
 * 用户早饭
 * @Author Leoren
 * @Date 2018/5/25 15:04
 */
public class Breakfast {

    /**
     * 用户吃早饭的时间
     */
    private Date date;

    /**
     * 用户吃早饭具体
     */
    private HashMap<String , Integer> breakContentMap;

    /**
     * 用户吃早饭所摄入能量
     */
    private int breakEnergy;



}
