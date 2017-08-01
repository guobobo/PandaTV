package com.demo.jiyun.pandatv.module.interactive;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.demo.jiyun.pandatv.R;
import com.demo.jiyun.pandatv.base.BaseActivity;
import com.demo.jiyun.pandatv.model.entity.InteractivesBean;
import com.demo.jiyun.pandatv.utils.CustomDialog;
import com.demo.jiyun.pandatv.utils.ToActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class InteractiveActivity extends BaseActivity implements InteractiveContarct.View {

    @BindView(R.id.iteractive_backImage)
    ImageView iteractiveBackImage;
    @BindView(R.id.interactive_recy)
    RecyclerView interactiveRecy;
    private InteractiveContarct.Presenter presenter;

    private InteractiveAdapter interactiveAdapter;
    private List<InteractivesBean.InteractiveBean> listBean;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_interactive;
    }

    @Override
    protected void init() {
        new InteractivePresenter(this);
        presenter.start();
        listBean=new ArrayList<>();
        interactiveAdapter=new InteractiveAdapter(this,listBean);
        interactiveRecy.setLayoutManager(new LinearLayoutManager(this));
        interactiveRecy.setAdapter(interactiveAdapter);
        interactiveAdapter.setOnWebClick(new InteractiveAdapter.OnWebClick() {
            @Override
            public void onClickLiner(int position) {
                ToActivity.loadInteractive(
                        listBean.get(position).getUrl(),
                        listBean.get(position).getTitle());
            }
        });
    }

    @Override
    public void showProgress() {
        CustomDialog.show(this);
    }

    @Override
    public void dimissProgress() {
        CustomDialog.dimiss();
    }

    @Override
    public void showMessage(String msg) {

    }

    @Override
    public void setPresenter(InteractiveContarct.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void showInteracitveData(InteractivesBean interactivesBeanList) {
        listBean.clear();
        listBean.addAll(interactivesBeanList.getInteractive());
        interactiveAdapter.notifyDataSetChanged();
    }

    @OnClick(R.id.iteractive_backImage)
    public void onViewClicked() {
        finish();
    }
}
