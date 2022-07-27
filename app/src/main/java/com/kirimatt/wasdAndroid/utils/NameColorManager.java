package com.kirimatt.wasdAndroid.utils;

import java.util.HashMap;
import java.util.Map;

public class NameColorManager {
    private static final Map<String, Integer> USER_COLORS = new HashMap<>();

    private NameColorManager() {
    }

    public static Integer getUserColors(String name) {
        return USER_COLORS.get(name);
    }

    public static void putUserColors(String name, Integer color) {
        USER_COLORS.put(name, color);
    }

    public static void clearColors() {
        USER_COLORS.clear();
    }

    public static boolean containsColor(String name) {
        return USER_COLORS.containsKey(name);
    }
}
