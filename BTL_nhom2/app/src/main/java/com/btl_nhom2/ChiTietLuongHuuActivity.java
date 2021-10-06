package com.btl_nhom2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

public class ChiTietLuongHuuActivity extends AppCompatActivity {

    TextView txtHoTen, txtMaBHXH, txtNgaySinh, txtGioiTinh, txtDiaChi, txtTinhTrang, txtThoiGianNghiHuu, txtLuongHuu;
    ImageButton imgback;
    DBhelper db;
    ArrayList<users> usersArrayList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_luong_huu);
        db =DBhelper.getInstance(this);
        usersArrayList = db.getAllInfor();
        getWidget();

        //fakeCSDL
//        fakeCSDL fake = new fakeCSDL();
//        ArrayList<users> user = fake.fakeusers();
//        ArrayList<users> usersArrayList = fake.fakeusers();

        //setText
        Intent callerIntent = getIntent();
        Bundle bundle = callerIntent.getBundleExtra("VITRI");
        int MaBHXH = bundle.getInt("MaBHXH");

        usersArrayList = db.getAllInfor();
        users u = new users();
        for(users x : usersArrayList){
            if(x.getMaBHXH() == MaBHXH){
                u = db.getInforByID("tblUsers","maBhxh", MaBHXH);
                break;
            }
        }

        txtHoTen.setText(u.getTenuser());
        txtMaBHXH.setText(u.getMaBHXH()+"");
        txtNgaySinh.setText(u.getNgaysinh());
        if(u.getGioitinh()==1){
            txtGioiTinh.setText("Nam");
            txtThoiGianNghiHuu.setText("60 tuổi");
        }else {
            txtGioiTinh.setText("Nữ");
            txtThoiGianNghiHuu.setText("55 tuổi");
        }
        txtDiaChi.setText(u.getDiachi());
        txtTinhTrang.setText(u.getTinhtrangnghihuu());

//        if(u.getTinhtrangnghihuu()=="Đã nghỉ hưu"){
//
//        }else {
//            txtThoiGianNghiHuu.setText("---Chưa nghỉ hưu---");
//        }

        int luongHuu = (int) ( u.getMucluong()*0.45);
        txtLuongHuu.setText(luongHuu+"");

        imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChiTietLuongHuuActivity.this.finish();
            }
        });
    }

    public void getWidget(){
        imgback = findViewById(R.id.imgBack);
        txtHoTen = findViewById(R.id.txtHoTen);
        txtMaBHXH = findViewById(R.id.txtMaBHXH);
        txtNgaySinh = findViewById(R.id.txtNgaySinh);
        txtGioiTinh = findViewById(R.id.txtGioiTinh);
        txtDiaChi = findViewById(R.id.txtDiaChi);
        txtTinhTrang = findViewById(R.id.txtTinhTrang);
        txtThoiGianNghiHuu = findViewById(R.id.txtThoiGianNghiHuu);
        txtLuongHuu = findViewById(R.id.txtLuongHuu);
    }
}