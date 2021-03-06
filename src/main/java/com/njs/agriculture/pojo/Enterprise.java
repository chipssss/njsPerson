package com.njs.agriculture.pojo;

public class Enterprise {
    private Integer id;

    private String name;

    private String creditCode;

    private String industry;

    private String mainProduct;

    private Integer status;

    private String address;

    private String image;

    public Enterprise(Integer id, String name, String creditCode, String industry, String mainProduct, Integer status, String address, String image) {
        this.id = id;
        this.name = name;
        this.creditCode = creditCode;
        this.industry = industry;
        this.mainProduct = mainProduct;
        this.status = status;
        this.address = address;
        this.image = image;
    }

    public Enterprise() {
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

    public String getCreditCode() {
        return creditCode;
    }

    public void setCreditCode(String creditCode) {
        this.creditCode = creditCode == null ? null : creditCode.trim();
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry == null ? null : industry.trim();
    }

    public String getMainProduct() {
        return mainProduct;
    }

    public void setMainProduct(String mainProduct) {
        this.mainProduct = mainProduct == null ? null : mainProduct.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image == null ? null : image.trim();
    }
}