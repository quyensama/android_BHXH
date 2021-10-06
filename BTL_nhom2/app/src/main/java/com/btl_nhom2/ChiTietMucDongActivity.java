package com.btl_nhom2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ChiTietMucDongActivity extends AppCompatActivity {

    ImageButton imgBack, imgCalendar, imgCalendar2;
    TextView txtMaBHXH, txtHoTen, txtMucLuong;
    EditText editTextTuThang, editTextDenThang;
    RadioButton rdDaDong, rdChuaDong;
    Button btnThem, btnSua, btnLamMoi;
    ListView listView;
    DatePickerDialog.OnDateSetListener dateSetListener;
    DatePickerDialog.OnDateSetListener dateSetListener2;

    DBhelper db;
    ArrayList<users> usersArrayList = new ArrayList<>();
    ArrayList<user_detail> usersDetailArrayList = new ArrayList<user_detail>();
    ArrayList<user_detail> listTmp1 =new ArrayList<user_detail>();
    chitietmucdong_adapter adapter1;

//    ArrayList<user_detail> usersDetailArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_muc_dong);
        db =DBhelper.getInstance(this);
        getWidget();
        usersArrayList = db.getAllInfor();
//        usersDetailArrayList = db.getAllInforDetail();

        //setText
        Intent callerIntent = getIntent();
        Bundle bundle = callerIntent.getBundleExtra("VITRI");
        int MaBHXH = bundle.getInt("MaBHXH");

        users u = new users();
        for(users x : usersArrayList){
            if(x.getMaBHXH() == MaBHXH){
                u = db.getInforByID("tblUsers","maBhxh", MaBHXH);
                break;
            }
        }

        txtHoTen.setText(u.getTenuser());
        txtMaBHXH.setText(u.getMaBHXH()+"");
        txtMucLuong.setText(u.getMucluong()+"");

        if(db!=null){
            listTmp1 = db.getAllInforDetail();
            for (user_detail uss : listTmp1) {
                if(uss.getMaBHXH()==u.getMaBHXH())
                    usersDetailArrayList.add(uss);
            }
            adapter1 = new chitietmucdong_adapter(
                    ChiTietMucDongActivity.this, R.layout.activity_chi_tiet_muc_dong_lvitem,
                    usersDetailArrayList);
            listView.setAdapter(adapter1);
        }

        //Xử lý sự kiện
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChiTietMucDongActivity.this.finish();
            }
        });

//        ArrayList<user_detail> listtmp = new ArrayList<>();
//        for(int i=0;i<user.size();i++){
//            if(user.get(i).getMaBHXH()==u.getMaBHXH()){
//                listtmp.add(user.get(i));
//            }
//        }

