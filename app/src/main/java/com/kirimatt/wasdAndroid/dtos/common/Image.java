package com.kirimatt.wasdAndroid.dtos.common;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Objects;

public class Image implements Serializable {
    @SerializedName("large")
    private String large;
    @SerializedName("medium")
    private String medium;
    @SerializedName("small")
    private String small;

    public String getLarge() {
        return large;
    }

    public void setLarge(String large) {
        this.large = large;
    }

    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }

    public String getSmall() {
        return small;
    }

    public void setSmall(String small) {
        this.small = small;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Image image = (Image) o;
        return Objects.equals(large, image.large)
                && Objects.equals(medium, image.medium)
                && Objects.equals(small, image.small);
    }

    @Override
    public int hashCode() {
        return Objects.hash(large, medium, small);
    }

    @NonNull
    @Override
    public String toString() {
        return "Image{" +
                "large='" + large + '\'' +
                ", medium='" + medium + '\'' +
                ", small='" + small + '\'' +
                '}';
    }
}