package com.example.bubblelayout.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;

import java.io.Serializable;

import org.greenrobot.greendao.annotation.Generated;

@Entity
public class ChatMessageEntity implements Serializable {

    private static final long serialVersionUID = -3734873597268655287L;
    @Id(autoincrement = true)
    private Long id;
    private Integer user_id;
    private Integer friend_id;
    private String content;//消息内容
    private Integer type;//聊天类型
    private Long post_date;//发送时间
    private Boolean is_read;//是否已读
    private Integer ismineChat; // 0:发送者是自己 1:发送者是对方
    private Integer deleteChat;
    private String nickname;
    private String friend_avatar_url;
    private String mine_avatar_url;
    private String friend_nickname;
    private Boolean isSave;
    private String uid ;

    @Generated(hash = 700979866)
    public ChatMessageEntity(Long id, Integer user_id, Integer friend_id,
            String content, Integer type, Long post_date, Boolean is_read,
            Integer ismineChat, Integer deleteChat, String nickname,
            String friend_avatar_url, String mine_avatar_url,
            String friend_nickname, Boolean isSave, String uid) {
        this.id = id;
        this.user_id = user_id;
        this.friend_id = friend_id;
        this.content = content;
        this.type = type;
        this.post_date = post_date;
        this.is_read = is_read;
        this.ismineChat = ismineChat;
        this.deleteChat = deleteChat;
        this.nickname = nickname;
        this.friend_avatar_url = friend_avatar_url;
        this.mine_avatar_url = mine_avatar_url;
        this.friend_nickname = friend_nickname;
        this.isSave = isSave;
        this.uid = uid;
    }

    @Generated(hash = 1666122499)
    public ChatMessageEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getFriend_id() {
        return friend_id;
    }

    public void setFriend_id(Integer friend_id) {
        this.friend_id = friend_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Long getPost_date() {
        return post_date;
    }

    public void setPost_date(Long post_date) {
        this.post_date = post_date;
    }

    public Boolean getIs_read() {
        return is_read;
    }

    public void setIs_read(Boolean is_read) {
        this.is_read = is_read;
    }

    public Integer getIsmineChat() {
        return ismineChat;
    }

    public void setIsmineChat(Integer ismineChat) {
        this.ismineChat = ismineChat;
    }

    public Integer getDeleteChat() {
        return deleteChat;
    }

    public void setDeleteChat(Integer deleteChat) {
        this.deleteChat = deleteChat;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getFriend_avatar_url() {
        return friend_avatar_url;
    }

    public void setFriend_avatar_url(String friend_avatar_url) {
        this.friend_avatar_url = friend_avatar_url;
    }

    public String getMine_avatar_url() {
        return mine_avatar_url;
    }

    public void setMine_avatar_url(String mine_avatar_url) {
        this.mine_avatar_url = mine_avatar_url;
    }

    public String getFriend_nickname() {
        return friend_nickname;
    }

    public void setFriend_nickname(String friend_nickname) {
        this.friend_nickname = friend_nickname;
    }

    public Boolean getSave() {
        return isSave;
    }

    public void setSave(Boolean save) {
        isSave = save;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Boolean getIsSave() {
        return this.isSave;
    }

    public void setIsSave(Boolean isSave) {
        this.isSave = isSave;
    }
}
