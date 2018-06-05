package com.leoren.liehu.User.BaseUserInfo.UserHappy;

import com.leoren.liehu.User.Appuser;

import java.net.URL;
import java.util.List;

/**
 * happy模块对第一个功能视频的实体类
 * @Author Leoren
 * @Date 2018/5/29 14:38
 */
public class LittleVideo {

    //视频的编号
    private int videoId;

    //视频上传者
    private Appuser user;

    //视频的URL
    private URL videoUrl;

    //视频的评论
    private List<VideoComment> commentList;

    //该视频是否被该用户点赞
    private boolean isPraise;


    public int getVideoId() {
        return videoId;
    }

    public void setVideoId(int videoId) {
        this.videoId = videoId;
    }

    public Appuser getUser() {
        return user;
    }

    public void setUser(Appuser user) {
        this.user = user;
    }

    public URL getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(URL videoUrl) {
        this.videoUrl = videoUrl;
    }

    public List<VideoComment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<VideoComment> commentList) {
        this.commentList = commentList;
    }

    public boolean isPraise() {
        return isPraise;
    }

    public void setPraise(boolean praise) {
        isPraise = praise;
    }

    public LittleVideo() {
    }

    public LittleVideo(int videoId, Appuser user, URL videoUrl, List<VideoComment> commentList, boolean isPraise) {
        this.videoId = videoId;
        this.user = user;
        this.videoUrl = videoUrl;
        this.commentList = commentList;
        this.isPraise = isPraise;
    }


}
