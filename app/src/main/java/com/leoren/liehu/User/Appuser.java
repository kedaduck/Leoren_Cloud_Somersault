package com.leoren.liehu.User;

import com.leoren.liehu.User.BaseUserInfo.UserAddress;
import com.leoren.liehu.User.BaseUserInfo.UserBodyInfo;
import com.leoren.liehu.User.BaseUserInfo.UserExe;

/**
 * App用户
 * @author Leoren
 * @DATE 2018/5/20 8:47
 */
public class Appuser {

    /**
     * 选择哪种模式登录
     * 0   APP默认方式登录
     * 1   QQ登录
     * 2   微博登录
     * 3   小米账号登录
     * 4   华为账号登录
     */
    public final static int NORMAL_LOGIN_MODE = 0;
    public final static int QQ_LOGIN_MODE = 1;
    public final static int WEIBO_LOGIN_MODE = 2;
    public final static int XIAOMI_LOGIN_MODE = 3;
    public final static int HUAWEI_LOGIN_MODE = 4;

    /**
     * 用户id
     */
    private int userId;

    /**
     * 用户名=========昵称
     * 若用普通模式登录  则直接显示
     * 若用第三方登录   则从中取出后登录
     **/
    private String userName;

    /**
     * 用户名密码
     * 若用普通模式登录  则有密码
     * 若用第三方登录  则无
     */
    private String password;

    /**
     * 用户所在地
     */
    private UserAddress userAddress;

    /**
     * 用户性别
     * 只能使用com.leoren.User.BaseUerInfo.UserGender里的属性
     */
    private int userGender;

    /**
     * 用户年龄
     */
    private int userAge;

    /**
     * 用户身体基本信息
     */
    private UserBodyInfo userBodyInfo;

    /**
     * 用户运动的信息
     */
    private UserExe userExe;












}
