package com.example.lifecycle.observer;

public interface Observable {
    void update(Object msg);

    void addObserver(Observer observer);

    void removeObserver(Observer observer);

    void clear();
}
