package com.example.bubblelayout.ws;

import android.util.Log;

import com.example.bubblelayout.utils.UserInfoUtil;
import com.google.gson.Gson;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class JWebSocketClient extends WebSocketClient {


    public JWebSocketClient(URI serverUri) {
        super(serverUri, new Draft_6455());
    }

    @Override
    public void onOpen(ServerHandshake handshakedata) {
        Log.e("JWebSocketClient", "onOpen()");
//        String json = "{'user_id':" + UserInfoUtil.INSTANCE.getUserId() + "}";
        WsManager.getInstance().sendMsg(new Gson().toJson(new WsMsg(UserInfoUtil.INSTANCE.getUserId())));
    }

    @Override
    public void onMessage(String message) {
        Log.e("JWebSocketClient", "onMessage:" + message);
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        Log.e("JWebSocketClient", "onClose:" + reason);
    }

    @Override
    public void onError(Exception ex) {
        ex.printStackTrace();
        Log.e("JWebSocketClient", "onError()");
    }
}
