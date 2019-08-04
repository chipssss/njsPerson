package com.njs.agriculture.pojo;

public class ServiceInfo {
    private Integer id;

    private String serviceName;

    public ServiceInfo(Integer id, String serviceName) {
        this.id = id;
        this.serviceName = serviceName;
    }

    public ServiceInfo() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName == null ? null : serviceName.trim();
    }
}