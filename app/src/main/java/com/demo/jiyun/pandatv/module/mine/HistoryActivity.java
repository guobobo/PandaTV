package com.demo.jiyun.pandatv.module.mine;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.demo.jiyun.pandatv.R;
import com.demo.jiyun.pandatv.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class HistoryActivity extends BaseActivity {


    @BindView(R.id.history_backImage)
    ImageView historyBackImage;
    @BindView(R.id.history_editBtn)
    TextView historyEditBtn;
    @BindView(R.id.history_listview)
    RecyclerView historyListview;
    @BindView(R.id.history_item_chooseBtn)
    TextView historyItemChooseBtn;
    @BindView(R.id.history_item_deleteBtn)
    TextView historyItemDeleteBtn;
    @BindView(R.id.liner_chooseAnddelete)
    LinearLayout linerChooseAnddelete;
    @BindView(R.id.activity_history)
    LinearLayout activityHistory;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_history;
    }

    @Override
    protected void init() {

    }



    @OnClick({R.id.history_backImage, R.id.history_editBtn, R.id.history_listview, R.id.history_item_chooseBtn, R.id.history_item_deleteBtn, R.id.liner_chooseAnddelete, R.id.activity_history})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.history_backImage:
                finish();
                break;
            case R.id.history_editBtn:
                break;
            case R.id.history_listview:
                break;
            case R.id.history_item_chooseBtn:
                break;
            case R.id.history_item_deleteBtn:
                break;
            case R.id.liner_chooseAnddelete:
                break;
            case R.id.activity_history:
                break;
        }
    }
}
