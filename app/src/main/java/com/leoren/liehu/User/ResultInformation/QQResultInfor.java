package com.leoren.liehu.User.ResultInformation;

import java.net.URL;

/**
 * 储存通过 QQ 注册登录后返回的各项用户信息
 * @author Leoren
 * @DATE 2018/5/19 16:53
 */
public class QQResultInfor {

    private static QQResultInfor info;
    //ret
    private  int ret;

    //QQ昵称
    private  String qq_nickname;

    //QQ头像地址小
    private  URL qq_headicon_url;

    //QQ头像地址大
    private  URL qq_bigheadicon_url;

    //QQ显示性别
    private  String qq_gender;

    private  String qq_province;

    private  String qq_city;

    private  String year;


    public int getRet() {
        return ret;
    }

    public void setRet(int ret) {
        this.ret = ret;
    }

    public String getQq_nickname() {
        return qq_nickname;
    }

    public void setQq_nickname(String qq_nickname) {
        this.qq_nickname = qq_nickname;
    }

    public URL getQq_headicon_url() {
        return qq_headicon_url;
    }

    public void setQq_headicon_url(URL qq_headicon_url) {
        this.qq_headicon_url = qq_headicon_url;
    }

    public URL getQq_bigheadicon_url() {
        return qq_bigheadicon_url;
    }

    public void setQq_bigheadicon_url(URL qq_bigheadicon_url) {
        this.qq_bigheadicon_url = qq_bigheadicon_url;
    }

    public String getQq_gender() {
        return qq_gender;
    }

    public void setQq_gender(String qq_gender) {
        this.qq_gender = qq_gender;
    }

    public String getQq_province() {
        return qq_province;
    }

    public void setQq_province(String qq_province) {
        this.qq_province = qq_province;
    }

    public String getQq_city() {
        return qq_city;
    }

    public void setQq_city(String qq_city) {
        this.qq_city = qq_city;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public QQResultInfor() {
    }

    private QQResultInfor(int ret, String qq_nickname, String qq_gender, String qq_province, String qq_city, String year, URL qq_headicon_url, URL qq_bigheadicon_url) {
        this.ret = ret;
        this.qq_nickname = qq_nickname;
        this.qq_gender = qq_gender;
        this.qq_province = qq_province;
        this.qq_city = qq_city;
        this.year = year;
        this.qq_headicon_url = qq_headicon_url;
        this.qq_bigheadicon_url = qq_bigheadicon_url;

    }

    public static void setQQResultInfor(int ret, String qq_nickname, String qq_gender, String qq_province, String qq_city, String year, URL qq_headicon_url, URL qq_bigheadicon_url){
        info = new QQResultInfor(ret, qq_nickname, qq_gender, qq_province, qq_city, year, qq_headicon_url, qq_bigheadicon_url);

    }

    public  static  QQResultInfor getQQLoginInstance(){
        return info;
    }

}
