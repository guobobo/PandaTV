package com.demo.jiyun.pandatv.utils;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.demo.jiyun.pandatv.app.App;
import com.demo.jiyun.pandatv.base.BaseFragment;

/**
 * Created by iu on 2017/7/27.
 */

public class FragmentBuild {

    public static BaseFragment lastFragment;

    public static BaseFragment changeFragment(Class<? extends BaseFragment> fragmentClass, int containId, boolean isHidden, Bundle bundle, boolean isBack) {

        FragmentManager manager = App.context.getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        String fragmentName = fragmentClass.getSimpleName();

        BaseFragment currentFragment = (BaseFragment) manager.findFragmentByTag(fragmentName);
        if(currentFragment == null){

            try {

                currentFragment = fragmentClass.newInstance();

                transaction.add(containId,currentFragment,fragmentName);

            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        if(isHidden) {
            //隐藏上一个Fragment
            if (lastFragment != null&isBack)
                transaction.hide(lastFragment);
            //显示当前Fragment
            transaction.show(currentFragment);
        }else {
            //替换上一个Fragment
            transaction.replace(containId,currentFragment,fragmentName);
        }
        //传递参数
        if(bundle != null){
            currentFragment.setBundle(bundle);
        }

        if(isBack){
            transaction.addToBackStack(fragmentName);
            lastFragment = currentFragment;
        }

        transaction.commit();

        return lastFragment;

    }


}
