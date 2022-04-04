package com.kirimatt.wasdAndroid.utils;

import com.kirimatt.wasdAndroid.dtos.chatInfo.ChatInfoRequestDto;

public class UrlConverterToRequestChatInfo {

    public static ChatInfoRequestDto convert(String url) {
        return new ChatInfoRequestDto(
                Integer.valueOf(url.replaceAll("[^\\d]", ""))
        );
    }
}
