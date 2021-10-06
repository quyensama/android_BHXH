package com.btl_nhom2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class QuanLyLuongHuuActivity extends AppCompatActivity {

    ImageButton imgBack;
    EditText editSearch;
    ListView listView;
    Spinner spnQLLuongHuu;
    String arr_[]={"Tất cả",
            "Đã nghỉ hưu",
            "Chưa nghỉ hưu"};
    String name = "";
    ArrayList<users> usersArrayList = new ArrayList<users>();
    ArrayList<users> listTmp =new ArrayList<users>();
    danhsachtaikhoandangky_adapter adapter1;

    DBhelper db = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_ly_luong_huu);
        db =DBhelper.getInstance(this);

        imgBack = findViewById(R.id.imgBack);
        editSearch = findViewById(R.id.editTxtSearch);
        listView = findViewById(R.id.lvLuongHuu);
        spnQLLuongHuu = findViewById(R.id.spnLuongHuu);

        danhsachtaikhoandangky_adapter  adapter1= new danhsachtaikhoandangky_adapter(
                QuanLyLuongHuuActivity.this,
                R.layout.activity_danh_sach_tai_khoan_dang_ky_lvitem,
                usersArrayList);
        listView.setAdapter(adapter1);
        adapter1.notifyDataSetChanged();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_spinner_item,
                arr_);
        // hiển thị danh sách spn
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        // thiết lập adapter cho spn
        spnQLLuongHuu.setAdapter(adapter);
        String itemspn = spnQLLuongHuu.getSelectedItem().toString();
        // when click spn
        spnQLLuongHuu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String itemspn = arr_[i];
                if(itemspn.equals("Đã nghỉ hưu")){
                    usersArrayList.clear();
                    if(db!=null){
                        listTmp.clear();
                        listTmp = db.getAllInforLuongHuu1();
                        usersArrayList.addAll(listTmp);
                        adapter1.notifyDataSetChanged();
                    }
                }else if(itemspn .equals("Chưa nghỉ hưu")){
                    usersArrayList.clear();
                    if(db!=null){
                        listTmp.clear();
                        listTmp = db.getAllInforLuongHuu2();
                        usersArrayList.addAll(listTmp);
                        adapter1.notifyDataSetChanged();
                    }
                }else {
                    usersArrayList.clear();
                    if(db!=null){
                        listTmp.clear();
                        listTmp = db.getAllInfor();
                        usersArrayList.addAll(listTmp);
                        adapter1.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                usersArrayList.clear();
                if (db != null) {
                    listTmp = db.getAllInfor();
                    usersArrayList.addAll(listTmp);
                    danhsachtaikhoandangky_adapter adapter1 = new danhsachtaikhoandangky_adapter(
                            QuanLyLuongHuuActivity.this,
                            R.layout.activity_danh_sach_tai_khoan_dang_ky_lvitem,
                            usersArrayList);
                    listView.setAdapter(adapter1);
                    adapter1.notifyDataSetChanged();
                }
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Bundle ViTri = new Bundle();
                ViTri.putInt("MaBHXH",usersArrayList.get(i).getMaBHXH());
                Intent intent = new Intent(QuanLyLuongHuuActivity.this, ChiTietLuongHuuActivity.class);
                intent.putExtra("VITRI",ViTri);
                startActivity(intent);
            }
        });

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                QuanLyLuongHuuActivity.this.finish();
            }
        });

        editSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String s = editSearch.getText().toString();
                s = s.toLowerCase();
                if(s.length()==0){
                    danhsachtaikhoandangky_adapter  adapter1= new danhsachtaikhoandangky_adapter(
                            QuanLyLuongHuuActivity.this,
                            R.layout.activity_danh_sach_tai_khoan_dang_ky_lvitem,
                            db.getAllInfor());
                    listView.setAdapter(adapter1);
                    adapter1.notifyDataSetChanged();
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
                    adapter1.notifyDataSetChanged();
                }
            }
        });
    }
//    private void timTheoTen() {
//        String s = editSearch.getText().toString();
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
//                QuanLyLuongHuuActivity.this,
//                R.layout.activity_danh_sach_tai_khoan_dang_ky_lvitem,
//                uss_check);
//        listView.setAdapter(adapter1);
//        adapter1.notifyDataSetChanged();
//    }
}