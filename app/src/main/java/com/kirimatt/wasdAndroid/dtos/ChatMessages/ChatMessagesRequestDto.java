package com.kirimatt.wasdAndroid.dtos.ChatMessages;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class ChatMessagesRequestDto implements Serializable {
    private Integer chatId;
    private Integer limit = 500;
    private Integer offset = 0;

    public ChatMessagesRequestDto(Integer chatId, Integer limit, Integer offset) {
        this.chatId = chatId;
        this.limit = limit;
        this.offset = offset;
    }

    public ChatMessagesRequestDto(Integer chatId) {
        this.chatId = chatId;
    }

    public ChatMessagesRequestDto(Integer chatId, Integer offset) {
        this.chatId = chatId;
        this.offset = offset;
    }

    public void incrementOffset() {
        this.offset += 500;
    }

    public Integer getChatId() {
        return chatId;
    }

    public void setChatId(Integer chatId) {
        this.chatId = chatId;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    @Override
    @NonNull
    public String toString() {
        return "ChatMessagesRequestDto{" +
                "chatId=" + chatId +
                ", limit=" + limit +
                ", offset=" + offset +
                '}';
    }
}
