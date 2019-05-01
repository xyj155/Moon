package com.example.module_base.bean;

public class PurseVideo {
    private String videoPictureUrl;
    private String videoPlayUrl;
    private String videoDuration;
    private String videoName;
    private String kind;
    private String createdAt;

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getVideoPictureUrl() {
        return videoPictureUrl;
    }

    public void setVideoPictureUrl(String videoPictureUrl) {
        this.videoPictureUrl = videoPictureUrl;
    }

    public String getVideoPlayUrl() {
        return videoPlayUrl;
    }

    public void setVideoPlayUrl(String videoPlayUrl) {
        this.videoPlayUrl = videoPlayUrl;
    }

    public String getVideoDuration() {
        return videoDuration;
    }

    public void setVideoDuration(String videoDuration) {
        this.videoDuration = videoDuration;
    }

    public String getVideoName() {
        return videoName;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }
}
