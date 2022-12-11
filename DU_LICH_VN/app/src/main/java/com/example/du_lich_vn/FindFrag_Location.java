package com.example.du_lich_vn;

import java.io.Serializable;

public class FindFrag_Location implements Serializable {
    int img;
    String name;
    String mota;

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    public FindFrag_Location(int img, String name, String mota) {
        this.img = img;
        this.name = name;
        this.mota = mota;
    }
}
