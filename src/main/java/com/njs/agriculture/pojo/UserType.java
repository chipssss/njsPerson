package com.njs.agriculture.pojo;

public class UserType {
    private Integer id;

    private String category;

    public UserType(Integer id, String category) {
        this.id = id;
        this.category = category;
    }

    public UserType() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category == null ? null : category.trim();
    }
}