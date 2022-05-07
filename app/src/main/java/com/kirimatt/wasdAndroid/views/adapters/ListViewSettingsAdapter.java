package com.kirimatt.wasdAndroid.views.adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.kirimatt.wasdAndroid.R;
import com.kirimatt.wasdAndroid.dtos.search.Row;
import com.kirimatt.wasdAndroid.dtos.settings.RowSettings;
import com.kirimatt.wasdAndroid.utils.ImageManager;

import java.util.List;
import java.util.Random;

public class ListViewSettingsAdapter extends ArrayAdapter<RowSettings> {
    private final int listLayout;
    private final List<RowSettings> channelsList;
    private final Context context;

    public ListViewSettingsAdapter(Context context, int listLayout,
                                   int field, List<RowSettings> rowSettings) {
        super(context, listLayout, field, rowSettings);
        this.context = context;
        this.listLayout = listLayout;
        this.channelsList = rowSettings;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null)
            convertView = inflater.inflate(listLayout, null, false);

        RowSettings row = channelsList.get(position);

        TextView name = convertView.findViewById(R.id.textViewSetting);
        CheckBox checkBox = convertView.findViewById(R.id.checkboxSetting);

        name.setText(row.getNameSetting());

        checkBox.setChecked(row.getStatus());
        checkBox.setEnabled(false);

        return convertView;
    }

    @Override
    public RowSettings getItem(int position) {
        return channelsList.get(position);
    }

}