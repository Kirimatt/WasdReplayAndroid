package com.kirimatt.wasdAndroid.views.controllers;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.MediaController;

import com.kirimatt.wasdAndroid.R;
import com.kirimatt.wasdAndroid.views.interfaces.ButtonClick;

public class VideoPortraitController extends MediaController {

    private ButtonClick buttonClickFull;

    public VideoPortraitController(Context context) {
        super(context);
    }

    @Override
    public void setAnchorView(View view) {

        super.setAnchorView(view);

        Button fullSizeButton = new Button(this.getContext());
        fullSizeButton.setCompoundDrawablesWithIntrinsicBounds(
                R.drawable.baseline_fullscreen_24,
                0,
                0,
                0
        );
        fullSizeButton.setBackground(null);

        FrameLayout.LayoutParams layoutParamsShrinkSize = new FrameLayout.LayoutParams(
                android.view.ViewGroup.LayoutParams.WRAP_CONTENT,
                android.view.ViewGroup.LayoutParams.WRAP_CONTENT
        );
        layoutParamsShrinkSize.gravity = Gravity.END;
        fullSizeButton.setLayoutParams(layoutParamsShrinkSize);
        fullSizeButton.setOnClickListener((view1 -> buttonClickFull.click()));
        addView(fullSizeButton);
    }

    public void setButtonClickFull(ButtonClick buttonClickFull) {
        this.buttonClickFull = buttonClickFull;
    }

}
