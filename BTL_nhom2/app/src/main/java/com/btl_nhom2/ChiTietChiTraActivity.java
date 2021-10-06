package com.btl_nhom2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ChiTietChiTraActivity extends AppCompatActivity {

    ImageButton imgBack;
    TextView txtHoTen, txtMaBHXH, txtTinhTrang;
    ArrayList<user_detail> u_detail = null;
    ListView listView;
    DBhelper db;
    ArrayList<users> usersArrayList = new ArrayList<>();
    ArrayList<user_detail> usersDetailArrayList = new ArrayList<user_detail>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_chi_tra);
        db =DBhelper.getInstance(this);
        usersArrayList = db.getAllInfor();
        getWidget();


        //fakeCSDL
        //fakeCSDL fake = new fakeCSDL();
        //u_detail = fake.fakeuser_detail();



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
//        users u_d = user.get(ViTri);

        txtHoTen.setText(u.getTenuser());
        txtMaBHXH.setText(u.getMaBHXH()+"");
        txtTinhTrang.setText(u.getTinhtrangchitra());

        //user_detail user_detail = u_detail.get(ViTri);
        //txtTinhTrang.setText(u.getTinhtrangchitra());

        ArrayList<user_detail> listtmp = db.getAllInforDetail();
        for (user_detail uss : listtmp) {
            if(uss.getMaBHXH()==u.getMaBHXH())
                usersDetailArrayList.add(uss);
        }

        chitietchitra_adapter adapter = new chitietchitra_adapter(
                ChiTietChiTraActivity.this,
                R.layout.activity_chi_tiet_chi_tra_lvitem,
                usersDetailArrayList);
        listView.setAdapter(adapter);

        //Xử lý sự kiện
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChiTietChiTraActivity.this.finish();
            }
        });
    }

    public void getWidget(){
        imgBack = findViewById(R.id.imgBack);
        txtHoTen = findViewById(R.id.txtHoTen);
        txtMaBHXH = findViewById(R.id.txtMaBHXH);
        txtTinhTrang = findViewById(R.id.txtTinhTrang);
        listView = findViewById(R.id.lvChiTietChiTra);
    }
}