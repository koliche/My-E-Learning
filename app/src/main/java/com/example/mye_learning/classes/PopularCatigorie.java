package com.example.mye_learning.classes;

public class PopularCatigorie {
    private  String img;
    private  String title;
    private  String date;
    public PopularCatigorie(){}
    public PopularCatigorie(String img, String title, String date) {
        this.img = img;
        this.title = title;
        this.date = date;
    }

    public  String getImg() {
        return this.img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public  String getTitle() {
        return this.title;
    }

    public void setCatName(String catName) {
        title = catName;
    }

    public  String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
