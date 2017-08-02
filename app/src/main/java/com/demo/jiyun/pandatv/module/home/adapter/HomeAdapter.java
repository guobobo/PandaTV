package com.demo.jiyun.pandatv.module.home.adapter;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.demo.jiyun.pandatv.R;
import com.demo.jiyun.pandatv.config.GlideImageLoader;
import com.demo.jiyun.pandatv.model.entity.HomecctvListBean;
import com.demo.jiyun.pandatv.model.entity.HomelightListBean;
import com.demo.jiyun.pandatv.model.entity.PandaHomeBean;
import com.demo.jiyun.pandatv.model.entity.PandaeyeListBean;
import com.demo.jiyun.pandatv.net.HttpFactory;
import com.demo.jiyun.pandatv.utils.ToActivity;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

import static com.demo.jiyun.pandatv.R.id.live_show_recy;
import static com.demo.jiyun.pandatv.R.id.live_title;

/**
 * Created by iu on 2017/7/28.
 */

public class HomeAdapter extends RecyclerView.Adapter implements HomeAreaAdapter.AreaOnClick,HomePandaeyeAdapter.PandaeyeOnClick,HomeLightAdapter.LightOnClick {

    private LayoutInflater inflater;
    public static final int BIGIMG = 0;//代表轮播图
    public static final int AREA = 1;//精彩推荐
    public static final int PANDAEYE = 2;//熊猫观察
    public static final int PANDALIVE = 3;//熊猫直播;
    public static final int WALLLIVE = 4;//长城直播
    public static final int CHINALIVE = 5;//直播中国
    public static final int INTERACTIVE = 6;//特别策划
    public static final int CCTV = 7;//cctv
    public static final int LIST = 8;//光影中国
    private Context context;
    private RecyclerView pandaeye_recy;
    private RecyclerView cctv_recy;
    private RecyclerView light_recy;
    private PandaHomeBean.DataBean.AreaBean areaBean;
    private PandaHomeBean.DataBean.PandaeyeBean pandaeyeBean;
    private PandaHomeBean.DataBean.InteractiveBean interactiveBean;
    private List<Object> datas;
    private List<PandaeyeListBean.ListBean> eyeList;
    private List<HomelightListBean.ListBean> lightList;
    private List<HomecctvListBean.ListBean> cctvList;


    public interface HomeOnclick {
        //播图监听
        void getbannerClick(String url);
        //精彩推荐监听
        void getareaClick(PandaHomeBean.DataBean.AreaBean.ListscrollBean listscrollBean);
        //熊猫观察
        void getpandaeyeClicks(PandaHomeBean.DataBean.PandaeyeBean.ItemsBean itemsBean);
        //特别策划
        void getInteractiveClick(PandaHomeBean.DataBean.InteractiveBean.InteractiveoneBean interactiveoneBean);
        //光影中国
        void getLightClick(HomelightListBean.ListBean listBean);
    }

    private HomeOnclick homeonclick;

    public void HomeOnclick(HomeOnclick homeonclick){
        this.homeonclick = homeonclick;
    }
    public HomeAdapter(List<Object> datas,
                       List<PandaeyeListBean.ListBean> eyeList,
                       List<HomelightListBean.ListBean> lightList,
                       List<HomecctvListBean.ListBean> cctvList,
                       Context context) {
        this.datas = datas;
        this.context = context;
        this.eyeList = eyeList;
        this.lightList = lightList;
        this.inflater = LayoutInflater.from(context);
        this.cctvList = cctvList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        switch (viewType) {
            case BIGIMG:
                View bigimgView = inflater.inflate(R.layout.homefragment_recy_banner, null);
                return new BannerHoder(bigimgView);
            case AREA:
                View areaView = inflater.inflate(R.layout.homefragment_recy_area, null);
                return new AreaHolder(areaView);
            case PANDAEYE:
                View pandaeyeView = inflater.inflate(R.layout.homefragment_recy_pandaeye, null);
                return new PandaeyeHolder(pandaeyeView);
            case PANDALIVE:
                View pandaliveview = inflater.inflate(R.layout.homefragment_recy_live, null);
                return new PandaliveHolder(pandaliveview);
            case WALLLIVE:
                View wallliveview = inflater.inflate(R.layout.homefragment_recy_live, null);
                return new WallliveHolder(wallliveview);
            case CHINALIVE:
                View chinaliveview = inflater.inflate(R.layout.homefragment_recy_live, null);
                return new ChainaLiveHolder(chinaliveview);
            case INTERACTIVE:
                View interactiveview = inflater.inflate(R.layout.interactive_r_item, null);
                return new InteractiveHolder(interactiveview);
            case CCTV:
                View cctvview = inflater.inflate(R.layout.homefragment_recy_cctv, null);
                return  new CCTVHolder(cctvview);
            case LIST:
                View lightview = inflater.inflate(R.layout.homefragment_recy_light, null);
                return  new LightHolder(lightview);
        }
        return null;
    }

