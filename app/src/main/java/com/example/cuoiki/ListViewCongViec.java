package com.example.cuoiki;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.cuoiki.Database.Database;
import com.example.cuoiki.Model.CongViec;

import java.util.ArrayList;

public class ListViewCongViec extends AppCompatActivity {
    ListView lvCongViec;
    ArrayList<CongViec> arrayCongViec;
    CongViecAdapter adapter;
    Button btnThem,btnBack;

    Database database;


    TextView textTen;
    ImageView imageDelete,imageEdit;
    CheckBox checkBox;


    private void Anhxa(){
        lvCongViec=(ListView) findViewById(R.id.listviewCV);
        btnThem=(Button) findViewById(R.id.button_add) ;
        btnBack=(Button) findViewById(R.id.button_back);
/*        textTen =(TextView) findViewById(R.id.textviewTen);
        imageDelete=(ImageView) findViewById(R.id.imageviewDelete);
        imageEdit=(ImageView) findViewById(R.id.imageviewEdit);
        checkBox =(CheckBox) findViewById(R.id.checkbox);*/
        arrayCongViec=new ArrayList<>();
       // arrayCongViec.add(new CongViec(1,"Làm bài tập Androi","yêu cầu bằng ngông ngữ java",null));
        // arrayCongViec.add(new CongViec(2,"Làm bài tập Web","hết hạn ngày 22/12/2024",null));
       // arrayCongViec.add(new CongViec(3,"Làm bài tập Mobile","yêu cầu bằng ngông ngữ PYTHON",null));




        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
           public void onClick(View v) {
                Intent myintent=new Intent(ListViewCongViec.this, MainActivity1.class);

               startActivity(myintent);
           }
      });

        btnThem.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            DiaLogThemCongViec();
           }
        });
     /*   imageEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Toast.makeText(Dong_congViec.this, "hhiii", Toast.LENGTH_SHORT).show();
                DialogSuaCongViec("Công việc bạn muốn sửa");
            }
        });*/
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_cong_viec);
        Anhxa();
      adapter=new CongViecAdapter(this,R.layout.activity_dong_cong_viec,arrayCongViec);
      lvCongViec.setAdapter(adapter);
        // tạo ghichu thông báo


        database =new Database(this,"ghichu.sqlite",null,1);

        //Tạo bảng công viêc

       database.QueryData("CREATE TABLE IF NOT EXISTS CongViec(Id INTEGER PRIMARY KEY AUTOINCREMENT, TenCV VARCHAR(200),ND VARCHAR(200))");



         //database.QueryData("INSERT INTO CongViec VALUES(null,'Viết ứng dùng Androi','Sử dụng Java')");
        GetDataCongViec();
    }

    private  void GetDataCongViec(){
        //select data
        Cursor dataCongViec=database.GetData("SELECT *FROM CongViec");
        arrayCongViec.clear();
        while (dataCongViec.moveToNext()){
            String ten=dataCongViec.getString(1);
            String nd=dataCongViec.getString(2);
            //Toast.makeText(this,ten,Toast.LENGTH_SHORT).show();
            int id=dataCongViec.getInt(0);
            //arrayCongViec.add(new CongViec(id,ten,nd)));
            arrayCongViec.add(new CongViec(id,ten,nd));

        }
        adapter.notifyDataSetChanged();
    }
    private  void DiaLogThemCongViec(){

        Dialog dialog=new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_them_cong_viec);

        EditText ediTen=dialog.findViewById(R.id.editTextCV);
        EditText editND=dialog.findViewById(R.id.editTextND);
        Button btnThem=(Button) dialog.findViewById((R.id.buttonThem));
        Button btnHuy=(Button) dialog.findViewById(R.id.buttonHuy);

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tencv=ediTen.getText().toString();
                String nd=editND.getText().toString();
                if(tencv.equals("")){
                    Toast.makeText(ListViewCongViec.this, "Vui lòng nhập các trường thông báo", Toast.LENGTH_SHORT).show();
                }else{
                    database.QueryData("INSERT INTO CongViec VALUES(null,'"+ tencv+ "','"+ nd+"')");
                    Toast.makeText(ListViewCongViec.this, "Đã Thêm.", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                    GetDataCongViec();
                }
            }
        });
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();;
            }
        });

        dialog.show();
    }
    public void DialogSuaCongViec(String ten, String nd ,int id ){
        Dialog dialog=new Dialog(this);
        dialog.setContentView(R.layout.dialog_sua_cong_viec);
        EditText edtTenCV=(EditText) dialog.findViewById(R.id.editTextTenCvEdit);
        EditText edtND=(EditText) dialog.findViewById(R.id.editTextNDEdit);
        Button btnXacNhan=(Button) dialog.findViewById(R.id.buttonXacNhan);
        Button btnHuyEdit=(Button) dialog.findViewById(R.id.buttonHuyEdit);

        edtND.setText(nd);
        edtTenCV.setText(ten);
        btnXacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenMoi=edtTenCV.getText().toString().trim();
                String ndMoi=edtND.getText().toString().trim();
                database.QueryData("UPDATE CongViec Set TenCV='"+tenMoi+"',  ND='"+ndMoi+"' WHERE Id='"+id+"'");
                Toast.makeText(ListViewCongViec.this, "Đã cập nhật", Toast.LENGTH_SHORT).show();


                dialog.dismiss();
                GetDataCongViec();
            }


        });
        btnHuyEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
            }
        });
        dialog.show();
    }
    public  void DialogXoaCV(String tencv, final  int id){
        AlertDialog.Builder dialogXoa=new AlertDialog.Builder(this);
        dialogXoa.setMessage("Bạn có muốn xóa thông báo "+ tencv+" không");
        dialogXoa.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                database.QueryData("DELETE FROM CongViec WHERE Id='"+id+"'");
                Toast.makeText(ListViewCongViec.this, "Đã xóa "+ tencv, Toast.LENGTH_SHORT).show();
                GetDataCongViec();

            }
        });
        dialogXoa.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        dialogXoa.show();
    }


}