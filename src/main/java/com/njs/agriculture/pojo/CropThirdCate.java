package com.njs.agriculture.pojo;

public class CropThirdCate {
    private Integer id;

    private String name;

    private Integer firstcateId;

    private Integer secondcateId;

    public CropThirdCate(Integer id, String name, Integer firstcateId, Integer secondcateId) {
        this.id = id;
        this.name = name;
        this.firstcateId = firstcateId;
        this.secondcateId = secondcateId;
    }

    public CropThirdCate() {
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

    public Integer getFirstcateId() {
        return firstcateId;
    }

    public void setFirstcateId(Integer firstcateId) {
        this.firstcateId = firstcateId;
    }

    public Integer getSecondcateId() {
        return secondcateId;
    }

    public void setSecondcateId(Integer secondcateId) {
        this.secondcateId = secondcateId;
    }
}