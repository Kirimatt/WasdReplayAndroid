package com.kirimatt.wasdAndroid.dtos.chatInfo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Tag implements Serializable {
    @SerializedName("tag_id")
    private int tagId;
    @SerializedName("tag_name")
    private String tagName;
    @SerializedName("tag_description")
    private String tagDescription;
    @SerializedName("tag_meta")
    private String tagMeta;
    @SerializedName("tag_type")
    private String tagType;
    @SerializedName("tag_media_containers_online_count")
    private int tagMediaContainersOnlineCount;

    public int getTagId() {
        return tagId;
    }

    public void setTagId(int tagId) {
        this.tagId = tagId;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public String getTagDescription() {
        return tagDescription;
    }

    public void setTagDescription(String tagDescription) {
        this.tagDescription = tagDescription;
    }

    public String getTagMeta() {
        return tagMeta;
    }

    public void setTagMeta(String tagMeta) {
        this.tagMeta = tagMeta;
    }

    public String getTagType() {
        return tagType;
    }

    public void setTagType(String tagType) {
        this.tagType = tagType;
    }

    public int getTagMediaContainersOnlineCount() {
        return tagMediaContainersOnlineCount;
    }

    public void setTagMediaContainersOnlineCount(int tagMediaContainersOnlineCount) {
        this.tagMediaContainersOnlineCount = tagMediaContainersOnlineCount;
    }
}
