package com.asg.android.poc.room.Util;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;

import com.asg.android.poc.room.R;


public class CommonUtils {

    private CommonUtils() {
    }

    public static ProgressDialog showLoadingDialog(Context context) {
        ProgressDialog pd = new ProgressDialog(context);
        if (pd.getWindow() != null) {
            pd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
        pd.setContentView(R.layout.progress_dialog);
        pd.setIndeterminate(true);
        pd.setCancelable(false);
        pd.setCanceledOnTouchOutside(false);
        pd.show();
        return pd;
    }
}
