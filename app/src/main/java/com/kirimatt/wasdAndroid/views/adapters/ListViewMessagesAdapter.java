package com.kirimatt.wasdAndroid.views.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.kirimatt.wasdAndroid.R;
import com.kirimatt.wasdAndroid.dtos.chatMessages.Info;
import com.kirimatt.wasdAndroid.dtos.chatMessages.Message;
import com.kirimatt.wasdAndroid.utils.ImageManager;

import java.util.List;
import java.util.Random;

public class ListViewMessagesAdapter extends ArrayAdapter<Message> {
    private static final Random RANDOM = new Random();
    private static final String MODERATOR_URL = "https://toptwitchstreamers.com/" +
            "wp-content/uploads/2018/09/mods.jpg";
    private final int listLayout;
    private final List<Message> messageList;
    private final Context context;

    public ListViewMessagesAdapter(Context context, int listLayout,
                                   int field, List<Message> messageList) {
        super(context, listLayout, field, messageList);
        this.context = context;
        this.listLayout = listLayout;
        this.messageList = messageList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null)
            convertView = inflater.inflate(listLayout, null, false);

        Message targetMessage = messageList.get(position);

        TextView name = convertView.findViewById(R.id.textViewName);
        TextView message = convertView.findViewById(R.id.textViewMessage);
        ImageView imageViewSticker = convertView.findViewById(R.id.imageViewSticker);
        ImageView imageViewAvatar = convertView.findViewById(R.id.imageViewAvatar);
        ImageView imageViewModerator = convertView.findViewById(R.id.imageViewModerator);

        Info info = targetMessage.getInfo();

        if (info.getUserChannelRole().equals("CHANNEL_MODERATOR")) {
            ImageManager.fetchImageWithScale(
                    MODERATOR_URL,
                    imageViewModerator,
                    context,
                    64,
                    64,
                    false
            );
        }

        //wasd не захотел сделать разные размеры картинок
        //Выдает одни и те же оригиналы аватарок при вызовах разных размеров, поэтому rescale
        ImageManager.fetchImageWithScale(
                info.getUserAvatar().getSmall(),
                imageViewAvatar,
                context,
                64,
                64,
                false
        );

        name.setText(info.getUserLogin());
        int color = Color.argb(
                255,
                RANDOM.nextInt(255),
                RANDOM.nextInt(255),
                RANDOM.nextInt(255)
        );
        name.setTextColor(color);

        if (targetMessage.getType().equals("MESSAGE")) {
            message.setText(info.getMessage());
            imageViewSticker.setImageBitmap(null);
            return convertView;
        }

        if (targetMessage.getType().equals("HIGHLIGHTED_MESSAGE")) {
            message.setText(info.getMessage());
            message.setTextColor(color);
            imageViewSticker.setImageBitmap(null);
            return convertView;
        }

        ImageManager.fetchImage(info.getSticker().getStickerImage().getMedium(), imageViewSticker, context);
        message.setText(null);
        return convertView;
    }

    @Override
    public Message getItem(int position) {
        return messageList.get(position);
    }

}