package com.demo.jiyun.pandatv.module.pandaeye;

import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.demo.jiyun.pandatv.R;
import com.demo.jiyun.pandatv.app.App;
import com.demo.jiyun.pandatv.base.BaseFragment;
import com.demo.jiyun.pandatv.model.entity.BroadCastBean;
import com.demo.jiyun.pandatv.model.entity.BroadCastListBean;
import com.demo.jiyun.pandatv.module.pandaeye.adapter.PandaeyeAdapterXRecy;
import com.demo.jiyun.pandatv.utils.CustomDialog;
import com.demo.jiyun.pandatv.utils.ToActivity;
import com.demo.jiyun.pandatv.utils.ToastManager;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by iu on 2017/7/28.
 */

public class PandaeyeFragment extends BaseFragment implements PandaeyeContract.View, XRecyclerView.LoadingListener {

    @BindView(R.id.pandaeye_xrecy)
    XRecyclerView pandaeyeXrecy;

    ImageView itemEyeImg;
    TextView itemEyeTitle;
    String url = null;
    private PandaeyeContract.Presenter presenter;
    private PandaeyeAdapterXRecy pandaeyeAdapterXRecy;
    private List<BroadCastBean.DataBean.BigImgBean> broadCasts;
    private List<BroadCastListBean.ListBean> broadCastListBeen;

    @Override
    protected int getLayoutId() {
        return R.layout.pandaeye_fragment;
    }

    @Override
    protected void init(View view) {
        new PandaeyePresenter(this);

        broadCasts = new ArrayList<>();
        broadCastListBeen = new ArrayList<>();
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        pandaeyeXrecy.setLayoutManager(manager);
        pandaeyeXrecy.setPullRefreshEnabled(true);
        pandaeyeXrecy.setLoadingMoreEnabled(false);
        pandaeyeXrecy.setLoadingListener(this);
        pandaeyeAdapterXRecy = new PandaeyeAdapterXRecy(getActivity(), broadCastListBeen);
        pandaeyeXrecy.setAdapter(pandaeyeAdapterXRecy);
        pandaeyeAdapterXRecy.OnPandaeyePlay(new PandaeyeAdapterXRecy.OnPandaeyePlay() {
            @Override
            public void showPandaeyePlay(int position) {
                ToActivity.loadPlay(
                        broadCastListBeen.get(position).getGuid(),
                        broadCastListBeen.get(position).getTitle(),
                        broadCastListBeen.get(position).getPicurl2(),
                        broadCastListBeen.get(position).getVideolength());
            }
        });
        showHead();
    }

    @Override
    protected void loadData() {
        presenter.start();
    }

    @Override
    public void showPandaeyeData(BroadCastBean broadCastBean) {
        broadCasts.clear();
        broadCasts.addAll(broadCastBean.getData().getBigImg());
        for (int i = 0; i < broadCasts.size(); i++) {
            BroadCastBean.DataBean.BigImgBean bigImgBean = broadCasts.get(i);
            itemEyeTitle.setText(bigImgBean.getTitle());
            Glide.with(getActivity()).load(bigImgBean.getImage()).into(itemEyeImg);
            url= bigImgBean.getUrl();
        }
        pandaeyeAdapterXRecy.notifyDataSetChanged();

    }

    @Override
    public void showPandaeyeListData(BroadCastListBean broadCastListBean) {
        broadCastListBeen.clear();
        broadCastListBeen.addAll(broadCastListBean.getList());
        pandaeyeAdapterXRecy.notifyDataSetChanged();
        pandaeyeXrecy.refreshComplete();
    }

    @Override
    public void playVideo() {

    }

    @Override
    public void loadWebView() {

    }

    @Override
    public void showProgress() {
        CustomDialog.show(getActivity());
    }

    @Override
    public void dimissProgress() {
        CustomDialog.dimiss();
    }

    @Override
    public void showMessage(String msg) {
        ToastManager.show(msg);
    }

    @Override
    public void setPresenter(PandaeyeContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onRefresh() {
        presenter.start();
    }

    @Override
    public void onLoadMore() {

    }

    public void showHead(){

        View bigimgView = LayoutInflater.from(App.context).inflate(R.layout.item_eye_bigimg, null);
        pandaeyeXrecy.addHeaderView(bigimgView);
        itemEyeImg = (ImageView) bigimgView.findViewById(R.id.item_eye_img);
        itemEyeTitle= (TextView) bigimgView.findViewById(R.id.item_eye_title);

        itemEyeImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToActivity.loadWeb(url);
            }
        });
    }

}
