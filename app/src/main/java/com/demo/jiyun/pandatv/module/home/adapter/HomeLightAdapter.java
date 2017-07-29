package com.demo.jiyun.pandatv.module.home.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.demo.jiyun.pandatv.R;
import com.demo.jiyun.pandatv.model.entity.HomelightListBean;
import com.demo.jiyun.pandatv.net.HttpFactory;

import java.util.List;

/**
 * Created by iu on 2017/7/29.
 */

public class HomeLightAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<HomelightListBean.ListBean> datas;
    private LayoutInflater inflater;

    public HomeLightAdapter(Context context, List<HomelightListBean.ListBean> datas) {
        this.context = context;
        this.datas = datas;
        this.inflater = LayoutInflater.from(context);
    }




    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.light_recy_item,null);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Holder h= (Holder) holder;
        HttpFactory.httpCreate().imageLoad(datas.get(position).getImage(),h.light_item_image);
        h.light_item_content.setText(datas.get(position).getTitle());
        h.light_item_time.setText(datas.get(position).getDaytime());
    }


    @Override
    public int getItemCount() {
        return datas.size();
    }
    class Holder extends RecyclerView.ViewHolder{


        private final TextView light_item_content;
        private final ImageView light_item_image;
        private final TextView light_item_time;

        public Holder(View itemView) {
            super(itemView);
            light_item_content = (TextView) itemView.findViewById(R.id.light_item_content);
            light_item_image = (ImageView) itemView.findViewById(R.id.light_item_image);
            light_item_time = (TextView) itemView.findViewById(R.id.light_item_time);
        }
    }
}

