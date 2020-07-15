package com.example.bubblelayout.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;

import java.io.Serializable;
import java.util.List;


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
    private Long id;
    private String uid;
    private String username;
    private String email;
    private String mobile;
    private String nickname;
    private String avatar_url;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    private String latest_ip;
    private String latest_time;
    private String access_token;
    private String type_role;
    private static final long serialVersionUID = 1L;
//    private SpaceEntity current_space;
//    private String account_uid;
//    private String avatar_url;
//    private SpaceEntity owner_space;
//    private String account_username;
//    private String access_token;
//    private String nickname;
//
//    private Long id;
//    private List<SpaceEntity> spaces;
//
//    public SpaceEntity getCurrent_space() {
//        return current_space;
//    }
//
//    public void setCurrent_space(SpaceEntity current_space) {
//        this.current_space = current_space;
//    }
//
//    public String getAccount_uid() {
//        return account_uid;
//    }
//
//    public void setAccount_uid(String account_uid) {
//        this.account_uid = account_uid;
//    }
//
//    public String getAvatar_url() {
//        return avatar_url;
//    }
//
//    public void setAvatar_url(String avatar_url) {
//        this.avatar_url = avatar_url;
//    }
//
//    public SpaceEntity getOwner_space() {
//        return owner_space;
//    }
//
//    public void setOwner_space(SpaceEntity owner_space) {
//        this.owner_space = owner_space;
//    }
//
//    public String getAccount_username() {
//        return account_username;
//    }
//
//    public void setAccount_username(String account_username) {
//        this.account_username = account_username;
//    }
//
//    public String getAccess_token() {
//        return access_token;
//    }
//
//    public void setAccess_token(String access_token) {
//        this.access_token = access_token;
//    }
//
//    public String getNickname() {
//        return nickname;
//    }
//
//    public void setNickname(String nickname) {
//        this.nickname = nickname;
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public List<SpaceEntity> getSpaces() {
//        return spaces;
//    }
//
//    public void setSpaces(List<SpaceEntity> spaces) {
//        this.spaces = spaces;
//    }

}
