package com.example.cuoiki.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.cuoiki.Model.Group;
import com.example.cuoiki.R;


import org.w3c.dom.Text;

import java.util.List;

public class GroupAdater extends  BaseAdapter {

   private Context context;
   private int layout;
   private List<Group> GrList;


    public GroupAdater(Context context, int layout, List<Group> grList) {
        this.context = context;
        this.layout = layout;
        GrList = grList;
    }

    @Override
    public int getCount() {
        return GrList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
    private class  ViewHolder{
        TextView textTenGR;
        View v;

    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        if(convertView==null){
            holder=new ViewHolder();
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(layout,null);

            holder.textTenGR=(TextView) convertView.findViewById(R.id.textGR);
            holder.v=(View)  convertView.findViewById(R.id.view);
            convertView.setTag(holder);



        }else{
            holder=(ViewHolder) convertView.getTag();

        }
       Group gr=GrList.get(position);
        holder.textTenGR.setText(gr.getName_group());

        return convertView;


    }
}
