package com.example.bubblelayout.imageloader;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.util.Log;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.example.bubblelayout.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ImageLoader {
    private static Handler handler;
    private static ExecutorService mExecutorService;
    //内存缓存
    private static Map<String, SoftReference<Bitmap>> memoryCache;
    //任务队列
    private static ArrayList<Task> taskQueue;
    private static OkHttpClient okHttpClient;
    private static ImageUtil imageUtil = ImageUtil.getInstance();
    public static Context sContext;

    private ImageLoader() {
    }

    public static ImageLoader getInstance() {
        return ImageLoaderBuilder.imageLoader;
    }

    private static class ImageLoaderBuilder {
        public static ImageLoader imageLoader = new ImageLoader();
    }

    public void init(Context context) {
        sContext = context;
        handler = new Handler(context.getMainLooper());
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        mExecutorService = Executors.newFixedThreadPool(availableProcessors / 2 + 1);
        memoryCache = new HashMap<>();
        taskQueue = new ArrayList<>();
        okHttpClient = new OkHttpClient.Builder()
                .build();
    }

    private Bitmap getBitmap(String key) {
        Bitmap bitmap;
        SoftReference<Bitmap> reference = memoryCache.get(key);
        if (reference != null) {
            bitmap = reference.get();
            if (bitmap != null) {
                return bitmap;
            } else {
                memoryCache.remove(key);
            }
        } else {
            bitmap = imageUtil.downloadBitmap(key);
        }
        return bitmap;
    }

    public synchronized void loadImage(final String url, final ImageView imageView) {
        imageView.setImageResource(R.mipmap.ic_launcher);
        imageView.setTag(url);
        SoftReference<Bitmap> reference = memoryCache.get(url);
        if (reference != null) {
            Bitmap bitmap = reference.get();
            if (bitmap != null) {
                imageView.setImageBitmap(bitmap);
                Log.e("tag", "load image from memory");
                return;
            } else {
                memoryCache.remove(url);
            }
        }
        File imageFromFile = FileUtil.getImageFromFile(url);
        if (imageFromFile.exists()) {
            try {
                FileInputStream fis = new FileInputStream(imageFromFile);
                Bitmap bitmap = BitmapFactory.decodeStream(fis);
                imageView.setImageBitmap(bitmap);
                Log.e("tag", "load image from disk");
                //缓存到内存
                memoryCache.put(url, new SoftReference<>(bitmap));
                return;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        mExecutorService.execute(new Runnable() {
            @Override
            public void run() {
                final Bitmap bitmap = imageUtil.downloadBitmap(url);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (bitmap == null) {
                            imageView.setImageResource(R.mipmap.ic_launcher);
                        } else {
                            //保存图片到本地
                            FileUtil.saveBitmap(url, bitmap);
                            imageView.setImageBitmap(bitmap);
                        }
                    }
                });
            }
        });


    }

    private class Task {
        String path;

        @Override
        public boolean equals(@Nullable Object obj) {
            if (obj == null) return false;
            return this.path.equals(((Task) obj).path);
        }
    }

}
