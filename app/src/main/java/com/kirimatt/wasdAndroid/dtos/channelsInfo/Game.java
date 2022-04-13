package com.kirimatt.wasdAndroid.dtos.channelsInfo;

import com.google.gson.annotations.SerializedName;
import com.kirimatt.wasdAndroid.dtos.common.Image;

import java.io.Serializable;

public class Game implements Serializable {
    @SerializedName("game_id")
    private int gameId;
    @SerializedName("game_name")
    private String gameName;
    @SerializedName("game_icon")
    private Image image;
    @SerializedName("game_color_hex")
    private String gameColorHex;

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public Image getGameIcon() {
        return image;
    }

    public void setGameIcon(Image image) {
        this.image = image;
    }

    public String getGameColorHex() {
        return gameColorHex;
    }

    public void setGameColorHex(String gameColorHex) {
        this.gameColorHex = gameColorHex;
    }
}
