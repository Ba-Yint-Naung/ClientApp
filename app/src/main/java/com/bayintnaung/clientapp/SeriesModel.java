package com.bayintnaung.clientapp;

public class SeriesModel {
    String seriesName,seriesImage,seriesCategory,createdAt;

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public SeriesModel(String seriesName, String seriesImage, String seriesCategory, String createdAt, int seriesCount) {
        this.seriesName = seriesName;
        this.seriesImage = seriesImage;
        this.seriesCategory = seriesCategory;
        this.createdAt = createdAt;
        this.seriesCount = seriesCount;
    }

    public int seriesCount;

    public int getSeriesCount() {
        return seriesCount;
    }

    public void setSeriesCount(int seriesCount) {
        this.seriesCount = seriesCount;
    }

    public SeriesModel(String seriesName, String seriesImage, String seriesCategory, int seriesCount) {
        this.seriesName = seriesName;
        this.seriesImage = seriesImage;
        this.seriesCategory = seriesCategory;
        this.seriesCount = seriesCount;
    }

    public SeriesModel() {
    }

    public SeriesModel(String seriesName, String seriesImage, String seriesCategory) {
        this.seriesName = seriesName;
        this.seriesImage = seriesImage;
        this.seriesCategory = seriesCategory;
    }

    public String getSeriesName() {
        return seriesName;
    }

    public void setSeriesName(String seriesName) {
        this.seriesName = seriesName;
    }

    public String getSeriesImage() {
        return seriesImage;
    }

    public void setSeriesImage(String seriesImage) {
        this.seriesImage = seriesImage;
    }

    public String getSeriesCategory() {
        return seriesCategory;
    }

    public void setSeriesCategory(String seriesCategory) {
        this.seriesCategory = seriesCategory;
    }
}
