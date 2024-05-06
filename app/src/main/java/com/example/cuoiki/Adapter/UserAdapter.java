package com.example.cuoiki.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.cuoiki.Model.User;
import com.example.cuoiki.R;


import java.util.List;

public class UserAdapter extends  BaseAdapter {

    private Context context;
    private  int layout;
    private List<User> userList;

    public UserAdapter(Context context, int layout, List<User> userList) {
        this.context = context;
        this.layout = layout;
        this.userList = userList;
    }



    @Override
    public int getCount() {
        return userList.size();

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
        TextView textTen;
        View v;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       ViewHolder holder;
       if(convertView==null){
          holder=new ViewHolder();
           LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
           convertView=inflater.inflate(layout,null);

           holder.textTen=(TextView) convertView.findViewById(R.id.textNhanVien);
           holder.v=(View)  convertView.findViewById(R.id.viewdong);
           convertView.setTag(holder);



       }else{
           holder=(ViewHolder) convertView.getTag();

       }
       User user=userList.get(position);
       holder.textTen.setText(user.getName());

       return convertView;



    }
}
