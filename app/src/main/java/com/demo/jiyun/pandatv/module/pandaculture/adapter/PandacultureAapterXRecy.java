package com.demo.jiyun.pandatv.module.pandaculture.adapter;


import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.demo.jiyun.pandatv.R;
import com.demo.jiyun.pandatv.config.GlideImageLoader;
import com.demo.jiyun.pandatv.model.entity.CurtureBean;
import com.demo.jiyun.pandatv.utils.ToActivity;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

public class PandacultureAapterXRecy extends RecyclerView.Adapter {

    private Context context;
    private List<Object> datas;
    private LayoutInflater inflater;

    public PandacultureAapterXRecy(Context context, List<Object> datas) {
        this.context = context;
        this.datas = datas;
        inflater = LayoutInflater.from(context);
    }

    public static final int ITEMCOUNT = 2;//加载不同布局
    public static final int BIGIMG = 0;//加载轮播图
    public static final int LIST = 1;//加载List

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return BIGIMG;
        } else if (position == 1) {
            return LIST;
        }
        return super.getItemViewType(position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case BIGIMG:
                View bigimgView = inflater.inflate(R.layout.item_culture_bigimg, null);
                return new BigimgHolder(bigimgView);
            case LIST:
                View listView = inflater.inflate(R.layout.item_culture_list, null);
                return new ListHolder(listView);
        }

        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case BIGIMG:
                BigimgHolder bigimgHolder= (BigimgHolder) holder;
                List<CurtureBean.BigImgBean> bigImgBeanList= (List<CurtureBean.BigImgBean>) datas.get(position);
                loadBigImg(bigimgHolder,bigImgBeanList);
                break;
            case LIST:
                ListHolder listHolder = (ListHolder) holder;
                List<CurtureBean.ListBean> listBeanList = (List<CurtureBean.ListBean>) datas.get(position);
                loadList(listHolder, listBeanList);
                break;
        }
    }

    private void loadBigImg(final BigimgHolder bigimgHolder, final List<CurtureBean.BigImgBean> bigImgBeanList) {
        final List<String> list=new ArrayList<>();
        for(int i = 0; i < bigImgBeanList.size(); i++) {
            CurtureBean.BigImgBean bigImgBean = bigImgBeanList.get(i);
            list.add(bigImgBean.getImage());
        }

        //设置图片加载器
        bigimgHolder.item_culture_banner.setImageLoader(new GlideImageLoader());
        //设置自动轮播，默认为true
        bigimgHolder.item_culture_banner.isAutoPlay(true);
        //设置轮播时间
        bigimgHolder.item_culture_banner.setDelayTime(2000);
        //设置图片集合
        bigimgHolder.item_culture_banner.setImages(list);
        //设置点的位置
        bigimgHolder.item_culture_banner.setIndicatorGravity(BannerConfig.RIGHT);
        //banner设置方法全部调用完毕时最后调用
        bigimgHolder.item_culture_banner.start();

        bigimgHolder.item_culture_banner.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(position<=4&&position!=0){
                    bigimgHolder.item_culture_banner_title.setText(bigImgBeanList.get(position-1).getTitle());
                }else {
                    position=0;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        bigimgHolder.item_culture_banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                CurtureBean.BigImgBean bigImgBean = bigImgBeanList.get(position);
                ToActivity.loadWeb(bigImgBean.getUrl());
            }
        });

    }


    private void loadList(ListHolder holder, final List<CurtureBean.ListBean> listBeanList) {
        RecyclerView recyclerView = holder.recyclerView;
        LinearLayoutManager manager=new LinearLayoutManager(context);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        CultureRecyAdapter cultureRecyAdapter= new CultureRecyAdapter(context,listBeanList);
        recyclerView.setAdapter(cultureRecyAdapter);
        cultureRecyAdapter.setOnCulturePlay(new CultureRecyAdapter.OnCulturePlay() {
            @Override
            public void loadCulturePlay(int position) {
                if(position==0){

                }else {
                    ToActivity.loadPlay(
                            listBeanList.get(position).getId(),
                            listBeanList.get(position).getTitle(),
                            listBeanList.get(position).getImage(),
                            listBeanList.get(position).getVideoLength());
                }

            }
        });
    }


    @Override
    public int getItemCount() {
        return datas.size();
    }

    class BigimgHolder extends RecyclerView.ViewHolder {
        private Banner item_culture_banner;
        private TextView item_culture_banner_title;
        public BigimgHolder(View itemView) {
            super(itemView);
            item_culture_banner = (Banner) itemView.findViewById(R.id.item_culture_banner);
            item_culture_banner_title = (TextView) itemView.findViewById(R.id.item_culture_banner_title);
        }
    }

    class ListHolder extends RecyclerView.ViewHolder {

        private RecyclerView recyclerView;
        public ListHolder(View itemView) {
            super(itemView);
            recyclerView= (RecyclerView) itemView.findViewById(R.id.item_culture_recy);
        }
    }

}
