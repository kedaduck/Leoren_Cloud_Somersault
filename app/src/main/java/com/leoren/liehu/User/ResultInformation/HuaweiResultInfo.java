package com.leoren.liehu.User.ResultInformation;

/**
 * 华为登录返回信息
 * @Author Leoren
 * @Date 2018/5/25 13:12
 */
public class HuaweiResultInfo {
    
    private static HuaweiResultInfo info = null;
    
    private String nickName;
    
    private String headUrl;
    
    private String openId;
    
    private String userId;
    
    private int gender;

    private String accessToken;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    private String getNickName() {
        return nickName;
    }

    private void setNickName(String nickName) {
        this.nickName = nickName;
    }

    private String getHeadUrl() {
        return headUrl;
    }

    private void setHeadUrl(String headUrl) {
        this.headUrl = headUrl;
    }

    private String getOpenId() {
        return openId;
    }

    private void setOpenId(String openId) {
        this.openId = openId;
    }

    private String getUserId() {
        return userId;
    }

    private void setUserId(String userId) {
        this.userId = userId;
    }

    private int getGender() {
        return gender;
    }

    private void setGender(int gender) {
        this.gender = gender;
    }

    private HuaweiResultInfo(String nickName, String headUrl, String openId, String userId, int gender, String accessToken) {

        this.nickName = nickName;
        this.headUrl = headUrl;
        this.openId = openId;
        this.userId = userId;
        this.gender = gender;
        this.accessToken = accessToken;
    }

    public static void setHuaweiResultInfo(String nickName, String headUrl, String openId, String userId, int gender, String accessToken) {
        HuaweiResultInfo.info = new HuaweiResultInfo(nickName, headUrl, openId, userId, gender, accessToken);
    }

    public static HuaweiResultInfo getInstance(){
        return info;
    }


}