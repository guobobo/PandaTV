package com.demo.jiyun.pandatv.utils;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;

import com.demo.jiyun.pandatv.R;

/**
 * Created by iu on 2017/7/28.
 */

public class CustomDialog extends Dialog {

    private static Dialog dialog;

    public CustomDialog(Context context) {
        super(context);
    }

    public CustomDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    public static void show(Context context){

        dialog = new CustomDialog(context, R.style.CustomDialog);

        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setContentView(R.layout.customdialog);
        dialog.getWindow().getAttributes().gravity = Gravity.CENTER;
        dialog.show();

    }

    public static void dimiss(){
//        if (dialog != null && dialog.isShowing())
            dialog.dismiss();
    }
}
