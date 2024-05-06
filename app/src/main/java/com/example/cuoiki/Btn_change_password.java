package com.example.cuoiki;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.cuoiki.Model.User;

import org.json.JSONException;
import org.json.JSONObject;

public class Btn_change_password extends AppCompatActivity {
     EditText edtMatKhauCu,edtMatKhauMoi,edtNhapLaiMatKhau;
     Button btnXacNhan,btnHuy;
    JSONObject j;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_btn_change_password);
        edtMatKhauCu=(EditText) findViewById(R.id.textMatKhauCu);
        edtMatKhauMoi=(EditText)  findViewById(R.id.textMatKhauMoi);
        edtNhapLaiMatKhau=(EditText) findViewById(R.id.textNhapLaiMatKhau);

        btnXacNhan=(Button) findViewById(R.id.btnSignUp);
        btnHuy=(Button) findViewById(R.id.btnCancel);
        Intent intent = getIntent();
        try {
            j = new JSONObject(intent.getStringExtra("data_user"));
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
         edtMatKhauCu.setText(j.optString("password"));
         edtMatKhauMoi.setText(j.optString(""));
         edtNhapLaiMatKhau.setText(j.optString(""));
         btnXacNhan.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 if(edtMatKhauMoi.getText().toString().equalsIgnoreCase(edtNhapLaiMatKhau.getText().toString())
                    && (!edtMatKhauMoi.getText().toString().equalsIgnoreCase(edtMatKhauCu.getText().toString()
                 ))) {
                     updatePassWord(j);

                     finish();
                 } else  {
                     if((edtMatKhauMoi.getText().toString().equalsIgnoreCase(edtMatKhauCu.getText().toString()
                     ))) {

                         Toast.makeText(Btn_change_password.this, "Mật khẩu mới bị trùng", Toast.LENGTH_SHORT).show();
                     }
                     else{
                         if((!edtMatKhauMoi.getText().toString().equalsIgnoreCase(edtNhapLaiMatKhau.getText().toString()))){
                             Toast.makeText(Btn_change_password.this, "Mật khẩu nhập lại không đúng", Toast.LENGTH_SHORT).show();
                         }
                     }
                 }
             }
         });
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent =new Intent(Btn_change_password.this, ThayDoiThongTinKhachHang.class);
                startActivity(intent);
            }
        });


    }
    public  void updatePassWord(JSONObject j){
        User user = new User(j.optInt("id"), edtMatKhauMoi.getText().toString());

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String url = "http://" + getString(R.string.url) +":8080/updatePassword";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, user.toJSON(),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        //  Log.d("State_Home",response.toString());
                        Toast.makeText(Btn_change_password.this, "Update success !", Toast.LENGTH_SHORT).show();
                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
//                        Intent scr = new Intent(MainActivity.this, HomeLayout.class);
//                        startActivity(scr);
                Toast.makeText(Btn_change_password.this, error.toString(), Toast.LENGTH_SHORT).show();
                Log.d("Text_err", error.toString());
            }
        });
        requestQueue.add(request);
    }
}