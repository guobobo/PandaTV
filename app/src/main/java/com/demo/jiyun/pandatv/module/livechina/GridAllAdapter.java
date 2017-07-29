package com.demo.jiyun.pandatv.module.livechina;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.demo.jiyun.pandatv.R;
import com.demo.jiyun.pandatv.model.entity.ChinaLiveBean;

import java.util.ArrayList;

public class GridAllAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<ChinaLiveBean.AlllistBean> list;

    public GridAllAdapter(Context context, ArrayList<ChinaLiveBean.AlllistBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MHolder holder;
        if(convertView==null){
            holder=new MHolder();
            convertView= LayoutInflater.from(context).inflate(R.layout.item_grid,null);
            holder.item_tab_title= (TextView) convertView.findViewById(R.id.item_tab_title);
            convertView.setTag(holder);
        }else {
            holder= (MHolder) convertView.getTag();
        }
        holder.item_tab_title.setText(list.get(position).getTitle());
        return convertView;
    }

    class MHolder{
        TextView item_tab_title;
    }

}
