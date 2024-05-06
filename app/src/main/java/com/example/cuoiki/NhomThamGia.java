package com.example.cuoiki;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.cuoiki.Model.Group;
import com.example.cuoiki.Model.User;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;



public class NhomThamGia extends AppCompatActivity {
   Button btnThongBao;

   TextView TenNhom,TenNhomTruong ,MaNhom;

   ListView lvUser;
   ArrayList<User> arrayUser;
   UserAdapter adapter;
    JSONObject jsonObject;

    int id_user;

    public User ConvertUser(JSONObject jsonObject){
        User user = new User();
        user.setId(jsonObject.optInt("id"));
        user.setName(jsonObject.optString("name"));


        return user;
    }
   private  void anhXa(){
      btnThongBao =(Button) findViewById(R.id.btnNotification) ;
      TenNhom=(TextView) findViewById((R.id.tvGroupName));
      TenNhomTruong=(TextView) findViewById(R.id.tvGroupLeaderName);
      MaNhom=(TextView) findViewById(R.id.MaNhom);
     lvUser=(ListView) findViewById(R.id.lvUser) ;
//       arrayUser =new ArrayList<>();
//      arrayUser.add(new User("Trương Thanh Tùng"));
//    arrayUser.add(new User("Lã Ngọc Hiếu"));
//    arrayUser.add(new User("Trương Thanh Tùng"));


       btnThongBao.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent=new Intent(NhomThamGia.this, ListViewCongViec.class);
               startActivity(intent);
           }
       });

   }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nhom_tham_gia);

       anhXa();
    //    adapter=new UserAdapter(this,R.layout.dong_nhan_vien,arrayUser);
       // lvUser.setAdapter(adapter);

        Intent intent = getIntent();
        Group group = new Group();
        arrayUser = new ArrayList<>();
        try {
            jsonObject = new JSONObject(intent.getStringExtra("dataGroup"));
            TenNhom.setText(jsonObject.optString("name_group"));
            MaNhom.setText("Mã Nhóm: "+jsonObject.optString("code_group"));
            TenNhomTruong.setText(jsonObject.getJSONObject("leader").optString("name"));
            group.setIdgroup(jsonObject.optInt("id"));
            id_user = intent.getIntExtra("id_user", 0);
            GetUserByGroup(jsonObject);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }





    }

    // ----------------- Get List User in Group --------------
    public void GetUserByGroup(JSONObject dataGroup) throws JSONException {
        Log.d("size1",dataGroup.toString());
//        Group group = new Group();
//        group.setIdgroup(dataGroup.optInt("idgroup"));
        RequestQueue requestQueue = Volley.newRequestQueue(NhomThamGia.this);
        String url = "http://" + getString(R.string.url) + ":8080/getListUser";
        JSONArray jsonArray = new JSONArray();
        try {
            jsonArray.put(Integer.parseInt("0"),dataGroup);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.POST, url, jsonArray
                , new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.d("size1",response.toString());
                List<JSONObject> jsonObjects = new ArrayList<>();
                try {
                    for (int i = 0; i < response.length(); i++) {
                        jsonObjects.add((JSONObject) response.get(i));
                        Log.d("row_user value: ", jsonObjects.get(i).toString());
                    }
                    for (JSONObject j : jsonObjects) {
                        User user = ConvertUser(j);
                        arrayUser.add(new User(user.getName()));
                        Log.d("row_task value: ", j.toString());
                    }
                    adapter = new UserAdapter(NhomThamGia.this, R.layout.dong_nhan_vien, arrayUser);
                    lvUser.setAdapter(adapter);
                    lvUser.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        }
                    });
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(NhomThamGia.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(jsonArrayRequest);
    }


}