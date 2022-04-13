package com.kirimatt.wasdAndroid.repositories;

import com.kirimatt.wasdAndroid.dtos.channelsInfo.ChannelPreviewResponseDto;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ChannelPreviewRepository {
    @GET("media-containers/plain")
    Observable<ChannelPreviewResponseDto> getPreviewsById(
            @Query("limit") Integer limit,
            @Query("offset") Integer offset,
            @Query("channel_id") Integer channelId
    );
}
