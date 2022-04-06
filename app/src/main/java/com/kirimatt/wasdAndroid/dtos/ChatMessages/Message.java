package com.kirimatt.wasdAndroid.dtos.ChatMessages;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;

public class Message implements Serializable {
    @SerializedName("id")
    private String id;
    @SerializedName("type")
    private String type;
    @SerializedName("info")
    private Info info;
    @SerializedName("date_time")
    private Date dateTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    @NonNull
    @Override
    public String toString() {
        return "Result{" +
                "id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", info=" + info +
                ", dateTime=" + dateTime +
                '}';
    }
}
