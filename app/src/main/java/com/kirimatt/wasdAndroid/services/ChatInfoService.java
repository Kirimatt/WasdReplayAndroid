package com.kirimatt.wasdAndroid.services;

import com.kirimatt.wasdAndroid.dtos.chatInfo.ChatInfoRequestDto;
import com.kirimatt.wasdAndroid.dtos.chatInfo.ChatInfoResponseDto;
import com.kirimatt.wasdAndroid.repositories.ChatInfoRepository;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ChatInfoService {

    private static final ChatInfoRepository CHAT_INFO_REPOSITORY;

    static {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.level(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://wasd.tv/api/v2/")
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();

        CHAT_INFO_REPOSITORY = retrofit.create(ChatInfoRepository.class);
    }

    private ChatInfoService() {
    }

    public static ChatInfoResponseDto getChatInfo(ChatInfoRequestDto requestDTO) {
        return CHAT_INFO_REPOSITORY.getChatInfo(requestDTO.getReplayNum())
                .onErrorReturn(e -> new ChatInfoResponseDto())
                .blockingFirst();
    }
}
