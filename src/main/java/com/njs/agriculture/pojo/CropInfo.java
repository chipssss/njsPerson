package com.njs.agriculture.pojo;

public class CropInfo {
    private Integer id;

    private Integer categoryId;

    private String name;

    private Integer sourceId;

    private Integer source;

    public CropInfo(Integer id, Integer categoryId, String name, Integer sourceId, Integer source) {
        this.id = id;
        this.categoryId = categoryId;
        this.name = name;
        this.sourceId = sourceId;
        this.source = source;
    }

    public CropInfo() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getSourceId() {
        return sourceId;
    }

    public void setSourceId(Integer sourceId) {
        this.sourceId = sourceId;
    }

    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }
}