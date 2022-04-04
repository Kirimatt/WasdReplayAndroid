package com.kirimatt.wasdAndroid.dtos.chatInfo;

import androidx.annotation.NonNull;

/**
 * Класс сущность для авторизации
 *
 * @author azamat
 */
public class ChatInfoRequestDto {
    private Integer replayNum;

    public ChatInfoRequestDto(Integer replayNum) {
        this.replayNum = replayNum;
    }

    public Integer getReplayNum() {
        return replayNum;
    }

    public void setReplayNum(Integer replayNum) {
        this.replayNum = replayNum;
    }

    @NonNull
    @Override
    public String toString() {
        return "ChatInfoRequestDTO{" +
                "replayNum=" + replayNum +
                '}';
    }
}
