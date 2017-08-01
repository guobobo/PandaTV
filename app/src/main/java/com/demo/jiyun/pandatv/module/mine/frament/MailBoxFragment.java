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
import com.demo.jiyun.pandatv.module.mine.LoginActivity;
import com.demo.jiyun.pandatv.utils.CustomDialog;
import com.demo.jiyun.pandatv.utils.ToActivity;
import com.demo.jiyun.pandatv.utils.ToastManager;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by iu on 2017/7/31.
 */

public class MailBoxFragment extends BaseFragment implements MailBoxContract.View {

    @BindView(R.id.et_email)
    EditText etEmail;
    @BindView(R.id.hint_email)
    TextView hintEmail;
    @BindView(R.id.et_emailpsw)
    EditText etEmailpsw;
    @BindView(R.id.et_emailquerenpsw)
    EditText etEmailquerenpsw;
    @BindView(R.id.et_emailpictureyanzheng)
    EditText etEmailpictureyanzheng;
    @BindView(R.id.email_image)
    ImageView emailImage;
    @BindView(R.id.mailboxfragment_check)
    CheckBox mailboxfragmentCheck;
    @BindView(R.id.mailboxfragment_btn_registered)
    Button mailboxfragmentBtnRegistered;
    @BindView(R.id.hint_pwd)
    TextView hintPwd;
    @BindView(R.id.hint_yanzhengma)
    TextView hintYanzhengma;
    private MailBoxContract.Presenter presenter;

    @Override
    protected int getLayoutId() {
        return R.layout.registered_mailboxfragment;
    }

    @Override
    protected void init(View view) {
        new MailBoxPresenter(this);

    }

    @Override
    protected void loadData() {
        presenter.start();
    }


    @Override
    public void showEmailTips(String msg) {
        hintEmail.setVisibility(View.VISIBLE);
        hintEmail.setText(msg);
    }

    @Override
    public void hideEmailTips() {
        hintEmail.setVisibility(View.GONE);
    }

    @Override
    public void showPwdTips(String msg) {

        hintPwd.setVisibility(View.VISIBLE);
        hintPwd.setText(msg);
    }

    @Override
    public void hidePwdTips() {
        hintPwd.setVisibility(View.GONE);
    }

    @Override
    public void showImgCode(Bitmap bitmap) {

        emailImage.setImageBitmap(bitmap);
    }

    @Override
    public void toLogin() {
        ToActivity.load(LoginActivity.class);
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
    public void setPresenter(MailBoxContract.Presenter presenter) {

        this.presenter = presenter;
    }

    @OnClick({R.id.email_image, R.id.mailboxfragment_btn_registered})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.email_image:
                presenter.start();
                break;
            case R.id.mailboxfragment_btn_registered:
                presenter.register(etEmail.getText().toString().trim(),
                        etEmailpsw.getText().toString().trim(),
                        etEmailpictureyanzheng.getText().toString().trim());
                break;
        }
    }

}
