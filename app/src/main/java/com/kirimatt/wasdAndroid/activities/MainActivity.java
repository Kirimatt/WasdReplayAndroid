package com.kirimatt.wasdAndroid.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.kirimatt.wasdAndroid.R;
import com.kirimatt.wasdAndroid.dtos.ChatMessages.ChatMessagesRequestDto;
import com.kirimatt.wasdAndroid.dtos.ChatMessages.Message;
import com.kirimatt.wasdAndroid.dtos.chatInfo.ChatInfoResponseDto;
import com.kirimatt.wasdAndroid.services.ChatInfoService;
import com.kirimatt.wasdAndroid.services.ChatMessagesService;
import com.kirimatt.wasdAndroid.utils.MainActivityDataShare;
import com.kirimatt.wasdAndroid.utils.UrlConverterToRequestChatInfo;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText editTextUrl;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextUrl = findViewById(R.id.editTextUrl);
        Button buttonStartChat = findViewById(R.id.buttonStartChat);
        progressBar = findViewById(R.id.progressBar);

        buttonStartChat.setOnClickListener(view -> {
            if (editTextUrl.getText().length() > 0)
                startChat();
        });

        //TODO: на время тестов
        String resource = "https://wasd.tv/serega_pirat/videos?record=936665";

        editTextUrl.setText(resource);
    }

    public void startChat() {
        new Thread(() ->{
            runOnUiThread(() -> progressBar.setVisibility(View.VISIBLE));

            //Получение chatId из URL трансляции
            ChatInfoResponseDto responseDto = ChatInfoService.getChatInfo(
                    UrlConverterToRequestChatInfo.convert(
                            editTextUrl.getText().toString()
                    )
            );

            //Проверка chatId
            if (responseDto.getResult().getMediaContainerStreams() == null
                    || responseDto.getResult().getMediaContainerStreams().isEmpty()) {

                runOnUiThread(() -> Toast.makeText(
                        getApplicationContext(),
                        "Произошла ошибка считывании ссылки",
                        Toast.LENGTH_LONG
                ).show());

                return;
            }

            //Получение сообщений из трансляции
            List<Message> messages = ChatMessagesService.getAllChatMessages(
                    new ChatMessagesRequestDto(responseDto)
            );

            runOnUiThread(() -> progressBar.setVisibility(View.INVISIBLE));

            //Проверка загруженных сообщений
            if (messages.isEmpty()) {
                runOnUiThread(() -> Toast.makeText(
                        getApplicationContext(),
                        "Произошла ошибка при загрузке сообщений",
                        Toast.LENGTH_LONG
                ).show());

                return;
            }

            //Запуск новой активности
            runOnUiThread(() -> {
                //Статический класс для содержания и передачи сообщений
                //TODO: shared preferences?? Put extra не работает, слишком много памяти
                MainActivityDataShare.setMessages(messages);
                MainActivityDataShare.setStartReplay(responseDto.getResult().getPublishedAt());
                MainActivityDataShare.setUriString(
                        responseDto.getResult()
                        .getMediaContainerStreams().get(0)
                        .getStreamMedia().get(0)
                        .getMediaMeta()
                        .getMediaArchiveUrl()
                );
                Intent intent = new Intent(this, ChatActivity.class);
                startActivity(intent);
            });

        }).start();
    }
}