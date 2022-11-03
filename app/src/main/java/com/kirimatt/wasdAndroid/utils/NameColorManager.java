package com.kirimatt.wasdAndroid.utils;

import android.util.Pair;

import androidx.core.graphics.ColorUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class NameColorManager {
    private static final Map<String, Integer> USER_COLORS = new HashMap<>();
    private static final Map<Integer, Integer> COLORS = new HashMap<>();
    private static final Random RANDOM = new Random();
    private static Integer counter = 0;

    static {
        for (int column = 1; column < 13; column++) {
            for (int row = 0; row < 7; row++) {
                Pair<Float, Float> hueAndSaturation = getHueAndSaturationFromColumn(column);
                Float lightness = getLightnessFromRow(row);

                Integer color =
                        ColorUtils.HSLToColor(
                                new float[]{
                                        hueAndSaturation.first,
                                        hueAndSaturation.second,
                                        lightness
                                }
                        );


                COLORS.put(counter++, color);
            }
        }
    }

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

    public static Integer getColorById(Integer colorId) {
        return COLORS.get(colorId);
    }

    public static Integer getRandomColor() {
        return COLORS.get(RANDOM.nextInt(COLORS.size() - 1));
    }

    private static Pair<Float, Float> getHueAndSaturationFromColumn(Integer column) {
        switch (column) {
            case 1:
                return new Pair<>(195f, 1f);
            case 2:
                return new Pair<>(216f, 1f);
            case 3:
                return new Pair<>(254f, 0.8f);
            case 4:
                return new Pair<>(285f, 0.7f);
            case 5:
                return new Pair<>(338f, 0.7f);
            case 6:
                return new Pair<>(7f, 1f);
            case 7:
                return new Pair<>(22f, 1f);
            case 8:
                return new Pair<>(37f, 1f);
            case 9:
                return new Pair<>(45f, 1f);
            case 10:
                return new Pair<>(58f, 1f);
            case 11:
                return new Pair<>(65f, 0.8f);
            case 12:
                return new Pair<>(90f, 0.55f);
            default:
                return new Pair<>(0f, 0f);
        }
    }

    private static Float getLightnessFromRow(Integer row) {
        switch (row) {
            case 0:
                return 0.31f;
            case 1:
                return 0.33f;
            case 2:
                return 0.44f;
            case 3:
                return 0.50f;
            case 4:
                return 0.6f;
            case 5:
                return 0.66f;
            case 6:
                return 0.7f;
            default:
                return 0.5f;
        }
    }
}
