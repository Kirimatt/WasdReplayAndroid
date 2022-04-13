package com.kirimatt.wasdAndroid.services;

import com.kirimatt.wasdAndroid.dtos.channelsInfo.ChannelPreviewRequestDto;
import com.kirimatt.wasdAndroid.dtos.channelsInfo.ChannelPreviewResponseDto;
import com.kirimatt.wasdAndroid.dtos.channelsInfo.ChatInfoRequestDto;
import com.kirimatt.wasdAndroid.dtos.channelsInfo.ChatInfoResponseDto;
import com.kirimatt.wasdAndroid.repositories.ChannelPreviewRepository;
import com.kirimatt.wasdAndroid.repositories.ChatInfoRepository;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class WasdV2ApiService {

    private static final ChatInfoRepository CHAT_INFO_REPOSITORY;
    private static final ChannelPreviewRepository CHANNEL_PREVIEW_REPOSITORY;

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
        CHANNEL_PREVIEW_REPOSITORY = retrofit.create(ChannelPreviewRepository.class);
    }

    private WasdV2ApiService() {
    }

    public static ChatInfoResponseDto getChatInfo(ChatInfoRequestDto requestDTO) {
        return CHAT_INFO_REPOSITORY.getChatInfo(requestDTO.getReplayNum())
                .onErrorReturn(e -> new ChatInfoResponseDto())
                .blockingFirst();
    }

    public static ChannelPreviewResponseDto getPreviews(ChannelPreviewRequestDto requestDto) {
        return CHANNEL_PREVIEW_REPOSITORY.getPreviewsById(
                requestDto.getLimit(),
                requestDto.getOffset(),
                requestDto.getChannelId()
        )
                .onErrorReturn(e -> new ChannelPreviewResponseDto())
                .blockingFirst();
    }
}
