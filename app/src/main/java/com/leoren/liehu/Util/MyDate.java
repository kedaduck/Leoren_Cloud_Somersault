package com.leoren.liehu.Util;

import java.text.DateFormat;
import java.util.Date;

/**
 * 自定义的一个时间工具类  用来管理记录身体变化情况
 * @Author Leoren
 * @Date 2018/5/25 13:55
 */
public class MyDate {

    private int bodyYear;
    private int bodyMonth;
    private int bodyDay;

    public int getBodyYear() {
        return bodyYear;
    }

    public void setBodyYear(int bodyYear) {
        this.bodyYear = bodyYear;
    }

    public int getBodyMonth() {
        return bodyMonth;
    }

    public void setBodyMonth(int bodyMonth) {
        this.bodyMonth = bodyMonth;
    }

    public int getBodyDay() {
        return bodyDay;
    }

    public void setBodyDay(int bodyDay) {
        this.bodyDay = bodyDay;
    }

    public MyDate(Date date){
        DateFormat format = DateFormat.getDateInstance();
        String str[] = format.format(date).toString().split("-");
        this.bodyYear = Integer.parseInt(str[0]);
        this.bodyMonth = Integer.parseInt(str[1]);
        this.bodyDay = Integer.parseInt(str[2]);
    }

    public static String getChattime(Date date){
        DateFormat he = DateFormat.getTimeInstance();
        return he.format(date).toString();
    }


}
