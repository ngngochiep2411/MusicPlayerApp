package com.example.musicplayerapp.Model;

import java.io.Serializable;

public class BaiHat implements Serializable {
    int index;
    String tenBaiHat,tenCaSi;
    int avtBaiHat;
    int linkMp3;



    public BaiHat(int index,String tenBaiHat,String tenCaSi, int avtBaiHat, int linkMp3) {
        this.index=index;
        this.tenBaiHat = tenBaiHat;
        this.tenCaSi=tenCaSi;
        this.avtBaiHat = avtBaiHat;
        this.linkMp3 = linkMp3;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getTenCaSi() {
        return tenCaSi;
    }

    public void setTenCaSi(String tenCaSi) {
        this.tenCaSi = tenCaSi;
    }

    public String getTenBaiHat() {
        return tenBaiHat;
    }

    public void setTenBaiHat(String tenBaiHat) {
        this.tenBaiHat = tenBaiHat;
    }

    public int getAvtBaiHat() {
        return avtBaiHat;
    }

    public void setAvtBaiHat(int avtBaiHat) {
        this.avtBaiHat = avtBaiHat;
    }

    public int getLinkMp3() {
        return linkMp3;
    }

    public void setLinkMp3(int linkMp3) {
        this.linkMp3 = linkMp3;
    }
}
