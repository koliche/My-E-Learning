package com.example.mye_learning.classes;

public class PopularCatigorie {
    private int img;
    private String catName;
    private int numCcours;

    public PopularCatigorie(int img, String catName, int numCcours) {
        this.img = img;
        this.catName = catName;
        this.numCcours = numCcours;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public int getNumCcours() {
        return numCcours;
    }

    public void setNumCcours(int numCcours) {
        this.numCcours = numCcours;
    }
}
