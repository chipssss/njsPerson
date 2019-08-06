package com.njs.agriculture.pojo;

import java.math.BigDecimal;

public class InputBarcode extends InputBarcodeKey {
    private String name;

    private String specification;

    private BigDecimal price;

    private String manufacturer;

    public InputBarcode(Integer id, String barcode, String name, String specification, BigDecimal price, String manufacturer) {
        super(id, barcode);
        this.name = name;
        this.specification = specification;
        this.price = price;
        this.manufacturer = manufacturer;
    }

    public InputBarcode() {
        super();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification == null ? null : specification.trim();
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer == null ? null : manufacturer.trim();
    }
}