package com.example.bubblelayout.entity;

import java.io.Serializable;


public class SpaceEntity implements Serializable {
    /**
     * space_id : 1
     * id : 2171
     * space_name : 戴卡团队
     */

    private static final long serialVersionUID = 12344567L;
    private int space_id;
    private Long id;
    private String space_name;

    public int getSpace_id() {
        return space_id;
    }

    public void setSpace_id(int space_id) {
        this.space_id = space_id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSpace_name() {
        return space_name;
    }

    public void setSpace_name(String space_name) {
        this.space_name = space_name;
    }
}
