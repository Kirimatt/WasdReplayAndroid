package com.kirimatt.wasdAndroid.activities;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.kirimatt.wasdAndroid.R;
import com.kirimatt.wasdAndroid.adapters.ListViewAdapter;
import com.kirimatt.wasdAndroid.dtos.ChatMessages.Message;
import com.kirimatt.wasdAndroid.utils.MainActivityDataShare;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ChatActivity extends AppCompatActivity {

    private List<Message> messages;
    private VideoView videoPlayer;
    private long startReplayInMillis;
    private List<Message> listToViewMessages;
    private ListView listView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_chat);

        // Убрать ActionBar
        Objects.requireNonNull(getSupportActionBar()).hide();

        videoPlayer = findViewById(R.id.videoView);
        videoPlayer.setVideoURI(Uri.parse(MainActivityDataShare.getUriString()));

        videoPlayer.setMediaController(new MediaController(this));

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
                    }
                } catch (IllegalStateException exception) {
                    Log.d("ChatThread", "An error occurred while auto-scrolling chat. " +
                            "Maybe it catches by pressing the previous activity button " +
                            "while the thread wants to add new messages.");
                    break;
                }
            }

        }).start();
    }
}
