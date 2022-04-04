package com.kirimatt.wasdAndroid.dtos.ChatMessages;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class ChatMessagesResponseDto implements Serializable {
    @SerializedName("result")
    private List<Message> messages;

    public List<Message> getResult() {
        return messages;
    }

    public void setResult(List<Message> message) {
        this.messages = message;
    }

    @NonNull
    @Override
    public String toString() {
        return "ChatMessagesResponseDto{" +
                "result=" + messages +
                '}';
    }
}
