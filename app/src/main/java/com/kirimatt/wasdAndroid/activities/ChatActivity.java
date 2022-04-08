package com.kirimatt.wasdAndroid.activities;

import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.kirimatt.wasdAndroid.R;
import com.kirimatt.wasdAndroid.adapters.ListViewAdapter;
import com.kirimatt.wasdAndroid.dtos.ChatMessages.Message;
import com.kirimatt.wasdAndroid.utils.MainActivityDataShare;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;

public class ChatActivity extends AppCompatActivity {

    private List<Message> messages;
    private VideoView videoPlayer;
    private long startReplayInMillis;
    private List<Message> listToViewMessages;
    private ListView listView;
    private MediaController mediaController;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int displayMode = getResources().getConfiguration().orientation;

        if (displayMode == Configuration.ORIENTATION_PORTRAIT) {
            setContentView(R.layout.activity_chat);
        } else {
            setContentView(R.layout.activity_chat_landscape);
        }

        // Убрать ActionBar
        Objects.requireNonNull(getSupportActionBar()).hide();

        videoPlayer = findViewById(R.id.videoView);
        videoPlayer.setVideoURI(Uri.parse(MainActivityDataShare.getUriString()));

        if (mediaController == null) {
            //creates a MediaController object if one is not present
            mediaController = new MediaController(this);
            videoPlayer.setMediaController(mediaController);
            mediaController.setAnchorView(videoPlayer);
        }

        videoPlayer.setOnErrorListener((mp, what, extra) -> {
            Toast.makeText(getApplicationContext(),
                    "Oops an error occurred while playing the video!",
                    Toast.LENGTH_LONG).show();
            return false;
        });

        videoPlayer.seekTo(MainActivityDataShare.getTimeToSeek());
        videoPlayer.start();

        listView = findViewById(R.id.listView);

        messages = MainActivityDataShare.getMessages();
        startReplayInMillis = MainActivityDataShare.getStartReplay().getTime();

        listToViewMessages = new ArrayList<>(messages.size());

        generateListView();
    }

    public void generateListView() {

        ListAdapter adapter = new ListViewAdapter(
                getApplicationContext(),
                R.layout.row,
                R.id.textViewName,
                listToViewMessages
        );

        listView.setAdapter(adapter);

        new Thread(() -> {
            int currentMessagePosition = messages.size() - 1;
            while (currentMessagePosition >= 0) {
                try {
                    if (messages.get(currentMessagePosition).getDateTime().getTime() - startReplayInMillis <=
                            videoPlayer.getCurrentPosition()) {
                        int finalCurrentMessagePosition = currentMessagePosition;
                        runOnUiThread(() -> {
                            listToViewMessages.add(messages.get(finalCurrentMessagePosition));
                            listView.setAdapter(adapter);
                            listView.setSelection(adapter.getCount() - 1);
                        });
                        currentMessagePosition--;
                        MainActivityDataShare.setTimeToSeek(videoPlayer.getCurrentPosition());
                    }
                } catch (IllegalStateException | IllegalArgumentException exception) {
                    Log.d("ChatThread", "An error occurred while auto-scrolling chat. " +
                            "Maybe it catches by pressing the previous activity button " +
                            "while the thread wants to add new messages.");
                    break;
                }
            }

        }).start();
    }
}
