package com.kirimatt.wasdAndroid.repositories;

import com.kirimatt.wasdAndroid.dtos.channelsInfo.ChatInfoResponseDto;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ChatInfoRepository {
    @GET("media-containers/{ReplayNum}")
    Observable<ChatInfoResponseDto> getChatInfo(@Path("ReplayNum") Integer replayNum);
}
