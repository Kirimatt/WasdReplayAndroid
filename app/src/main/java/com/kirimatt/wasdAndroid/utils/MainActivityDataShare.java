package com.kirimatt.wasdAndroid.utils;

import com.kirimatt.wasdAndroid.dtos.ChatMessages.Message;

import java.util.Date;
import java.util.List;

public class MainActivityDataShare {
    private static List<Message> messages;
    private static String uriString;
    private static Date startReplay;
    private static int timeToSeek;

    private MainActivityDataShare() {

    }

    public static int getTimeToSeek() {
        return timeToSeek;
    }

    public static void setTimeToSeek(int timeToSeek) {
        MainActivityDataShare.timeToSeek = timeToSeek;
    }

    public static List<Message> getMessages() {
        return messages;
    }

    public static void setMessages(List<Message> messages) {
        MainActivityDataShare.messages = messages;
    }

    public static String getUriString() {
        return uriString;
    }

    public static void setUriString(String uriString) {
        MainActivityDataShare.uriString = uriString;
    }

    public static Date getStartReplay() {
        return startReplay;
    }

    public static void setStartReplay(Date startReplay) {
        MainActivityDataShare.startReplay = startReplay;
    }
}
