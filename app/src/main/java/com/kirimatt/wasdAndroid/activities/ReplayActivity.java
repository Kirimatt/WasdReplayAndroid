package com.kirimatt.wasdAndroid.activities;

import android.annotation.SuppressLint;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.VideoView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.kirimatt.wasdAndroid.R;
import com.kirimatt.wasdAndroid.dtos.ChatMessages.Message;
import com.kirimatt.wasdAndroid.utils.MainActivityDataShare;
import com.kirimatt.wasdAndroid.views.adapters.ListViewMessagesAdapter;
import com.kirimatt.wasdAndroid.views.controllers.VideoLandController;
import com.kirimatt.wasdAndroid.views.controllers.VideoPortraitController;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ReplayActivity extends AppCompatActivity {

    private List<Message> messages;
    private VideoView videoPlayer;
    private long startReplayInMillis;
    private List<Message> listToViewMessages;
    private ListView listView;
    private MediaController mediaController;

    //Locked orientation is necessary to control content of view
    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int displayMode = getResources().getConfiguration().orientation;

        if (displayMode == Configuration.ORIENTATION_PORTRAIT) {
            setContentView(R.layout.activity_video);
        } else {
            if (MainActivityDataShare.isChatActivated())
                setContentView(R.layout.activity_video_landscape);
            else
                setContentView(R.layout.activity_video_landscape_only_video);
        }

        // Убрать ActionBar
        Objects.requireNonNull(getSupportActionBar()).hide();

        videoPlayer = findViewById(R.id.videoView);
        videoPlayer.setVideoURI(Uri.parse(MainActivityDataShare.getUriString()));

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
                        MainActivityDataShare.setChatActivated(!MainActivityDataShare.isChatActivated());
                        recreate();
                    }
            );


            mediaController = videoLandController;
        }
        videoPlayer.setMediaController(mediaController);
        mediaController.setAnchorView((RelativeLayout) findViewById(R.id.video_container));
        videoPlayer.seekTo(MainActivityDataShare.getTimeToSeek());
        videoPlayer.start();

        listView = findViewById(R.id.listView);

        messages = MainActivityDataShare.getMessages();
        startReplayInMillis = MainActivityDataShare.getStartReplay().getTime();

        listToViewMessages = new ArrayList<>(messages.size());

        generateListView();
    }

    public void generateListView() {

        ListAdapter adapter = new ListViewMessagesAdapter(
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
                if (messages == null) {
                    break;
                }

                if (messages.get(currentMessagePosition).getDateTime().getTime() - startReplayInMillis <=
                        videoPlayer.getCurrentPosition()) {
                    int finalCurrentMessagePosition = currentMessagePosition;
                    runOnUiThread(() -> {
                        listToViewMessages.add(messages.get(finalCurrentMessagePosition));
                        listView.setAdapter(adapter);
                        listView.setSelection(adapter.getCount() - 1);
                    });
                    currentMessagePosition--;
                }
            }

        }).start();
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
}
