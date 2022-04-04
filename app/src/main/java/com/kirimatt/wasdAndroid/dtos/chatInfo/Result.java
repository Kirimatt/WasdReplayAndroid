package com.kirimatt.wasdAndroid.dtos.chatInfo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Result implements Serializable {
    @SerializedName("media_container_id")
    private int mediaContainerId;
    @SerializedName("media_container_name")
    private String mediaContainerName;
    @SerializedName("media_container_description")
    private String mediaContainerDescription;
    @SerializedName("media_container_type")
    private String mediaContainerType;
    @SerializedName("media_container_status")
    private String mediaContainerStatus;
    @SerializedName("media_container_online_status")
    private String mediaContainerOnlineStatus;
    @SerializedName("user_id")
    private int userId;
    @SerializedName("channel_id")
    private int channelId;
    @SerializedName("created_at")
    private Date createdAt;
    @SerializedName("is_mature_content")
    private boolean isMatureContent;
    @SerializedName("published_at")
    private Date publishedAt;
    @SerializedName("game")
    private Game game;
    @SerializedName("media_container_streams")
    private List<MediaContainerStream> mediaContainerStreams;
    @SerializedName("tags")
    private List<Tag> tags;
    @SerializedName("media_container_user")
    private MediaContainerUser mediaContainerUser;
    @SerializedName("media_container_channel")
    private MediaContainerChannel mediaContainerChannel;

    public int getMediaContainerId() {
        return mediaContainerId;
    }

    public void setMediaContainerId(int mediaContainerId) {
        this.mediaContainerId = mediaContainerId;
    }

    public String getMediaContainerName() {
        return mediaContainerName;
    }

    public void setMediaContainerName(String mediaContainerName) {
        this.mediaContainerName = mediaContainerName;
    }

    public String getMediaContainerDescription() {
        return mediaContainerDescription;
    }

    public void setMediaContainerDescription(String mediaContainerDescription) {
        this.mediaContainerDescription = mediaContainerDescription;
    }

    public String getMediaContainerType() {
        return mediaContainerType;
    }

    public void setMediaContainerType(String mediaContainerType) {
        this.mediaContainerType = mediaContainerType;
    }

    public String getMediaContainerStatus() {
        return mediaContainerStatus;
    }

    public void setMediaContainerStatus(String mediaContainerStatus) {
        this.mediaContainerStatus = mediaContainerStatus;
    }

    public String getMediaContainerOnlineStatus() {
        return mediaContainerOnlineStatus;
    }

    public void setMediaContainerOnlineStatus(String mediaContainerOnlineStatus) {
        this.mediaContainerOnlineStatus = mediaContainerOnlineStatus;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

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

    public boolean isMatureContent() {
        return isMatureContent;
    }

    public void setMatureContent(boolean matureContent) {
        this.isMatureContent = matureContent;
    }

    public Date getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(Date publishedAt) {
        this.publishedAt = publishedAt;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public List<MediaContainerStream> getMediaContainerStreams() {
        return mediaContainerStreams;
    }

    public void setMediaContainerStreams(List<MediaContainerStream> mediaContainerStreams) {
        this.mediaContainerStreams = mediaContainerStreams;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public MediaContainerUser getMediaContainerUser() {
        return mediaContainerUser;
    }

    public void setMediaContainerUser(MediaContainerUser mediaContainerUser) {
        this.mediaContainerUser = mediaContainerUser;
    }

    public MediaContainerChannel getMediaContainerChannel() {
        return mediaContainerChannel;
    }

    public void setMediaContainerChannel(MediaContainerChannel mediaContainerChannel) {
        this.mediaContainerChannel = mediaContainerChannel;
    }
}