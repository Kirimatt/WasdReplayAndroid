package com.kirimatt.wasdAndroid.services;

import com.kirimatt.wasdAndroid.dtos.ChatMessages.ChatMessagesRequestDto;
import com.kirimatt.wasdAndroid.dtos.ChatMessages.ChatMessagesResponseDto;
import com.kirimatt.wasdAndroid.dtos.ChatMessages.Message;
import com.kirimatt.wasdAndroid.dtos.search.SearchRequestDto;
import com.kirimatt.wasdAndroid.dtos.search.SearchResponseDto;
import com.kirimatt.wasdAndroid.repositories.ChatMessagesRepository;
import com.kirimatt.wasdAndroid.repositories.SearchChannelRepository;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class WasdApiService {

    private static final SearchChannelRepository SEARCH_CHANNEL_REPOSITORY;
    private static final ChatMessagesRepository CHAT_MESSAGES_REPOSITORY;

    static {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.level(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://wasd.tv/api/")
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();

        SEARCH_CHANNEL_REPOSITORY = retrofit.create(SearchChannelRepository.class);
        CHAT_MESSAGES_REPOSITORY = retrofit.create(ChatMessagesRepository.class);
    }

    private WasdApiService() {
    }

    public static SearchResponseDto getChannelsByName(SearchRequestDto requestDto) {
        return SEARCH_CHANNEL_REPOSITORY.getChannelsByName(
                requestDto.getLimit(),
                requestDto.getOffset(),
                requestDto.getName()
        ).onErrorReturn(e -> new SearchResponseDto())
                .blockingFirst();
    }

    public static ChatMessagesResponseDto getChatMessages(ChatMessagesRequestDto requestDTO) {
        return CHAT_MESSAGES_REPOSITORY.getChatInfo(
                requestDTO.getChatId(),
                requestDTO.getLimit(),
                requestDTO.getOffset()
        )
                .onErrorReturn(e -> new ChatMessagesResponseDto())
                .blockingFirst();
    }

    public static List<Message> getAllChatMessages(
            ChatMessagesRequestDto requestDTO) {

        List<Message> responseDtoList = new ArrayList<>();

        ChatMessagesResponseDto responseDto;
        do {
            responseDto = getChatMessages(requestDTO);
            responseDtoList.addAll(responseDto.getMessages());
            requestDTO.incrementOffset();

        } while (!responseDto.messages().isEmpty());

        return responseDtoList;
    }
}
