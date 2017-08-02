package com.demo.jiyun.pandatv.module.home;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.demo.jiyun.pandatv.R;
import com.demo.jiyun.pandatv.base.BaseFragment;
import com.demo.jiyun.pandatv.model.biz.UpdateBean;
import com.demo.jiyun.pandatv.model.entity.HomecctvListBean;
import com.demo.jiyun.pandatv.model.entity.HomelightListBean;
import com.demo.jiyun.pandatv.model.entity.PandaHomeBean;
import com.demo.jiyun.pandatv.model.entity.PandaeyeListBean;
import com.demo.jiyun.pandatv.module.home.adapter.HomeAdapter;
import com.demo.jiyun.pandatv.utils.CustomDialog;
import com.demo.jiyun.pandatv.utils.MyLog;
import com.demo.jiyun.pandatv.utils.ToActivity;
import com.demo.jiyun.pandatv.utils.ToastManager;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by iu on 2017/7/27.
 */

public class HomeFragment extends BaseFragment implements HomeContract.View ,HomeAdapter.HomeOnclick{


    @BindView(R.id.home_xrecy)
    XRecyclerView homeXrecy;
    private List<Object> datas;
    private HomeContract.Presenter presenter;
    private HomeAdapter homeAdapter;
    private List<PandaeyeListBean.ListBean> eyeList;
    private List<HomelightListBean.ListBean> lightList;
    private List<HomecctvListBean.ListBean> cctvList;
    private int total = 0;
    private static int versionCode;
    private String versionsUrl;
    private AlertDialog alertDialog;
    @Override
    protected int getLayoutId() {
        return R.layout.home_fragment;
    }

    @Override
    protected void init(View view) {

        this.datas = new ArrayList<Object>();
        this.eyeList = new ArrayList<PandaeyeListBean.ListBean>();
        this.lightList = new ArrayList<HomelightListBean.ListBean>();
        this.cctvList = new ArrayList<HomecctvListBean.ListBean>();

        getVersion();

        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        homeXrecy.setLayoutManager(manager);
        homeXrecy.setPullRefreshEnabled(true);
        homeXrecy.setLoadingMoreEnabled(false);

        homeAdapter = new HomeAdapter(datas,eyeList,lightList,cctvList,getActivity());
        homeXrecy.setAdapter(homeAdapter);

        homeAdapter.notifyDataSetChanged();
        homeAdapter.HomeOnclick(this);
        homeXrecy.refreshComplete();
        homeXrecy.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                presenter.start();
            }

