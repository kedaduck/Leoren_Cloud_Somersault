package com.leoren.liehu.User.BaseUserInfo.UserHappy;

import com.leoren.liehu.User.Appuser;

/**
 * 视频评论的实体类
 * @Author Leoren
 * @Date 2018/5/29 14:47
 */
public class VideoComment {

    //评论用户
    private Appuser user;

    //评论时间
    private String commentTime;

    //评论内容
    private String conmmentContent;

    public Appuser getUser() {
        return user;
    }

    public void setUser(Appuser user) {
        this.user = user;
    }

    public String getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(String commentTime) {
        this.commentTime = commentTime;
    }

    public String getConmmentContent() {
        return conmmentContent;
    }

    public void setConmmentContent(String conmmentContent) {
        this.conmmentContent = conmmentContent;
    }

    public VideoComment() {
    }

    public VideoComment(Appuser user, String commentTime, String conmmentContent) {
        this.user = user;
        this.commentTime = commentTime;
        this.conmmentContent = conmmentContent;
    }
}
