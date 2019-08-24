package com.njs.agriculture.pojo;

public class UserRelationship {
    private Integer id;

    private Integer userId;

    private Integer enterpriseId;

    private Integer position;

    private Integer status;

    public UserRelationship(Integer id, Integer userId, Integer enterpriseId, Integer position, Integer status) {
        this.id = id;
        this.userId = userId;
        this.enterpriseId = enterpriseId;
        this.position = position;
        this.status = status;
    }

    public UserRelationship() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Integer enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}