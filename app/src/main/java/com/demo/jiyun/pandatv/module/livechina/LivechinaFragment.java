package com.demo.jiyun.pandatv.module.livechina;

import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.demo.jiyun.pandatv.R;
import com.demo.jiyun.pandatv.base.BaseFragment;
import com.demo.jiyun.pandatv.model.entity.ChinaLiveBean;
import com.demo.jiyun.pandatv.utils.CustomDialog;
import com.demo.jiyun.pandatv.utils.CustomViewPager;
import com.demo.jiyun.pandatv.utils.MyLog;
import com.demo.jiyun.pandatv.utils.ToastManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by iu on 2017/7/27.
 */

public class LivechinaFragment extends BaseFragment implements LivechinaContract.View {
    @BindView(R.id.live_china_tab)
    TabLayout liveChinaTab;
    @BindView(R.id.live_china_add_channel)
    ImageView liveChinaAddChannel;
    @BindView(R.id.china_layout)
    CustomViewPager chinaLayout;

    private List<ChinaLiveBean.TablistBean> tablistBeen = new ArrayList<>();
    private List<ChinaLiveBean.AlllistBean> alllistBeen = new ArrayList<>();
    private GridView china_all_grid;
    private GridView china_tab_grid;
    private GridTabAdapter tabAdapter;
    private GridAllAdapter allAdapter;
    private ArrayList<Fragment> list = new ArrayList<>();
    private LiveChinaTabAdapter liveChinaTabAdapter;
    private ImageView delect_channel;
    private PopupWindow popupWindow;
    private Button live_china_edit;

    private LivechinaContract.Presenter presenter;

    @Override
    protected int getLayoutId() {
        return R.layout.livechina_fragment;
    }

    @Override
    protected void init(View view) {

        new LivechinaPresenter(this);

    }

