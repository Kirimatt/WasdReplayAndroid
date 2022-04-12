package com.kirimatt.wasdAndroid.dtos.search;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class SearchResponseDto implements Serializable {
    @SerializedName("result")
    private ResultSearch resultSearch;

    public ResultSearch getResult() {
        return resultSearch;
    }

    public void setResult(ResultSearch resultSearch) {
        this.resultSearch = resultSearch;
    }
}
