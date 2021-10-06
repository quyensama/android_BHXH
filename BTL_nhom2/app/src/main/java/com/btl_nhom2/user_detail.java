package com.btl_nhom2;

import java.io.Serializable;

public class user_detail  implements Serializable {

    protected String tuthang;
    protected String denthang;
    protected String tinhtrangmucdong;
    protected int tienBHXH;
//    protected String tinhtrangchitra;
//    protected String tinhtrangnghihuu;
    protected int maBHXH;

    public user_detail() {}

    public user_detail(String tuthang, String denthang, String tinhtrangmucdong,int tienBHXH, int maBHXH) {
        this.tuthang = tuthang;
        this.denthang = denthang;
        this.tinhtrangmucdong = tinhtrangmucdong;
        this.tienBHXH = tienBHXH;
//        this.tinhtrangchitra = tinhtrangchitra;
//        this.tinhtrangnghihuu = tinhtrangnghihuu;
        this.maBHXH = maBHXH;
    }

    public int getTienBHXH() {
        return tienBHXH;
    }

    public void setTienBHXH(int tienBHXH) {
        this.tienBHXH = tienBHXH;
    }

    public int getMaBHXH() {
        return maBHXH;
    }

    public void setMaBHXH(int maBHXH) {
        this.maBHXH = maBHXH;
    }

    public String getTuthang() {
        return tuthang;
    }

    public void setTuthang(String tuthang) {
        this.tuthang = tuthang;
    }

    public String getDenthang() {
        return denthang;
    }

    public void setDenthang(String denthang) {
        this.denthang = denthang;
    }

    public String getTinhtrangmucdong() {
        return tinhtrangmucdong;
    }

    public void setTinhtrangmucdong(String tinhtrangmucdong) {
        this.tinhtrangmucdong = tinhtrangmucdong;
    }

//    public String getTinhtrangchitra() {
//        return tinhtrangchitra;
//    }
//
//    public void setTinhtrangchitra(String tinhtrangchitra) {
//        this.tinhtrangchitra = tinhtrangchitra;
//    }
//
//    public String getTinhtrangnghihuu() {
//        return tinhtrangnghihuu;
//    }
//
//    public void setTinhtrangnghihuu(String tinhtrangnghihuu) {
//        this.tinhtrangnghihuu = tinhtrangnghihuu;
//    }
}
