package com.njs.agriculture.pojo;

public class ProductBasic {
    private Integer id;

    private String name;

    private Integer typeId;

    private String process;

    private String feature;

    private String packing;

    private String level;

    private String image;

    private Integer source;

    private Integer sourceId;

    private String producttype;

    private Integer totalStock;

    private Integer totalSale;

    public ProductBasic(Integer id, String name, Integer typeId, String process, String feature, String packing, String level, String image, Integer source, Integer sourceId, String producttype, Integer totalStock, Integer totalSale) {
        this.id = id;
        this.name = name;
        this.typeId = typeId;
        this.process = process;
        this.feature = feature;
        this.packing = packing;
        this.level = level;
        this.image = image;
        this.source = source;
        this.sourceId = sourceId;
        this.producttype = producttype;
        this.totalStock = totalStock;
        this.totalSale = totalSale;
    }

    public ProductBasic() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getProcess() {
        return process;
    }

    public void setProcess(String process) {
        this.process = process == null ? null : process.trim();
    }

    public String getFeature() {
        return feature;
    }

    public void setFeature(String feature) {
        this.feature = feature == null ? null : feature.trim();
    }

    public String getPacking() {
        return packing;
    }

    public void setPacking(String packing) {
        this.packing = packing == null ? null : packing.trim();
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level == null ? null : level.trim();
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image == null ? null : image.trim();
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

    public String getProducttype() {
        return producttype;
    }

    public void setProducttype(String producttype) {
        this.producttype = producttype == null ? null : producttype.trim();
    }

    public Integer getTotalStock() {
        return totalStock;
    }

    public void setTotalStock(Integer totalStock) {
        this.totalStock = totalStock;
    }

    public Integer getTotalSale() {
        return totalSale;
    }

    public void setTotalSale(Integer totalSale) {
        this.totalSale = totalSale;
    }
}