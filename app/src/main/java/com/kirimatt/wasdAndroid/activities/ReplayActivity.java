package com.kirimatt.wasdAndroid.activities;

import android.annotation.SuppressLint;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.kirimatt.wasdAndroid.R;
import com.kirimatt.wasdAndroid.dtos.chatMessages.Message;
import com.kirimatt.wasdAndroid.utils.MainActivityDataShare;
import com.kirimatt.wasdAndroid.views.adapters.ListViewMessagesAdapter;
import com.kirimatt.wasdAndroid.views.controllers.VideoLandController;
import com.kirimatt.wasdAndroid.views.controllers.VideoPortraitController;
import com.kirimatt.wasdAndroid.views.interfaces.CustomOnScrollListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;

public class ReplayActivity extends AppCompatActivity {

    private List<Message> messages;
    private VideoView videoPlayer;
    private long startReplayInMillis;
    private List<Message> listToViewMessages;
    private ListView listView;
    private MediaController mediaController;
    private final AtomicBoolean isChatAutoScrollEnabled = new AtomicBoolean(true);
    private ImageButton buttonChatAutoScroll;
    private ListAdapter adapter;

    public void generateListView() {

        adapter = new ListViewMessagesAdapter(
                getApplicationContext(),
                R.layout.activity_video_row,
                R.id.textViewName,
                listToViewMessages
        );

        listView.setAdapter(adapter);
        //TODO: on seekTo(millis) recreate?
        new Thread(() -> {
            int currentMessagePosition = messages.size() - 1;
            while (currentMessagePosition >= 0) {
                try {

                    if (isChatAutoScrollEnabled.get() && videoPlayer.getCurrentPosition() >=
                            messages.get(currentMessagePosition).getDateTime().getTime()
                                    - startReplayInMillis) {
                        int finalCurrentMessagePosition = currentMessagePosition;
                        runOnUiThread(() -> {
                            listToViewMessages.add(messages.get(finalCurrentMessagePosition));
                            listView.setAdapter(adapter);
                            listView.setSelection(adapter.getCount() - 1);
                        });
                        currentMessagePosition--;
                    }

                } catch (NullPointerException e) {
                    Log.d("CHAT", "is crashed in thread by NPE");
                    break;
                }
            }

        }).start();
    }

    public void decideOrientationContent(int displayMode) {
        if (displayMode == Configuration.ORIENTATION_PORTRAIT) {
            setContentView(R.layout.activity_video);
            return;
        }

        if (MainActivityDataShare.isChatActivated()) {
            setContentView(R.layout.activity_video_landscape);
            return;
        }

        setContentView(R.layout.activity_video_landscape_only_video);
        RelativeLayout.LayoutParams layoutParamsVideo = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
        );
        layoutParamsVideo.addRule(RelativeLayout.CENTER_VERTICAL);
        layoutParamsVideo.addRule(RelativeLayout.CENTER_HORIZONTAL);
        layoutParamsVideo.addRule(RelativeLayout.CENTER_IN_PARENT);

