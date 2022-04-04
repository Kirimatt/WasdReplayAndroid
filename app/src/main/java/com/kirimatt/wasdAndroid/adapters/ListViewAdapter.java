package com.kirimatt.wasdAndroid.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.kirimatt.wasdAndroid.R;
import com.kirimatt.wasdAndroid.dtos.ChatMessages.Info;
import com.kirimatt.wasdAndroid.dtos.ChatMessages.Message;

import java.util.List;

public class ListViewAdapter extends ArrayAdapter<Message> {
    private final int listLayout;
    private final List<Message> messageList;
    private final Context context;

    public ListViewAdapter(Context context, int listLayout,
                           int field, List<Message> messageList) {
        super(context, listLayout, field, messageList);
        this.context = context;
        this.listLayout = listLayout;
        this.messageList = messageList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View listViewItem = inflater.inflate(listLayout, null, false);
            TextView name = listViewItem.findViewById(R.id.textViewName);
            TextView message = listViewItem.findViewById(R.id.textViewMessage);
            Info info = messageList.get(position).getInfo();
            name.setText(info.getUserLogin());
            message.setText(info.getMessage());

            return listViewItem;
        } else {
            return convertView;
        }
    }

    @Override
    public Message getItem(int position) {
        return messageList.get(position);
    }

}