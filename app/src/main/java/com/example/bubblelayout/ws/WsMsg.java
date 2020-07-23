package com.example.bubblelayout.ws;

import java.io.Serializable;

public class WsMsg implements Serializable {

    private Integer user_id;

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public WsMsg(Integer user_id) {
        this.user_id = user_id;
    }
}
