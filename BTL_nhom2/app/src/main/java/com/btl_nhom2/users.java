package com.btl_nhom2;

import java.io.Serializable;

public class users implements Serializable {
    protected int maBHXH;
    protected String tenuser;
    protected String ngaysinh;
    protected int gioitinh;
    protected int soCMND;
    protected String SDT;
    protected String diachi;
    protected int mucluong;
    protected String tinhtrangchitra;
    protected String tinhtrangnghihuu;

    public users(){}

    public users(int maBHXH, String tenuser, String ngaysinh, int gioitinh, int soCMND, String SDT, String diachi, int mucluong, String tinhtrangchitra, String tinhtrangnghihuu) {
        this.maBHXH = maBHXH;
        this.tenuser = tenuser;
        this.ngaysinh = ngaysinh;
        this.gioitinh = gioitinh;
        this.soCMND = soCMND;
        this.SDT = SDT;
        this.diachi = diachi;
        this.mucluong = mucluong;
        this.tinhtrangchitra = tinhtrangchitra;
        this.tinhtrangnghihuu = tinhtrangnghihuu;
    }

    public int getMaBHXH() {
        return maBHXH;
    }

    public void setMaBHXH(int maBHXH) {
        this.maBHXH = maBHXH;
    }

    public String getTenuser() {
        return tenuser;
    }

    public void setTenuser(String tenuser) {
        this.tenuser = tenuser;
    }

    public String getNgaysinh() {
        return ngaysinh;
    }

    public void setNgaysinh(String ngaysinh) {
        this.ngaysinh = ngaysinh;
    }

    public int getGioitinh() {
        return gioitinh;
    }

    public void setGioitinh(int gioitinh) {
        this.gioitinh = gioitinh;
    }

    public int getSoCMND() {
        return soCMND;
    }

    public void setSoCMND(int soCMND) {
        this.soCMND = soCMND;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public int getMucluong() {
        return mucluong;
    }

    public void setMucluong(int mucluong) {
        this.mucluong = mucluong;
    }

        public String getTinhtrangchitra() {
        return tinhtrangchitra;
    }

    public void setTinhtrangchitra(String tinhtrangchitra) {
        this.tinhtrangchitra = tinhtrangchitra;
    }

    public String getTinhtrangnghihuu() {
        return tinhtrangnghihuu;
    }

    public void setTinhtrangnghihuu(String tinhtrangnghihuu) {
        this.tinhtrangnghihuu = tinhtrangnghihuu;
    }

}
