package com.kirimatt.wasdAndroid.dtos.ChatMessages;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;
import com.kirimatt.wasdAndroid.dtos.common.Image;

import java.io.Serializable;

public class Sticker implements Serializable {
    @SerializedName("sticker_id")
    private int stickerId;
    @SerializedName("sticker_image")
    private Image stickerImage;
    @SerializedName("sticker_name")
    private String stickerName;
    @SerializedName("sticker_alias")
    private String stickerAlias;
    @SerializedName("sticker_pack_id")
    private int stickerPackId;
    @SerializedName("sticker_status")
    private Object stickerStatus;

    public int getStickerId() {
        return stickerId;
    }

    public void setStickerId(int stickerId) {
        this.stickerId = stickerId;
    }

    public Image getStickerImage() {
        return stickerImage;
    }

    public void setStickerImage(Image stickerImage) {
        this.stickerImage = stickerImage;
    }

    public String getStickerName() {
        return stickerName;
    }

    public void setStickerName(String stickerName) {
        this.stickerName = stickerName;
    }

    public String getStickerAlias() {
        return stickerAlias;
    }

    public void setStickerAlias(String stickerAlias) {
        this.stickerAlias = stickerAlias;
    }

    public int getStickerPackId() {
        return stickerPackId;
    }

    public void setStickerPackId(int stickerPackId) {
        this.stickerPackId = stickerPackId;
    }

    public Object getStickerStatus() {
        return stickerStatus;
    }

    public void setStickerStatus(Object stickerStatus) {
        this.stickerStatus = stickerStatus;
    }

    @NonNull
    @Override
    public String toString() {
        return "Sticker{" +
                "stickerId=" + stickerId +
                ", stickerImage=" + stickerImage +
                ", stickerName='" + stickerName + '\'' +
                ", stickerAlias='" + stickerAlias + '\'' +
                ", stickerPackId=" + stickerPackId +
                ", stickerStatus=" + stickerStatus +
                '}';
    }
}
