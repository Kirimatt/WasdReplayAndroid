package com.kirimatt.wasdAndroid.activities;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.media.MediaPlayer;
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
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.kirimatt.wasdAndroid.R;
import com.kirimatt.wasdAndroid.dtos.chatMessages.Message;
import com.kirimatt.wasdAndroid.dtos.settings.AllSettings;
import com.kirimatt.wasdAndroid.utils.MainActivityDataShare;
import com.kirimatt.wasdAndroid.views.adapters.ListViewMessagesAdapter;
import com.kirimatt.wasdAndroid.views.controllers.VideoLandController;
import com.kirimatt.wasdAndroid.views.controllers.VideoPortraitController;
import com.kirimatt.wasdAndroid.views.interfaces.CustomOnScrollListener;
import com.kirimatt.wasdAndroid.views.video.VideoViewWithCustomSeek;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;

public class ReplayActivity extends AppCompatActivity {

    private static final int TIME_OFFSET_DELAY_MILLIS = 15000;
    private final AtomicBoolean isChatAutoScrollEnabled = new AtomicBoolean(true);
    private ImageButton buttonChatAutoScroll;
    private List<Message> listToViewMessages;
    private List<Message> messages;
    private ListAdapter adapter;
    private ListView listView;
    private MediaController mediaController;
    private VideoViewWithCustomSeek videoPlayer;
    private long startReplayInMillis;
    private int currentMessagePosition;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int displayMode = getResources().getConfiguration().orientation;

        decideOrientationContent(displayMode);

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

        videoPlayer.setOnPreparedListener(mediaPlayer -> {
            mediaController.setAnchorView(findViewById(R.id.video_container));
            videoPlayer.seekTo(MainActivityDataShare.getTimeToSeek());
            videoPlayer.start();
        });

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

        videoPlayer.setButtonClick(this::onSeekToPrev);

        MediaPlayer.OnErrorListener myVideoViewErrorListener = (mp, what, extra) -> {

            String errWhat;
            switch (what){
                case MediaPlayer.MEDIA_ERROR_UNKNOWN:
                    errWhat = "MEDIA_ERROR_UNKNOWN";
                    break;
                case MediaPlayer.MEDIA_ERROR_SERVER_DIED:
                    errWhat = "MEDIA_ERROR_SERVER_DIED";
                    break;
                default: errWhat = "unknown what";
            }

            String errExtra;
            switch (extra){
                case MediaPlayer.MEDIA_ERROR_IO:
                    errExtra = "MEDIA_ERROR_IO";
                    break;
                case MediaPlayer.MEDIA_ERROR_MALFORMED:
                    errExtra = "MEDIA_ERROR_MALFORMED";
                    break;
                case MediaPlayer.MEDIA_ERROR_UNSUPPORTED:
                    errExtra = "MEDIA_ERROR_UNSUPPORTED";
                    break;
                case MediaPlayer.MEDIA_ERROR_TIMED_OUT:
                    errExtra = "MEDIA_ERROR_TIMED_OUT";
                    break;
                default:
                    errExtra = "...others";

            }

            Toast.makeText(getApplicationContext(),
                    "Error!!!\n" +
                            "what: " + errWhat + "\n" +
                            "extra: " + errExtra,
                    Toast.LENGTH_LONG).show();
            return true;
        };

        videoPlayer.setOnErrorListener(myVideoViewErrorListener);
    }

    public void generateListView() {

        SharedPreferences sharedPref = getSharedPreferences("setting", MODE_PRIVATE);
        AllSettings allSettings = new AllSettings(sharedPref);

        Log.d("ALLSETTINGS", allSettings.toString());

        adapter = new ListViewMessagesAdapter(
                getApplicationContext(),
                R.layout.activity_video_row,
                R.id.textViewName,
                listToViewMessages,
                allSettings
        );

        listView.setAdapter(adapter);

        new Thread(() -> {
            currentMessagePosition = messages.size() - 1;
            while (currentMessagePosition >= 0) {
                try {

                    if (isChatAutoScrollEnabled.get() && videoPlayer.getCurrentPosition()
                            + TIME_OFFSET_DELAY_MILLIS >=
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

                } catch (NullPointerException | IllegalStateException e) {
                    Log.d("CHAT", "is crashed in thread by NPE or ISE");
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
                    () -> {
                        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                        MainActivityDataShare.setChatActivated(false);
                    }
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
        setListViewWidth(isChatActivated);
        setButtonChatAutoScroll(isChatActivated);
        setVideoContainer(isChatActivated);
    }

    public void setButtonChatAutoScroll(boolean isChatActivated) {
        if (isChatActivated && !isChatAutoScrollEnabled.get()) {
            buttonChatAutoScroll.setEnabled(true);
            buttonChatAutoScroll.setVisibility(View.VISIBLE);
        } else {
            buttonChatAutoScroll.setEnabled(false);
            buttonChatAutoScroll.setVisibility(View.GONE);
        }
    }

    public void setListViewWidth(boolean isChatActivated) {
        float factor = getApplicationContext()
                .getResources()
                .getDisplayMetrics()
                .density;

        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                isChatActivated ? (int) (222 * factor) : 0,
                ViewGroup.LayoutParams.MATCH_PARENT
        );
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_END);
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        listView.setLayoutParams(layoutParams);
    }

    public void setVideoContainer(boolean isChatActivated) {
        if (isChatActivated) {
            RelativeLayout.LayoutParams layoutParamsVideo = new RelativeLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
            );
            layoutParamsVideo.addRule(RelativeLayout.CENTER_VERTICAL);
            layoutParamsVideo.addRule(RelativeLayout.START_OF, R.id.listView);

            View relativeContainer = findViewById(R.id.video_container);
            relativeContainer.setLayoutParams(layoutParamsVideo);
        } else {
            RelativeLayout.LayoutParams layoutParamsVideo = new RelativeLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
            );
            layoutParamsVideo.addRule(RelativeLayout.CENTER_VERTICAL);
            layoutParamsVideo.addRule(RelativeLayout.CENTER_IN_PARENT);
            layoutParamsVideo.addRule(RelativeLayout.CENTER_HORIZONTAL);

            View relativeContainer = findViewById(R.id.video_container);
            relativeContainer.setLayoutParams(layoutParamsVideo);
        }
    }

    private void onSeekToPrev() {
        listToViewMessages.clear();
        currentMessagePosition = messages.size() - 1;

        listView.setAdapter(adapter);
    }

    @Override
    protected void onPause() {
        MainActivityDataShare.setTimeToSeek(videoPlayer.getCurrentPosition());
        super.onPause();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        //TODO: find not deprecated method and variables
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
