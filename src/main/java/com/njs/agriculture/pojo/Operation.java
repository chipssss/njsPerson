package com.njs.agriculture.pojo;

public class Operation {
    private Integer id;

    private String name;

    public Operation(Integer id, String name) {
        this.id = id;
        this.name = name;
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
}