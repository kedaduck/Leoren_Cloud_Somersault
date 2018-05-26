package com.leoren.liehu.User.BaseUserInfo;

/**
 * 用户所在地
 * @Author Leoren
 * @Date 2018/5/25 13:20
 */
public class UserAddress {

    /**
     * 省市区三级
     */
    private String province;
    private String city;
    private String region;

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public UserAddress(){

    }

    public UserAddress(String province, String city, String region) {
        this.province = province;
        this.city = city;
        this.region = region;
    }
}
