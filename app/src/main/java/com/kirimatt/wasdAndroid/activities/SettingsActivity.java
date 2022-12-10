package com.kirimatt.wasdAndroid.activities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.kirimatt.wasdAndroid.R;
import com.kirimatt.wasdAndroid.dtos.settings.RowSettingType;
import com.kirimatt.wasdAndroid.dtos.settings.RowStatusSetting;
import com.kirimatt.wasdAndroid.dtos.settings.RowFieldSetting;
import com.kirimatt.wasdAndroid.dtos.settings.RowSetting;
import com.kirimatt.wasdAndroid.views.adapters.ListViewSettingsAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SettingsActivity extends AppCompatActivity {

    public static final String DELAY_ALIAS = "DELAY";
    private final List<RowSetting<?>> settings = new ArrayList<>();
    private SharedPreferences sharedPref;
    private ListView listView;
    private ListViewSettingsAdapter adapter;

    private void init() {

        sharedPref = getSharedPreferences("setting", MODE_PRIVATE);
        settings.add(new RowStatusSetting(
                "Время сообщения в чате",
                "DATE_NEEDED",
                sharedPref.getBoolean("DATE_NEEDED", true))
        );

        settings.add(new RowStatusSetting(
                "Монохромный режим",
                "MONOCHROME_NEEDED",
                sharedPref.getBoolean("MONOCHROME_NEEDED", false))
        );

        settings.add(new RowStatusSetting(
                "Аватарки в чате",
                "AVATAR_NEEDED",
                sharedPref.getBoolean("AVATAR_NEEDED", true))
        );

        settings.add(new RowStatusSetting(
                "Модераторы в чате",
                "MODERATOR_NEEDED",
                sharedPref.getBoolean("MODERATOR_NEEDED", true))
        );

        settings.add(new RowStatusSetting(
                "Стикеры в чате",
                "STICKER_NEEDED",
                sharedPref.getBoolean("STICKER_NEEDED", true))
        );

        settings.add(new RowFieldSetting(
                "Задержка",
                DELAY_ALIAS,
                sharedPref.getFloat(DELAY_ALIAS, 0f))
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

        listView.setOnItemClickListener(
                (adapterView, view, position, id) -> onClickItem(view, position));
    }

    private void onClickItem(View view, int position) {
        if (settings.get(position).getRowSettingType() == RowSettingType.STATUS) {
            RowStatusSetting rowSetting = (RowStatusSetting) settings.get(position);
            rowSetting.setValue(!rowSetting.getValue());
            settings.remove(position);
            settings.add(position, rowSetting);
            listView.setAdapter(adapter);
        } else {
            view.requestFocus();
        }
    }

    @Override
    protected void onPause() {
        SharedPreferences.Editor editor = sharedPref.edit();

        for (int i = 0; i < settings.size(); i++) {
            if (settings.get(i).getRowSettingType() == RowSettingType.STATUS) {
                editor.putBoolean(
                        settings.get(i).getAliasSetting(),
                        ((RowStatusSetting) settings.get(i)).getValue());
            }
        }

        editor.apply();

        super.onPause();
    }
}