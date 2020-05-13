package com.bayintnaung.clientapp;

public class RequestModel {
    public String name,imagelink,createdAt;

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public RequestModel(String name, String imagelink, String createdAt) {
        this.name = name;
        this.imagelink = imagelink;
        this.createdAt = createdAt;
    }

    public RequestModel() {
    }

    public RequestModel(String name, String imagelink) {
        this.name = name;
        this.imagelink = imagelink;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImagelink() {
        return imagelink;
    }

    public void setImagelink(String imagelink) {
        this.imagelink = imagelink;
    }
}
