package com.leoren.liehu.User.ResultInformation;

import java.net.URL;

/**
 * 小米登录返回信息
 * @Author Leoren
 * @Date 2018/5/25 13:12
 */
public class MiResultInfo {

    /**
     * 返回的小米账号昵称
     */
    private  String nickName;

    /**
     * 返回的小米账号头像url  1
     */
    private  URL miliaoIcon;

    /**
     * 返回的小米账号头像url  2
     */
    private  URL miliaoIconBig;

    /**
     * 返回的小米账号id
     */
    private  String miuserId;

    /**
     * 返回小米账号注册的手机号
     */
    private  String miphoneNumber;



    private static MiResultInfo info;


    public String getNickName() {
        return nickName;
    }

    private void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public URL getMiliaoIcon() {
        return miliaoIcon;
    }

    private void setMiliaoIcon(URL miliaoIcon) {
        this.miliaoIcon = miliaoIcon;
    }

    public URL getMiliaoIconBig() {
        return miliaoIconBig;
    }

    private void setMiliaoIconBig(URL miliaoIconBig) {
        this.miliaoIconBig = miliaoIconBig;
    }

    public String getMiuserId() {
        return miuserId;
    }

    private void setMiuserId(String miuserId) {
        this.miuserId = miuserId;
    }

    public String getMiphoneNumber() {
        return miphoneNumber;
    }

    private void setMiphoneNumber(String miphoneNumber) {
        this.miphoneNumber = miphoneNumber;
    }



    private MiResultInfo() {
    }

    private MiResultInfo(String nickName, URL miliaoIcon, URL miliaoIconBig, String miuserId, String miphoneNumber) {
        this.nickName = nickName;
        this.miliaoIcon = miliaoIcon;
        this.miliaoIconBig = miliaoIconBig;
        this.miuserId = miuserId;
        this.miphoneNumber = miphoneNumber;
    }

    public static void setMiResultInfo(String nickName, URL url, URL urlBig, String miuserId, String miphoneNumber){
        info = new MiResultInfo(nickName,url, urlBig, miuserId, miphoneNumber);
    }

    public  static MiResultInfo getInstance(){
        return info;
    }


}
