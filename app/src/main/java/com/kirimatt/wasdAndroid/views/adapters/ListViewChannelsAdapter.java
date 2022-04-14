package com.kirimatt.wasdAndroid.views.adapters;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.kirimatt.wasdAndroid.R;
import com.kirimatt.wasdAndroid.dtos.search.Row;
import com.kirimatt.wasdAndroid.utils.ImageManager;

import java.util.List;
import java.util.Random;

public class ListViewChannelsAdapter extends ArrayAdapter<Row> {
    private static final Random RANDOM = new Random();
    private final int listLayout;
    private final List<Row> channelsList;
    private final Context context;

    public ListViewChannelsAdapter(Context context, int listLayout,
                                   int field, List<Row> channelsList) {
        super(context, listLayout, field, channelsList);
        this.context = context;
        this.listLayout = listLayout;
        this.channelsList = channelsList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //TODO: Fix inflate refresh
        View listViewItem = inflater.inflate(listLayout, null, false);

        Row row = channelsList.get(position);

        TextView name = listViewItem.findViewById(R.id.textViewName);
        ImageView imageViewAvatar = listViewItem.findViewById(R.id.imageViewAvatar);

        if (row.getUserLogin() != null) {
            name.setText(row.getUserLogin());

            int color = Color.argb(
                    255,
                    RANDOM.nextInt(255),
                    RANDOM.nextInt(255),
                    RANDOM.nextInt(255)
            );
            name.setTextColor(color);
        }
        //wasd не захотел сделать разные размеры картинок
        //Выдает одни и те же оригиналы аватарок при вызовах разных размеров, поэтому rescale
        Log.d("CHANNEL", row.toString());
        if (row.getProfileImage() != null) {
            ImageManager.fetchImageWithScale(
                    row.getProfileImage().getMedium() == null ?
                            row.getProfileImage().getLarge() :
                            row.getProfileImage().getMedium(),
                    imageViewAvatar,
                    context,
                    128,
                    128,
                    false
            );
        }

        return listViewItem;
    }

    @Override
    public Row getItem(int position) {
        return channelsList.get(position);
    }

}