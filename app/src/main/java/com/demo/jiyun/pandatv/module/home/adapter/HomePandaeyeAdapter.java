package com.demo.jiyun.pandatv.module.home.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.demo.jiyun.pandatv.R;
import com.demo.jiyun.pandatv.model.entity.PandaeyeListBean;
import com.demo.jiyun.pandatv.net.HttpFactory;

import java.util.List;

/**
 * Created by iu on 2017/7/28.
 */

public class HomePandaeyeAdapter extends RecyclerView.Adapter {


    private Context context;
    private List<PandaeyeListBean.ListBean> datas;
    private LayoutInflater inflater;

    public HomePandaeyeAdapter(Context context, List<PandaeyeListBean.ListBean> datas) {
        this.context = context;
        this.datas = datas;
        this.inflater = LayoutInflater.from(context);
    }


    public interface PandaeyeOnClick{
        void setPandaeyeOnClick(View v,int pos);
    }

    private PandaeyeOnClick pandaeyeOnClick;

    public void PandaeyeAdapterOnClick (HomePandaeyeAdapter.PandaeyeOnClick pandaeyeOnClick) {
        this.pandaeyeOnClick = pandaeyeOnClick;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.pandaeye_recy_item,null);
        return new Holder(view,pandaeyeOnClick);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Holder h= (Holder) holder;
        HttpFactory.httpCreate().imageLoad(datas.get(position).getImage(),h.eye_list_image1);

        h.eye_list_text1.setText(datas.get(position).getTitle());
        h.eye_list_text2.setText(datas.get(position).getDaytime());
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }
    class Holder extends RecyclerView.ViewHolder{

        private final ImageView eye_list_image1;
        private final TextView eye_list_text1;
        private final TextView eye_list_text2;
        PandaeyeOnClick pandaeyeOnClick;

        public Holder(View itemView, final PandaeyeOnClick pandaeyeOnClick) {
            super(itemView);
            eye_list_image1 = (ImageView) itemView.findViewById(R.id.eye_list_image1);
            eye_list_text1 = (TextView) itemView.findViewById(R.id.eye_list_text1);
            eye_list_text2 = (TextView) itemView.findViewById(R.id.eye_list_text2);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    pandaeyeOnClick.setPandaeyeOnClick(v,getAdapterPosition());
                }
            });
        }
    }
}
