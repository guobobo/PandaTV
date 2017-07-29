package com.demo.jiyun.pandatv.module.pandaeye.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.demo.jiyun.pandatv.R;
import com.demo.jiyun.pandatv.model.entity.BroadCastListBean;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class PandaeyeAdapterXRecy extends RecyclerView.Adapter {

    private Context context;
    private List<BroadCastListBean.ListBean> list;
    private LayoutInflater inflater;

    public PandaeyeAdapterXRecy(Context context, List<BroadCastListBean.ListBean> broadCastListBeen) {
        this.context = context;
        this.list = broadCastListBeen;
        inflater=LayoutInflater.from(context);
    }

    public interface OnPandaeyePlay {
        void showPandaeyePlay(int position);
    }
    private OnPandaeyePlay onPandaeyePlay;

    public void OnPandaeyePlay(OnPandaeyePlay onPandaeyePlay) {
        this.onPandaeyePlay = onPandaeyePlay;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View urllistView = inflater.inflate(R.layout.item_eye_list, null);
        return new UrlListHolder(urllistView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        UrlListHolder urllistHolder = (UrlListHolder) holder;
        Date date=new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String format = sdf.format(date);
        urllistHolder.item_eye_list_date.setText(format);
        urllistHolder.item_eye_list_title.setText(list.get(position).getTitle());
        urllistHolder.item_eye_list_videolength.setText(list.get(position).getVideolength());
        Glide.with(context).load(list.get(position).getPicurl2()).into(urllistHolder.item_eye_list_img);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class UrlListHolder extends RecyclerView.ViewHolder {
        private ImageView item_eye_list_img;
        private TextView item_eye_list_videolength;
        private TextView item_eye_list_title;
        private TextView item_eye_list_date;
        public UrlListHolder(View itemView) {
            super(itemView);
            item_eye_list_img = (ImageView) itemView.findViewById(R.id.item_eye_list_img);
            item_eye_list_videolength = (TextView) itemView.findViewById(R.id.item_eye_list_videolength);
            item_eye_list_title = (TextView) itemView.findViewById(R.id.item_eye_list_title);
            item_eye_list_date = (TextView) itemView.findViewById(R.id.item_eye_list_date);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onPandaeyePlay.showPandaeyePlay(getAdapterPosition());
                }
            });
        }
    }
}
