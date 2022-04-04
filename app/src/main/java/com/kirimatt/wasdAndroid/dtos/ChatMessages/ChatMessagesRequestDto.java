package com.kirimatt.wasdAndroid.dtos.ChatMessages;

import com.kirimatt.wasdAndroid.dtos.chatInfo.ChatInfoResponseDto;

import java.io.Serializable;

public class ChatMessagesRequestDto implements Serializable {
    private ChatInfoResponseDto responseDto;

    public ChatMessagesRequestDto(ChatInfoResponseDto responseDto) {
        this.responseDto = responseDto;
    }

    public void setResponseDto(ChatInfoResponseDto responseDto) {
        this.responseDto = responseDto;
    }

    public Integer getChatId() {
        return responseDto.getResult().getMediaContainerStreams().get(0).getStreamId();
    }
}
