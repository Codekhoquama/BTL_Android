package com.example.cuoiki;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
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
import org.w3c.dom.Text;

public class Btn_change_info extends AppCompatActivity {

    EditText textname,textDiachi,textEmail,textSDT;
    Button btnXacNhan,btnhuy;
    JSONObject j;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_btn_change_info);

       textname=(EditText) findViewById(R.id.textName);
       textDiachi=(EditText) findViewById(R.id.textDiaChi);
       textEmail=(EditText) findViewById(R.id.textEmail);
       textSDT=(EditText) findViewById(R.id.textSDT);

       btnXacNhan=(Button) findViewById(R.id.btnSignUp);
       btnhuy=(Button) findViewById(R.id.btnCancel);
        Intent intent = getIntent();
        try {
            j = new JSONObject(intent.getStringExtra("data_user1"));
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        textname.setText(j.optString("name"));
        textDiachi.setText(j.optString("diachi"));
        textEmail.setText(j.optString("email"));
        textSDT.setText(j.optString("sdt"));

       btnXacNhan.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               updateInfor(j);


               finish();

           }
       });
       btnhuy.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent =new Intent(Btn_change_info.this, ThayDoiThongTinKhachHang.class);
               startActivity(intent);

           }
       });


    }
    public  void updateInfor(JSONObject j){

        User user = new User(j.optInt("id"), textname.getText().toString(), textSDT.getText().toString(),textEmail.getText().toString(),textDiachi.getText().toString());

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String url = "http://" + getString(R.string.url) +":8080/updateInfor";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, user.toJSON(),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Intent intent = new Intent(Btn_change_info.this, ThayDoiThongTinKhachHang.class);
                        Log.d("user", response.toString());
                       // intent.putExtra("data_user", "1234");
                        setResult(Activity.RESULT_OK,intent);


                        finish();
                        //  Log.d("State_Home",response.toString());
                        Toast.makeText(Btn_change_info.this, "Update thành công vui lòng đăng nhập lại để hiển thị !", Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
//                        Intent scr = new Intent(MainActivity.this, HomeLayout.class);
//                        startActivity(scr);
                Toast.makeText(Btn_change_info.this, error.toString(), Toast.LENGTH_SHORT).show();
                Log.d("Text_err", error.toString());
            }
        });
        requestQueue.add(request);
    }

}