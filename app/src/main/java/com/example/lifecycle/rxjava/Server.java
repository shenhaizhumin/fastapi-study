package com.example.lifecycle.rxjava;

import com.example.lifecycle.observer.Observable;
import com.example.lifecycle.observer.Observer;

import java.util.ArrayList;
import java.util.List;

public class Server implements Observable {
    private List<Observer> list = new ArrayList<>();

    @Override
    public void update(Object msg) {
        for (Observer observer : list) {
            observer.notifyMsg(msg);
        }
    }

    @Override
    public void addObserver(Observer observer) {
        list.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        list.remove(observer);
    }

    @Override
    public void clear() {
        list.clear();
    }
}
