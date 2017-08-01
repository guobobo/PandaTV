package com.demo.jiyun.pandatv.module.mine.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.demo.jiyun.pandatv.R;
import com.demo.jiyun.pandatv.model.db.History;

import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<History> list;
    private LayoutInflater inflater;
    public HistoryAdapter(Context context, List<History> list) {
        this.context = context;
        this.list = list;
        inflater=LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MHolder(inflater.inflate(R.layout.item_collection,null));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        MHolder mHolder= (MHolder) holder;
        Glide.with(context).load(list.get(position).getImage()).into(mHolder.item_collection_img);
        mHolder.item_collection_videolength.setText(list.get(position).getDuration());
        mHolder.item_collectiontitle.setText(list.get(position).getTitle());
        mHolder.item_collection_date.setText(list.get(position).getData());

        if (list.get(position).getVisibility()) {
            mHolder.item_collection_checklinear.setVisibility(View.VISIBLE);
        } else {
            mHolder.item_collection_checklinear.setVisibility(View.GONE);
        }

        mHolder.item_collectioncheckBox.setChecked(list.get(position).getFlag());

        mHolder.item_collection_linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onHistoryLinear.onHistory(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MHolder extends RecyclerView.ViewHolder {
        private RadioButton item_collectioncheckBox;
        private ImageView item_collection_img;
        private TextView item_collection_videolength;
        private TextView item_collectiontitle;
        private TextView item_collection_date;
        private LinearLayout item_collection_linear;
        private LinearLayout item_collection_checklinear;
        public MHolder(View itemView) {
            super(itemView);
            item_collectioncheckBox = (RadioButton) itemView.findViewById(R.id.item_collectioncheckBox);
            item_collection_img = (ImageView) itemView.findViewById(R.id.item_collection_img);
            item_collection_videolength = (TextView) itemView.findViewById(R.id.item_collection_videolength);
            item_collectiontitle = (TextView) itemView.findViewById(R.id.item_collectiontitle);
            item_collection_date = (TextView) itemView.findViewById(R.id.item_collection_date);
            item_collection_linear = (LinearLayout) itemView.findViewById(R.id.item_collection_linear);
            item_collection_checklinear= (LinearLayout) itemView.findViewById(R.id.item_collection_checklinear);

        }
    }

    public interface OnHistoryLinear{
        void onHistory(int position);
    }
    private OnHistoryLinear onHistoryLinear;

    public void setOnHistoryLinear(OnHistoryLinear onHistoryLinear) {
        this.onHistoryLinear = onHistoryLinear;
    }
}
