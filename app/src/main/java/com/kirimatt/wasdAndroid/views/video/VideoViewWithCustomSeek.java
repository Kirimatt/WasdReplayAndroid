package com.kirimatt.wasdAndroid.views.video;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.VideoView;

import com.kirimatt.wasdAndroid.views.interfaces.ButtonClick;

public class VideoViewWithCustomSeek extends VideoView {
    private ButtonClick buttonClick;

    public VideoViewWithCustomSeek(Context context) {
        super(context);
    }

    public VideoViewWithCustomSeek(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public VideoViewWithCustomSeek(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public VideoViewWithCustomSeek(Context context, AttributeSet attrs, int defStyleAttr,
                                   int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public ButtonClick getButtonClick() {
        return buttonClick;
    }

    public void setButtonClick(ButtonClick buttonClick) {
        this.buttonClick = buttonClick;
    }

    @Override
    public void seekTo(int mSec) {
        if (mSec < getCurrentPosition()) {
            super.seekTo(mSec);
            buttonClick.click();

            return;
        }
        super.seekTo(mSec);
    }
}
