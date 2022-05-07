package com.kirimatt.wasdAndroid.views.controllers;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.MediaController;
import android.widget.RelativeLayout;

import com.kirimatt.wasdAndroid.R;
import com.kirimatt.wasdAndroid.views.interfaces.ButtonClick;

public class VideoLandController extends MediaController {

    private Button chatButton;
    private ButtonClick buttonClickChat;
    private ButtonClick buttonClickShrink;

    public VideoLandController(Context context) {
        super(context);
    }

    private void buttonChatSetVisibleAndEnabled(boolean isGoingVisible) {
        chatButton.setEnabled(isGoingVisible);
        chatButton.setVisibility(isGoingVisible ? VISIBLE : GONE);
    }

    @Override
    public void setAnchorView(View view) {

        super.setAnchorView(view);

        Button shrinkSizeButton = new Button(this.getContext());
        shrinkSizeButton.setCompoundDrawablesWithIntrinsicBounds(
                R.drawable.ic_shrink_svgrepo_com,
                0,
                0,
                0
        );
        shrinkSizeButton.setBackground(null);

        FrameLayout.LayoutParams layoutParamsShrinkSize = new FrameLayout.LayoutParams(
                android.view.ViewGroup.LayoutParams.WRAP_CONTENT,
                android.view.ViewGroup.LayoutParams.WRAP_CONTENT
        );
        layoutParamsShrinkSize.gravity = Gravity.END;
        shrinkSizeButton.setLayoutParams(layoutParamsShrinkSize);
        shrinkSizeButton.setOnClickListener((view1 -> buttonClickShrink.click()));
        addView(shrinkSizeButton);

        float factor = view.getContext()
                .getResources()
                .getDisplayMetrics()
                .density;

        RelativeLayout relativeLayout = (RelativeLayout) view;

        chatButton = new Button(this.getContext());

        RelativeLayout.LayoutParams layoutParamsChat = new RelativeLayout.LayoutParams(
                android.view.ViewGroup.LayoutParams.WRAP_CONTENT,
                android.view.ViewGroup.LayoutParams.WRAP_CONTENT
        );
        layoutParamsChat.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        layoutParamsChat.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        chatButton.setBackground(null);
        chatButton.setCompoundDrawablesWithIntrinsicBounds(
                R.drawable.ic_chat_svgrepo_com,
                0,
                0,
                0
        );
        chatButton.setLayoutParams(layoutParamsChat);
        chatButton.setPaddingRelative(0,
                (int) (25 * factor),
                (int) (25 * factor),
                0
        );
        chatButton.setOnClickListener((view1 -> buttonClickChat.click()));
        buttonChatSetVisibleAndEnabled(false);
        relativeLayout.addView(chatButton);

    }

    public void setButtonClickChat(ButtonClick buttonClickChat) {
        this.buttonClickChat = buttonClickChat;
    }

    public void setButtonClickShrink(ButtonClick buttonClickShrink) {
        this.buttonClickShrink = buttonClickShrink;
    }

    @Override
    public void show(int timeout) {
        //TODO: Activity com.kirimatt.wasdAndroid.activities.ChatActivity has
        // leaked window DecorView@5fa91f5[] that was originally added here
        // При перемене layout (из-за ориентации) все еще вызывается метод show(int)
        super.show(timeout);
        buttonChatSetVisibleAndEnabled(true);
    }

    @Override
    public void show() {

        super.show();
        buttonChatSetVisibleAndEnabled(true);
    }

    @Override
    public void hide() {
        buttonChatSetVisibleAndEnabled(false);
        super.hide();
    }


}
