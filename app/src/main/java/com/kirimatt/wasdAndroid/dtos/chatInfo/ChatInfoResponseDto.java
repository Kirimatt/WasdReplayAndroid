package com.kirimatt.wasdAndroid.dtos.chatInfo;

import java.io.Serializable;

public class ChatInfoResponseDto implements Serializable {
    private Result result;

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }
}