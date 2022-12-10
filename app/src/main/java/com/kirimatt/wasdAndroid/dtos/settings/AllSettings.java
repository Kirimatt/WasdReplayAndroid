package com.kirimatt.wasdAndroid.dtos.settings;

import android.content.SharedPreferences;

public class AllSettings {
    private boolean date;
    private boolean mono;
    private boolean avatars;
    private boolean moderators;
    private boolean stickers;
    private float delay;

    public AllSettings() {
    }

    public AllSettings(SharedPreferences sharedPref) {
        this.date = sharedPref.getBoolean("DATE_NEEDED", true);
        this.mono = sharedPref.getBoolean("MONOCHROME_NEEDED", false);
        this.avatars = sharedPref.getBoolean("AVATAR_NEEDED", true);
        this.moderators = sharedPref.getBoolean("MODERATOR_NEEDED", true);
        this.stickers = sharedPref.getBoolean("STICKER_NEEDED", true);
        this.delay = sharedPref.getFloat("DELAY", 0f);
    }

    public boolean isDate() {
        return date;
    }

    public void setDate(boolean date) {
        this.date = date;
    }

    public boolean isMono() {
        return mono;
    }

    public void setMono(boolean mono) {
        this.mono = mono;
    }

    public boolean isAvatars() {
        return avatars;
    }

    public void setAvatars(boolean avatars) {
        this.avatars = avatars;
    }

    public boolean isModerators() {
        return moderators;
    }

    public void setModerators(boolean moderators) {
        this.moderators = moderators;
    }

    public boolean isStickers() {
        return stickers;
    }

    public void setStickers(boolean stickers) {
        this.stickers = stickers;
    }

    public float getDelay() {
        return delay;
    }

    public void setDelay(float delay) {
        this.delay = delay;
    }

    @Override
    public String toString() {
        return "AllSettings{" +
                "date=" + date +
                ", mono=" + mono +
                ", avatars=" + avatars +
                ", moderators=" + moderators +
                ", stickers=" + stickers +
                ", delay=" + delay +
                '}';
    }
}
