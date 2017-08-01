package com.demo.jiyun.pandatv.module.interactive;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.demo.jiyun.pandatv.R;
import com.demo.jiyun.pandatv.model.entity.InteractivesBean;

import java.util.List;

public class InteractiveAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<InteractivesBean.InteractiveBean> list;
    private LayoutInflater inflater;

    public InteractiveAdapter(Context context, List<InteractivesBean.InteractiveBean> list) {
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MHolder(inflater.inflate(R.layout.item_interactive, null));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MHolder mHolder = (MHolder) holder;
        mHolder.item_interactive_title.setText(list.get(position).getTitle());
        Glide.with(context).load(list.get(position).getImage()).into(mHolder.item_interactive_image);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MHolder extends RecyclerView.ViewHolder {
        private TextView item_interactive_title;
        private ImageView item_interactive_image;
        public MHolder(View itemView) {
            super(itemView);
            item_interactive_title= (TextView) itemView.findViewById(R.id.item_interactive_title);
            item_interactive_image = (ImageView) itemView.findViewById(R.id.item_interactive_image);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onWebClick.onClickLiner(getAdapterPosition());
                }
            });
        }
    }

    public interface OnWebClick{
        void onClickLiner(int position);
    }

    public void setOnWebClick(OnWebClick onWebClick) {
        this.onWebClick = onWebClick;
    }

    public OnWebClick onWebClick;

}
