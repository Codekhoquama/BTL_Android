//package com.example.projecttrenlop;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.os.Bundle;
//import android.widget.Button;
//import android.widget.EditText;
//
//public class MainActivity1 extends AppCompatActivity {
//    EditText editText;
//    EditText editPass;
//    Button btn;
//    Button btn2, btnSignUp, btnDialog;
//    public void init_Widget(){
//        editText = (EditText) findViewById(R.id.editTextText);
//        editPass = (EditText) findViewById(R.id.editTextTextPassword);
//        btn = (Button) findViewById(R.id.button);
//        btn2 = (Button) findViewById(R.id.button2);
//        btnSignUp = (Button) findViewById(R.id.btnSignUp);
//        btnDialog = (Button) findViewById(R.id.btndialog);
//    }
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main1);
//    }
//}




//..............
package com.example.cuoiki;

import static androidx.core.content.ContentProviderCompat.requireContext;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentContainerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity1 extends AppCompatActivity {
    ImageView imguser;
    TextView texviewChange;
    FragmentContainerView test;
    ImageView setting, note, group, plus;
    JSONObject j;
//    ListView lvCongViec;
//    ArrayList<CongViec> arrayCongViec;
//    CongViecAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);

      imguser = (ImageView) findViewById(R.id.imageUser);
      texviewChange=(TextView) findViewById(R.id.textChange);

        setting = (ImageView) findViewById(R.id.btnSetting);
        note = (ImageView) findViewById(R.id.btnNote);
        group = (ImageView) findViewById(R.id.btnGroup);
        plus = (ImageView) findViewById(R.id.btnPlus);

      //test = (FragmentContainerView) findViewById(R.id.fragmentContainerView5);
        Intent intent = getIntent();
        try {
            j = new JSONObject(intent.getStringExtra("data_user"));
            texviewChange.setText(j.optString("name"));
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        setting.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Tạo Intent và chuyển đến Activity hoặc Fragment mới
                Intent myIntentSetting = new Intent(MainActivity1.this, ThayDoiThongTinKhachHang.class);
                myIntentSetting.putExtra("data_user", j.toString());
                startActivity(myIntentSetting);
            }
        });

        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Tạo Intent và chuyển đến Activity hoặc Fragment mới
                Intent myIntentSetting = new Intent(MainActivity1.this, TaoNhom.class);
                myIntentSetting.putExtra("data_user", j.toString());
                startActivity(myIntentSetting);
            }
        });


        // Hoặc nếu bạn muốn xác định sự kiện khi một Fragment được thêm vào FragmentContainerView
        // Sử dụng FragmentManager để thêm một fragment mới vào FragmentContainerView

    }
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_thay_doi_thong_tin_khach_hang,menu);
//        return super.onCreateOptionsMenu(menu);
//    }
//    private void Anhxa(){
//        lvCongViec=(ListView) findViewById(R.id.listviewCV);
//        arrayCongViec=new ArrayList<>();
//        arrayCongViec.add(new CongViec(1,"Tungdz1"));
//        arrayCongViec.add(new CongViec(2,"Tungdz2"));
//        arrayCongViec.add(new CongViec(3,"Tungdz3"));
//    }



}