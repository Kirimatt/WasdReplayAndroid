package com.kirimatt.wasdAndroid.dtos.search;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;
import com.kirimatt.wasdAndroid.dtos.common.Image;

import java.io.Serializable;
import java.util.Date;

public class Row implements Serializable {
    @SerializedName("channel_id")
    private int channelId;
    @SerializedName("created_at")
    private Date createdAt;
    @SerializedName("deleted_at")
    private Date deletedAt;
    @SerializedName("profile_background")
    private Image profileBackground;
    @SerializedName("profile_description")
    private String profileDescription;
    @SerializedName("profile_description_enabled")
    private boolean profileDescriptionEnabled;
    @SerializedName("profile_first_name")
    private String profileFirstName;
    @SerializedName("profile_image")
    private Image profileImage;
    @SerializedName("profile_is_live")
    private boolean profileIsLive;
    @SerializedName("profile_last_name")
    private String profileLastName;
    @SerializedName("profile_stream_preview_image_url")
    private Image profileStreamPreviewImageUrl;
    @SerializedName("profile_stream_push_url")
    private String profileStreamPushUrl;
    @SerializedName("profile_stream_view_url")
    private String profileStreamViewUrl;
    @SerializedName("updated_at")
    private Date updatedAt;
    @SerializedName("user_id")
    private int userId;
    @SerializedName("user_login")
    private String userLogin;

    public int getChannelId() {
        return channelId;
    }

    public void setChannelId(int channelId) {
        this.channelId = channelId;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Date deletedAt) {
        this.deletedAt = deletedAt;
    }

    public Image getProfileBackground() {
        return profileBackground;
    }

    public void setProfileBackground(Image profileBackground) {
        this.profileBackground = profileBackground;
    }

    public String getProfileDescription() {
        return profileDescription;
    }

    public void setProfileDescription(String profileDescription) {
        this.profileDescription = profileDescription;
    }

    public boolean isProfileDescriptionEnabled() {
        return profileDescriptionEnabled;
    }

    public void setProfileDescriptionEnabled(boolean profileDescriptionEnabled) {
        this.profileDescriptionEnabled = profileDescriptionEnabled;
    }

    public String getProfileFirstName() {
        return profileFirstName;
    }

    public void setProfileFirstName(String profileFirstName) {
        this.profileFirstName = profileFirstName;
    }

    public Image getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(Image profileImage) {
        this.profileImage = profileImage;
    }

    public boolean isProfileIsLive() {
        return profileIsLive;
    }

    public void setProfileIsLive(boolean profileIsLive) {
        this.profileIsLive = profileIsLive;
    }

    public String getProfileLastName() {
        return profileLastName;
    }

    public void setProfileLastName(String profileLastName) {
        this.profileLastName = profileLastName;
    }

    public Image getProfileStreamPreviewImageUrl() {
        return profileStreamPreviewImageUrl;
    }

    public void setProfileStreamPreviewImageUrl(Image profileStreamPreviewImageUrl) {
        this.profileStreamPreviewImageUrl = profileStreamPreviewImageUrl;
    }

    public String getProfileStreamPushUrl() {
        return profileStreamPushUrl;
    }

    public void setProfileStreamPushUrl(String profileStreamPushUrl) {
        this.profileStreamPushUrl = profileStreamPushUrl;
    }

    public String getProfileStreamViewUrl() {
        return profileStreamViewUrl;
    }

    public void setProfileStreamViewUrl(String profileStreamViewUrl) {
        this.profileStreamViewUrl = profileStreamViewUrl;
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

    @NonNull
    @Override
    public String toString() {
        return "Row{" +
                "channelId=" + channelId +
                ", createdAt=" + createdAt +
                ", deletedAt=" + deletedAt +
                ", profileBackground=" + profileBackground +
                ", profileDescription='" + profileDescription + '\'' +
                ", profileDescriptionEnabled=" + profileDescriptionEnabled +
                ", profileFirstName='" + profileFirstName + '\'' +
                ", profileImage=" + profileImage +
                ", profileIsLive=" + profileIsLive +
                ", profileLastName='" + profileLastName + '\'' +
                ", profileStreamPreviewImageUrl=" + profileStreamPreviewImageUrl +
                ", profileStreamPushUrl='" + profileStreamPushUrl + '\'' +
                ", profileStreamViewUrl='" + profileStreamViewUrl + '\'' +
                ", updatedAt=" + updatedAt +
                ", userId=" + userId +
                ", userLogin='" + userLogin + '\'' +
                '}';
    }
}