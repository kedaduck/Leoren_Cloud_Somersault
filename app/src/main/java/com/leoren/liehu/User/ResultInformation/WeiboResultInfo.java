package com.leoren.liehu.User.ResultInformation;

/**
 * 微博登录返回信息
 * @Author Leoren
 * @Date 2018/5/25 13:12
 */
public class WeiboResultInfo {

    private static WeiboResultInfo info;

    //    用户id
    private int userId;

    //用户昵称
    private String screenName;

    //用户友好显示名称
    private String name;

    //用户所在省级ID
    private int province;

    //用户所在城市ID
    private int city;

    //用户所在地
    private String location;

    //用户头像地址
    private String headImgUrl;

    //用户男女
    private String gender;

    //用户头像地址
    private String headBigImgUrl;

    public int getUserId() {
        return userId;
    }

    private void setUserId(int userId) {
        this.userId = userId;
    }

    public String getScreenName() {
        return screenName;
    }

    private void setScreenName(String screenName) {
        this.screenName = screenName;
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public int getProvince() {
        return province;
    }

    private void setProvince(int province) {
        this.province = province;
    }

    public int getCity() {
        return city;
    }

    private void setCity(int city) {
        this.city = city;
    }

    public String getLocation() {
        return location;
    }

    private void setLocation(String location) {
        this.location = location;
    }

    public String getHeadImgUrl() {
        return headImgUrl;
    }

    private void setHeadImgUrl(String headImgUrl) {
        this.headImgUrl = headImgUrl;
    }

    public String getGender() {
        return gender;
    }

    private void setGender(String gender) {
        this.gender = gender;
    }

    public String getHeadBigImgUrl() {
        return headBigImgUrl;
    }

    private void setHeadBigImgUrl(String headBigImgUrl) {
        this.headBigImgUrl = headBigImgUrl;
    }

    private WeiboResultInfo(int userId, String screenName, String name, int province, int city, String location, String headImgUrl, String gender, String headBigImgUrl) {
        this.userId = userId;
        this.screenName = screenName;
        this.name = name;
        this.province = province;
        this.city = city;
        this.location = location;
        this.headImgUrl = headImgUrl;
        this.gender = gender;
        this.headBigImgUrl = headBigImgUrl;
    }

    public static void setWeiboUserInfo(int userId, String screenName, String name, int province, int city, String location, String headImgUrl, String gender, String headBigImgUrl){
        info = new WeiboResultInfo(userId, screenName, name, province, city, location, headImgUrl, gender, headBigImgUrl);
    }


    public static WeiboResultInfo  getInstance(){
        return info;
    }


}
