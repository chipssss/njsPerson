package com.njs.agriculture.pojo;

public class Field {
    private Integer id;

    private Float square;

    private String name;

    private String manager;

    private Integer status;

    private String location;

    private Integer qrcodeId;

    private String remark;

    private Integer sourceId;

    private Integer source;

    public Field(Integer id, Float square, String name, String manager, Integer status, String location, Integer qrcodeId, String remark, Integer sourceId, Integer source) {
        this.id = id;
        this.square = square;
        this.name = name;
        this.manager = manager;
        this.status = status;
        this.location = location;
        this.qrcodeId = qrcodeId;
        this.remark = remark;
        this.sourceId = sourceId;
        this.source = source;
    }

    public Field() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Float getSquare() {
        return square;
    }

    public void setSquare(Float square) {
        this.square = square;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager == null ? null : manager.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location == null ? null : location.trim();
    }

    public Integer getQrcodeId() {
        return qrcodeId;
    }

    public void setQrcodeId(Integer qrcodeId) {
        this.qrcodeId = qrcodeId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
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