    @Override
    public int getItemViewType(int position) {
        Object obj = datas.get(position);
        if (position == 0) {
            return BIGIMG;
        } else if (obj instanceof PandaHomeBean.DataBean.AreaBean) {
            return AREA;
        } else if (obj instanceof PandaHomeBean.DataBean.PandaeyeBean) {
            return PANDAEYE;
        } else if (obj instanceof PandaHomeBean.DataBean.PandaliveBean) {
            return PANDALIVE;
        } else if (obj instanceof PandaHomeBean.DataBean.WallliveBean) {
            return WALLLIVE;
        } else if (obj instanceof PandaHomeBean.DataBean.ChinaliveBean) {
            return CHINALIVE;
        } else if (obj instanceof PandaHomeBean.DataBean.InteractiveBean) {
            return INTERACTIVE;
        } else if (obj instanceof PandaHomeBean.DataBean.CctvBean) {
            return CCTV;
        } else if (position == 8) {
            return LIST;
        }
        return super.getItemViewType(position);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Object obj = datas.get(position);

        switch (getItemViewType(position)) {
            case BIGIMG:
                BannerHoder bannerHoder  = (BannerHoder) holder;
                List<PandaHomeBean.DataBean.BigImgBean>
                imgBean = (List<PandaHomeBean.DataBean.BigImgBean>) datas.get(position);
                loadBanner(bannerHoder,imgBean);
                break;
            case AREA:
                AreaHolder areaHolder = (AreaHolder) holder;
                areaBean = (PandaHomeBean.DataBean.AreaBean) datas.get(position);
                loadArea(areaHolder, areaBean);
                break;
            case PANDAEYE:
                PandaeyeHolder pandaeyeHolder = (PandaeyeHolder) holder;
                pandaeyeBean = (PandaHomeBean.DataBean.PandaeyeBean) datas.get(position);
                pandaeye_recy = pandaeyeHolder.pandaeye_recy;
                LinearLayoutManager managereye = new LinearLayoutManager(context);
                pandaeye_recy.setLayoutManager(managereye);
                pandaeye_recy.setAdapter(new HomePandaeyeAdapter(context, eyeList));
                break;
            case PANDALIVE:
                PandaliveHolder pandaliveHolder = (PandaliveHolder) holder;
                PandaHomeBean.DataBean.PandaliveBean pandaliveBean = (PandaHomeBean.DataBean.PandaliveBean) datas.get(position);
                loadPandalive(pandaliveHolder, pandaliveBean);
                break;
            case WALLLIVE:
                WallliveHolder wallliveHolder = (WallliveHolder) holder;
                PandaHomeBean.DataBean.WallliveBean wallliveBean = (PandaHomeBean.DataBean.WallliveBean) datas.get(position);
                loadWalllive(wallliveHolder, wallliveBean);
                break;
            case CHINALIVE:
                ChainaLiveHolder chainaLiveHolder = (ChainaLiveHolder) holder;
                PandaHomeBean.DataBean.ChinaliveBean chinaliveBean = (PandaHomeBean.DataBean.ChinaliveBean) datas.get(position);
                loadChinalive(chainaLiveHolder, chinaliveBean);
                break;
            case INTERACTIVE:
                InteractiveHolder interactiveHolder = (InteractiveHolder) holder;
                interactiveBean = (PandaHomeBean.DataBean.InteractiveBean) datas.get(position);
                loadInteractive(interactiveHolder, interactiveBean);
                break;
            case CCTV:
                CCTVHolder cctvHolder = (CCTVHolder) holder;
                PandaHomeBean.DataBean.CctvBean cctvBean = (PandaHomeBean.DataBean.CctvBean) datas.get(position);
                cctv_recy = cctvHolder.cctv_recy;
                GridLayoutManager manager = new GridLayoutManager(context,2);
                cctv_recy.setLayoutManager(manager);
                cctv_recy.setAdapter(new HomeCCTVAdapter(context,cctvList));
                break;
            case LIST:
                LightHolder lightHolder = (LightHolder) holder;
                List<PandaHomeBean.DataBean.ListBeanXXX> listBeanXXX = (List<PandaHomeBean.DataBean.ListBeanXXX>) datas.get(position);
                light_recy = lightHolder.light_recy;
                LinearLayoutManager lightManager = new LinearLayoutManager(context);
                light_recy.setLayoutManager(lightManager);
                HomeLightAdapter homeLightAdapter = new HomeLightAdapter(context, lightList);
                light_recy.setAdapter(homeLightAdapter);
                break;
        }
    }


