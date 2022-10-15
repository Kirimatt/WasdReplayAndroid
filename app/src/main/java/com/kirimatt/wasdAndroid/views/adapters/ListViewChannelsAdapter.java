package com.kirimatt.wasdAndroid.views.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.kirimatt.wasdAndroid.R;
import com.kirimatt.wasdAndroid.dtos.search.Row;
import com.kirimatt.wasdAndroid.utils.ImageManager;
import com.kirimatt.wasdAndroid.utils.NameColorManager;

import java.util.List;

public class ListViewChannelsAdapter extends ArrayAdapter<Row> {
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

        //TODO: fix update avatars
        // when search - repeat avatars
        convertView = inflater.inflate(listLayout, null, false);

        Row row = channelsList.get(channelsList.size() - position - 1);

        TextView name = convertView.findViewById(R.id.textViewName);
        ImageView imageViewAvatar = convertView.findViewById(R.id.imageViewAvatar);

        //wasd не захотел сделать разные размеры картинок
        //Выдает одни и те же оригиналы аватарок при вызовах разных размеров, поэтому rescale
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

        if (row.getUserLogin() != null) {
            name.setText(row.getUserLogin());
            name.setTextColor(NameColorManager.getRandomColor());
        }

        return convertView;
    }

    @Override
    public Row getItem(int position) {
        return channelsList.get(channelsList.size() - position - 1);
    }

}