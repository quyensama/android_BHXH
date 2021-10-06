package com.btl_nhom2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DangKyActivity extends AppCompatActivity {
    EditText editTenTk, editMatKhau, editReMatKhau;
    Button btnDangKy;
    DBhelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky);
        editTenTk = (EditText) findViewById(R.id.editTenTk1);
        editMatKhau = (EditText) findViewById(R.id.editMatKhau1);
        editReMatKhau = (EditText) findViewById(R.id.editReMatKhau);
        btnDangKy = (Button) findViewById(R.id.btnDangKy1);
        DB = new DBhelper(this);
        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = editTenTk.getText().toString();
                String pass = editMatKhau.getText().toString();
                String repass = editReMatKhau.getText().toString();

                if(user.equals("")||pass.equals("")||repass.equals(""))
                    Toast.makeText(DangKyActivity.this,
                            "Không được để trống thông tin", Toast.LENGTH_SHORT).show();
                else{
                    if(pass.equals(repass)){
                        Boolean checkuser = DB.checktentk(user);
                        if(checkuser==false){
                            Boolean insert = DB.insertData(user, pass);
                            if(insert==true){
                                Toast.makeText(DangKyActivity.this,
                                        "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                                startActivity(intent);
                            }else{
                                Toast.makeText(DangKyActivity.this,
                                        "Đăng ký thất bại", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            Toast.makeText(DangKyActivity.this,
                                    "Tài khoản đã tồn tại, vui lòng đăng nhập", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(DangKyActivity.this,
                                "Mật khẩu không khớp", Toast.LENGTH_SHORT).show();
                    }
                } }
        });
    }
}