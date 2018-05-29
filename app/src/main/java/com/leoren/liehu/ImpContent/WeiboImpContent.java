package com.leoren.liehu.ImpContent;

/**
 * 此类用于封装调用微博接口所使用的一些数据
 * @author Leoren
 * @DATE 2018/5/19 16:14
 */
public class WeiboImpContent {

    //调用微博接口所使用的唯一APP_ID
    public final static String APP_KEY = "1874632603";

    //调用微博接口所使用的APP_ID对应的APP_SECRET
    public final static String APP_SECRET = "83087049e11d1557dff05def783303b6";

    //回调地址
    public static final String REDIRECT_URL = "https://api.weibo.com/oauth2/default.html";


    //调用微博接口获取用户各种权限   方便直接写all(全部权限)
    //public final static String SCOPE = "all";

    public static final String SCOPE =
            "email,direct_messages_read,direct_messages_write,"
                    + "friendships_groups_read,friendships_groups_write,statuses_to_me_read,"
                    + "follow_app_official_microblog," + "invitation_write";
}
