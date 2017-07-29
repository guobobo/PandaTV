package com.demo.jiyun.pandatv.module.home.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.demo.jiyun.pandatv.R;
import com.demo.jiyun.pandatv.model.entity.PandaHomeBean;
import com.demo.jiyun.pandatv.net.HttpFactory;

import java.util.List;

/**
 * Created by xingge on 2017/7/26.
 */

public class HomeAreaAdapter extends Adapter {

    private Context context;
    private List<PandaHomeBean.DataBean.AreaBean.ListscrollBean> datas;
    private LayoutInflater inflater;
    public HomeAreaAdapter(Context context, List<PandaHomeBean.DataBean.AreaBean.ListscrollBean> datas) {
        this.context = context;
        this.datas = datas;
        this.inflater = LayoutInflater.from(context);
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.home_area_item,null);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        Holder h = (Holder) holder;
        HttpFactory.httpCreate().imageLoad(datas.get(position).getImage(),h.img);
        h.titleTv.setText(datas.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    class Holder extends RecyclerView.ViewHolder{
        ImageView img;
        TextView titleTv;
        public Holder(View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.areaImg);
            titleTv = (TextView) itemView.findViewById(R.id.areaTitle);
        }
    }
}
