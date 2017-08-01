package com.demo.jiyun.pandatv.module.mine;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.demo.jiyun.pandatv.R;
import com.demo.jiyun.pandatv.base.BaseActivity;
import com.demo.jiyun.pandatv.model.db.Keep;
import com.demo.jiyun.pandatv.module.mine.adapter.CollectionAdapter;
import com.demo.jiyun.pandatv.utils.DBUtils;
import com.demo.jiyun.pandatv.utils.ToActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class CollectionActivity extends BaseActivity {

    @BindView(R.id.login_backImage)
    ImageView loginBackImage;
    @BindView(R.id.collection_edit)
    TextView collectionEdit;
    @BindView(R.id.activity_collection)
    LinearLayout activityCollection;
    @BindView(R.id.collection_recy)
    RecyclerView collectionRecy;
    @BindView(R.id.history_item_chooseBtn)
    TextView historyItemChooseBtn;
    @BindView(R.id.history_item_deleteBtn)
    TextView historyItemDeleteBtn;
    @BindView(R.id.liner_chooseAnddelete)
    LinearLayout linerChooseAnddelete;

    private CollectionAdapter adapter;
    private List<Keep> keepList;
    private int number = 0;
    private DBUtils instance;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_collection;
    }

    @Override
    protected void init() {
        instance = DBUtils.getInstance(this);
        keepList=new ArrayList<>();
        List<Keep> collection = DBUtils.getInstance(this).collection();
        keepList.clear();
        keepList.addAll(collection);
        adapter=new CollectionAdapter(this,keepList);
        collectionRecy.setLayoutManager(new LinearLayoutManager(this));
        collectionRecy.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        adapter.setOnCollection(new CollectionAdapter.OnCollectionClickLinear() {
            @Override
            public void onCollection(int position) {
                Keep keep = keepList.get(position);
                if(collectionEdit.getText().toString().equals("取消")){
                    if (keepList.get(position).getFlag() == false) {
                        keep.setFlag(true);
                        instance.update(keep);
                        number++;
                        historyItemDeleteBtn.setText("删除"+number);
                    } else {
                        keep.setFlag(false);
                        instance.update(keep);
                        number--;
                        historyItemDeleteBtn.setText("删除"+number);
                    }
                    if (number == 0) {
                        historyItemDeleteBtn.setText("删除");
                    }
                    if(number==keepList.size()){
                        historyItemChooseBtn.setText("取消全选");
                    }else {
                        historyItemChooseBtn.setText("全选");
                    }
                }else {
                    ToActivity.loadPlay(
                            keepList.get(position).getUrl(),
                            keepList.get(position).getTitle(),
                            keepList.get(position).getImage(),
                            keepList.get(position).getDuration());
                }

                adapter.notifyDataSetChanged();
            }
        });


    }

    @OnClick({R.id.login_backImage, R.id.collection_edit,R.id.history_item_chooseBtn, R.id.history_item_deleteBtn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login_backImage:
                finish();
                for (int i = 0; i < keepList.size(); i++) {
                    Keep keep = keepList.get(i);
                    keep.setVisibility(false);
                    instance.update(keep);
                }
                for (int i = 0; i < keepList.size(); i++) {
                    Keep keep = keepList.get(i);
                    keep.setFlag(false);
                    instance.update(keep);
                }
                collectionEdit.setText("编辑");
                linerChooseAnddelete.setVisibility(View.GONE);
                adapter.notifyDataSetChanged();
                break;
            case R.id.collection_edit:
                if(collectionEdit.getText().toString().equals("编辑")){
                    for (int i = 0; i < keepList.size(); i++) {
                        Keep keep = keepList.get(i);
                        keep.setVisibility(true);
                        instance.update(keep);
                    }
                    collectionEdit.setText("取消");
                    linerChooseAnddelete.setVisibility(View.VISIBLE);
                    adapter.notifyDataSetChanged();
                }else {
                    for (int i = 0; i < keepList.size(); i++) {
                        Keep keep = keepList.get(i);
                        keep.setVisibility(false);
                        instance.update(keep);
                    }
                    for (int i = 0; i < keepList.size(); i++) {
                        Keep keep = keepList.get(i);
                        keep.setFlag(false);
                        instance.update(keep);
                    }
                    number = 0;
                    collectionEdit.setText("编辑");
                    historyItemDeleteBtn.setText("删除");
                    historyItemChooseBtn.setText("全选");
                    linerChooseAnddelete.setVisibility(View.GONE);
                    adapter.notifyDataSetChanged();
                }
                break;
            case R.id.history_item_chooseBtn:
                if (historyItemChooseBtn.getText().equals("全选")) {
                    historyItemChooseBtn.setText("取消全选");
                    if (collectionEdit.getText().equals("取消")) {
                        for (int i = 0; i < keepList.size(); i++) {
                            Keep keep = keepList.get(i);
                            keep.setFlag(true);
                            instance.update(keep);
                        }
                        number = keepList.size();
                        historyItemDeleteBtn.setText("删除" + number);
                        adapter.notifyDataSetChanged();
                    }
                } else {
                    for (int i = 0; i < keepList.size(); i++) {
                        Keep keep = keepList.get(i);
                        keep.setFlag(false);
                        instance.update(keep);
                    }
                    number = 0;
                    historyItemDeleteBtn.setText("删除");
                    historyItemChooseBtn.setText("全选");
                    adapter.notifyDataSetChanged();
                }
                break;
            case R.id.history_item_deleteBtn:
                for (int i = keepList.size() - 1; i >= 0; i--) {
                    if (keepList.get(i).getFlag()) {
                        Keep keep = keepList.get(i);
                        instance.delete(keep);
                        keepList.remove(keepList.get(i));
                    }
                }

                number = 0;
                adapter.notifyDataSetChanged();
                historyItemDeleteBtn.setText("删除");
                if (keepList.size() == 0) {
                    collectionEdit.setVisibility(View.GONE);
                    linerChooseAnddelete.setVisibility(View.GONE);
                }
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        for (int i = 0; i < keepList.size(); i++) {
            Keep keep = keepList.get(i);
            keep.setVisibility(false);
            instance.update(keep);
        }
        for (int i = 0; i < keepList.size(); i++) {
            Keep keep = keepList.get(i);
            keep.setFlag(false);
            instance.update(keep);
        }
        collectionEdit.setText("编辑");
        linerChooseAnddelete.setVisibility(View.GONE);
        adapter.notifyDataSetChanged();
    }
}
