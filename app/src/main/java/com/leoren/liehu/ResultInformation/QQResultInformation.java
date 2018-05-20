package com.leoren.liehu.ResultInformation;

import java.net.URL;

/**
 * 储存通过 QQ 注册登录后返回的各项用户信息
 * @author Leoren
 * @DATE 2018/5/19 16:53
 */
public class QQResultInformation {

    //ret
    private static int ret;

    //QQ昵称
    private static String qq_nickname;

    //QQ头像地址小
    private static URL qq_headicon_url;

    //QQ头像地址大
    private static URL qq_bigheadicon_url;

    //QQ显示性别
    private static String qq_gender;

    private static String qq_province;

    private static String qq_city;

    private static String year;



    public static int getRet() {
        return ret;
    }

    public static void setRet(int ret) {
        QQResultInformation.ret = ret;
    }

    public static String getQq_gender() {
        return qq_gender;
    }

    public static void setQq_gender(String qq_gender) {
        QQResultInformation.qq_gender = qq_gender;
    }

    public static String getQq_nickname() {
        return qq_nickname;
    }

    public static void setQq_nickname(String qq_nickname) {
        QQResultInformation.qq_nickname = qq_nickname;
    }

    public static URL getQq_headicon_url() {
        return qq_headicon_url;
    }

    public static void setQq_headicon_url(URL qq_headicon_url) {
        QQResultInformation.qq_headicon_url = qq_headicon_url;
    }

    public static URL getQq_bigheadicon_url() {
        return qq_bigheadicon_url;
    }

    public static void setQq_bigheadicon_url(URL qq_bigheadicon_url) {
        QQResultInformation.qq_bigheadicon_url = qq_bigheadicon_url;
    }

    public static String getQq_province() {
        return qq_province;
    }

    public static void setQq_province(String qq_province) {
        QQResultInformation.qq_province = qq_province;
    }

    public static String getQq_city() {
        return qq_city;
    }

    public static void setQq_city(String qq_city) {
        QQResultInformation.qq_city = qq_city;
    }

    public static String getYear() {
        return year;
    }

    public static void setYear(String year) {
        QQResultInformation.year = year;
    }

    public QQResultInformation() {

    }

    public QQResultInformation(int ret, String qq_nickname, String qq_gender, String qq_province, String qq_city, String year, URL qq_headicon_url, URL qq_bigheadicon_url){
        setRet(ret);
        setQq_nickname(qq_nickname);
        setQq_gender(qq_gender);
        setQq_province(qq_province);
        setQq_city(qq_city);
        setYear(year);
        setQq_headicon_url(qq_headicon_url);
        setQq_bigheadicon_url(qq_bigheadicon_url);
    }

}
