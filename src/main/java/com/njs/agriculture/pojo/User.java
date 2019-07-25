package com.njs.agriculture.pojo;

public class User {
    private Integer userId;

    private String username;

    private String password;

    private String phonenum;

    private Integer type;

    private String image;

    public User(Integer userId, String username, String password, String phonenum, Integer type, String image) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.phonenum = phonenum;
        this.type = type;
        this.image = image;
    }

    public User() {
        super();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getPhonenum() {
        return phonenum;
    }

    public void setPhonenum(String phonenum) {
        this.phonenum = phonenum == null ? null : phonenum.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image == null ? null : image.trim();
    }
}