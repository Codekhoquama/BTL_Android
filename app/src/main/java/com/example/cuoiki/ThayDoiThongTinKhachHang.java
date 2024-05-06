package com.example.cuoiki;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

public class ThayDoiThongTinKhachHang extends AppCompatActivity {
    Button btnChangeInfor, btnChangePassword, btnLogout;
    JSONObject j;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thay_doi_thong_tin_khach_hang);
        btnChangeInfor = (Button) findViewById(R.id.btn_change_info);
        btnChangePassword = (Button) findViewById(R.id.btn_change_password);
        btnLogout = (Button) findViewById(R.id.btn_logout);
        Intent intent = getIntent();
        try {
            j = new JSONObject(intent.getStringExtra("data_user"));
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        btnChangeInfor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intenChangeInfor = new Intent(ThayDoiThongTinKhachHang.this, Btn_change_info.class);
                intenChangeInfor.putExtra("data_user1", j.toString());
               // startActivityForResult(intenChangeInfor, 11);
                startActivity(intenChangeInfor);
            }
        });
        btnChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentChangePassworrd = new Intent(ThayDoiThongTinKhachHang.this, Btn_change_password.class);
                intentChangePassworrd.putExtra("data_user", j.toString());
                startActivity(intentChangePassworrd);
            }
        });
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLogout = new Intent(ThayDoiThongTinKhachHang.this, MainActivity.class);
                startActivity(intentLogout);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 11 && resultCode == Activity.RESULT_OK && data != null) {
            Toast.makeText(this, "123456789", Toast.LENGTH_SHORT).show();
            try {
                // Lấy dữ liệu trả về từ Intent và cập nhật JSONObject
                j = new JSONObject(data.getStringExtra("data_user"));
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        } else {
            Toast.makeText(this, "loi", Toast.LENGTH_SHORT).show();
        }
    }
}