        View relativeContainer = findViewById(R.id.video_container);
        relativeContainer.setLayoutParams(layoutParamsVideo);
    }

    //Locked orientation is necessary to control content of view
    @SuppressLint("SourceLockedOrientationActivity")
    public void setMediaController(int displayMode) {
        if (displayMode == Configuration.ORIENTATION_PORTRAIT) {

            VideoPortraitController videoPortraitController = new VideoPortraitController(this);
            videoPortraitController.setButtonClickFull(
                    () -> setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE)
            );
            mediaController = videoPortraitController;

        } else {

            VideoLandController videoLandController = new VideoLandController(this);

            videoLandController.setButtonClickShrink(
                    () -> setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
            );

            videoLandController.setButtonClickChat(
                    () -> {
                        MainActivityDataShare.setChatActivated(
                                !MainActivityDataShare.isChatActivated()
                        );
                        clickChatInLandscape(MainActivityDataShare.isChatActivated());
                    }
            );

            mediaController = videoLandController;
        }
    }

    private void clickChatInLandscape(boolean isChatActivated) {
        if (isChatActivated) {
            float factor = getApplicationContext()
                    .getResources()
                    .getDisplayMetrics()
                    .density;

            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                    (int) (222 * factor),
                    ViewGroup.LayoutParams.MATCH_PARENT
            );
            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_END);
            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
            listView.setLayoutParams(layoutParams);

            if (!isChatAutoScrollEnabled.get()) {
                buttonChatAutoScroll.setEnabled(true);
                buttonChatAutoScroll.setVisibility(View.VISIBLE);
            }

            RelativeLayout.LayoutParams layoutParamsVideo = new RelativeLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
            );
            layoutParamsVideo.addRule(RelativeLayout.CENTER_VERTICAL);
            layoutParamsVideo.addRule(RelativeLayout.CENTER_HORIZONTAL);
            layoutParamsVideo.addRule(RelativeLayout.START_OF, R.id.listView);

            View relativeContainer = findViewById(R.id.video_container);
            relativeContainer.setLayoutParams(layoutParamsVideo);
            videoPlayer.setLayoutParams(layoutParamsVideo);

        } else {
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                    0,
                    ViewGroup.LayoutParams.MATCH_PARENT
            );
            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_END);
            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
            listView.setLayoutParams(layoutParams);

            buttonChatAutoScroll.setEnabled(false);
            buttonChatAutoScroll.setVisibility(View.GONE);

            RelativeLayout.LayoutParams layoutParamsVideo = new RelativeLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
            );
            layoutParamsVideo.addRule(RelativeLayout.CENTER_VERTICAL);
            layoutParamsVideo.addRule(RelativeLayout.CENTER_HORIZONTAL);
            layoutParamsVideo.addRule(RelativeLayout.CENTER_IN_PARENT);

            View relativeContainer = findViewById(R.id.video_container);
            relativeContainer.setLayoutParams(layoutParamsVideo);
            videoPlayer.setLayoutParams(layoutParamsVideo);
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int displayMode = getResources().getConfiguration().orientation;

        decideOrientationContent(displayMode);

        // Убрать ActionBar
        Objects.requireNonNull(getSupportActionBar()).hide();

        buttonChatAutoScroll = findViewById(R.id.buttonAutoScrollChat);

        buttonChatAutoScroll.setOnClickListener(view -> {
            buttonChatAutoScroll.setVisibility(View.GONE);
            buttonChatAutoScroll.setEnabled(false);
            isChatAutoScrollEnabled.set(true);
            listView.setSelection(adapter.getCount() - 1);
        });

        buttonChatAutoScroll.setVisibility(View.GONE);
        buttonChatAutoScroll.setEnabled(false);

        videoPlayer = findViewById(R.id.videoView);
        videoPlayer.setVideoURI(Uri.parse(MainActivityDataShare.getUriString()));

        setMediaController(displayMode);

        videoPlayer.setMediaController(mediaController);
        mediaController.setAnchorView((RelativeLayout) findViewById(R.id.video_container));
        videoPlayer.seekTo(MainActivityDataShare.getTimeToSeek());
        videoPlayer.start();

        listView = findViewById(R.id.listView);
        listView.setOnScrollListener((CustomOnScrollListener) (absListView, i) -> {
            buttonChatAutoScroll.setVisibility(View.VISIBLE);
            buttonChatAutoScroll.setEnabled(true);
            isChatAutoScrollEnabled.set(false);
        });

        messages = MainActivityDataShare.getMessages();
        startReplayInMillis = MainActivityDataShare.getStartReplay().getTime();

        listToViewMessages = new ArrayList<>(messages.size());
        generateListView();
    }

    @Override
    protected void onPause() {
        MainActivityDataShare.setTimeToSeek(videoPlayer.getCurrentPosition());
        messages = null;
        videoPlayer = null;
        startReplayInMillis = 0;
        listToViewMessages = null;
        listView = null;
        mediaController = null;
        super.onPause();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        );
    }
}
