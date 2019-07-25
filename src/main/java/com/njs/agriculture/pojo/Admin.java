package com.njs.agriculture.pojo;

public class Admin {
    private Integer adminId;

    private String adminName;

    private String password;

    private Integer status;

    public Admin(Integer adminId, String adminName, String password, Integer status) {
        this.adminId = adminId;
        this.adminName = adminName;
        this.password = password;
        this.status = status;
    }

    public Admin() {
        super();
    }

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName == null ? null : adminName.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}