package com.example.bubblelayout.entity;

import java.util.List;

/**
 * {
 *             "_id": "5f02eb55808d6d2fe6b56f31",
 *             "author": "Espoir",
 *             "category": "GanHuo",
 *             "createdAt": "2020-07-06 17:13:57",
 *             "desc": "是时候提高你撸RecycleView的效率了，简单而方便！",
 *             "images": [
 *                 "https://gank.io/images/52f5fcc58dcd4f8c854f073e13a88d30"
 *             ],
 *             "likeCounts": 0,
 *             "publishedAt": "2020-07-06 17:13:57",
 *             "stars": 1,
 *             "title": "是时候提高你撸RecycleView的效率了",
 *             "type": "Android",
 *             "url": "https://github.com/EspoirX/EfficientAdapter",
 *             "views": 2
 *         }
 */
public class CategoryEntity {
    private String author;
    private String desc;
    private String title;
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    private String publishedAt;
//"desc": "Always deliver more than expected.（Larry Page）",
//        "title": "Android",
//        "type": "Android"
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    private List<String> images;

}
