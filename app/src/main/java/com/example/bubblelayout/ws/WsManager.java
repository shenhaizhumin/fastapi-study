package com.example.bubblelayout.ws;

import android.content.Context;
import android.os.Handler;
import android.util.Log;

import com.example.bubblelayout.MyApp;
import com.example.bubblelayout.utils.UserInfoUtil;

import java.lang.ref.WeakReference;
import java.net.URI;
import java.nio.ByteBuffer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class WsManager {
    private final String TAG = getClass().getSimpleName();
    private ExecutorService executorService;
    private JWebSocketClient mClient;
    private static WeakReference<Handler> wrHandler;
    public static WsManager wsManager;


    private WsManager() {
        wrHandler = new WeakReference<>(new Handler(MyApp.context.getMainLooper()));
        executorService = Executors.newCachedThreadPool();
    }

    public static WsManager getInstance() {
        if (wsManager == null) {
            synchronized (WsManager.class) {
                if (wsManager == null) {
                    wsManager = new WsManager();
                }
            }
        }
        return wsManager;
//        return Builder.wsManager;
    }

    private static class Builder {
        public static WsManager wsManager = new WsManager();
    }

    public void connectToWebSocket(String path, IMessageCallback callback) {
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                mClient = new JWebSocketClient(URI.create(path)) {
                    @Override
                    public void onMessage(String message) {
                        super.onMessage(message);
//                        Log.e(TAG, message);
//                        Handler handler = wrHandler.get();
//                        if (handler != null) {
//                            handler.post(() -> callback.onMessage(message));
//                        }
                        callback.onMessage(message);
                    }

                    @Override
                    public void onMessage(ByteBuffer bytes) {
                        super.onMessage(bytes);
                    }
                };
                try {
                    mClient.connectBlocking(10, TimeUnit.SECONDS);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Log.e(TAG, "连接超时！");
                }
            }
        });
    }

    public void sendMsg(String msg) {
        if (mClient != null && mClient.isOpen()) {
            mClient.send(msg);
        }
    }

    /**
     * 断开连接
     */
    public void closeConnect() {
        try {
            if (null != mClient) {
                mClient.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            mClient = null;
        }
    }


}
