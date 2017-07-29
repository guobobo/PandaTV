package com.demo.jiyun.pandatv.module.livechina;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.demo.jiyun.pandatv.model.entity.ChinaLiveBean;

import java.util.ArrayList;

public class LiveChinaTabAdapter extends FragmentPagerAdapter {

    private ArrayList<Fragment> list;
    private ArrayList<ChinaLiveBean.TablistBean> tablist;
    public LiveChinaTabAdapter(FragmentManager fm, ArrayList<Fragment> list, ArrayList<ChinaLiveBean.TablistBean> tablist) {
        super(fm);
        this.list=list;
        this.tablist=tablist;
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tablist.get(position).getTitle();
    }
}
