package com.example.bubblelayout;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class Head {
    @Override
    public String toString() {
        return "Head{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    @Id
    private String id;
    private String name;
    @Generated(hash = 2070389002)
    public Head(String id, String name) {
        this.id = id;
        this.name = name;
    }
    @Generated(hash = 1745729831)
    public Head() {
    }
    public String getId() {
        return this.id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
