package com.njs.agriculture.pojo;

import java.util.Date;

public class AppErrorDO {
    private Integer id;

    private String appVersion;

    private String osVersion;

    private String otherPhoneVersion;

    private Date createTime;

    private String errorMsg;

    public AppErrorDO(Integer id, String appVersion, String osVersion, String otherPhoneVersion, Date createTime) {
        this.id = id;
        this.appVersion = appVersion;
        this.osVersion = osVersion;
        this.otherPhoneVersion = otherPhoneVersion;
        this.createTime = createTime;
    }

    public AppErrorDO(Integer id, String appVersion, String osVersion, String otherPhoneVersion, Date createTime, String errorMsg) {
        this.id = id;
        this.appVersion = appVersion;
        this.osVersion = osVersion;
        this.otherPhoneVersion = otherPhoneVersion;
        this.createTime = createTime;
        this.errorMsg = errorMsg;
    }

    public AppErrorDO() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion == null ? null : appVersion.trim();
    }

    public String getOsVersion() {
        return osVersion;
    }

    public void setOsVersion(String osVersion) {
        this.osVersion = osVersion == null ? null : osVersion.trim();
    }

    public String getOtherPhoneVersion() {
        return otherPhoneVersion;
    }

    public void setOtherPhoneVersion(String otherPhoneVersion) {
        this.otherPhoneVersion = otherPhoneVersion == null ? null : otherPhoneVersion.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg == null ? null : errorMsg.trim();
    }
}