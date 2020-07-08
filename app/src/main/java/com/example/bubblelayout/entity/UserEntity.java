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
    private static final long serialVersionUID = 1L;
    private SpaceEntity current_space;
    private String account_uid;
    private String avatar_url;
    private SpaceEntity owner_space;
    private String account_username;
    private String access_token;
    private String nickname;

    private Long id;
    private List<SpaceEntity> spaces;

    public SpaceEntity getCurrent_space() {
        return current_space;
    }

    public void setCurrent_space(SpaceEntity current_space) {
        this.current_space = current_space;
    }

    public String getAccount_uid() {
        return account_uid;
    }

    public void setAccount_uid(String account_uid) {
        this.account_uid = account_uid;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    public SpaceEntity getOwner_space() {
        return owner_space;
    }

    public void setOwner_space(SpaceEntity owner_space) {
        this.owner_space = owner_space;
    }

    public String getAccount_username() {
        return account_username;
    }

    public void setAccount_username(String account_username) {
        this.account_username = account_username;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<SpaceEntity> getSpaces() {
        return spaces;
    }

    public void setSpaces(List<SpaceEntity> spaces) {
        this.spaces = spaces;
    }

}
