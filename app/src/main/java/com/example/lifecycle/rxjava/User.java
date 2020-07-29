package com.example.lifecycle.rxjava;

import android.util.Log;

import com.example.lifecycle.observer.Observer;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class User implements Observer {

    @Id
    private Long id;

    private String name;
    private String pwd;

    @Generated(hash = 1918463323)
    public User(Long id, String name, String pwd) {
        this.id = id;
        this.name = name;
        this.pwd = pwd;
    }

    @Generated(hash = 586692638)
    public User() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return this.pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    @Override
    public void notifyMsg(Object msg) {
        System.out.println(this.name + " 接收到推送：" + msg);
    }
}