    @Override
    public int getItemCount() {
        return datas.size();
    }


    //轮播图
    class BannerHoder extends RecyclerView.ViewHolder {

        private final Banner home_banner;
        private final TextView home_banner_title;

        public BannerHoder(View itemView) {
            super(itemView);
            home_banner = (Banner) itemView.findViewById(R.id.home_banner);
            home_banner_title = (TextView) itemView.findViewById(R.id.home_banner_title);
        }
    }

    //    精彩推荐
    class AreaHolder extends RecyclerView.ViewHolder {


        RecyclerView recyclerView;
        ImageView areaIcon;

        public AreaHolder(View itemView) {
            super(itemView);
            recyclerView = (RecyclerView) itemView.findViewById(R.id.area_recy);
            areaIcon = (ImageView) itemView.findViewById(R.id.area_imge);
        }
    }

    //熊猫观察
    class PandaeyeHolder extends RecyclerView.ViewHolder {

        private RecyclerView pandaeye_recy;

        public PandaeyeHolder(View itemView) {
            super(itemView);
            pandaeye_recy = (RecyclerView) itemView.findViewById(R.id.pandaeye_recy);
        }
    }

//    熊猫直播

    class PandaliveHolder extends RecyclerView.ViewHolder {

        private final RecyclerView live_show_recy;
        private final TextView panda_live_title;

        public PandaliveHolder(View itemView) {
            super(itemView);
            live_show_recy = (RecyclerView) itemView.findViewById(R.id.live_show_recy);
            panda_live_title = (TextView) itemView.findViewById(R.id.live_title);
        }
    }

    //长城直播
    class WallliveHolder extends RecyclerView.ViewHolder {

        private final RecyclerView live_wall_recy;
        private final TextView wall_live_title;

        public WallliveHolder(View itemView) {
            super(itemView);

            live_wall_recy = (RecyclerView) itemView.findViewById(live_show_recy);
            wall_live_title = (TextView) itemView.findViewById(R.id.live_title);
        }
    }

    //直播中国
    class ChainaLiveHolder extends RecyclerView.ViewHolder {

        private final RecyclerView china_live_recy;
        private final TextView china_live_title;

        public ChainaLiveHolder(View itemView) {
            super(itemView);
            china_live_recy = (RecyclerView) itemView.findViewById(live_show_recy);
            china_live_title = (TextView) itemView.findViewById(live_title);
        }
    }
    //特别策划
    class  InteractiveHolder extends RecyclerView.ViewHolder{

        private final ImageView interactive_r_item_img;
        private final TextView interactive_r_item_tv1,interactive_r_item_tv2;

        public InteractiveHolder(View itemView) {
            super(itemView);
            interactive_r_item_img = (ImageView) itemView.findViewById(R.id.interactive_r_item_img);

            interactive_r_item_tv1 = (TextView) itemView.findViewById(R.id.interactive_r_item_tv1);
            interactive_r_item_tv2 = (TextView) itemView.findViewById(R.id.interactive_r_item_tv2);
        }
    }
    //CCTV
    class CCTVHolder extends RecyclerView.ViewHolder{

        private final RecyclerView cctv_recy;
        private final TextView cctv_title;

        public CCTVHolder(View itemView) {
            super(itemView);
            cctv_recy = (RecyclerView) itemView.findViewById(R.id.cctv_recy);
            cctv_title = (TextView) itemView.findViewById(R.id.cctv_title);
        }
    }

    //光影中国
    class LightHolder extends RecyclerView.ViewHolder{

        private final RecyclerView light_recy;
        private final TextView light_title;

        public LightHolder(View itemView) {
            super(itemView);
            light_recy = (RecyclerView) itemView.findViewById(R.id.light_recy);
            light_title = (TextView) itemView.findViewById(R.id.light_title);
        }
    }
    //精彩推荐
    private void loadArea(AreaHolder holder, PandaHomeBean.DataBean.AreaBean areaBean) {
        List<PandaHomeBean.DataBean.AreaBean.ListscrollBean> areas = areaBean.getListscroll();
        ImageView areaIcon = holder.areaIcon;
        HttpFactory.httpCreate().imageLoad(areaBean.getImage(), areaIcon);
        RecyclerView recyclerView = holder.recyclerView;
        LinearLayoutManager manager = new LinearLayoutManager(context);
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(manager);
        HomeAreaAdapter homeAreaAdapter = new HomeAreaAdapter(context, areas);
        homeAreaAdapter.HomeAreaAdapter(this);
        recyclerView.setAdapter(homeAreaAdapter);
    }

//    熊猫直播

