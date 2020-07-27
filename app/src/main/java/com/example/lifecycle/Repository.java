package com.example.lifecycle;

public class Repository {

    public static User setUser() {
        User user = new User("张三", 22);
        return user;
    }
}
