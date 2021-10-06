package com.btl_nhom2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class XinChaoActivity extends AppCompatActivity {

    ImageButton imgBtnQuanLyTaiKhoan, imgQuanLyMucDong, imgQuanLyChiTra, imgQuanLyLuongHuu;
    TextView txtQuanLyTaiKhoan, txtQuanLyMucDong, txtQuanLyChiTra, txtQuanLyLuongHuu,txtXinChaoTenAdmin;
    DBhelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xin_chao);

        db =DBhelper.getInstance(this);
        //check CSDL
        checkCsdl();

        txtXinChaoTenAdmin = (TextView) findViewById(R.id.txtXinChaoTenAdmin);

        imgBtnQuanLyTaiKhoan =(ImageButton) findViewById(R.id.imgBtnQuanLyTaiKhoan);
        imgQuanLyMucDong = (ImageButton) findViewById(R.id.imgBtnQuanLyMucDong);
        imgQuanLyChiTra = (ImageButton) findViewById(R.id.imgBtnQuanLyChiTra);
        imgQuanLyLuongHuu = (ImageButton) findViewById(R.id.imgBtnTinhLuongHuu);

        txtQuanLyChiTra = (TextView) findViewById(R.id.txtQuanLyChiTra);
        txtQuanLyMucDong = (TextView) findViewById(R.id.txtQuanLyMucDong);
        txtQuanLyTaiKhoan = (TextView) findViewById(R.id.txtQuanLyTaiKhoan);
        txtQuanLyLuongHuu = (TextView) findViewById(R.id.txtQuanLyLuongHuu);

        imgBtnQuanLyTaiKhoan.setOnClickListener(new MyEnvent());
        imgQuanLyMucDong.setOnClickListener(new MyEnvent());
        imgQuanLyChiTra.setOnClickListener(new MyEnvent());
        imgQuanLyLuongHuu.setOnClickListener(new MyEnvent());

        txtQuanLyTaiKhoan.setOnClickListener(new MyEnvent());
        txtQuanLyMucDong.setOnClickListener(new MyEnvent());
        txtQuanLyChiTra.setOnClickListener(new MyEnvent());
        txtQuanLyLuongHuu.setOnClickListener(new MyEnvent());

        Intent callerIntent = getIntent();
        Bundle bundle = callerIntent.getBundleExtra("ADMIN");

        String txtAdmin = bundle.getString("admin");
        txtXinChaoTenAdmin.setText("Xin chào, " + txtAdmin);

    }

    private class MyEnvent implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            if(view.getId()==R.id.imgBtnQuanLyTaiKhoan || view.getId()==R.id.txtQuanLyTaiKhoan){
                quanLyTaiKhoan();
            }else if(view.getId()==R.id.imgBtnQuanLyMucDong || view.getId()==R.id.txtQuanLyMucDong){
                quanLyMucDong();
            }else if(view.getId()==R.id.imgBtnQuanLyChiTra || view.getId()==R.id.txtQuanLyChiTra){
                quanLyChiTra();
            }else if(view.getId()==R.id.imgBtnTinhLuongHuu || view.getId()==R.id.txtQuanLyLuongHuu) {
                quanLyLuongHuu();
            }
        }
    }

    private void quanLyLuongHuu() {
        Intent intent = new Intent(XinChaoActivity.this, QuanLyLuongHuuActivity.class);
        startActivity(intent);
    }

    private void quanLyTaiKhoan() {
        Intent intent = new Intent(XinChaoActivity.this, QuanLyTaiKhoanActivity.class);
        startActivity(intent);
    }

    private void quanLyMucDong() {
        Intent intent = new Intent(XinChaoActivity.this, QuanLyMucDongActivity.class);
        startActivity(intent);
    }

    private void quanLyChiTra() {
        Intent intent = new Intent(XinChaoActivity.this, QuanLyChiTra1LanActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(XinChaoActivity.this);
        builder.setMessage("Bạn muốn đăng xuất khỏi ứng dụng?");
        builder.setCancelable(true);
        builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        builder.setPositiveButton("Thoát", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_option, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.menu_option,menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        doSomthing(item);
        return super.onOptionsItemSelected(item);
    }

    private void doSomthing(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menuDangXuat:
                final AlertDialog.Builder builder = new AlertDialog.Builder(XinChaoActivity.this);
                builder.setMessage("Bạn muốn đăng xuất khỏi ứng dụng?");
                builder.setCancelable(true);
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                builder.setPositiveButton("Thoát", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
                break;
        }
    }

    public void checkCsdl(){
        if(db.getTotal()==0){
            db.insertInfor(new users(123450, "Nguyen Van Duc", "23/07/1950", 1, 1001001, "01241440", "Hà Nội", 8500000,"Đã trả","Đã nghỉ hưu"));
            db.insertInfor(new users(123451, "Nguyen Thanh Thao", "23/07/1980", 0, 1001002, "01241441", "Hà Nam", 7300000,"Chưa trả","Chưa nghỉ hưu"));
            db.insertInfor(new users(123452, "Nguyen Duc Nam", "23/07/1975", 1, 1001003, "01241442", "Nam Định", 8100000,"Chưa trả","Chưa nghỉ hưu"));
            db.insertInfor(new users(123453, "Nguyen Thao Tam", "23/07/1955", 0, 1001004, "01241443", "Bắc Ninh", 9200000,"Chưa trả","Đã nghỉ hưu"));
            db.insertInfor(new users(123454, "Do Anh Duc", "23/06/1995", 1, 1001005, "01242342", "Thái Bình", 9400000,"Chưa trả","Chưa nghỉ hưu"));
            db.insertInfor(new users(123455, "Phan Van Long", "11/07/2000", 1, 1001044, "01241123", "Hải Phòng", 10000000,"Chưa trả","Chưa nghỉ hưu"));
            db.insertInfor(new users(123456, "Phi Hoang Dang", "20/01/1965", 1, 1001344, "01241532", "Ninh Bình", 9600000,"Chưa trả","Chưa nghỉ hưu"));
        }
        if(db.getTotalDetail()==0){
            db.insertInforDetail(new user_detail("01/2018","06/2018","Đã đóng",892500, 123450));
            db.insertInforDetail(new user_detail("07/2018","12/2018","Đã đóng",892500, 123450));
            db.insertInforDetail(new user_detail("01/2019","06/2019","Đã đóng",892500, 123450));
            db.insertInforDetail(new user_detail("01/2018","06/2018","Đã đóng",766500, 123451));
            db.insertInforDetail(new user_detail("07/2028","12/2018","Chưa đóng",766500, 123451));
            db.insertInforDetail(new user_detail("01/2019","06/2019","Chưa đóng",766500, 123451));
            db.insertInforDetail(new user_detail("01/2018","06/2018","Chưa đóng",850500, 123452));
            db.insertInforDetail(new user_detail("07/2018","12/2018","Chưa đóng",850500, 123452));
            db.insertInforDetail(new user_detail("01/2019","06/2019","Chưa đóng",850500, 123452));
            db.insertInforDetail(new user_detail("01/2018","06/2018","Đã đóng",966000, 123453));
            db.insertInforDetail(new user_detail("07/2018","12/2018","Đã đóng",966000, 123453));
            db.insertInforDetail(new user_detail("01/2019","06/2019","Đã đóng",966000, 123453));
            db.insertInforDetail(new user_detail("01/2018","06/2018","Đã đóng",966000, 123454));
            db.insertInforDetail(new user_detail("07/2018","12/2018","Đã đóng",966000, 123455));
            db.insertInforDetail(new user_detail("01/2019","06/2019","Đã đóng",966000, 123456));
        }
    }
}