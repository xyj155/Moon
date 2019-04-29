package com.example.module_base.bean;

import cn.bmob.v3.BmobObject;

public class MemberVideo extends BmobObject {
    private String videoPlayUrl;
    private String videoPictureUrl;
    private String videoDuration;
    private String kind;

    public String getVideoPlayUrl() {
        return videoPlayUrl;
    }

    public void setVideoPlayUrl(String videoPlayUrl) {
        this.videoPlayUrl = videoPlayUrl;
    }

    public String getVideoPictureUrl() {
        return videoPictureUrl;
    }

    public void setVideoPictureUrl(String videoPictureUrl) {
        this.videoPictureUrl = videoPictureUrl;
    }

    public String getVideoDuration() {
        return videoDuration;
    }

    public void setVideoDuration(String videoDuration) {
        this.videoDuration = videoDuration;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getVideoName() {
        return videoName;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName;
    }

    private String videoName;

}
