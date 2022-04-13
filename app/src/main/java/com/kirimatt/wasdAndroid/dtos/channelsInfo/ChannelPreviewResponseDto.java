package com.kirimatt.wasdAndroid.dtos.channelsInfo;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class ChannelPreviewResponseDto implements Serializable {
    @SerializedName("result")
    private List<ResultPreviews> resultPreviews;

    public List<ResultPreviews> getResults() {
        return resultPreviews;
    }

    public void setResults(List<ResultPreviews> resultPreviews) {
        this.resultPreviews = resultPreviews;
    }

    @Override
    @NonNull
    public String toString() {
        return "ChannelPreviewResponseDto{" +
                "results=" + resultPreviews +
                '}';
    }
}