//        chitietmucdong_adapter adapter = new chitietmucdong_adapter(
//                ChiTietMucDongActivity.this,
//                R.layout.activity_chi_tiet_muc_dong_lvitem,
//                listtmp);
//        listView.setAdapter(adapter);
//
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                user_detail u = user.get(i);
//                editTextTuThang.setText(u.getTuthang());
//                editTextDenThang.setText(u.getDenthang());
//                if(u.getTinhtrangmucdong()=="Đã đóng"){
//                    rdDaDong.setChecked(true);
//                } else{
//                    rdChuaDong.setChecked(true);
//                }
//            }
//        });

        imgCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        ChiTietMucDongActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        dateSetListener,
                        year,month,0);
                 dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                 dialog.getDatePicker().setMaxDate(cal.getTimeInMillis());
                 dialog.getDatePicker().findViewById(Resources.getSystem().getIdentifier("day","id","android")).setVisibility(View.GONE);
                 dialog.show();
            }
        });
        dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                i1+=1;
                String date = i1 + "/"+ i;
                editTextTuThang.setText(date);
            }
        };

        imgCalendar2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        ChiTietMucDongActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        dateSetListener2,
                        year,month,0);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.getDatePicker().findViewById(Resources.getSystem().getIdentifier("day","id","android")).setVisibility(View.GONE);
                dialog.show();
            }
        });
        dateSetListener2 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                i1+=1;
                String date = i1 + "/"+ i;
                editTextDenThang.setText(date);
            }
        };

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                editTextTuThang.setText(usersDetailArrayList.get(i).getTuthang());
                editTextDenThang.setText(usersDetailArrayList.get(i).getDenthang());
                ArrayList<users> arrayList = new ArrayList<>();
                arrayList = db.getAllInfor();
                int maBHXH = Integer.parseInt(usersDetailArrayList.get(i).getMaBHXH()+"");
                for (users uss : arrayList) {
                    if(uss.getMaBHXH()==maBHXH)
                        txtMucLuong.setText(uss.getMucluong()+"");
                    break;
                }
                if(usersDetailArrayList.get(i).getTinhtrangmucdong().equals("Đã đóng"))
                    rdDaDong.setChecked(true);
                else rdChuaDong.setChecked(true);
            }
        });

        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tuThang, denThang, tinhTrangMucDong;
                int tienBHXH, maBHXH;

                tuThang = editTextTuThang.getText()+"";
                denThang = editTextDenThang.getText()+"";
                if(rdChuaDong.isChecked()){
                    tinhTrangMucDong = "Chưa đóng";
                }else tinhTrangMucDong = "Đã đóng";
                tienBHXH = tinhTienBHXH();
                maBHXH = Integer.parseInt(txtMaBHXH.getText()+"");
                String ID = txtMaBHXH.getText()+"";

                //update
                if(tuThang != "" || denThang != ""){
                    db.updateUserDetail(new user_detail(tuThang,denThang,tinhTrangMucDong,tienBHXH,maBHXH),ID,tuThang,denThang);
                    usersDetailArrayList.clear();
                    listTmp1 = db.getAllInforDetail();
                    for (user_detail uss : listTmp1) {
                        if(uss.getMaBHXH()==MaBHXH)
                            usersDetailArrayList.add(uss);
                    }
                    adapter1 = new chitietmucdong_adapter(
                            ChiTietMucDongActivity.this, R.layout.activity_chi_tiet_muc_dong_lvitem,
                            usersDetailArrayList);
                    listView.setAdapter(adapter1);
                }
                else{
                    Toast.makeText(ChiTietMucDongActivity.this, "Vui lòng kiểm tra lại thông tin", Toast.LENGTH_LONG).show();
                }
            }
        });

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tuThang, denThang, tinhTrangMucDong;
                int tienBHXH, maBHXH;

                tuThang = editTextTuThang.getText()+"";
                denThang = editTextDenThang.getText()+"";
                if(rdChuaDong.isChecked()){
                    tinhTrangMucDong = "Chưa đóng";
                }else tinhTrangMucDong = "Đã đóng";
                tienBHXH = tinhTienBHXH();
                maBHXH = Integer.parseInt(txtMaBHXH.getText()+"");

                //thêm vào csdl
                if(tuThang != "" || denThang != ""){
                    db.insertInforDetail(new user_detail(tuThang,denThang,tinhTrangMucDong,tienBHXH,maBHXH));
                    usersDetailArrayList.clear();
                    listTmp1 = db.getAllInforDetail();
                    for (user_detail uss : listTmp1) {
                        if(uss.getMaBHXH()==MaBHXH)
                            usersDetailArrayList.add(uss);
                    }
                    adapter1 = new chitietmucdong_adapter(
                            ChiTietMucDongActivity.this, R.layout.activity_chi_tiet_muc_dong_lvitem,
                            usersDetailArrayList);
                    listView.setAdapter(adapter1);
                }
                else{
                    Toast.makeText(ChiTietMucDongActivity.this, "Vui lòng kiểm tra lại thông tin", Toast.LENGTH_LONG).show();
                }
            }
        });

        btnLamMoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editTextTuThang.setText("");
                editTextDenThang.setText("");
            }
        });
    }

    public int tinhTienBHXH(){
        int mucLuong, tienBHXH;
        mucLuong = Integer.parseInt(txtMucLuong.getText()+"");
        int laythoigiandong = layThoiGianDong();
        tienBHXH = (int) (0.105*mucLuong*laythoigiandong);
        return tienBHXH;
    }
    public int layThoiGianDong(){
        String layTuThang = String.valueOf(editTextTuThang.getText());
        String[] items1 = layTuThang.split("/");
        String m0=items1[0];
        String y0=items1[1];
        int thang0 = Integer.parseInt(m0);
        int nam0 = Integer.parseInt(y0);

        String layDenThang = String.valueOf(editTextDenThang.getText());
        String[] items2 = layDenThang.split("/");
        String m1=items2[0];
        String y1=items2[1];
        int thang1 = Integer.parseInt(m1);
        int nam1 = Integer.parseInt(y1);
        int time = thang1 - thang0 + (nam1 - nam0)*12;
        return time;
    }
    public void getWidget(){
        imgBack = findViewById(R.id.imgBack);
        imgCalendar = findViewById(R.id.imgCalendar);
        imgCalendar2 = findViewById(R.id.imgCalendar2);

        txtMaBHXH = findViewById(R.id.txtMaBHXH);
        txtHoTen = findViewById(R.id.txtHoTen);
        txtMucLuong = findViewById(R.id.txtMucLuong);
        editTextDenThang = findViewById(R.id.editTextDenThang);
        editTextTuThang = findViewById(R.id.editTextTuThang);
        editTextDenThang.setEnabled(false);
        editTextTuThang.setEnabled(false);

        rdDaDong = findViewById(R.id.rdDaDong);
        rdDaDong.setChecked(true);
        rdChuaDong = findViewById(R.id.rdChuaDong);

        btnThem = findViewById(R.id.btnThem);
        btnSua = findViewById(R.id.btnSua);
        btnLamMoi = findViewById(R.id.btnLamMoi);

        listView = findViewById(R.id.lvChiTienMucDong);
    }

}