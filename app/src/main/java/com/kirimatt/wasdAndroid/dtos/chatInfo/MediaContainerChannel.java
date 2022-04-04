package com.kirimatt.wasdAndroid.dtos.chatInfo;

import com.google.gson.annotations.SerializedName;
import com.kirimatt.wasdAndroid.dtos.common.Image;

import java.io.Serializable;
import java.util.Date;

public class MediaContainerChannel implements Serializable {
    @SerializedName("created_at")
    private Date createdAt;
    @SerializedName("deleted_at")
    private Date deletedAt;
    @SerializedName("updated_at")
    private Date updatedAt;
    @SerializedName("channel_id")
    private int channelId;
    @SerializedName("channel_name")
    private String channelName;
    @SerializedName("user_id")
    private int userId;
    @SerializedName("followers_count")
    private int followersCount;
    @SerializedName("channel_subscribers_count")
    private int channelSubscribersCount;
    @SerializedName("channel_is_live")
    private boolean channelIsLive;
    @SerializedName("channel_description")
    private String channelDescription;
    @SerializedName("channel_description_enabled")
    private boolean channelDescriptionEnabled;
    @SerializedName("channel_donation_url")
    private String channelDonationUrl;
    @SerializedName("channel_image")
    private Image image;
    @SerializedName("channel_status")
    private String channelStatus;
    @SerializedName("channel_clips_count")
    private int channelClipsCount;
    @SerializedName("channel_alias")
    private String channelAlias;
    @SerializedName("channel_priority")
    private int channelPriority;
    @SerializedName("last_activity_date")
    private Date lastActivityDate;
    @SerializedName("meta")
    private Meta meta;
    @SerializedName("notification")
    private boolean notification;
    @SerializedName("is_user_follower")
    private boolean isUserFollower;
    @SerializedName("is_partner")
    private boolean isPartner;

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

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public int getChannelId() {
        return channelId;
    }

    public void setChannelId(int channelId) {
        this.channelId = channelId;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getFollowersCount() {
        return followersCount;
    }

    public void setFollowersCount(int followersCount) {
        this.followersCount = followersCount;
    }

    public int getChannelSubscribersCount() {
        return channelSubscribersCount;
    }

    public void setChannelSubscribersCount(int channelSubscribersCount) {
        this.channelSubscribersCount = channelSubscribersCount;
    }

    public boolean isChannelIsLive() {
        return channelIsLive;
    }

    public void setChannelIsLive(boolean channelIsLive) {
        this.channelIsLive = channelIsLive;
    }

    public String getChannelDescription() {
        return channelDescription;
    }

    public void setChannelDescription(String channelDescription) {
        this.channelDescription = channelDescription;
    }

    public boolean isChannelDescriptionEnabled() {
        return channelDescriptionEnabled;
    }

    public void setChannelDescriptionEnabled(boolean channelDescriptionEnabled) {
        this.channelDescriptionEnabled = channelDescriptionEnabled;
    }

    public String getChannelDonationUrl() {
        return channelDonationUrl;
    }

    public void setChannelDonationUrl(String channelDonationUrl) {
        this.channelDonationUrl = channelDonationUrl;
    }

    public Image getChannelImage() {
        return image;
    }

    public void setChannelImage(Image image) {
        this.image = image;
    }

    public String getChannelStatus() {
        return channelStatus;
    }

    public void setChannelStatus(String channelStatus) {
        this.channelStatus = channelStatus;
    }

    public int getChannelClipsCount() {
        return channelClipsCount;
    }

    public void setChannelClipsCount(int channelClipsCount) {
        this.channelClipsCount = channelClipsCount;
    }

    public String getChannelAlias() {
        return channelAlias;
    }

    public void setChannelAlias(String channelAlias) {
        this.channelAlias = channelAlias;
    }

    public int getChannelPriority() {
        return channelPriority;
    }

    public void setChannelPriority(int channelPriority) {
        this.channelPriority = channelPriority;
    }

    public Date getLastActivityDate() {
        return lastActivityDate;
    }

    public void setLastActivityDate(Date lastActivityDate) {
        this.lastActivityDate = lastActivityDate;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public boolean isNotification() {
        return notification;
    }

    public void setNotification(boolean notification) {
        this.notification = notification;
    }

    public boolean isUserFollower() {
        return isUserFollower;
    }

    public void setUserFollower(boolean userFollower) {
        this.isUserFollower = userFollower;
    }

    public boolean isPartner() {
        return isPartner;
    }

    public void setPartner(boolean partner) {
        this.isPartner = partner;
    }
}
