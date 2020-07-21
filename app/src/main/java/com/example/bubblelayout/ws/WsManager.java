package com.example.bubblelayout.ws;

import android.util.Log;

import java.net.URI;
import java.nio.ByteBuffer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class WsManager {
    private final String TAG = getClass().getSimpleName();
    private ExecutorService executorService;
    private JWebSocketClient mClient;

    private WsManager() {
        executorService = Executors.newCachedThreadPool();
    }

    public static WsManager getInstance() {
        return Builder.wsManager;
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
                        Log.e(TAG, message);
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
