package com.njs.agriculture.pojo;

import lombok.Data;

import java.util.Date;
@Data
public class App {
    private Integer appId;

    private Integer versionCode;

    private String delcare;

    private String file;

    private Date createTime;

    public App(Integer appId, Integer versionCode, String delcare, String file, Date createTime) {
        this.appId = appId;
        this.versionCode = versionCode;
        this.delcare = delcare;
        this.file = file;
        this.createTime = createTime;
    }

    public App() {
        super();
    }

    public Integer getAppId() {
        return appId;
    }

    public void setAppId(Integer appId) {
        this.appId = appId;
    }

    public Integer getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(Integer versionCode) {
        this.versionCode = versionCode;
    }

    public String getDelcare() {
        return delcare;
    }

    public void setDelcare(String delcare) {
        this.delcare = delcare == null ? null : delcare.trim();
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file == null ? null : file.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}