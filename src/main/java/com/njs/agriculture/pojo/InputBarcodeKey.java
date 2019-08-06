package com.njs.agriculture.pojo;

public class InputBarcodeKey {
    private Integer id;

    private String barcode;

    public InputBarcodeKey(Integer id, String barcode) {
        this.id = id;
        this.barcode = barcode;
    }

    public InputBarcodeKey() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode == null ? null : barcode.trim();
    }
}