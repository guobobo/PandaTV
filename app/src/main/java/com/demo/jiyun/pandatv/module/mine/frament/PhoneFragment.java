package com.demo.jiyun.pandatv.module.mine.frament;

import android.graphics.Bitmap;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.demo.jiyun.pandatv.R;
import com.demo.jiyun.pandatv.base.BaseFragment;
import com.demo.jiyun.pandatv.utils.ToastManager;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by iu on 2017/7/31.
 */

public class PhoneFragment extends BaseFragment implements PhoneContract.View {

    @BindView(R.id.phonefragmentName)
    EditText phonefragmentName;
    @BindView(R.id.hint_phone)
    TextView hintPhone;
    @BindView(R.id.phonefragment_Iamge_edit_Yanzhengma)
    EditText phonefragmentIamgeEditYanzhengma;
    @BindView(R.id.phonefragment_net_yanzhengma)
    ImageView phonefragmentNetYanzhengma;
    @BindView(R.id.hint_imagyanzhengma)
    TextView hintImagyanzhengma;
    @BindView(R.id.phonefragment_duanxin_edit_Yanzhengma)
    EditText phonefragmentDuanxinEditYanzhengma;
    @BindView(R.id.phonefragment_btn_Yanzhengma)
    Button phonefragmentBtnYanzhengma;
    @BindView(R.id.hint_duanxin)
    TextView hintDuanxin;
    @BindView(R.id.phonefragmentpassward)
    EditText phonefragmentpassward;
    @BindView(R.id.hint_password)
    TextView hintPassword;
    @BindView(R.id.phonefragment_check)
    CheckBox phonefragmentCheck;
    @BindView(R.id.phonefragment_btn_registered)
    Button phonefragmentBtnRegistered;
    private PhoneContract.Presenter presenter;

    @Override
    protected int getLayoutId() {
        return R.layout.registered_phonefragment;
    }

    @Override
    protected void init(View view) {

        new PhonePresenter(this);

    }

    @Override
    protected void loadData() {
        presenter.start();
    }

    @Override
    public void showNumberTips(String msg) {

    }

    @Override
    public void hideNumberTips() {

    }

    @Override
    public void showPwdTips(String msg) {

    }

    @Override
    public void hidePwdTips() {

    }

    @Override
    public void showImgCode(Bitmap bitmap) {


        phonefragmentNetYanzhengma.setImageBitmap(bitmap);
    }

    @Override
    public void showPhoneCode(String code) {

    }

    @Override
    public void showYzmTips(String msg) {

    }

    @Override
    public void hideYzmTips() {

    }

    @Override
    public void toLogin() {

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void dimissProgress() {

    }

    @Override
    public void showMessage(String msg) {
        ToastManager.show(msg);
    }

    @Override
    public void setPresenter(PhoneContract.Presenter presenter) {

        this.presenter = presenter;

    }


    @OnClick({R.id.phonefragment_net_yanzhengma, R.id.phonefragment_btn_Yanzhengma, R.id.phonefragment_btn_registered})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.phonefragment_net_yanzhengma:
                presenter.start();
                break;
            case R.id.phonefragment_btn_Yanzhengma:
                presenter.getPhoneCode(phonefragmentName.getText().toString().trim(),
                        phonefragmentIamgeEditYanzhengma.getText().toString().trim());
                break;
            case R.id.phonefragment_btn_registered:
                break;
        }
    }
}
