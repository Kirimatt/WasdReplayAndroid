package com.kirimatt.wasdAndroid.dtos.channelsInfo;

import java.io.Serializable;

public class ChatInfoResponseDto implements Serializable {
    private ResultPreviews resultPreviews;

    public ResultPreviews getResult() {
        return resultPreviews;
    }

    public void setResult(ResultPreviews resultPreviews) {
        this.resultPreviews = resultPreviews;
    }
}