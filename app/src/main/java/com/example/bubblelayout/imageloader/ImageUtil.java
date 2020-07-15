package com.example.bubblelayout.imageloader;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.util.Log;

import androidx.core.graphics.drawable.DrawableKt;

import com.bumptech.glide.load.resource.drawable.DrawableDecoderCompat;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ImageUtil {
    private static OkHttpClient client;

    private ImageUtil() {
        client = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .build();
    }

    public static ImageUtil getInstance() {
        return ImageUtilHolder.imageUtil;
    }

    private static class ImageUtilHolder {
        public static ImageUtil imageUtil = new ImageUtil();
    }


    /**
     * 下载bitmap
     *
     * @param url
     * @return
     */
    public Bitmap downloadBitmap(String url) {
        if (url == null || url.isEmpty()) {
            return null;
        }
        if (!url.contains("http://") && !url.contains("https://")) {
            url = "https://" + url;
        }
        Request request = new Request.Builder()
                .url(url)
                .build();
        InputStream inputStream = null;
        Response response = null;
        try {
            response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                inputStream = response.body().byteStream();
                //保存图片到本地
                //FileUtil.saveToDisk(url, inputStream);
                return BitmapFactory.decodeStream(inputStream);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (response != null) {
                response.close();
            }
            Log.e("tag", "load image from network");
        }
        return null;
    }

    /**
     * 下载drawable
     *
     * @param url
     * @return
     */
    public Drawable downloadDrawable(String url) {
        Request request = new Request.Builder()
                .url(url)
                .build();
        InputStream inputStream = null;
        Response response = null;
        try {
            response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                inputStream = response.body().byteStream();
                return Drawable.createFromStream(inputStream, "src");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (response != null) {
                response.close();
            }
            Log.e("tag", "load image from network");
        }
        return null;
    }
}
