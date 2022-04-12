package com.kirimatt.wasdAndroid.dtos.search;

public class SearchRequestDto {
    private Integer limit = 500;
    private Integer offset = 0;
    private String name;

    public SearchRequestDto(Integer limit, Integer offset, String name) {
        this.limit = limit;
        this.offset = offset;
        this.name = name;
    }

    public SearchRequestDto(String name) {
        this.name = name;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
