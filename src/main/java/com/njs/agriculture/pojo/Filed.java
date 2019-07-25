package com.njs.agriculture.pojo;

public class Filed {
    private Integer id;

    private Float area;

    private String name;

    private Integer managerId;

    private Integer status;

    private String coordinate;

    private Integer qrcodeId;

    private String remark;

    private Integer sourceId;

    private Integer source;

    public Filed(Integer id, Float area, String name, Integer managerId, Integer status, String coordinate, Integer qrcodeId, String remark, Integer sourceId, Integer source) {
        this.id = id;
        this.area = area;
        this.name = name;
        this.managerId = managerId;
        this.status = status;
        this.coordinate = coordinate;
        this.qrcodeId = qrcodeId;
        this.remark = remark;
        this.sourceId = sourceId;
        this.source = source;
    }

    public Filed() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Float getArea() {
        return area;
    }

    public void setArea(Float area) {
        this.area = area;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getManagerId() {
        return managerId;
    }

    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(String coordinate) {
        this.coordinate = coordinate == null ? null : coordinate.trim();
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