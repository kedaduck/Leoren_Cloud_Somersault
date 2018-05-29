package com.leoren.liehu.Activity.loginandregister.weibo;

/**
 * 该类定义了微博授权时所需要的参数。
 */
public interface Constants {

    public static final String APP_KEY      = "1874632603";

    public static final String REDIRECT_URL = "https://api.weibo.com/oauth2/default.html";


    public static final String SCOPE =
            "email,direct_messages_read,direct_messages_write,"
            + "friendships_groups_read,friendships_groups_write,statuses_to_me_read,"
            + "follow_app_official_microblog," + "invitation_write";
}
