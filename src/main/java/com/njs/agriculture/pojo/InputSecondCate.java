package com.njs.agriculture.pojo;

public class InputSecondCate {
    private Integer id;

    private String name;

    private Integer firstcateId;

    public InputSecondCate(Integer id, String name, Integer firstcateId) {
        this.id = id;
        this.name = name;
        this.firstcateId = firstcateId;
    }

    public InputSecondCate() {
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
}