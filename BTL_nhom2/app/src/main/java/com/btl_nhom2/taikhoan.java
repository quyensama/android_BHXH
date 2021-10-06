package com.btl_nhom2;

public class taikhoan {
    private String tentk;
    private String matkhau;

    public taikhoan(){}
    public taikhoan(String tentk, String matkhau) {
        this.tentk = tentk;
        this.matkhau = matkhau;
    }

    public String getTentk() {
        return tentk;
    }

    public void setTentk(String tentk) {
        this.tentk = tentk;
    }

    public String getMatkhau() {
        return matkhau;
    }

    public void setMatkhau(String matkhau) {
        this.matkhau = matkhau;
    }

}
