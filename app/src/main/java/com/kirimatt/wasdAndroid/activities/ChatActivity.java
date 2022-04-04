package com.kirimatt.wasdAndroid.activities;

import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.kirimatt.wasdAndroid.R;
import com.kirimatt.wasdAndroid.adapters.ListViewAdapter;
import com.kirimatt.wasdAndroid.dtos.ChatMessages.Info;
import com.kirimatt.wasdAndroid.dtos.ChatMessages.Message;
import com.kirimatt.wasdAndroid.utils.ListOfMessages;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ChatActivity extends AppCompatActivity {

    private List<Message> messages;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_chat);

        ListView listView = findViewById(R.id.listView);

        messages = ListOfMessages.getMessages();

        runOnUiThread(() -> Toast.makeText(
                getApplicationContext(),
                String.valueOf(messages.size()),
                Toast.LENGTH_LONG
        ).show());

        generateListView(messages, this, listView);

        Message message = messages.get(0);
        Info info = message.getInfo();
        info.setMessage("ПАМ ПАРААААМ МОДЕРЫ ХУЕСОСЫ");
        info.setUserLogin("kirimatt");

        message.setInfo(info);

        messages.set(0, message);
        messages.add(1, message);
    }

    public static void generateListView(List<Message> messages, AppCompatActivity appCompatActivity,
                                        ListView listView) {

        ListAdapter adapter = new ListViewAdapter(
                appCompatActivity.getApplicationContext(),
                R.layout.row,
                R.id.textViewName,
                messages
        );

        listView.setAdapter(adapter);
    }
}
