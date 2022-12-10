package com.kirimatt.wasdAndroid.views.adapters;

import static android.content.Context.MODE_PRIVATE;

import static com.kirimatt.wasdAndroid.activities.SettingsActivity.DELAY_ALIAS;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.res.ResourcesCompat;

import com.kirimatt.wasdAndroid.R;
import com.kirimatt.wasdAndroid.dtos.settings.RowFieldSetting;
import com.kirimatt.wasdAndroid.dtos.settings.RowSetting;
import com.kirimatt.wasdAndroid.dtos.settings.RowSettingType;
import com.kirimatt.wasdAndroid.dtos.settings.RowStatusSetting;

import java.util.List;

public class ListViewSettingsAdapter extends ArrayAdapter<RowSetting<?>> {
    private final int listLayout;
    private final List<RowSetting<?>> settingsList;
    private final Context context;
    private final SharedPreferences sharedPref;

    public ListViewSettingsAdapter(Context context, int listLayout,
                                   int field, List<RowSetting<?>> rowSettings) {
        super(context, listLayout, field, rowSettings);
        this.context = context;
        this.listLayout = listLayout;
        this.settingsList = rowSettings;
        this.sharedPref = context.getSharedPreferences("setting", MODE_PRIVATE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null)
            convertView = inflater.inflate(listLayout, null, false);

        TextView name = convertView.findViewById(R.id.textViewSetting);
        RowSetting<?> rowSetting = settingsList.get(position);

        if (rowSetting.getRowSettingType() == RowSettingType.FIELD) {
            fieldSetting(convertView);
        } else {
            statusSetting((RowStatusSetting) rowSetting, convertView);
        }

        name.setText(rowSetting.getNameSetting());
        return convertView;
    }

    private void fieldSetting(View convertView) {
        EditText editTextValue = convertView.findViewById(R.id.editTextSettingValue);
        editTextValue.setVisibility(View.VISIBLE);

        float delayValue = sharedPref.getFloat(DELAY_ALIAS, 0f);
        editTextValue.setText(delayValue == 0f ? "" : String.valueOf(delayValue));
        editTextValue.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // not needed
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // not needed
            }

            @Override
            public void afterTextChanged(Editable s) {
                String target = s.toString();
                if (target.length() > 1 || (target.length() > 0 && !target.startsWith("-"))) {
                    sharedPref
                            .edit()
                            .putFloat(DELAY_ALIAS, Float.parseFloat(s.toString()))
                            .apply();
                }
            }
        });
    }

    private void statusSetting(RowSetting<Boolean> row, View convertView) {
        ImageView checkBoxImage = convertView.findViewById(R.id.checkboxSetting);
        checkBoxImage.setVisibility(View.VISIBLE);

        checkBoxImage.setImageDrawable(
                Boolean.TRUE.equals(row.getValue()) ?
                        ResourcesCompat.getDrawable(context.getResources(),
                                R.drawable.baseline_done_24, null) : null
        );
    }

    @Override
    public RowSetting<?> getItem(int position) {
        return settingsList.get(position);
    }

}