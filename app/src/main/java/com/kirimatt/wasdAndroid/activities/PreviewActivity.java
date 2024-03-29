package com.kirimatt.wasdAndroid.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import com.kirimatt.wasdAndroid.R;
import com.kirimatt.wasdAndroid.dtos.channelsInfo.ChannelPreviewRequestDto;
import com.kirimatt.wasdAndroid.dtos.channelsInfo.ChannelPreviewResponseDto;
import com.kirimatt.wasdAndroid.dtos.channelsInfo.ResultPreviews;
import com.kirimatt.wasdAndroid.dtos.chatMessages.ChatMessagesRequestDto;
import com.kirimatt.wasdAndroid.dtos.chatMessages.Message;
import com.kirimatt.wasdAndroid.dtos.search.Row;
import com.kirimatt.wasdAndroid.services.WasdApiService;
import com.kirimatt.wasdAndroid.services.WasdV2ApiService;
import com.kirimatt.wasdAndroid.utils.MainActivityDataShare;
import com.kirimatt.wasdAndroid.views.adapters.ListViewPreviewAdapter;

import java.util.List;

public class PreviewActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private Row row;
    private ListView listViewPreviews;
    private ListViewPreviewAdapter adapter;

    public void initPreviews() {
        new Thread(() -> {
            runOnUiThread(() -> onUiLoad(true));

            ChannelPreviewResponseDto responseDto =
                    WasdV2ApiService.getPreviews(new ChannelPreviewRequestDto(row.getChannelId()));

            List<ResultPreviews> previews = responseDto.getResults();

            previews.removeIf(resultPreviews -> resultPreviews
                    .getMediaContainerStatus()
                    .equals("RUNNING")
            );

            runOnUiThread(() -> {
                adapter = new ListViewPreviewAdapter(
                        getApplicationContext(),
                        R.layout.activity_preview_row,
                        R.id.textViewName,
                        previews
                );

                listViewPreviews.setAdapter(adapter);

                onUiLoad(false);
            });

        }).start();
    }

    public void onUiLoad(boolean isLoading) {
        progressBar.setVisibility(isLoading ? View.VISIBLE : View.GONE);
        progressBar.animate();
        listViewPreviews.setEnabled(!isLoading);
    }

    public void startChat(int position) {
        new Thread(() -> {
            runOnUiThread(() -> onUiLoad(true));

            ResultPreviews resultPreviews = adapter.getItem(position);

            //Получение сообщений из трансляции
            List<Message> messages = WasdApiService.getAllChatMessages(
                    new ChatMessagesRequestDto(resultPreviews
                            .getMediaContainerStreams().get(0).getStreamId())
            );

            Log.d("published", resultPreviews.getPublishedAt().toString());
            Log.d("created", resultPreviews.getCreatedAt().toString());

            runOnUiThread(() -> {
                //Запуск новой активности
                //Статический класс для содержания и передачи сообщений
                MainActivityDataShare.setMessages(messages);
                MainActivityDataShare.setStartReplay(resultPreviews.getPublishedAt());
                MainActivityDataShare.setTimeToSeek(0);
                MainActivityDataShare.setChatActivated(false);
                MainActivityDataShare.setUriString(
                        resultPreviews
                                .getMediaContainerStreams().get(0)
                                .getStreamMedia().get(0)
                                .getMediaMeta()
                                .getMediaArchiveUrl()
                );
                MainActivityDataShare.setCreatedDelay((resultPreviews.getPublishedAt().getTime()
                        - resultPreviews.getCreatedAt().getTime()) * 2);
                Log.d("CreatedDelay", String.valueOf(MainActivityDataShare.getCreatedDelay()));
                onUiLoad(false);
                Intent intent = new Intent(this, ReplayActivity.class);
                startActivity(intent);
            });

        }).start();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview);

        Intent intent = getIntent();
        row = (Row) intent.getSerializableExtra(SearchActivity.EXTRA_ACTIVITY_ROW);

        listViewPreviews = findViewById(R.id.listView);
        listViewPreviews.setOnItemClickListener((adapterView, view, position, id) -> startChat(position));

        progressBar = findViewById(R.id.progressBar);

        initPreviews();

        ImageButton imageButtonSettings = findViewById(R.id.imageButtonSettings);
        imageButtonSettings.setOnClickListener(view -> {
            Intent intentSettings = new Intent(this, SettingsActivity.class);
            startActivity(intentSettings);
        });

    }
}