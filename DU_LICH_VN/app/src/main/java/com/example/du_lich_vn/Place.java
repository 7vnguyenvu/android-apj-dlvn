package com.example.du_lich_vn;

import java.io.Serializable;

public class Place implements Serializable {
    private int image;
    private String name;
    private String description;
    private String rating;
    private String province;

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    private String position;

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDiscription(String discription) {
        this.description = discription;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public Place(int image, String name, String description, String rating, String province, String position) {
        this.image = image;
        this.name = name;
        this.description = description;
        this.rating = rating;
        this.province = province;
        this.position = position;
    }
}
