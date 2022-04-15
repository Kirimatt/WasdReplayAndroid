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
        fetchImageAll(iUrl, iView, context, 0, 0, false);
    }

    public static void fetchImageWithScale(final String iUrl, final ImageView iView, Context context,
                                           int width, int height, boolean isPixelated) {
        fetchImageAll(iUrl, iView, context, width, height, isPixelated);
    }

    private static void fetchImageAll(final String iUrl, final ImageView iView, Context context,
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
            final Bitmap image = width == 0 && height == 0 ?
                    downloadImage(iUrl) :
                    Bitmap.createScaledBitmap(
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

        try {
            HttpURLConnection conn = (HttpURLConnection) new URL(iUrl).openConnection();
            conn.setDoInput(true);
            conn.setRequestProperty("Connection", "Keep-Alive");
            conn.connect();
            try (BufferedInputStream bufferedInputStream =
                         new BufferedInputStream(conn.getInputStream(), 8192)) {
                bitmap = BitmapFactory.decodeStream(bufferedInputStream);
            }
            conn.disconnect();
        } catch (MalformedURLException ex) {
            Log.e(TAG, "Url parsing was failed: " + iUrl);
        } catch (IOException ex) {
            Log.d(TAG, iUrl + " does not exists");
        } catch (OutOfMemoryError e) {
            Log.w(TAG, "Out of memory!!!");
            return null;
        }
        return bitmap;
    }
}
