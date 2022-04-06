package com.kirimatt.wasdAndroid.activities;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
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
import java.util.concurrent.CopyOnWriteArrayList;

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
                if (messages.get(currentMessagePosition).getDateTime().getTime() - startReplayInMillis <=
                        videoPlayer.getCurrentPosition()) {
                    int finalCurrentMessagePosition = currentMessagePosition;
                    runOnUiThread(() -> {
                        listToViewMessages.add(messages.get(finalCurrentMessagePosition));
                        listView.setAdapter(adapter);
                    });
                    currentMessagePosition--;
                }
            }

        }).start();
    }


}
