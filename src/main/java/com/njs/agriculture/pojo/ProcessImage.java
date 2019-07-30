package com.njs.agriculture.pojo;

public class ProcessImage {
    private Integer id;

    private Integer recordId;

    private String image;

    public ProcessImage(Integer id, Integer recordId, String image) {
        this.id = id;
        this.recordId = recordId;
        this.image = image;
    }

    public ProcessImage() {
        super();
    }

    public ProcessImage(Integer recordId, String image) {
        this.recordId = recordId;
        this.image = image;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRecordId() {
        return recordId;
    }

    public void setRecordId(Integer recordId) {
        this.recordId = recordId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image == null ? null : image.trim();
    }
}