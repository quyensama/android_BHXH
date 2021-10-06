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
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class QuanLyChiTra1LanActivity extends AppCompatActivity {

    ImageButton imgBack;
    EditText editSearch;
    ListView listView;
    Spinner spnQLChiTra;
    TextView txtDieuKienChiTra;
    String name="";
    String arr[]={"Tất cả",
            "Đã trả",
            "Chưa trả"};
    ArrayList<users> usersArrayList = new ArrayList<users>();
    ArrayList<users> listTmp =new ArrayList<users>();
    danhsachtaikhoandangky_adapter adapter1;

    DBhelper db = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_ly_chi_tra_1_lan);
        db =DBhelper.getInstance(this);

        imgBack = findViewById(R.id.imgBack);
        editSearch = findViewById(R.id.editTxtSearch);
        listView = findViewById(R.id.lvChiTra);
        spnQLChiTra = findViewById(R.id.spnQLChiTra);
        txtDieuKienChiTra = (TextView) findViewById(R.id.txtDieuKienNhanBHXH);

        listTmp = db.getAllInforChiTra1();
        usersArrayList.addAll(listTmp);
        adapter1= new danhsachtaikhoandangky_adapter(
                QuanLyChiTra1LanActivity.this,
                R.layout.activity_danh_sach_tai_khoan_dang_ky_lvitem,
                usersArrayList);
        listView.setAdapter(adapter1);
        adapter1.notifyDataSetChanged();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_spinner_item,arr);
        // hiển thị danh sách spn
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        // thiết lập adapter cho spn
        spnQLChiTra.setAdapter(adapter);
        String itemspn = spnQLChiTra.getSelectedItem().toString();
        // when click spn
        spnQLChiTra.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String itemspn = spnQLChiTra.getSelectedItem().toString();
                if(itemspn == "Đã trả"){
                    usersArrayList.clear();
                    if(db!=null){
                        listTmp = db.getAllInforChiTra1();
                        usersArrayList.addAll(listTmp);
                        adapter1.notifyDataSetChanged();
                    }
                }
                if(itemspn == "Chưa trả"){
                    usersArrayList.clear();
                    if(db!=null){
                        listTmp = db.getAllInforChiTra2();
                        usersArrayList.addAll(listTmp);
                        adapter1.notifyDataSetChanged();
                    }
                }
                if(itemspn == "Tất cả"){
                    usersArrayList.clear();
                        if(db!=null){
                            listTmp = db.getAllInfor();
                            usersArrayList.addAll(listTmp);
                            adapter1.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                usersArrayList.clear();
                if(db!=null){
                    listTmp = db.getAllInfor();
                    usersArrayList.addAll(listTmp);
                    adapter1.notifyDataSetChanged();
                }
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Bundle ViTri = new Bundle();
                ViTri.putInt("MaBHXH", usersArrayList.get(i).getMaBHXH());
                Intent intent = new Intent(QuanLyChiTra1LanActivity.this, ChiTietChiTraActivity.class);
                intent.putExtra("VITRI",ViTri);
                startActivity(intent);
            }
        });

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                QuanLyChiTra1LanActivity.this.finish();
            }
        });

        txtDieuKienChiTra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(QuanLyChiTra1LanActivity.this, DieuKienTra1LanActivity.class);
                startActivity(intent);
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
                            QuanLyChiTra1LanActivity.this,
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

//    private void tienHanhKiemTra(){
//        ArrayList<users> uss = db.getAllInfor();
//        usersArrayList.clear();
//        if(name.length()>2){
//            for(users u : uss){
//                String nameUser = u.getTenuser().toLowerCase();
//                if(nameUser.indexOf(name)>=0){
//                    usersArrayList.add(u);
//                }
//            }
//        }else {
//            usersArrayList.clear();
//        }
//        adapter1.notifyDataSetChanged();
//    }
}