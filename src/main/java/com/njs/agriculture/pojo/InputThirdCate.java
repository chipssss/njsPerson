package com.njs.agriculture.pojo;

public class InputThirdCate {
    private Integer id;

    private String name;

    private Integer userId;

    private Integer secondcateId;

    public InputThirdCate(Integer id, String name, Integer userId, Integer secondcateId) {
        this.id = id;
        this.name = name;
        this.userId = userId;
        this.secondcateId = secondcateId;
    }

    public InputThirdCate() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getSecondcateId() {
        return secondcateId;
    }

    public void setSecondcateId(Integer secondcateId) {
        this.secondcateId = secondcateId;
    }
}