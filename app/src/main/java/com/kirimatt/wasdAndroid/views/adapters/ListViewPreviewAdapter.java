package com.kirimatt.wasdAndroid.views.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.kirimatt.wasdAndroid.R;
import com.kirimatt.wasdAndroid.dtos.channelsInfo.ResultPreviews;
import com.kirimatt.wasdAndroid.utils.ImageManager;

import java.util.List;

public class ListViewPreviewAdapter extends ArrayAdapter<ResultPreviews> {

    private final int listLayout;
    private final List<ResultPreviews> previews;
    private final Context context;

    public ListViewPreviewAdapter(Context context, int listLayout,
                                  int field, List<ResultPreviews> previews) {
        super(context, listLayout, field, previews);
        this.context = context;
        this.listLayout = listLayout;
        this.previews = previews;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            //TODO: Fix inflate refresh
            View listViewItem = inflater.inflate(listLayout, null, false);

            ResultPreviews row = previews.get(position);

            TextView name = listViewItem.findViewById(R.id.textViewName);
            ImageView imageViewPreview = listViewItem.findViewById(R.id.imageViewAvatar);

            name.setText(row.getMediaContainerName());

            //wasd не захотел сделать разные размеры картинок
            //Выдает одни и те же оригиналы аватарок при вызовах разных размеров, поэтому rescale
            ImageManager.fetchImage(
                    row.getMediaContainerStreams().get(0)
                            .getStreamMedia().get(0)
                            .getMediaMeta()
                            .getMediaPreviewArchiveImages()
                            .getMedium(),
                    imageViewPreview,
                    context
            );
            return listViewItem;
        }

        return convertView;
    }

    @Override
    public ResultPreviews getItem(int position) {
        return previews.get(position);
    }

}
