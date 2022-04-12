package com.kirimatt.wasdAndroid.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.kirimatt.wasdAndroid.R;
import com.kirimatt.wasdAndroid.dtos.search.SearchRequestDto;
import com.kirimatt.wasdAndroid.dtos.search.SearchResponseDto;
import com.kirimatt.wasdAndroid.services.WasdApiService;
import com.kirimatt.wasdAndroid.views.adapters.ListViewChannelsAdapter;

public class SearchActivity extends AppCompatActivity {

    public static final String EXTRA_ACTIVITY_ROW = "com.kirimatt.activities.SearchActivity.Row";
    private EditText editTextSearch;
    private ListView listViewChannels;
    private ListViewChannelsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        editTextSearch = findViewById(R.id.searchEditText);
        ImageButton searchButton = findViewById(R.id.searchButton);
        listViewChannels = findViewById(R.id.listView);

        listViewChannels.setOnItemClickListener(
                (adapterView, view, position, id) -> onClickItem(position)
        );

        editTextSearch.setBackgroundResource(R.drawable.rounded_edittext);

        searchButton.setOnClickListener(view -> {
            if (editTextSearch.getText().length() > 0)
                search();
        });
    }

    private void search() {
        new Thread(() -> {
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
            });

        }).start();
    }

    private void onClickItem(int position) {
        Intent intent = new Intent(this, PreviewActivity.class);
        intent.putExtra(
                EXTRA_ACTIVITY_ROW,
                adapter.getItem(position)
        );
        startActivity(intent);
    }
}