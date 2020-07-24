package com.example.bubblelayout.entity;

import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;

import java.io.Serializable;

import org.greenrobot.greendao.annotation.Generated;


@Entity
public class UserEntity implements Serializable {

    /**
     * current_space : {"space_id":1,"id":2171,"space_name":"戴卡团队"}
     * account_uid : c06f42ff7b68d83e0e79913fb55db85d473079b6
     * avatar_url :
     * owner_space : {"space_id":1,"id":2171,"space_name":"戴卡团队"}
     * account_username : ickGxLUy
     * access_token : eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VybmFtZSI6Imlja0d4TFV5IiwidWlkIjoiYzA2ZjQyZmY3YjY4ZDgzZTBlNzk5MTNmYjU1ZGI4NWQ0NzMwNzliNiIsImNsaWVudF90eXBlIjoiYXBwIiwiZXhwIjoxNTk2Nzc5NTAyfQ.grRvtzaXA6LY5U_j1nDFD5r4hRajXFK9ZBEz96RltSw
     * nickname : zengqi123
     * id : 1086
     * spaces : [{"space_id":1,"id":2171,"space_name":"戴卡团队"}]
     */
    /**
     * {
     *         "id": 12,
     *         "uid": "240234991a3d7fa51b92cef887eebc60ac58b2e5",
     *         "username": "zengqi",
     *         "email": "549624893@qq.com",
     *         "mobile": "19973488682",
     *         "nickname": "hello",
     *         "avatar_url": null,
     *         "latest_ip": "127.0.0.1",
     *         "type_role": null,
     *         "latest_time": "2020-07-15T18:11:20.616429",
     *         "access_token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VybmFtZSI6IjE5OTczNDg4NjgyIiwidWlkIjoiMjQwMjM0OTkxYTNkN2ZhNTFiOTJjZWY4ODdlZWJjNjBhYzU4YjJlNSIsImV4cCI6MTU5NDkwNzMzN30.Mp-YxtBsfSGgSGFimd1XOQCZ7XSM9UeX7qUdtPa70Ko"
     *     }
     */
    @Id
    @SerializedName("xxx")
    private Long id;
    @SerializedName("id")
    private Integer userId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    public String getMoment_image() {
        return moment_image;
    }

    public void setMoment_image(String moment_image) {
        this.moment_image = moment_image;
    }

    public String getLatest_ip() {
        return latest_ip;
    }

    public void setLatest_ip(String latest_ip) {
        this.latest_ip = latest_ip;
    }

    public String getLatest_time() {
        return latest_time;
    }

    public void setLatest_time(String latest_time) {
        this.latest_time = latest_time;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getType_role() {
        return type_role;
    }

    public void setType_role(String type_role) {
        this.type_role = type_role;
    }

    private String uid;
    private String username;
    private String email;
    private String mobile;
    private String nickname;
    private String avatar_url;
    private String moment_image;
    private String latest_ip;
    private String latest_time;
    private String access_token;
    private String type_role;
    private static final long serialVersionUID = 1L;

    @Generated(hash = 1503988259)
    public UserEntity(Long id, Integer userId, String uid, String username, String email, String mobile, String nickname, String avatar_url, String moment_image, String latest_ip, String latest_time, String access_token, String type_role) {
        this.id = id;
        this.userId = userId;
        this.uid = uid;
        this.username = username;
        this.email = email;
        this.mobile = mobile;
        this.nickname = nickname;
        this.avatar_url = avatar_url;
        this.moment_image = moment_image;
        this.latest_ip = latest_ip;
        this.latest_time = latest_time;
        this.access_token = access_token;
        this.type_role = type_role;
    }

    @Generated(hash = 1433178141)
    public UserEntity() {
    }

}
