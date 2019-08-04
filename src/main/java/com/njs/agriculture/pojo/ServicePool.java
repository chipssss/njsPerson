package com.njs.agriculture.pojo;

public class ServicePool {
    private Integer id;

    private Integer sourceId;

    private String reason;

    private Integer serviceId;

    private Integer status;

    private String reply;

    private Integer source;

    public ServicePool(Integer id, Integer sourceId, String reason, Integer serviceId, Integer status, String reply, Integer source) {
        this.id = id;
        this.sourceId = sourceId;
        this.reason = reason;
        this.serviceId = serviceId;
        this.status = status;
        this.reply = reply;
        this.source = source;
    }

    public ServicePool() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSourceId() {
        return sourceId;
    }

    public void setSourceId(Integer sourceId) {
        this.sourceId = sourceId;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason == null ? null : reason.trim();
    }

    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply == null ? null : reply.trim();
    }

    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }
}