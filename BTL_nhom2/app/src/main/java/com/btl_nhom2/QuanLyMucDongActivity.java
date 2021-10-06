package com.btl_nhom2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class QuanLyMucDongActivity extends AppCompatActivity {

    ImageButton imgBtnBack;
    EditText editTxtSearch;
    TextView txtXemChiTiet;
    ListView listView;
    ArrayList<users> usersArrayList = new ArrayList<users>();
    ArrayList<users> listTmp =new ArrayList<users>();
    danhsachtaikhoandangky_adapter adapter;

    String name = "";

    ArrayList<user_detail> usersDetailArrayList = new ArrayList<user_detail>();
    ArrayList<user_detail> listTmp1 =new ArrayList<user_detail>();
    chitietmucdong_adapter adapter1;
    DBhelper db = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_ly_muc_dong);
        db =DBhelper.getInstance(this);

        imgBtnBack = findViewById(R.id.imgBack);
        editTxtSearch = findViewById(R.id.editTxtSearch);
        txtXemChiTiet = findViewById(R.id.txtXemChiTiet);
        listView = findViewById(R.id.lvTaiKhoan);

        if(db!=null){
            listTmp = db.getAllInfor();
            usersArrayList.addAll(listTmp);
            adapter = new danhsachtaikhoandangky_adapter(
                    QuanLyMucDongActivity.this,
                    R.layout.activity_danh_sach_tai_khoan_dang_ky_lvitem,
                    usersArrayList);
            listView.setAdapter(adapter);
        }

        imgBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                QuanLyMucDongActivity.this.finish();
            }
        });

        editTxtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String s = editTxtSearch.getText().toString();
                s = s.toLowerCase();
                if(s.length()==0){
                    adapter= new danhsachtaikhoandangky_adapter(
                            QuanLyMucDongActivity.this,
                            R.layout.activity_danh_sach_tai_khoan_dang_ky_lvitem,
                            db.getAllInfor());
                    listView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                }
                if(s.length()>=2){
                    //Kiểm tra và update lại listview
                    name = s;
                    listTmp = db.getAllInfor();
                    usersArrayList.clear();
                    if(name.length()>2){
                        for(users u : listTmp){
                            String nameUser = u.getTenuser().toLowerCase();
                            if(nameUser.indexOf(name)>=0){
                                usersArrayList.add(u);
                            }
                        }
                    }else {
                        usersArrayList.clear();
                    }
                    adapter.notifyDataSetChanged();
                }
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Bundle ViTri = new Bundle();
                ViTri.putInt("MaBHXH", usersArrayList.get(i).getMaBHXH());
                Intent intent = new Intent(QuanLyMucDongActivity.this, ChiTietMucDongActivity.class);
                intent.putExtra("VITRI",ViTri);
                startActivity(intent);
            }
        });

    }

//    private void timTheoTen() {
//        String s = editTxtSearch.getText().toString();
//        s = s.toLowerCase();
//
//        //Kiểm tra và update lại listview
//        name = s;
//        tienHanhKiemTra();
//    }
//
//    private void tienHanhKiemTra(){
//        ArrayList<users> uss = db.getAllInfor();
//        ArrayList<users> uss_check = new ArrayList<users>();
//        if(name.length()>2){
//            for(users u : uss){
//                String nameUser = u.getTenuser().toLowerCase();
//                if(nameUser.indexOf(name)>=0){
//                    uss_check.add(u);
//                }
//            }
//        }else {
//            uss_check = new ArrayList<>();
//        }
//        danhsachtaikhoandangky_adapter  adapter1= new danhsachtaikhoandangky_adapter(
//                QuanLyMucDongActivity.this,
//                R.layout.activity_danh_sach_tai_khoan_dang_ky_lvitem,
//                uss_check);
//        listView.setAdapter(adapter1);
//        adapter1.notifyDataSetChanged();
//    }
}