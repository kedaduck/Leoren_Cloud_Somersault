package com.leoren.liehu.Function.Friends;

/**
 * 双人聊天的实体类
 * @Author Leoren
 * @Date 2018/5/26 14:46
 */
public class FriendChatroom {

    /**
     * 好友昵称或备注名称
     */
    private String friendName;

    /**
     * 上一次聊天内容
     */
    private String lastChatContent;

    /**
     * 好友头像
     */
    private int headId;

    /**
     * 上一次聊天时间
     */
    private String lastChatTime;

    public String getFriendName() {
        return friendName;
    }

    public void setFriendName(String friendName) {
        this.friendName = friendName;
    }

    public String getLastChatContent() {
        return lastChatContent;
    }

    public void setLastChatContent(String lastChatContent) {
        this.lastChatContent = lastChatContent;
    }

    public int getHeadId() {
        return headId;
    }

    public void setHeadId(int headId) {
        this.headId = headId;
    }

    public String getLastChatTime() {
        return lastChatTime;
    }

    public void setLastChatTime(String lastChatTime) {
        this.lastChatTime = lastChatTime;
    }
}
