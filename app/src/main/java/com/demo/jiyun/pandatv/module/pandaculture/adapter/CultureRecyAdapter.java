package com.demo.jiyun.pandatv.module.pandaculture.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.demo.jiyun.pandatv.R;
import com.demo.jiyun.pandatv.model.entity.CurtureBean;

import java.util.List;

public class CultureRecyAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<CurtureBean.ListBean> listBeanList;
    private LayoutInflater inflater;
    public CultureRecyAdapter(Context context, List<CurtureBean.ListBean> listBeanList) {
        this.context = context;
        this.listBeanList = listBeanList;
        inflater=LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MHolder(inflater.inflate(R.layout.item_culture_recy,null));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MHolder mHolder= (MHolder) holder;
        CurtureBean.ListBean listBean = listBeanList.get(position);
        mHolder.item_culture_list_title.setText(listBean.getTitle());
        mHolder.item_culture_list_videolength.setText(listBean.getVideoLength());
        mHolder.item_culture_list_brief.setText(listBean.getBrief());
        Glide.with(context).load(listBean.getImage()).into(mHolder.item_culture_list_img);
    }

    @Override
    public int getItemCount() {
        return listBeanList.size();
    }

    class MHolder extends RecyclerView.ViewHolder {
        private ImageView item_culture_list_img;
        private TextView item_culture_list_videolength;
        private TextView item_culture_list_title;
        private TextView item_culture_list_brief;
        public MHolder(View itemView) {
            super(itemView);
            item_culture_list_img = (ImageView) itemView.findViewById(R.id.item_culture_list_img);
            item_culture_list_videolength = (TextView) itemView.findViewById(R.id.item_culture_list_videolength);
            item_culture_list_title = (TextView) itemView.findViewById(R.id.item_culture_list_title);
            item_culture_list_brief = (TextView) itemView.findViewById(R.id.item_culture_list_brief);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onCulturePlay.loadCulturePlay(getAdapterPosition());
                }
            });
        }
    }

    public interface OnCulturePlay{
        void loadCulturePlay(int position);
    }

    public void setOnCulturePlay(OnCulturePlay onCulturePlay) {
        this.onCulturePlay = onCulturePlay;
    }

    private OnCulturePlay onCulturePlay;
}
