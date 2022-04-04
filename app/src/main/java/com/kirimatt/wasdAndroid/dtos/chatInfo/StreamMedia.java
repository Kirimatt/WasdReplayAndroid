package com.kirimatt.wasdAndroid.dtos.chatInfo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class StreamMedia implements Serializable {
    @SerializedName("media_id")
    private int mediaId;
    @SerializedName("media_type")
    private String mediaType;
    @SerializedName("media_meta")
    private MediaMeta mediaMeta;
    @SerializedName("media_duration")
    private int mediaDuration;
    @SerializedName("media_status")
    private String mediaStatus;

    public int getMediaId() {
        return mediaId;
    }

    public void setMediaId(int mediaId) {
        this.mediaId = mediaId;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public MediaMeta getMediaMeta() {
        return mediaMeta;
    }

    public void setMediaMeta(MediaMeta mediaMeta) {
        this.mediaMeta = mediaMeta;
    }

    public int getMediaDuration() {
        return mediaDuration;
    }

    public void setMediaDuration(int mediaDuration) {
        this.mediaDuration = mediaDuration;
    }

    public String getMediaStatus() {
        return mediaStatus;
    }

    public void setMediaStatus(String mediaStatus) {
        this.mediaStatus = mediaStatus;
    }
}