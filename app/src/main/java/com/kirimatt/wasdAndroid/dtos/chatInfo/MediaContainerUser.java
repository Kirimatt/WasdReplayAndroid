package com.kirimatt.wasdAndroid.dtos.chatInfo;

import com.google.gson.annotations.SerializedName;
import com.kirimatt.wasdAndroid.dtos.common.Image;

import java.io.Serializable;
import java.util.Date;

public class MediaContainerUser implements Serializable {
    @SerializedName("created_at")
    private Date createdAt;
    @SerializedName("deleted_at")
    private Object deletedAt;
    @SerializedName("updated_at")
    private Date updatedAt;
    @SerializedName("user_id")
    private int userId;
    @SerializedName("user_login")
    private String userLogin;
    @SerializedName("profile_description")
    private String profileDescription;
    @SerializedName("profile_stream_preview_image_url")
    private Object profileStreamPreviewImageUrl;
    @SerializedName("profile_stream_view_url")
    private Object profileStreamViewUrl;
    @SerializedName("profile_image")
    private Image profileImage;
    @SerializedName("profile_background")
    private Image profileBackground;
    @SerializedName("channel_id")
    private int channelId;
    @SerializedName("profile_is_live")
    private boolean profileIsLive;

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Object getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Object deletedAt) {
        this.deletedAt = deletedAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public String getProfileDescription() {
        return profileDescription;
    }

    public void setProfileDescription(String profileDescription) {
        this.profileDescription = profileDescription;
    }

    public Object getProfileStreamPreviewImageUrl() {
        return profileStreamPreviewImageUrl;
    }

    public void setProfileStreamPreviewImageUrl(Object profileStreamPreviewImageUrl) {
        this.profileStreamPreviewImageUrl = profileStreamPreviewImageUrl;
    }

    public Object getProfileStreamViewUrl() {
        return profileStreamViewUrl;
    }

    public void setProfileStreamViewUrl(Object profileStreamViewUrl) {
        this.profileStreamViewUrl = profileStreamViewUrl;
    }

    public Image getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(Image profileImage) {
        this.profileImage = profileImage;
    }

    public Image getProfileBackground() {
        return profileBackground;
    }

    public void setProfileBackground(Image profileBackground) {
        this.profileBackground = profileBackground;
    }

    public int getChannelId() {
        return channelId;
    }

    public void setChannelId(int channelId) {
        this.channelId = channelId;
    }

    public boolean isProfileIsLive() {
        return profileIsLive;
    }

    public void setProfileIsLive(boolean profileIsLive) {
        this.profileIsLive = profileIsLive;
    }
}
