package com.kirimatt.wasdAndroid.dtos.channelsInfo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class MediaContainerStream implements Serializable {
    @SerializedName("stream_id")
    private int streamId;
    @SerializedName("stream_total_viewers")
    private int streamTotalViewers;
    @SerializedName("stream_current_viewers")
    private int streamCurrentViewers;
    @SerializedName("stream_current_active_viewers")
    private int streamCurrentActiveViewers;
    @SerializedName("stream_media")
    private List<StreamMedia> streamMedia;

    public int getStreamId() {
        return streamId;
    }

    public void setStreamId(int streamId) {
        this.streamId = streamId;
    }

    public int getStreamTotalViewers() {
        return streamTotalViewers;
    }

    public void setStreamTotalViewers(int streamTotalViewers) {
        this.streamTotalViewers = streamTotalViewers;
    }

    public int getStreamCurrentViewers() {
        return streamCurrentViewers;
    }

    public void setStreamCurrentViewers(int streamCurrentViewers) {
        this.streamCurrentViewers = streamCurrentViewers;
    }

    public int getStreamCurrentActiveViewers() {
        return streamCurrentActiveViewers;
    }

    public void setStreamCurrentActiveViewers(int streamCurrentActiveViewers) {
        this.streamCurrentActiveViewers = streamCurrentActiveViewers;
    }

    public List<StreamMedia> getStreamMedia() {
        return streamMedia;
    }

    public void setStreamMedia(List<StreamMedia> streamMedia) {
        this.streamMedia = streamMedia;
    }
}