package com.example.cuoiki;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.cuoiki.Model.CongViec;

import java.util.List;

public class CongViecAdapter extends BaseAdapter {
    private final ListViewCongViec context;
    private final int layout;
    private final List<CongViec> congViecList;

    public CongViecAdapter(Context context, int layout, List<CongViec> congViecList) {
        this.context = (ListViewCongViec) context;
        this.layout = layout;
        this.congViecList = congViecList;
    }

    @Override
    public int getCount() {
        return congViecList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private class ViewHolder{
        TextView textTen,textND;
        ImageView imageDelete,imageEdit;
        CheckBox checkBox;
    }
    @SuppressLint("ViewHolder")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView==null){
            holder=new ViewHolder();
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            convertView=inflater.inflate(layout,null);
            holder.textND=(TextView) convertView.findViewById(R.id.textviewNoiDung);
            holder.textTen=(TextView)  convertView.findViewById(R.id.textviewTen);
            holder.imageDelete=(ImageView) convertView.findViewById(R.id.imageviewDelete);
            holder.imageEdit=(ImageView) convertView.findViewById(R.id.imageviewEdit);
            convertView.setTag(holder);

        }else {
            holder=(ViewHolder) convertView.getTag();
        }
        CongViec congViec=congViecList.get(position);

        holder.textTen.setText(congViec.getTenCV());
        holder.textND.setText((congViec.getND()));
        //bắt sự kiện xóa và sửa
        holder.imageEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(context, "Sua"+congViec.getTenCV(), Toast.LENGTH_SHORT).show();
                context.DialogSuaCongViec(congViec.getTenCV(),congViec.getND(),congViec.getIdCV());

            }
        });
        holder.imageDelete.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                context.DialogXoaCV(congViec.getTenCV(),congViec.getIdCV());
            }
        });
        return convertView;
    }
}
