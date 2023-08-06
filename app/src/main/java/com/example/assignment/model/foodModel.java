package com.example.assignment.model;

import com.google.gson.annotations.SerializedName;

public class foodModel {
    @SerializedName("_id")
    private String id;
    private String namesp ;
    private double dongia ;
    private String image;


    public foodModel(String id) {
        this.id = id;
    }

    private String description ;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public foodModel(String id, String namesp, double dongia, String image, String description) {
        this.id = id;
        this.namesp = namesp;
        this.dongia = dongia;
        this.image = image;
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getNamesp() {
        return namesp;
    }

    public void setNamesp(String namesp) {
        this.namesp = namesp;
    }

    public double getDongia() {
        return dongia;
    }

    public void setDongia(double dongia) {
        this.dongia = dongia;
    }

    public foodModel(String namesp, double dongia, String image, String description) {
        this.namesp = namesp;
        this.dongia = dongia;
        this.image = image;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public foodModel() {
    }


}
