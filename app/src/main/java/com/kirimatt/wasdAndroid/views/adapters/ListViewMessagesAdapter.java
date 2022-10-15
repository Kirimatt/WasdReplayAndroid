package com.kirimatt.wasdAndroid.views.adapters;

import android.content.Context;
import android.graphics.Color;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.kirimatt.wasdAndroid.R;
import com.kirimatt.wasdAndroid.dtos.chatMessages.Info;
import com.kirimatt.wasdAndroid.dtos.chatMessages.Message;
import com.kirimatt.wasdAndroid.dtos.settings.AllSettings;
import com.kirimatt.wasdAndroid.utils.ImageManager;
import com.kirimatt.wasdAndroid.utils.NameColorManager;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public class ListViewMessagesAdapter extends ArrayAdapter<Message> {
    private static final String MODERATOR_URL = "https://toptwitchstreamers.com/" +
            "wp-content/uploads/2018/09/mods.jpg";
    private final int listLayout;
    private final List<Message> messageList;
    private final Context context;
    private final AllSettings allSettings;
    private final int finalScale;

    public ListViewMessagesAdapter(Context context, int listLayout,
                                   int field, List<Message> messageList, AllSettings allSettings) {
        super(context, listLayout, field, messageList);
        this.context = context;
        this.listLayout = listLayout;
        this.messageList = messageList;
        this.allSettings = allSettings;

        float factor = context
                .getResources()
                .getDisplayMetrics()
                .density;

        this.finalScale = (int) (20 * factor);

        ImageManager.preDownloadWithScale(
                MODERATOR_URL,
                finalScale,
                finalScale,
                false
        );
    }

    public String getLocalTimeString(LocalDateTime localDateTime) {

        return (localDateTime.getHour() < 10 ?
                "0" + localDateTime.getHour() : localDateTime.getHour()) +
                ":" +
                (localDateTime.getMinute() < 10 ?
                        "0" + localDateTime.getMinute() : localDateTime.getMinute());
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null)
            convertView = inflater.inflate(listLayout, null, false);

        Message targetMessage = messageList.get(position);

        TextView date = convertView.findViewById(R.id.textViewDate);
        TextView name = convertView.findViewById(R.id.textViewName);
        ImageView imageViewSticker = convertView.findViewById(R.id.imageViewSticker);
        ImageView imageViewAvatar = convertView.findViewById(R.id.imageViewAvatar);
        ImageView imageViewModerator = convertView.findViewById(R.id.imageViewModerator);

        Info info = targetMessage.getInfo();

        if (allSettings.isDate()) {
            LocalDateTime dateTimeLocal = info.getDateTime()
                    .toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDateTime();

            date.setText(getLocalTimeString(dateTimeLocal));
            date.setTextColor(Color.WHITE);
        }

        if (allSettings.isModerators() && info.getUserChannelRole().equals("CHANNEL_MODERATOR")) {
            ImageManager.fetchImageWithScale(
                    MODERATOR_URL,
                    imageViewModerator,
                    context,
                    finalScale,
                    finalScale,
                    false
            );
        } else {
            imageViewModerator.setImageBitmap(null);
        }

        if (allSettings.isAvatars()) {
            //wasd не захотел сделать разные размеры картинок
            //Выдает одни и те же оригиналы аватарок при вызовах разных размеров, поэтому rescale
            ImageManager.fetchImageWithScale(
                    info.getUserAvatar().getSmall(),
                    imageViewAvatar,
                    context,
                    finalScale,
                    finalScale,
                    false
            );
        } else {
            imageViewAvatar.setImageBitmap(null);
        }

        int color = allSettings.isMono() ? Color.WHITE : Optional.ofNullable(
                NameColorManager.getUserColors(info.getUserLogin())
        ).orElse(NameColorManager.getRandomColor());

        if (!NameColorManager.containsColor(info.getUserLogin())) {
            NameColorManager.putUserColors(info.getUserLogin(), color);
        }

        if (targetMessage.getType().equals("MESSAGE")
                || targetMessage.getType().equals("HIGHLIGHTED_MESSAGE")) {

            final SpannableStringBuilder nameText = new SpannableStringBuilder(info.getUserLogin());
            final ForegroundColorSpan style = new ForegroundColorSpan(
                    color
            );

            nameText.append(": ");
            nameText.append(info.getMessage());

            nameText.setSpan(
                    style,
                    0,
                    info.getUserLogin().length(),
                    Spanned.SPAN_INCLUSIVE_INCLUSIVE
            );

            nameText.setSpan(
                    new ForegroundColorSpan(Color.WHITE),
                    info.getUserLogin().length(),
                    nameText.length(),
                    Spanned.SPAN_INCLUSIVE_INCLUSIVE
            );

            name.setText(nameText);
            imageViewSticker.setImageBitmap(null);

            if (targetMessage.getType().equals("HIGHLIGHTED_MESSAGE")) {
                nameText.setSpan(
                        new ForegroundColorSpan(color),
                        info.getUserLogin().length(),
                        nameText.length(),
                        Spanned.SPAN_INCLUSIVE_INCLUSIVE
                );
            }

            return convertView;
        }

        String nameText = info.getUserLogin() + ": ";
        name.setText(nameText);
        name.setTextColor(color);

        if (allSettings.isStickers()) {
            ImageManager.fetchImage(
                    info.getSticker().getStickerImage().getMedium(),
                    imageViewSticker,
                    context
            );
        } else {
            imageViewSticker.setImageBitmap(null);
        }

        return convertView;
    }

    @Override
    public Message getItem(int position) {
        return messageList.get(position);
    }

}