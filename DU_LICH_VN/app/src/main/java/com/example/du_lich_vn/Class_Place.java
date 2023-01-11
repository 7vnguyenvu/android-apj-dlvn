package com.example.du_lich_vn;

import java.io.Serializable;

public class Class_Place implements Serializable {
    private int image;
    private String code;
    private String name;
    private String description;
    private String rating;
    private String province;
    private String position;
    private String link;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }


    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }


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

    public Class_Place(int image, String name, String description, String rating, String province, String position, String link, String code) {
        this.image = image;
        this.name = name;
        this.description = description;
        this.rating = rating;
        this.province = province;
        this.position = position;
        this.link = link;
        this.code = code;
    }
}