            @Override
            public void onLoadMore() {
            }
        });
    }

    @Override
    protected void loadData() {
        presenter.start();
    }

    @Override
    public void showHomeListData(PandaHomeBean pandaHomeBean) {

        datas.clear();
        datas.add(pandaHomeBean.getData().getBigImg());
        datas.add(pandaHomeBean.getData().getArea());
        datas.add(pandaHomeBean.getData().getPandaeye());
        datas.add(pandaHomeBean.getData().getPandalive());
        datas.add(pandaHomeBean.getData().getWalllive());
        datas.add(pandaHomeBean.getData().getChinalive());
        datas.add(pandaHomeBean.getData().getInteractive());
        datas.add(pandaHomeBean.getData().getCctv());
        datas.add(pandaHomeBean.getData().getList());
    }

    @Override
    public void showPandaeyeData(PandaeyeListBean pandaeyeListBean) {
        eyeList.clear();
        eyeList.addAll(pandaeyeListBean.getList());
        MyLog.d("TAG",pandaeyeListBean.getList().size()+"........");
        homeAdapter.notifyDataSetChanged();
    }

    @Override
    public void showChinaLightData(HomelightListBean homelightListBean) {
        lightList.clear();
        lightList.addAll(homelightListBean.getList());
        homeAdapter.notifyDataSetChanged();
    }

    @Override
    public void showCCTVData(HomecctvListBean homecctvListBean) {
        cctvList.clear();
        cctvList.addAll(homecctvListBean.getList());
        homeAdapter.notifyDataSetChanged();
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
    public void setPresenter(HomeContract.Presenter presenter) {
        this.presenter = presenter;
    }


    @Override
    public void getbannerClick(String url) {

    }

    @Override
    public void getareaClick(PandaHomeBean.DataBean.AreaBean.ListscrollBean listscrollBean) {

        ToActivity.loadPlay(listscrollBean.getPid(),listscrollBean.getTitle(),listscrollBean.getImage(),listscrollBean.getVideoLength());

    }

    @Override
    public void getpandaeyeClicks(PandaHomeBean.DataBean.PandaeyeBean.ItemsBean itemsBean) {
        ToActivity.loadWeb(itemsBean.getUrl());
    }
    @Override
    public void getInteractiveClick(PandaHomeBean.DataBean.InteractiveBean.InteractiveoneBean interactiveoneBean) {

        ToActivity.loadInteractive(interactiveoneBean.getUrl(),interactiveoneBean.getTitle());

    }

    @Override
    public void getLightClick(HomelightListBean.ListBean listBean) {
        ToActivity.loadPlay(listBean.getPid(),listBean.getTitle(),listBean.getImage(),listBean.getVideoLength());
    }

    //获取当前版本
    protected void initData() {
        getAppVersionName(getActivity());
    }

    public static String getAppVersionName(Context context) {
        String versionName = "";

        try {
            // ---get the package info---
            PackageManager pm = context.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
            versionName = pi.versionName;
            //versioncode = pi.versionCode;
            versionCode = pi.versionCode;
            MyLog.d("TAG", versionCode + "");
            if (versionName == null || versionName.length() <= 0) {
                return "";
            }
        } catch (Exception e) {
           MyLog.d("TAG", versionName);
        }
        return versionName;

    }

    //发送网络请求获取最新版本号

    public void getVersion() {
        presenter.getversion();
    }

    @Override
    public void getVersionData(UpdateBean updateBean) {
        String versionsNum = updateBean.getData().getVersionsNum();
        versionsUrl = updateBean.getData().getVersionsUrl();
        int versionsInt = Integer.parseInt(versionsNum);
        if (versionCode < versionsInt) {
            showDialogUpdate();
        } else {
            Toast.makeText(getActivity(), "已经是最新版本", Toast.LENGTH_LONG).show();
        }

    }

    //提示更新
    private void showDialogUpdate() {
        // 这里的属性可以一直设置，因为每次设置后返回的是一个builder对象
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // 设置提示框的标题
        builder.setTitle("版本升级").
                // 设置要显示的信息
                        setMessage("发现新版本  修复了已知的BUG").
                // 设置确定按钮
                        setPositiveButton("确定", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Toast.makeText(MainActivity.this, "选择确定哦", 0).show();
                        dialog.dismiss();
                        loadNewVersionProgress();//下载最新的版本程序

                    }
                }).

                // 设置取消按钮,null是什么都不做，并关闭对话框
                        setNegativeButton("取消", null);

        // 生产对话框
        alertDialog = builder.create();
        // 显示对话框
        alertDialog.show();


    }


    //     下载新版本程序
    private void loadNewVersionProgress() {
        final String uri = versionsUrl;
        final ProgressDialog pd;    //进度条对话框
        pd = new ProgressDialog(getActivity());
        pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        pd.setMessage("正在下载更新");
        pd.show();
        //启动子线程下载任务
        new Thread() {
            @Override
            public void run() {
                try {
                    File file = getFileFromServer(uri, pd);
                    sleep(3000);
                    installApk(file);
                    pd.dismiss(); //结束掉进度条对话框
                } catch (Exception e) {
                    //下载apk失败
                    MyLog.d("TAG", "下载失败");
//                    Toast.makeText(getActivity(), "下载新版本失败", Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            }
        }.start();
    }

    //从服务器获取apk文件的代码

    public File getFileFromServer(String uri, final ProgressDialog pd) throws Exception {
        //如果相等的话表示当前的sdcard挂载在手机上并且是可用的
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            URL url = new URL(uri);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(5000);
            //获取到文件的大小
            pd.setMax(conn.getContentLength() / 1000);
            InputStream is = conn.getInputStream();
            long time = System.currentTimeMillis();//当前时间的毫秒数
            File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), time + "updata.apk");
            if (!file.exists())
                file.createNewFile();
            FileOutputStream fos = new FileOutputStream(file);
            BufferedInputStream bis = new BufferedInputStream(is);
            byte[] buffer = new byte[1024];
            int len;

            while ((len = bis.read(buffer)) != -1) {
                fos.write(buffer, 0, len);
                total += len;
                //获取当前下载量
                pd.setProgress(total / 1000);
            }
            fos.close();
            bis.close();
            is.close();
            return file;
        } else {
            return null;
        }
    }

    //安装Apk
    protected void installApk(File file) {
        Intent intent = new Intent();
        //执行动作
        intent.setAction(Intent.ACTION_VIEW);
        //执行的数据类型
        intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
        startActivity(intent);
    }
}

