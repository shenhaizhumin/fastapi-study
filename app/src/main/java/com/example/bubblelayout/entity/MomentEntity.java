package com.example.bubblelayout.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 朋友圈 实体类
 */
public class MomentEntity implements Serializable {

    /**
     * id : 1
     * content : 今天天气hai ke yi
     * images : []
     * content_url : null
     * user_icon : null
     * user_nickname : 深海住民
     * user_id : 1
     * comments : [{"id":1,"operator_user_id":1,"content":"嗯 是的 还不错","publish_time":"2020-07-18T16:00:00.064695","user_nickname":"深海住民","user_avatar_url":null,"moment_id":1}]
     * collects : []
     */

    private int id;
    private String content;
    private String content_url;
    private List<?> images;
    private List<CommentEntity> comments;
    private List<?> collects;
    private PublisherEntity publisher;

    public PublisherEntity getPublisher() {
        return publisher;
    }

    public void setPublisher(PublisherEntity publisher) {
        this.publisher = publisher;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public List<?> getImages() {
        return images;
    }

    public void setImages(List<?> images) {
        this.images = images;
    }

    public List<CommentEntity> getComments() {
        return comments;
    }

    public void setComments(List<CommentEntity> comments) {
        this.comments = comments;
    }

    public List<?> getCollects() {
        return collects;
    }

    public void setCollects(List<?> collects) {
        this.collects = collects;
    }

    
}
