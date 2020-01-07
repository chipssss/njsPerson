package com.njs.agriculture.pojo;

import java.util.Date;

public class News {
    private Integer id;

    private String title;

    private String author;

    private Date reporttime;

    private String imagepath;

    private String content;

    public News(Integer id, String title, String author, Date reporttime, String imagepath) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.reporttime = reporttime;
        this.imagepath = imagepath;
    }

    public News(Integer id, String title, String author, Date reporttime, String imagepath, String content) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.reporttime = reporttime;
        this.imagepath = imagepath;
        this.content = content;
    }

    public News() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author == null ? null : author.trim();
    }

    public Date getReporttime() {
        return reporttime;
    }

    public void setReporttime(Date reporttime) {
        this.reporttime = reporttime;
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