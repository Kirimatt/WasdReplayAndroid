package com.kirimatt.wasdAndroid.dtos.chatInfo;

import com.google.gson.annotations.SerializedName;
import com.kirimatt.wasdAndroid.dtos.common.Image;

import java.io.Serializable;

public class MediaMeta implements Serializable {
    @SerializedName("media_url")
    private String mediaUrl;
    @SerializedName("media_archive_url")
    private String mediaArchiveUrl;
    @SerializedName("media_current_fps")
    private Object mediaCurrentFps;
    @SerializedName("media_preview_url")
    private String mediaPreviewUrl;
    @SerializedName("media_preview_images")
    private Image mediaPreviewImages;
    @SerializedName("media_current_bitrate")
    private Object mediaCurrentBitrate;
    @SerializedName("media_current_resolution")
    private Object mediaCurrentResolution;
    @SerializedName("media_preview_archive_url")
    private String mediaPreviewArchiveUrl;
    @SerializedName("media_preview_archive_images")
    private Image mediaPreviewArchiveImages;

    public String getMediaUrl() {
        return mediaUrl;
    }

    public void setMediaUrl(String mediaUrl) {
        this.mediaUrl = mediaUrl;
    }

    public String getMediaArchiveUrl() {
        return mediaArchiveUrl;
    }

    public void setMediaArchiveUrl(String mediaArchiveUrl) {
        this.mediaArchiveUrl = mediaArchiveUrl;
    }

    public Object getMediaCurrentFps() {
        return mediaCurrentFps;
    }

    public void setMediaCurrentFps(Object mediaCurrentFps) {
        this.mediaCurrentFps = mediaCurrentFps;
    }

    public String getMediaPreviewUrl() {
        return mediaPreviewUrl;
    }

    public void setMediaPreviewUrl(String mediaPreviewUrl) {
        this.mediaPreviewUrl = mediaPreviewUrl;
    }

    public Image getMediaPreviewImages() {
        return mediaPreviewImages;
    }

    public void setMediaPreviewImages(Image mediaPreviewImages) {
        this.mediaPreviewImages = mediaPreviewImages;
    }

    public Object getMediaCurrentBitrate() {
        return mediaCurrentBitrate;
    }

    public void setMediaCurrentBitrate(Object mediaCurrentBitrate) {
        this.mediaCurrentBitrate = mediaCurrentBitrate;
    }

    public Object getMediaCurrentResolution() {
        return mediaCurrentResolution;
    }

    public void setMediaCurrentResolution(Object mediaCurrentResolution) {
        this.mediaCurrentResolution = mediaCurrentResolution;
    }

    public String getMediaPreviewArchiveUrl() {
        return mediaPreviewArchiveUrl;
    }

    public void setMediaPreviewArchiveUrl(String mediaPreviewArchiveUrl) {
        this.mediaPreviewArchiveUrl = mediaPreviewArchiveUrl;
    }

    public Image getMediaPreviewArchiveImages() {
        return mediaPreviewArchiveImages;
    }

    public void setMediaPreviewArchiveImages(Image image) {
        this.mediaPreviewArchiveImages = image;
    }
}