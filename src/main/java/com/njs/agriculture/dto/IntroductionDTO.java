package com.njs.agriculture.dto;

import lombok.Data;

@Data
public class IntroductionDTO {
    private  String name;

    private Integer adId;

    private String imagepath;

    private String content;

    public IntroductionDTO(Integer adId, String imagepath) {
        this.adId = adId;
        this.imagepath = imagepath;
    }

    public IntroductionDTO(Integer adId, String imagepath, String content) {
        this.adId = adId;
        this.imagepath = imagepath;
        this.content = content;
    }

    public IntroductionDTO() {
        super();
    }

    public Integer getAdId() {
        return adId;
    }

    public void setAdId(Integer adId) {
        this.adId = adId;
    }

    public String getImagepath() {
        return imagepath;
    }

    public void setImagepath(String imagepath) {
        this.imagepath = imagepath == null ? null : imagepath.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}
