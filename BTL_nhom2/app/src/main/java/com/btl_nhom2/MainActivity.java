package com.btl_nhom2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText editTenTk, editMatKhau;
    Button btnDangNhap, btnDangKy;
    DBhelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTenTk = (EditText) findViewById(R.id.editTenTk);
        editMatKhau = (EditText) findViewById(R.id.editMatKhau);
        btnDangNhap = (Button) findViewById(R.id.btnDangNhap);
        btnDangKy = (Button) findViewById(R.id.btnDangKy);
        DB = new DBhelper(this);
        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String user = editTenTk.getText().toString();
                String pass = editMatKhau.getText().toString();

                if(user.equals("")||pass.equals(""))
                    Toast.makeText(MainActivity.this, "Không được bỏ trống thông tin", Toast.LENGTH_SHORT).show();
                else{
                    Boolean checkuserpass = DB.checktentkmatkhau(user, pass);
                    if(checkuserpass==true){
                        Toast.makeText(MainActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), XinChaoActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("admin", user);
                        intent.putExtra("ADMIN",bundle);
                        startActivity(intent);
                        editTenTk.setText("");
                        editMatKhau.setText("");
                    }else{
                        Toast.makeText(MainActivity.this, "Vui lòng kiểm tra lại thông tin", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), DangKyActivity.class);
                startActivity(intent);
            }
        });
    }
}