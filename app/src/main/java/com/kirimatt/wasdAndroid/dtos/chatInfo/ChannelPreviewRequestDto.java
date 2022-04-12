package com.kirimatt.wasdAndroid.dtos.chatInfo;

public class ChannelPreviewRequestDto {
    private int limit = 500;
    private int offset = 0;
    private int channelId;

    public ChannelPreviewRequestDto(int channelId) {
        this.channelId = channelId;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getChannelId() {
        return channelId;
    }

    public void setChannelId(int channelId) {
        this.channelId = channelId;
    }
}
