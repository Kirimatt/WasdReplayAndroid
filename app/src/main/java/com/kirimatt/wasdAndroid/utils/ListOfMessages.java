package com.kirimatt.wasdAndroid.utils;

import com.kirimatt.wasdAndroid.dtos.ChatMessages.Message;

import java.util.List;

public class ListOfMessages {
    private static List<Message> messages;

    private ListOfMessages() {

    }

    public static List<Message> getMessages() {
        return messages;
    }

    public static void setMessages(List<Message> messages) {
        ListOfMessages.messages = messages;
    }
}
