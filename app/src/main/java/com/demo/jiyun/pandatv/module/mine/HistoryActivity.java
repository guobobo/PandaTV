package com.demo.jiyun.pandatv.module.mine;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.demo.jiyun.pandatv.R;
import com.demo.jiyun.pandatv.base.BaseActivity;
import com.demo.jiyun.pandatv.model.db.History;
import com.demo.jiyun.pandatv.module.mine.adapter.HistoryAdapter;
import com.demo.jiyun.pandatv.utils.DBUtils;
import com.demo.jiyun.pandatv.utils.ToActivity;

import java.util.ArrayList;
import java.util.List;

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

    private List<History> historyList;
    private DBUtils instance;
    private HistoryAdapter adapter;
    private int number=0;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_history;
    }

    @Override
    protected void init() {
        instance = DBUtils.getInstance(this);
        historyList=new ArrayList<>();
        List<History> historyquery = DBUtils.getInstance(this).history();
        historyList.clear();
        historyList.addAll(historyquery);
        adapter=new HistoryAdapter(this,historyList);
        historyListview.setLayoutManager(new LinearLayoutManager(this));
        historyListview.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        adapter.setOnHistoryLinear(new HistoryAdapter.OnHistoryLinear() {
            @Override
            public void onHistory(int position) {
                History history = historyList.get(position);
                if(historyEditBtn.getText().toString().equals("取消")){
                    if (historyList.get(position).getFlag() == false) {
                        history.setFlag(true);
                        instance.update(history);
                        number++;
                        historyItemDeleteBtn.setText("删除"+number);
                    } else {
                        history.setFlag(false);
                        instance.update(history);
                        number--;
                        historyItemDeleteBtn.setText("删除"+number);
                    }
                    if (number == 0) {
                        historyItemDeleteBtn.setText("删除");
                    }
                    if(number==historyList.size()){
                        historyItemChooseBtn.setText("取消全选");
                    }else {
                        historyItemChooseBtn.setText("全选");
                    }
                }else {
                    ToActivity.loadPlay(
                            historyList.get(position).getUrl(),
                            historyList.get(position).getTitle(),
                            historyList.get(position).getImage(),
                            historyList.get(position).getDuration());
                }

                adapter.notifyDataSetChanged();
            }
        });
    }



    @OnClick({R.id.history_backImage, R.id.history_editBtn, R.id.history_item_chooseBtn, R.id.history_item_deleteBtn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.history_backImage:
                finish();
                for (int i = 0; i < historyList.size(); i++) {
                    History history = historyList.get(i);
                    history.setVisibility(false);
                    instance.update(history);
                }
                for (int i = 0; i < historyList.size(); i++) {
                    History history = historyList.get(i);
                    history.setFlag(false);
                    instance.update(history);
                }
                historyEditBtn.setText("编辑");
                linerChooseAnddelete.setVisibility(View.GONE);
                adapter.notifyDataSetChanged();
                break;
            case R.id.history_editBtn:
                if(historyEditBtn.getText().toString().equals("编辑")){
                    for (int i = 0; i < historyList.size(); i++) {
                        History history = historyList.get(i);
                        history.setVisibility(true);
                        instance.update(history);
                    }
                    historyEditBtn.setText("取消");
                    linerChooseAnddelete.setVisibility(View.VISIBLE);
                    adapter.notifyDataSetChanged();
                }else {
                    for (int i = 0; i < historyList.size(); i++) {
                        History history = historyList.get(i);
                        history.setVisibility(false);
                        instance.update(history);
                    }
                    for (int i = 0; i < historyList.size(); i++) {
                        History history = historyList.get(i);
                        history.setFlag(false);
                        instance.update(history);
                    }
                    number = 0;
                    historyEditBtn.setText("编辑");
                    historyItemDeleteBtn.setText("删除");
                    historyItemChooseBtn.setText("全选");
                    linerChooseAnddelete.setVisibility(View.GONE);
                    adapter.notifyDataSetChanged();
                }
                break;
            case R.id.history_item_chooseBtn:
                if (historyItemChooseBtn.getText().equals("全选")) {
                    historyItemChooseBtn.setText("取消全选");
                    if (historyEditBtn.getText().equals("取消")) {
                        for (int i = 0; i < historyList.size(); i++) {
                            History history = historyList.get(i);
                            history.setFlag(true);
                            instance.update(history);
                        }
                        number = historyList.size();
                        historyItemDeleteBtn.setText("删除" + number);
                        adapter.notifyDataSetChanged();
                    }
                } else {
                    for (int i = 0; i < historyList.size(); i++) {
                        History history = historyList.get(i);
                        history.setFlag(false);
                        instance.update(history);
                    }
                    number = 0;
                    historyItemDeleteBtn.setText("删除");
                    historyItemChooseBtn.setText("全选");
                    adapter.notifyDataSetChanged();
                }
                break;
            case R.id.history_item_deleteBtn:
                for (int i = historyList.size() - 1; i >= 0; i--) {
                    if (historyList.get(i).getFlag()) {
                        History history = historyList.get(i);
                        instance.delete(history);
                        historyList.remove(historyList.get(i));
                    }
                }
                number = 0;
                adapter.notifyDataSetChanged();
                historyItemDeleteBtn.setText("删除");
                if (historyList.size() == 0) {
                    historyEditBtn.setVisibility(View.GONE);
                    linerChooseAnddelete.setVisibility(View.GONE);
                }
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        for (int i = 0; i < historyList.size(); i++) {
            History history = historyList.get(i);
            history.setVisibility(false);
            instance.update(history);
        }
        for (int i = 0; i < historyList.size(); i++) {
            History history = historyList.get(i);
            history.setFlag(false);
            instance.update(history);
        }
        historyEditBtn.setText("编辑");
        linerChooseAnddelete.setVisibility(View.GONE);
        adapter.notifyDataSetChanged();
    }
}
