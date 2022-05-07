package com.kirimatt.wasdAndroid.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.kirimatt.wasdAndroid.R;
import com.kirimatt.wasdAndroid.dtos.settings.RowSettings;
import com.kirimatt.wasdAndroid.utils.MainActivityDataShare;
import com.kirimatt.wasdAndroid.views.adapters.ListViewSettingsAdapter;

import java.util.ArrayList;
import java.util.List;

public class SettingsActivity extends AppCompatActivity {

    private final List<RowSettings> settings = new ArrayList<>();
    private SharedPreferences sharedPref;
    private ListView listView;
    private ListViewSettingsAdapter adapter;

    private void init() {
        sharedPref = getSharedPreferences("setting", MODE_PRIVATE);
        settings.add(new RowSettings(
                "Дата сообщения в чате",
                "DATE_NEEDED",
                sharedPref.getBoolean("DATE_NEEDED", true))
        );

        settings.add(new RowSettings(
                "Монохромный режим",
                "MONOCHROME_NEEDED",
                sharedPref.getBoolean("MONOCHROME_NEEDED", false))
        );

        settings.add(new RowSettings(
                "Аватарки в чате",
                "AVATAR_NEEDED",
                sharedPref.getBoolean("AVATAR_NEEDED", true))
        );

        settings.add(new RowSettings(
                "Модераторы в чате",
                "MODERATOR_NEEDED",
                sharedPref.getBoolean("MODERATOR_NEEDED", true))
        );

        settings.add(new RowSettings(
                "Стикеры в чате",
                "STICKER_NEEDED",
                sharedPref.getBoolean("STICKER_NEEDED", true))
        );
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        init();

        listView = findViewById(R.id.listView);

        adapter = new ListViewSettingsAdapter(
                getApplicationContext(),
                R.layout.activity_settings_row,
                R.id.textViewSetting,
                settings
        );

        listView.setAdapter(adapter);

        listView.setOnItemClickListener((adapterView, view, position, id) -> onClickItem(position));
    }

    private void onClickItem(int position) {
        RowSettings rowSettings = settings.get(position);
        rowSettings.setStatus(!rowSettings.getStatus());

        settings.remove(position);
        settings.add(position, rowSettings);

        listView.setAdapter(adapter);
    }

    @Override
    protected void onPause() {
        SharedPreferences.Editor editor = sharedPref.edit();

        for (int i = 0; i < settings.size(); i++) {
            editor.putBoolean(
                    settings.get(i).getAliasSetting(),
                    settings.get(i).getStatus()
            );
        }

        editor.apply();

        super.onPause();
    }
}