package com.example.bubblelayout;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class StudentEntity {
    @Id
    private String id;
    private String name;

    private String teachId;

    @Generated(hash = 938350252)
    public StudentEntity(String id, String name, String teachId) {
        this.id = id;
        this.name = name;
        this.teachId = teachId;
    }

    @Override
    public String toString() {
        return "StudentEntity{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", teachId='" + teachId + '\'' +
                '}';
    }

    @Generated(hash = 634333355)
    public StudentEntity() {
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

    public String getTeachId() {
        return this.teachId;
    }

    public void setTeachId(String teachId) {
        this.teachId = teachId;
    }


}
