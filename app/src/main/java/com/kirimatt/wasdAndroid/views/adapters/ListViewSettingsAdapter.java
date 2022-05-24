package com.kirimatt.wasdAndroid.views.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.res.ResourcesCompat;

import com.kirimatt.wasdAndroid.R;
import com.kirimatt.wasdAndroid.dtos.settings.RowSettings;

import java.util.List;

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
        ImageView checkBoxImage = convertView.findViewById(R.id.checkboxSetting);

        name.setText(row.getNameSetting());

        checkBoxImage.setImageDrawable(
                Boolean.TRUE.equals(row.getStatus()) ?
                        ResourcesCompat.getDrawable(context.getResources(),
                                R.drawable.baseline_done_24, null) : null
        );

        return convertView;
    }

    @Override
    public RowSettings getItem(int position) {
        return channelsList.get(position);
    }

}