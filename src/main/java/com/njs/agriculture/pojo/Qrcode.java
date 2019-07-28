package com.njs.agriculture.pojo;

import java.util.Date;

public class Qrcode {
    private Integer id;

    private Date createTime;

    public Qrcode(Integer id, Date createTime) {
        this.id = id;
        this.createTime = createTime;
    }


    public Qrcode() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}