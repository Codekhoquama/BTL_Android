package com.example.cuoiki;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Dong_congViec extends AppCompatActivity {
    TextView textTen;
    ImageView imageDelete,imageEdit;
    CheckBox checkBox;
  //  Button btnThem,btn_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dong_cong_viec);
        textTen =(TextView) findViewById(R.id.textviewTen);
        imageDelete=(ImageView) findViewById(R.id.imageviewDelete);
        imageEdit=(ImageView) findViewById(R.id.imageviewEdit);
      //  checkBox =(CheckBox) findViewById(R.id.checkbox);
       // btnThem=(Button) findViewById(R.id.button_add);
       // btn_back=(Button) findViewById(R.id.button_back);

///*        btn_back.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent myintent=new Intent(Dong_congViec.this, MainActivity1.class);
//
//                startActivity(myintent);
//            }
//        });
//
//        btnThem.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//            DiaLogThemCongViec();
//            }
//        });*/

       imageEdit.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Toast.makeText(Dong_congViec.this, "hhiii", Toast.LENGTH_SHORT).show();
              // DialogSuaCongViec("Công việc bạn muốn sửa");
           }
       });

        imageDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogXoaCV("Lập Trình Androi",1);
            }
        });
    }
    private  void DiaLogThemCongViec(){

        Dialog dialog=new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_them_cong_viec);

        EditText ediTen=dialog.findViewById(R.id.editTextCV);
        Button btnThem=(Button) dialog.findViewById((R.id.buttonThem));
        Button btnHuy=(Button) dialog.findViewById(R.id.buttonHuy);

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Dong_congViec.this, "Bạn vưa thêm 1 công việc thành công", Toast.LENGTH_SHORT).show();
                //sử lý tương tác với data
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
    public void DialogSuaCongViec(String ten){
        Dialog dialog=new Dialog(this);
        dialog.setContentView(R.layout.dialog_sua_cong_viec);
        EditText edtTenCV=(EditText) dialog.findViewById(R.id.editTextTenCvEdit);
        Button btnXacNhan=(Button) dialog.findViewById(R.id.buttonXacNhan);
        Button btnHuyEdit=(Button) dialog.findViewById(R.id.buttonHuyEdit);

        edtTenCV.setText(ten);
        btnXacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Dong_congViec.this, "Cập nhật công việc thành công", Toast.LENGTH_SHORT).show();
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
        dialogXoa.setMessage("Bạn có muốn xóa công việc "+ tencv+" không");
        dialogXoa.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Toast.makeText(Dong_congViec.this, "Đã xóa "+ tencv, Toast.LENGTH_SHORT).show();

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