    private void loadPandalive(PandaliveHolder holder, PandaHomeBean.DataBean.PandaliveBean pandaliveBean) {

        List<PandaHomeBean.DataBean.PandaliveBean.ListBean> pandalive = pandaliveBean.getList();
        RecyclerView live_show_recys = holder.live_show_recy;
        holder.panda_live_title.setText(pandaliveBean.getTitle());
        GridLayoutManager manager = new GridLayoutManager(context, 3);
        live_show_recys.setLayoutManager(manager);
        live_show_recys.setAdapter(new HomePandaLiveAdapter(context, pandalive));
    }

    //长城直播
    private void loadWalllive(WallliveHolder holder, PandaHomeBean.DataBean.WallliveBean wallliveBean) {

        List<PandaHomeBean.DataBean.WallliveBean.ListBeanX> walllive = wallliveBean.getList();

        RecyclerView live_wall_recy = holder.live_wall_recy;
        holder.wall_live_title.setText(wallliveBean.getTitle());

        GridLayoutManager manager = new GridLayoutManager(context, 3);
        live_wall_recy.setLayoutManager(manager);
        live_wall_recy.setAdapter(new HomeWallLiveAdapter(context, walllive));

    }

    //直播中国

    private void loadChinalive(ChainaLiveHolder holder, PandaHomeBean.DataBean.ChinaliveBean chinaliveBean) {

        List<PandaHomeBean.DataBean.ChinaliveBean.ListBeanXX> chinalive = chinaliveBean.getList();

        RecyclerView china_live_recys = holder.china_live_recy;
        holder.china_live_title.setText(chinaliveBean.getTitle());

        GridLayoutManager manager = new GridLayoutManager(context, 3);
        china_live_recys.setLayoutManager(manager);
        china_live_recys.setAdapter(new HomeChinaLiveAdapter(context, chinalive));
    }

//    特别策划


    private void loadInteractive(InteractiveHolder holder, final PandaHomeBean.DataBean.InteractiveBean interactiveBean) {

        List<PandaHomeBean.DataBean.InteractiveBean.InteractiveoneBean> interactiveone = interactiveBean.getInteractiveone();
        HttpFactory.httpCreate().imageLoad(interactiveone.get(0).getImage(), holder.interactive_r_item_img);
        holder.interactive_r_item_tv1.setText(interactiveBean.getTitle());
        holder.interactive_r_item_tv2.setText(interactiveone.get(0).getTitle());
        holder.interactive_r_item_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homeonclick.getInteractiveClick(interactiveBean.getInteractiveone().get(0));
            }
        });
    }
    //轮播图
    private void loadBanner(final BannerHoder hoder, final List<PandaHomeBean.DataBean.BigImgBean>imgBean){

        ArrayList<String> list = new ArrayList<String>();

        for(int i = 0; i < imgBean.size(); i++) {
            String url1 = imgBean.get(i).getImage();
            list.add(url1);
        }

        //设置图片加载器
        hoder.home_banner.setImageLoader(new GlideImageLoader());
        //设置自动轮播，默认为true
        hoder.home_banner.isAutoPlay(true);
        //设置轮播时间
        hoder.home_banner.setDelayTime(2000);
        //设置图片集合
        hoder.home_banner.setImages(list);
        //banner设置方法全部调用完毕时最后调用
        hoder.home_banner.setIndicatorGravity(BannerConfig.RIGHT);
        hoder.home_banner.start();
        hoder.home_banner.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                if(position<=4&&position!=0){

                    hoder.home_banner_title.setText(imgBean.get(position-1).getTitle());
                }else {
                    position=0;
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
      hoder.home_banner.setOnBannerListener(new OnBannerListener() {
          @Override
          public void OnBannerClick(int position) {

              ToActivity.loadWeb(imgBean.get(position).getUrl());
          }
      });

    }


    //精彩推荐
    @Override
    public void setAreaOnClick(View v, int pos) {
        homeonclick.getareaClick(areaBean.getListscroll().get(pos));
    }

    //熊猫观察
    @Override
    public void setPandaeyeOnClick(View v, int pos) {

        homeonclick.getpandaeyeClicks(pandaeyeBean.getItems().get(pos));
    }

    //光影中国
    @Override
    public void setLightOnClick(View v, int pos) {

        homeonclick.getLightClick(lightList.get(pos));
    }


}

