package com.kirimatt.wasdAndroid.repositories;

import com.kirimatt.wasdAndroid.dtos.search.SearchResponseDto;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SearchChannelRepository {
    @GET("search/profiles")
    Observable<SearchResponseDto> getChannelsByName(
            @Query("limit") Integer limit,
            @Query("offset") Integer offset,
            @Query("search_phrase") String searchPhrase
    );
}
