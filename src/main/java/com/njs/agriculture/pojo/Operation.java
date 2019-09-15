package com.njs.agriculture.pojo;

public class Operation {
    private Integer id;

    private String name;

    private String logoImg;

    public Operation(Integer id, String name, String logoImg) {
        this.id = id;
        this.name = name;
        this.logoImg = logoImg;
    }

    public Operation() {
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

    public String getLogoImg() {
        return logoImg;
    }

    public void setLogoImg(String logoImg) {
        this.logoImg = logoImg == null ? null : logoImg.trim();
    }
}