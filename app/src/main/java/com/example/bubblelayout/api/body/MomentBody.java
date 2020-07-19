package com.example.bubblelayout.api.body;

import com.example.bubblelayout.entity.FileEntity;

import java.io.Serializable;
import java.util.List;

public class MomentBody implements Serializable {
    //val content: String, val content_url: String?, val images: List<Int>?
    private String content;
    private String content_url;
    private Integer moment_id;
    private List<FileEntity> images;

    public Integer getMoment_id() {
        return moment_id;
    }

    public void setMoment_id(Integer moment_id) {
        this.moment_id = moment_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent_url() {
        return content_url;
    }

    public void setContent_url(String content_url) {
        this.content_url = content_url;
    }

    public List<FileEntity> getImages() {
        return images;
    }

    public void setImages(List<FileEntity> images) {
        this.images = images;
    }


    public MomentBody() {
    }

    public MomentBody(String content, String content_url, List<FileEntity> images) {
        this.content = content;
        this.content_url = content_url;
        this.images = images;
    }
}
