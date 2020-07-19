package com.example.bubblelayout.api.body;

import java.io.Serializable;

public class UserBody implements Serializable {
    //    val username: String?,
//    val password: String?,
//    val email: String?,
//    val mobile: String?,
//    val nickname: String?,
//    val avatar_url: String?
    private String username;
    private String password;
    private String email;
    private String mobile;
    private String moment_image;

    public String getMoment_image() {
        return moment_image;
    }

    public void setMoment_image(String moment_image) {
        this.moment_image = moment_image;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public UserBody() {
    }

    private Integer id;

    public UserBody(String username, String password, String email, String mobile, String nickname, String avatar_url) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.mobile = mobile;
        this.nickname = nickname;
        this.avatar_url = avatar_url;
    }

    private String nickname;
    private String avatar_url;
}
