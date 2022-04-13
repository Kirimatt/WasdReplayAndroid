package com.kirimatt.wasdAndroid.repositories;

import com.kirimatt.wasdAndroid.dtos.chatMessages.ChatMessagesResponseDto;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ChatMessagesRepository {
    @GET("chat/streams/{chatId}/messages")
    Observable<ChatMessagesResponseDto> getChatInfo(
            @Path("chatId") Integer chatId,
            @Query("limit") Integer limit,
            @Query("offset") Integer offset
    );
}
