package com.njs.agriculture.pojo;

public class SecureImage {
    private String secureImage;

    private Integer id;

    public SecureImage(String secureImage, Integer id) {
        this.secureImage = secureImage;
        this.id = id;
    }

    public SecureImage() {
        super();
    }

    public String getSecureImage() {
        return secureImage;
    }

    public void setSecureImage(String secureImage) {
        this.secureImage = secureImage == null ? null : secureImage.trim();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}