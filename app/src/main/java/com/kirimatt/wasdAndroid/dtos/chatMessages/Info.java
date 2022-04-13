package com.kirimatt.wasdAndroid.dtos.chatMessages;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;
import com.kirimatt.wasdAndroid.dtos.common.Image;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Info implements Serializable {
    @SerializedName("user_id")
    private int userId;
    @SerializedName("message")
    private String message;
    @SerializedName("user_login")
    private String userLogin;
    @SerializedName("user_avatar")
    private Image userAvatar;
    @SerializedName("hash")
    private String hash;
    @SerializedName("is_follower")
    private boolean isFollower;
    @SerializedName("other_roles")
    private List<String> stringList;
    @SerializedName("user_channel_role")
    private String userChannelRole;
    @SerializedName("channel_id")
    private int channelId;
    @SerializedName("stream_id")
    private int streamId;
    @SerializedName("date_time")
    private Date dateTime;
    @SerializedName("streamer_id")
    private int streamerId;
    @SerializedName("sticker")
    private Sticker sticker;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public Image getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(Image userAvatar) {
        this.userAvatar = userAvatar;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public boolean isFollower() {
        return isFollower;
    }

    public void setFollower(boolean follower) {
        isFollower = follower;
    }

    public List<String> getStringList() {
        return stringList;
    }

    public void setStringList(List<String> stringList) {
        this.stringList = stringList;
    }

    public String getUserChannelRole() {
        return userChannelRole;
    }

    public void setUserChannelRole(String userChannelRole) {
        this.userChannelRole = userChannelRole;
    }

    public int getChannelId() {
        return channelId;
    }

    public void setChannelId(int channelId) {
        this.channelId = channelId;
    }

    public int getStreamId() {
        return streamId;
    }

    public void setStreamId(int streamId) {
        this.streamId = streamId;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public int getStreamerId() {
        return streamerId;
    }

    public void setStreamerId(int streamerId) {
        this.streamerId = streamerId;
    }

    public Sticker getSticker() {
        return sticker;
    }

    public void setSticker(Sticker sticker) {
        this.sticker = sticker;
    }

    @NonNull
    @Override
    public String toString() {
        return "Info{" +
                "userId=" + userId +
                ", message='" + message + '\'' +
                ", userLogin='" + userLogin + '\'' +
                ", userAvatar=" + userAvatar +
                ", hash='" + hash + '\'' +
                ", isFollower=" + isFollower +
                ", stringList=" + stringList +
                ", userChannelRole='" + userChannelRole + '\'' +
                ", channelId=" + channelId +
                ", streamId=" + streamId +
                ", dateTime=" + dateTime +
                ", streamerId=" + streamerId +
                ", sticker=" + sticker +
                '}';
    }
}
