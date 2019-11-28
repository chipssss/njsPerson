package com.njs.agriculture.pojo;

import java.util.Date;

public class App {
    private Integer appId;

    private Integer versionCode;

    private String declare;

    private String file;

    private Date createTime;

    public App(Integer appId, Integer versionCode, String declare, String file, Date createTime) {
        this.appId = appId;
        this.versionCode = versionCode;
        this.declare = declare;
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

    public String getDeclare() {
        return declare;
    }

    public void setDeclare(String declare) {
        this.declare = declare == null ? null : declare.trim();
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