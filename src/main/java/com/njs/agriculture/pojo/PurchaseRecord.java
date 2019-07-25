package com.njs.agriculture.pojo;

public class PurchaseRecord {
    private Integer id;

    private Integer personnalId;

    private Integer poolId;

    private Integer quantity;

    private Integer buyerId;

    private Integer enterpriseId;

    private Integer source;

    public PurchaseRecord(Integer id, Integer personnalId, Integer poolId, Integer quantity, Integer buyerId, Integer enterpriseId, Integer source) {
        this.id = id;
        this.personnalId = personnalId;
        this.poolId = poolId;
        this.quantity = quantity;
        this.buyerId = buyerId;
        this.enterpriseId = enterpriseId;
        this.source = source;
    }

    public PurchaseRecord() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPersonnalId() {
        return personnalId;
    }

    public void setPersonnalId(Integer personnalId) {
        this.personnalId = personnalId;
    }

    public Integer getPoolId() {
        return poolId;
    }

    public void setPoolId(Integer poolId) {
        this.poolId = poolId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(Integer buyerId) {
        this.buyerId = buyerId;
    }

    public Integer getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Integer enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }
}