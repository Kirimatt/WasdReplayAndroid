package com.kirimatt.wasdAndroid.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.kirimatt.wasdAndroid.R;
import com.kirimatt.wasdAndroid.dtos.search.ResultSearch;
import com.kirimatt.wasdAndroid.dtos.search.Row;
import com.kirimatt.wasdAndroid.dtos.search.SearchRequestDto;
import com.kirimatt.wasdAndroid.dtos.search.SearchResponseDto;
import com.kirimatt.wasdAndroid.services.WasdApiService;
import com.kirimatt.wasdAndroid.views.adapters.ListViewChannelsAdapter;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {

    public static final String EXTRA_ACTIVITY_ROW = "com.kirimatt.activities.SearchActivity.Row";
    private static final Gson GSON = new Gson();
    private EditText editTextSearch;
    private ListView listViewChannels;
    private ListViewChannelsAdapter adapter;
    private ProgressBar progressBar;

    private void search() {

        if (editTextSearch.getText().length() == 0)
            return;

        new Thread(() -> {
            runOnUiThread(() -> progressBar.setVisibility(View.VISIBLE));

            SearchRequestDto requestDto = new SearchRequestDto(editTextSearch.getText().toString());

            SearchResponseDto responseDto = WasdApiService.getChannelsByName(requestDto);

            runOnUiThread(() -> {
                adapter = new ListViewChannelsAdapter(
                        getApplicationContext(),
                        R.layout.activity_search_row,
                        R.id.textViewName,
                        responseDto.getResult().getRows()
                );

                listViewChannels.setAdapter(adapter);

                progressBar.setVisibility(View.GONE);
            });

        }).start();
    }

    private void onClickItem(int position) {
        Row selected = adapter.getItem(position);
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        String defaultValue = getResources().getString(R.string.savedHistory);
        String savedHistory = sharedPref.getString(
                getString(R.string.savedHistory),
                defaultValue
        );

        ResultSearch resultSearchHistory = GSON.fromJson(
                savedHistory, ResultSearch.class
        );

        List<Row> rows;

        if (resultSearchHistory == null || resultSearchHistory.getRows() == null) {
            resultSearchHistory = new ResultSearch();
            rows = new ArrayList<>();
        } else {
            rows = resultSearchHistory.getRows();
        }

        if (!rows.contains(selected)) {
            rows.add(selected);
        }

        resultSearchHistory.setRows(rows);

        //set shared data
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(
                getString(R.string.savedHistory),
                GSON.toJson(resultSearchHistory)
        );
        editor.apply();

        Intent intent = new Intent(this, PreviewActivity.class);
        intent.putExtra(
                EXTRA_ACTIVITY_ROW,
                selected
        );
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        editTextSearch = findViewById(R.id.searchEditText);
        ImageButton searchButton = findViewById(R.id.searchButton);
        listViewChannels = findViewById(R.id.listView);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);

        listViewChannels.setOnItemClickListener(
                (adapterView, view, position, id) -> onClickItem(position)
        );

        editTextSearch.setBackgroundResource(R.drawable.rounded_edittext);
        editTextSearch.setOnKeyListener((v, keyCode, event) -> {
            // If the event is a key-down event on the "enter" button
            if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                    (keyCode == KeyEvent.KEYCODE_ENTER)) {
                // Perform action on key press
                search();
                return true;
            }
            return false;
        });

        searchButton.setOnClickListener(view -> search());

        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        String defaultValue = getResources().getString(R.string.savedHistory);
        String savedHistory = sharedPref.getString(
                getString(R.string.savedHistory),
                defaultValue
        );

        ResultSearch resultSearchHistory = GSON.fromJson(
                savedHistory, ResultSearch.class
        );

        if (resultSearchHistory != null) {
            adapter = new ListViewChannelsAdapter(
                    getApplicationContext(),
                    R.layout.activity_search_row,
                    R.id.textViewName,
                    resultSearchHistory.getRows()
            );

            listViewChannels.setAdapter(adapter);
        }
    }
}