package com.example.bubblelayout.imageloader;

import android.graphics.Bitmap;
import android.util.Log;

import com.blankj.utilcode.util.EncodeUtils;
import com.blankj.utilcode.util.EncryptUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;


public class FileUtil {
    private static String cacheDir = ImageLoader.sContext.getExternalCacheDir().getAbsolutePath() + File.separator + "imageCache" + File.separator;

    public static void saveToDisk(String url, InputStream is) {
        File fileCacheDir = new File(cacheDir);
        if (!fileCacheDir.exists()) {
            boolean mkdirs = fileCacheDir.mkdirs();
            Log.d("TAG", "saveToDisk: " + mkdirs);
        }
        File file = new File(fileCacheDir, getFileName(url));
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            byte[] bytes = new byte[1024];
//            int length = 0;
            while (is.read(bytes) != -1) {
                fos.write(bytes);
            }
            fos.flush();
            Log.e("tag", "已保存至：" + file.getAbsolutePath());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void saveBitmap(String url, Bitmap bitmap) {
        File fileCacheDir = new File(cacheDir);
        if (!fileCacheDir.exists()) {
            boolean mkdirs = fileCacheDir.mkdirs();
            Log.d("TAG", "saveToDisk: " + mkdirs);
        }
        File file = new File(fileCacheDir, getFileName(url));
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 80, fos);
            fos.flush();
            Log.e("tag", "已保存至：" + file.getAbsolutePath());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static String getFileName(String url) {
        return EncryptUtils.encryptMD5ToString(url) + ".png";
    }

    public static File getImageFromFile(String url) {
        return new File(cacheDir + getFileName(url));
    }
}
