package com.demo.jiyun.pandatv.module.home.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.demo.jiyun.pandatv.R;
import com.demo.jiyun.pandatv.model.entity.HomecctvListBean;
import com.demo.jiyun.pandatv.net.HttpFactory;

import java.util.List;

/**
 * Created by iu on 2017/7/29.
 */

public class HomeCCTVAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<HomecctvListBean.ListBean> datas;
    private LayoutInflater inflater;

    public HomeCCTVAdapter(Context context, List<HomecctvListBean.ListBean> datas) {
        this.context = context;
        this.datas = datas;
        this.inflater = LayoutInflater.from(context);
    }



    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.cctv_recy_item,null);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Holder h= (Holder) holder;

        HttpFactory.httpCreate().imageLoad(datas.get(position).getImage(),h.cctv_item_image);
        h.cctv_item_text.setText(datas.get(position).getTitle());

    }


    @Override
    public int getItemCount() {
        return datas.size();
    }
    class Holder extends RecyclerView.ViewHolder{


        private final ImageView cctv_item_image;
        private final TextView cctv_item_text;

        public Holder(View itemView) {
            super(itemView);
            cctv_item_image = (ImageView) itemView.findViewById(R.id.cctv_item_image);
            cctv_item_text = (TextView) itemView.findViewById(R.id.cctv_item_text);
        }
    }
}

