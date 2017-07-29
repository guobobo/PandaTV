package com.demo.jiyun.pandatv.module.pandalive;


import android.graphics.Color;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.demo.jiyun.pandatv.R;
import com.demo.jiyun.pandatv.base.BaseFragment;
import com.demo.jiyun.pandatv.model.entity.LookchatBean;
import com.demo.jiyun.pandatv.model.entity.PandaLiveBean;
import com.demo.jiyun.pandatv.model.entity.PandaMultipleBean;
import com.demo.jiyun.pandatv.utils.ToastManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class LiveOnFragment extends BaseFragment implements LiveOnContarct.View {


    @BindView(R.id.live_image)
    ImageView liveImage;
    @BindView(R.id.live_title)
    TextView liveTitle;
    @BindView(R.id.live_checkbox)
    CheckBox liveCheckbox;
    @BindView(R.id.live_brief)
    TextView liveBrief;
    @BindView(R.id.live_linear)
    LinearLayout liveLinear;
    @BindView(R.id.duo)
    RadioButton duo;
    @BindView(R.id.bian)
    RadioButton bian;
    @BindView(R.id.live_recy)
    RecyclerView liveRecy;
    @BindView(R.id.linear_duo)
    LinearLayout linearDuo;
    @BindView(R.id.bian_edit)
    EditText bianEdit;
    @BindView(R.id.live_recy1)
    RecyclerView liveRecy1;
    @BindView(R.id.linear_bian)
    LinearLayout linearBian;

    private List<PandaMultipleBean.ListBean> multipleBeen;
    LiveAdapterRecy adapterRecy;

    private LiveOnContarct.Presenter presenter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_liveon;
    }

    @Override
    protected void init(View view) {
        new LiveOnPresenter(this);
    }

    @Override
    protected void loadData() {
        presenter.start();
        presenter.multiple();
        presenter.lookchat();
    }

    @Override
    public void setMultiple(final PandaMultipleBean pandaMultipleBean) {


        Glide.with(getContext()).load(pandaMultipleBean.getList().get(0).getImage()).asBitmap().into(liveImage);
        multipleBeen = new ArrayList<>();
        for (int i = 0; i < pandaMultipleBean.getList().size(); i++) {
            PandaMultipleBean.ListBean listBean = pandaMultipleBean.getList().get(i);
            multipleBeen.add(listBean);
        }
        adapterRecy = new LiveAdapterRecy(getContext(), multipleBeen);
        liveRecy.setLayoutManager(new GridLayoutManager(getContext(), 3));
        liveRecy.setAdapter(adapterRecy);

        adapterRecy.setMyClickListener(new LiveAdapterRecy.MyClickListener() {
            @Override
            public void onItemClick(int postion) {
                String title = pandaMultipleBean.getList().get(postion).getTitle();
                String image = pandaMultipleBean.getList().get(postion).getImage();
                liveTitle.setText(title);
                Glide.with(getContext()).load(image).asBitmap().into(liveImage);
            }
        });


    }

    @Override
    public void setResult(PandaLiveBean pandaLiveBean) {


        liveTitle.setText(pandaLiveBean.getLive().get(0).getTitle());
        liveBrief.setText(pandaLiveBean.getLive().get(0).getBrief());

    }

    @Override
    public void setMessage(String msg) {
        ToastManager.show(msg);
    }

    @Override
    public void setLookchat(LookchatBean lookchatBean) {


        ArrayList<LookchatBean.DataBean.ContentBean> contentBeen=new ArrayList<>();
        contentBeen.addAll(lookchatBean.getData().getContent());
        LookchatAdapterRecy lookchatAdapter=new LookchatAdapterRecy(getContext(),contentBeen);
        liveRecy1.setLayoutManager(new LinearLayoutManager(getContext()));
        liveRecy1.setAdapter(lookchatAdapter);
    }

    @OnClick({R.id.live_checkbox, R.id.duo, R.id.bian})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.live_checkbox:
                boolean checked = liveCheckbox.isChecked();
                if (checked) {
                    liveCheckbox.setChecked(true);
                    liveLinear.setVisibility(View.VISIBLE);
                } else {
                    liveCheckbox.setChecked(false);
                    liveLinear.setVisibility(View.GONE);
                }
                break;
            case R.id.duo:
                linearDuo.setVisibility(View.VISIBLE);
                linearBian.setVisibility(View.GONE);
                bian.setTextColor(Color.BLACK);
                duo.setTextColor(Color.BLUE);
                break;
            case R.id.bian:
                linearBian.setVisibility(View.VISIBLE);
                linearDuo.setVisibility(View.GONE);
                duo.setTextColor(Color.BLACK);
                bian.setTextColor(Color.BLUE);
                break;
        }

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void dimissProgress() {

    }

    @Override
    public void showMessage(String msg) {

    }

    @Override
    public void setPresenter(LiveOnContarct.Presenter presenter) {

        this.presenter = presenter;

    }
}
