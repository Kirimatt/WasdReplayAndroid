package com.kirimatt.wasdAndroid.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.ImageView;

import androidx.core.content.ContextCompat;

import com.kirimatt.wasdAndroid.R;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class ImageManager {
    private static final String TAG = "ImageManager";
    private static final Map<String, Bitmap> IMAGE_MAP = new HashMap<>();

    /**
     * Private constructor prevents instantiation from other classes
     */
    private ImageManager() {
    }

    public static void fetchImage(final String iUrl, final ImageView iView, Context context) {
        //TODO: сделать отдельным методом от репликации кода
        if (iUrl == null || iView == null)
            return;

        if (IMAGE_MAP.containsKey(iUrl)) {
            iView.setImageBitmap(IMAGE_MAP.get(iUrl));
            return;
        }

        // Create an executor that executes tasks in the main thread.
        Executor mainExecutor = ContextCompat.getMainExecutor(context);

        // Create an executor that executes tasks in a background thread.
        ScheduledExecutorService backgroundExecutor = Executors.newSingleThreadScheduledExecutor();

        backgroundExecutor.execute(() -> {
            final Bitmap image = downloadImage(iUrl);
            IMAGE_MAP.put(iUrl, image);
            if (image != null) {
                mainExecutor.execute(() -> iView.setImageBitmap(image));
            }
        });
        iView.setImageResource(R.drawable.icon);
    }

    public static void fetchImageWithScale(final String iUrl, final ImageView iView, Context context,
                                           int width, int height, boolean isPixelated) {
        if (iUrl == null || iView == null)
            return;

        if (IMAGE_MAP.containsKey(iUrl)) {
            iView.setImageBitmap(IMAGE_MAP.get(iUrl));
            return;
        }

        // Create an executor that executes tasks in the main thread.
        Executor mainExecutor = ContextCompat.getMainExecutor(context);

        // Create an executor that executes tasks in a background thread.
        ScheduledExecutorService backgroundExecutor = Executors.newSingleThreadScheduledExecutor();

        backgroundExecutor.execute(() -> {
            final Bitmap image = Bitmap.createScaledBitmap(
                    downloadImage(iUrl),
                    width,
                    height,
                    isPixelated
            );

            IMAGE_MAP.put(iUrl, image);

            if (image != null) {
                mainExecutor.execute(() -> iView.setImageBitmap(image));
            }

        });
        iView.setImageResource(R.drawable.icon);
    }

    public static Bitmap downloadImage(String iUrl) {
        Bitmap bitmap = null;
        HttpURLConnection conn = null;
        BufferedInputStream bufferedInputStream = null;
        //TODO: Change to try-with-resources
        try {
            conn = (HttpURLConnection) new URL(iUrl).openConnection();
            conn.setDoInput(true);
            conn.setRequestProperty("Connection", "Keep-Alive");
            conn.connect();
            bufferedInputStream = new BufferedInputStream(conn.getInputStream(), 8192);
            bitmap = BitmapFactory.decodeStream(bufferedInputStream);
            bufferedInputStream.close();
            conn.disconnect();
            bufferedInputStream = null;
            conn = null;
        } catch (MalformedURLException ex) {
            Log.e(TAG, "Url parsing was failed: " + iUrl);
        } catch (IOException ex) {
            Log.d(TAG, iUrl + " does not exists");
        } catch (OutOfMemoryError e) {
            Log.w(TAG, "Out of memory!!!");
            return null;
        } finally {
            if (bufferedInputStream != null)
                try {
                    bufferedInputStream.close();
                } catch (IOException ignored) {
                }
            if (conn != null)
                conn.disconnect();
        }
        return bitmap;
    }
}
