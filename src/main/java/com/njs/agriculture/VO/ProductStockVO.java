package com.njs.agriculture.VO;

import java.util.Date;
import java.util.List;

public class ProductStockVO {
    private static final long serialVersionUID = 718680644568507041L;

    private Integer id;

    private Integer productId;

    private Integer quantity;

    private Date productionTime;

    private String productionMessage;

    private Integer shelfLife;

    private String batchId;

    private Date createTime;

    List<String> secureImage;

    private Integer source;

    private Integer sourceId;

    private String barcode;

    public ProductStockVO(Integer id, Integer productId, Integer quantity, Date productionTime, String productionMessage, Integer shelfLife, String batchId, Date createTime, List<String> secureImage, Integer source, Integer sourceId, String barcode) {
        this.id = id;
        this.productId = productId;
        this.quantity = quantity;
        this.productionTime = productionTime;
        this.productionMessage = productionMessage;
        this.shelfLife = shelfLife;
        this.batchId = batchId;
        this.createTime = createTime;
        this.secureImage = secureImage;
        this.source = source;
        this.sourceId = sourceId;
        this.barcode = barcode;
    }

    public ProductStockVO() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Date getProductionTime() {
        return productionTime;
    }

    public void setProductionTime(Date productionTime) {
        this.productionTime = productionTime;
    }

    public String getProductionMessage() {
        return productionMessage;
    }

    public void setProductionMessage(String productionMessage) {
        this.productionMessage = productionMessage == null ? null : productionMessage.trim();
    }

    public Integer getShelfLife() {
        return shelfLife;
    }

    public void setShelfLife(Integer shelfLife) {
        this.shelfLife = shelfLife;
    }

    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId == null ? null : batchId.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public List<String>  getSecureImage() {
        return secureImage;
    }

    public void setSecureImage(List<String> secureImage) {
        this.secureImage = secureImage;
    }

    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }

    public Integer getSourceId() {
        return sourceId;
    }

    public void setSourceId(Integer sourceId) {
        this.sourceId = sourceId;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode == null ? null : barcode.trim();
    }
}
