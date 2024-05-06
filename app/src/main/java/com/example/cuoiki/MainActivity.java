//package com.example.projecttrenlop;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.appcompat.widget.Toolbar;
//
//import android.os.Bundle;
//import android.view.Menu;
//import android.view.View;
//import android.widget.ImageView;
//import android.widget.ListView;
//import android.widget.TextView;
//
//import java.util.ArrayList;
//
//public class MainActivity extends AppCompatActivity {
//    ImageView imguser;
//    TextView texviewChange;
////    ListView lvCongViec;
////    ArrayList<CongViec> arrayCongViec;
////    CongViecAdapter adapter;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//      imguser = (ImageView) findViewById(R.id.imageUser);
//      texviewChange=(TextView) findViewById(R.id.textChange);
////      Anhxa();
////      adapter=new CongViecAdapter(this,R.layout.activity_dong_cong_viec,arrayCongViec);
//    }
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_thay_doi_thong_tin_khach_hang,menu);
//        return super.onCreateOptionsMenu(menu);
//    }
////    private void Anhxa(){
////        lvCongViec=(ListView) findViewById(R.id.listviewCV);
////        arrayCongViec=new ArrayList<>();
////        arrayCongViec.add(new CongViec(1,"Tungdz1"));
////        arrayCongViec.add(new CongViec(2,"Tungdz2"));
////        arrayCongViec.add(new CongViec(3,"Tungdz3"));
////    }
//
//
//
//}





//........
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


import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    EditText editPass;
    Button btn;
    Button btn2, btnSignUp, btnDialog;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = (EditText) findViewById(R.id.editTextText);
        editPass = (EditText) findViewById(R.id.editTextTextPassword);
        btn = (Button) findViewById(R.id.button);
        btn2 = (Button) findViewById(R.id.button2);
        btnDialog = (Button) findViewById(R.id.btndialog);
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        // 192.168.1.3 : Local Server
        // 192.168.1.150: Remote Server
        String url = "http://" + getString(R.string.url) +":8080/login";
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s1 = editText.getText().toString();
                String s2 = editPass.getText().toString();
                if(!s1.equals("") && !s2.equals("") ){
                    User user = new User();
                    user.setUsername(s1);
                    user.setPassword(s2);


                    Log.d("datasend",user.toJSON().toString());
                    Log.d("State_Home","Off1");
                    //gửi dữ liệu lên sever
                    JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, user.toJSON(),
                            new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject response) {
                                  //  Log.d("State_Home",response.toString());
                                    Intent scr = new Intent(MainActivity.this, MainActivity1.class);
                                    //Log.d("State_Home","Off1");
                                    scr.putExtra("data_user", response.toString());
                                    //Log.d("State_Home","Off2");
                                    startActivity(scr);
                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
//                        Intent scr = new Intent(MainActivity.this, HomeLayout.class);
//                        startActivity(scr);
                            Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                            Log.d("Text_err", error.toString());
                        }
                    });
                    requestQueue.add(request);
                }
                else{
                    Toast.makeText(MainActivity.this, "Vui lòng nhập đủ thông tin", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
