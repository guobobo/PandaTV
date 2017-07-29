package com.demo.jiyun.pandatv.module.home.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
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
 * Created by iu on 2017/7/29.
 */

public class HomePandaLiveAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<PandaHomeBean.DataBean.PandaliveBean.ListBean> datas;
    private LayoutInflater inflater;

    public HomePandaLiveAdapter(Context context, List<PandaHomeBean.DataBean.PandaliveBean.ListBean> datas) {
        this.context = context;
        this.datas = datas;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.live_recy_item,null);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        Holder h = (Holder) holder;
        HttpFactory.httpCreate().imageLoad(datas.get(position).getImage(),h.imageView);
        h.textView.setText(datas.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    class Holder extends RecyclerView.ViewHolder{


        private final ImageView imageView;
        private final TextView textView;

        public Holder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.live_recy_image);
            textView = (TextView) itemView.findViewById(R.id.live_recy_text);
        }
    }
}
