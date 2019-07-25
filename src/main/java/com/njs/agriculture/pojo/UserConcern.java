package com.njs.agriculture.pojo;

public class UserConcern {
    private Integer id;

    private Integer userId;

    private String variety;

    private String domain;

    public UserConcern(Integer id, Integer userId, String variety, String domain) {
        this.id = id;
        this.userId = userId;
        this.variety = variety;
        this.domain = domain;
    }

    public UserConcern() {
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

    public String getVariety() {
        return variety;
    }

    public void setVariety(String variety) {
        this.variety = variety == null ? null : variety.trim();
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain == null ? null : domain.trim();
    }
}