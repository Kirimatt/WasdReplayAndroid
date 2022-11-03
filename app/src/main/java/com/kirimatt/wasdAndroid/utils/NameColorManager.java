package com.kirimatt.wasdAndroid.utils;

import android.graphics.Color;
import android.util.Pair;

import androidx.core.graphics.ColorUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import kotlin.Triple;
//TODO: CLEAR
public class NameColorManager {
    private static final Map<String, Integer> USER_COLORS = new HashMap<>();
    private static final Map<Integer, Integer> COLORS = new HashMap<>();
    private static final Random RANDOM = new Random();
    private static Integer counter = 0;

    static {
        for (int column = 1; column < 13; column++) {
//            Triple<Float, Float, Float> white = getWhiteFromColumn(column);
//
//            Integer colorWhite =
//                    ColorUtils.HSLToColor(
//                            new float[]{
//                                    white.component1(),
//                                    white.component2(),
//                                    white.component3()
//                            }
//
//                    );

//            COLORS.put(counter++, colorWhite);

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
//        COLORS.put(0, Color.parseColor("#1abc9c"));
//        COLORS.put(1, Color.parseColor("#2ecc71"));
//        COLORS.put(2, Color.parseColor("#3498db"));
//        COLORS.put(3, Color.parseColor("#9b59b6"));
//        COLORS.put(4, Color.parseColor("#16a085"));
//        COLORS.put(5, Color.parseColor("#27ae60"));
//        COLORS.put(6, Color.parseColor("#2980b9"));
//        COLORS.put(7, Color.parseColor("#8e44ad"));
//        COLORS.put(8, Color.parseColor("#f1c40f"));
//        COLORS.put(9, Color.parseColor("#e67e22"));
//        COLORS.put(10, Color.parseColor("#e74c3c"));
//        COLORS.put(11, Color.parseColor("#ecf0f1"));
//        COLORS.put(12, Color.parseColor("#f39c12"));
//        COLORS.put(13, Color.parseColor("#d35400"));
//        COLORS.put(14, Color.parseColor("#d35400"));
//        COLORS.put(15, Color.parseColor("#c0392b"));
//        COLORS.put(16, Color.parseColor("#00a8ff"));
//        COLORS.put(17, Color.parseColor("#9c88ff"));
//        COLORS.put(18, Color.parseColor("#fbc531"));
//        COLORS.put(19, Color.parseColor("#4cd137"));
//        COLORS.put(20, Color.parseColor("#487eb0"));
//        COLORS.put(21, Color.parseColor("#0097e6"));
//        COLORS.put(22, Color.parseColor("#8c7ae6"));
//        COLORS.put(23, Color.parseColor("#e1b12c"));
//        COLORS.put(24, Color.parseColor("#44bd32"));
//        COLORS.put(25, Color.parseColor("#40739e"));
//        COLORS.put(26, Color.parseColor("#e84118"));
//        COLORS.put(27, Color.parseColor("#f5f6fa"));
//        COLORS.put(28, Color.parseColor("#7f8fa6"));
//        COLORS.put(29, Color.parseColor("#c23616"));
//        COLORS.put(30, Color.parseColor("#dcdde1"));
//        COLORS.put(31, Color.parseColor("#55efc4"));
//        COLORS.put(32, Color.parseColor("#81ecec"));
//        COLORS.put(33, Color.parseColor("#74b9ff"));
//        COLORS.put(34, Color.parseColor("#a29bfe"));
//        COLORS.put(35, Color.parseColor("#dfe6e9"));
//        COLORS.put(36, Color.parseColor("#00b894"));
//        COLORS.put(37, Color.parseColor("#00cec9"));
//        COLORS.put(38, Color.parseColor("#0984e3"));
//        COLORS.put(39, Color.parseColor("#6c5ce7"));
//        COLORS.put(40, Color.parseColor("#b2bec3"));
//        COLORS.put(41, Color.parseColor("#ffeaa7"));
//        COLORS.put(42, Color.parseColor("#fab1a0"));
//        COLORS.put(43, Color.parseColor("#ff7675"));
//        COLORS.put(44, Color.parseColor("#fd79a8"));
//        COLORS.put(45, Color.parseColor("#fdcb6e"));
//        COLORS.put(46, Color.parseColor("#e17055"));
//        COLORS.put(47, Color.parseColor("#d63031"));
//        COLORS.put(48, Color.parseColor("#e84393"));
//        COLORS.put(49, Color.parseColor("#ffa502"));
//        COLORS.put(50, Color.parseColor("#1e90ff"));
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
                return 0.22f;
            case 1:
                return 0.33f;
            case 2:
                return 0.40f;
            case 3:
                return 0.50f;
            case 4:
                return 0.6f;
            case 5:
                return 0.7f;
            case 6:
                return 0.8f;
            case 7:
                return 0.9f;
            default:
                return 0.5f;
        }

//        switch (row) {
//            case 0:
//                return 0.15f;
//            case 1:
//                return 0.22f;
//            case 2:
//                return 0.33f;
//            case 3:
//                return 0.40f;
//            case 4:
//                return 0.5f;
//            case 5:
//                return 0.6f;
//            case 6:
//                return 0.7f;
//            case 7:
//                return 0.8f;
//            case 8:
//                return 0.9f;
//            case 9:
//                return 0.10f;
//            default:
//                return 0f;
//        }
    }

    private static Triple<Float, Float, Float> getWhiteFromColumn(Integer column) {
        switch (column) {
            case 1:
                return new Triple<>(120f, 1f, 1f);
            case 2:
                return new Triple<>(0f, 0f, 0.92f);
            case 3:
                return new Triple<>(0f, 0f, 0.84f);
            case 4:
                return new Triple<>(0f, 0f, 0.76f);
            case 5:
                return new Triple<>(0f, 0f, 0.7f);
            case 6:
                return new Triple<>(0f, 0f, 0.6f);
            case 7:
                return new Triple<>(0f, 0f, 0.5f);
            case 8:
                return new Triple<>(0f, 0f, 0.4f);
            case 9:
                return new Triple<>(0f, 0f, 0.36f);
            case 10:
                return new Triple<>(0f, 0f, 0.28f);
            case 11:
                return new Triple<>(0f, 0f, 0.2f);
            default:
                return new Triple<>(0f, 0f, 0f);
        }
    }
}