    @Override
    protected void loadData() {

        presenter.start();

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @OnClick(R.id.live_china_add_channel)
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.live_china_add_channel:
                initpopupwindow();
                popupWindow.setTouchable(true);
                popupWindow.setFocusable(true);
                popupWindow.setBackgroundDrawable(new BitmapDrawable());
                popupWindow.setOutsideTouchable(true);
                popupWindow.showAtLocation(view, Gravity.BOTTOM, 0, 0);
                popupWindow.update();
                delect_channel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Window window = getActivity().getWindow();
                        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                        popupWindow.dismiss();
                    }
                });
                break;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void initpopupwindow() {
        Window window = getActivity().getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        View view1 = LayoutInflater.from(getContext()).inflate(R.layout.china_popupwindow, null);
        final TextView china_text = (TextView) view1.findViewById(R.id.china_text);
        china_tab_grid = (GridView) view1.findViewById(R.id.china_tab_grid);
        china_all_grid = (GridView) view1.findViewById(R.id.china_all_grid);
        delect_channel = (ImageView) view1.findViewById(R.id.live_china_delect_channel);
        popupWindow = new PopupWindow(view1, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, true);
        live_china_edit = (Button) view1.findViewById(R.id.live_china_edit);
        inittabGridView();
        initallGridView();

        live_china_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (live_china_edit.getText().equals("编辑")) {
                    tabAdapter.EDIT_TRUE=true;
                    live_china_edit.setText("完成");
                    china_text.setVisibility(View.VISIBLE);



                    tabAdapter.notifyDataSetChanged();
                } else {
                    tabAdapter.notifyDataSetChanged();
                    tabAdapter.EDIT_TRUE=false;
                    live_china_edit.setText("编辑");
                    china_text.setVisibility(View.GONE);
                    list.clear();
                    for (int i = 0; i < tablistBeen.size(); i++) {
                        ChinaItemFragment childfragment = new ChinaItemFragment(tablistBeen.get(i).getUrl());
                        new ChinaItemPresenter(childfragment);
                        list.add(childfragment);
                    }
                    tabAdapter.notifyDataSetChanged();
                    liveChinaTabAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    public void inittabGridView() {

        china_tab_grid.setAdapter(tabAdapter);
        china_tab_grid.setNumColumns(3);
        china_tab_grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(tablistBeen.size()<5) {
                    Toast.makeText(getContext(),"栏目区，不能少于四个频道",Toast.LENGTH_SHORT).show();
                }
                else {
                    if(live_china_edit.getText().equals("完成")) {
                        ChinaLiveBean.AlllistBean all = new ChinaLiveBean.AlllistBean();
                        all.setTitle(tablistBeen.get(position).getTitle());
                        all.setOrder(tablistBeen.get(position).getOrder());
                        all.setType(tablistBeen.get(position).getType());
                        all.setUrl(tablistBeen.get(position).getUrl());
                        alllistBeen.add(all);
                        allAdapter.notifyDataSetChanged();
                        tablistBeen.remove(tablistBeen.get(position));
                        tabAdapter.notifyDataSetChanged();
                    }
                }
            }
        });
    }

    public void initallGridView() {

        china_all_grid.setAdapter(allAdapter);
        china_all_grid.setNumColumns(3);
        china_all_grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(live_china_edit.getText().equals("完成")) {

                    ChinaLiveBean.TablistBean tab = new ChinaLiveBean.TablistBean();
                    tab.setTitle(alllistBeen.get(position).getTitle());
                    tab.setOrder(alllistBeen.get(position).getOrder());
                    tab.setType(alllistBeen.get(position).getType());
                    tab.setUrl(alllistBeen.get(position).getUrl());

                    tablistBeen.add(tab);
                    alllistBeen.remove(alllistBeen.get(position));
                    allAdapter.notifyDataSetChanged();
                    tabAdapter.notifyDataSetChanged();
                }
            }
        });
    }


    @Override
    public void showLivechinaData(ChinaLiveBean chinaLiveBean) {

        MyLog.d("TAG",chinaLiveBean.getAlllist().get(0).getTitle());

        list.clear();
        alllistBeen.clear();
        tablistBeen.clear();
        alllistBeen.addAll(chinaLiveBean.getAlllist());
        tablistBeen.addAll(chinaLiveBean.getTablist());
        tabAdapter = new GridTabAdapter(getContext(), (ArrayList<ChinaLiveBean.TablistBean>) tablistBeen);

        for (int i = 0; i < tablistBeen.size(); i++) {
            ChinaLiveBean.TablistBean tablistBean = tablistBeen.get(i);
            for (int j = 0; j < alllistBeen.size(); j++) {
                if(tablistBean.getTitle().equals(alllistBeen.get(j).getTitle())){
                    alllistBeen.remove(j);
                }
            }
        }

        allAdapter = new GridAllAdapter(getContext(), (ArrayList<ChinaLiveBean.AlllistBean>) alllistBeen);
        for (int i = 0; i < chinaLiveBean.getTablist().size(); i++) {
            ChinaItemFragment childfragment = new ChinaItemFragment(tablistBeen.get(i).getUrl());
            new ChinaItemPresenter(childfragment);
            list.add(childfragment);
        }

        liveChinaTabAdapter = new LiveChinaTabAdapter(getActivity().getSupportFragmentManager(), list, (ArrayList<ChinaLiveBean.TablistBean>) tablistBeen);
        chinaLayout.setAdapter(liveChinaTabAdapter);
        chinaLayout.setScanScroll(false);
        liveChinaTab.setupWithViewPager(chinaLayout);
        liveChinaTab.setTabMode(TabLayout.MODE_SCROLLABLE);
        liveChinaTab.setSelectedTabIndicatorColor(ContextCompat.getColor(getActivity(), R.color.colorPrimary));
        liveChinaTab.setTabTextColors(ContextCompat.getColor(getActivity(), R.color.colorBlank), ContextCompat.getColor(getActivity(), R.color.colorPrimary));

        LinearLayout linearLayout = (LinearLayout) liveChinaTab.getChildAt(0);
        linearLayout.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
        linearLayout.setDividerDrawable(ContextCompat.getDrawable(getContext(),
                R.drawable.radio_bg));
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
    public void setPresenter(LivechinaContract.Presenter presenter) {

        this.presenter = presenter;

    }
}
