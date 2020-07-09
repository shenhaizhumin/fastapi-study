package com.example.bubblelayout.entity;

import java.io.Serializable;

public class HomeDataEntity implements Serializable {

    private Long id;
    private String itemTitle;
    private String itemDesc;
    private Long itemDownloads;
    private String imagePath;

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    private Integer height;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getItemTitle() {
        return itemTitle;
    }

    public void setItemTitle(String itemTitle) {
        this.itemTitle = itemTitle;
    }

    public String getItemDesc() {
        return itemDesc;
    }

    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc;
    }

    public Long getItemDownloads() {
        return itemDownloads;
    }

    public void setItemDownloads(Long itemDownloads) {
        this.itemDownloads = itemDownloads;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
