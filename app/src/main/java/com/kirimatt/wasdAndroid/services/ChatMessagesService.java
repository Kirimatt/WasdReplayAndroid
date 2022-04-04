package com.kirimatt.wasdAndroid.services;

import com.kirimatt.wasdAndroid.dtos.ChatMessages.ChatMessagesRequestDto;
import com.kirimatt.wasdAndroid.dtos.ChatMessages.ChatMessagesResponseDto;
import com.kirimatt.wasdAndroid.dtos.ChatMessages.Message;
import com.kirimatt.wasdAndroid.repositories.ChatMessagesRepository;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ChatMessagesService {

    private static final ChatMessagesRepository CHAT_MESSAGES_REPOSITORY;

    static {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.level(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://wasd.tv/api/chat/")
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();

        CHAT_MESSAGES_REPOSITORY = retrofit.create(ChatMessagesRepository.class);
    }

    private ChatMessagesService() {
    }

    public static ChatMessagesResponseDto getChatMessages(ChatMessagesRequestDto requestDTO,
                                                          Integer offset) {
        return CHAT_MESSAGES_REPOSITORY.getChatInfo(requestDTO.getChatId(), 500, offset)
                .onErrorReturn(e -> new ChatMessagesResponseDto())
                .blockingFirst();
    }

    public static List<Message> getAllChatMessages(
            ChatMessagesRequestDto requestDTO) {

        int offset = 0;
        List<Message> responseDtoList = new ArrayList<>();

        ChatMessagesResponseDto responseDto;
        do {
            responseDto = getChatMessages(requestDTO, offset);

            responseDtoList.addAll(responseDto.getResult());
            offset += 500;

        } while (!responseDto.getResult().isEmpty());


        return responseDtoList;
    }
}
