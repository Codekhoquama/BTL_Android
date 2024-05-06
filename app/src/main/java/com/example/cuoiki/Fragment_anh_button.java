package com.example.cuoiki;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import kotlinx.coroutines.CancelHandler;

public class Fragment_anh_button extends Fragment {
    ImageView setting, note, group, plus;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.anh_button, container,false);
        setting = (ImageView) view.findViewById(R.id.btnSetting);
        note = (ImageView) view.findViewById(R.id.btnNote);
        group = (ImageView) view.findViewById(R.id.btnGroup);
        plus = (ImageView) view.findViewById(R.id.btnPlus);


        group.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "ch có", Toast.LENGTH_SHORT).show();
            }
        });
        note.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Khai báo Intent và sử dụng getContext() hoặc requireContext()
                //Intent myIntentNote = new Intent(requireContext(), ListViewCongViec.class);
                Toast.makeText(getContext(), "ch có", Toast.LENGTH_SHORT).show();
                // Sử dụng phương thức startActivity của Context
                //requireContext().startActivity(myIntentNote);
            }
        });

//        setting.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent myIntentSetting=new Intent(requireContext(), ThayDoiThongTinKhachHang.class);
//
//                requireContext().startActivity(myIntentSetting);
//            }
//        });
//        plus.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent myIntentPlus=new Intent(requireContext(),TaoNhom.class);
//                requireContext().startActivity(myIntentPlus);
//            }
//        });
        return view;


    }
}


