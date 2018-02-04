package com.asg.android.poc.room.presentation.base;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.asg.android.poc.room.R;
import com.asg.android.poc.room.Util.CommonUtils;
import com.asg.android.poc.room.Util.NetworkUtils;

import butterknife.ButterKnife;

/**
 * Created by AGularia on 08/01/18.
 */

public abstract class BaseFragment extends Fragment implements MvpView {

    private static final String TAG = BaseActivity.class.getSimpleName();
    private ProgressDialog progressDialog;
    protected MvpPresenter presenter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = bindView(inflater,container);
        ButterKnife.bind(this, root);

        //set presenter for this
        presenter = bindPresenter();
        presenter.onAttach(this);

        return root;
    }

    protected abstract View bindView(LayoutInflater inflater, ViewGroup container);
    protected abstract MvpPresenter bindPresenter();


    @Override
    public void showSnackBar(String message) {
        Snackbar snackbar = Snackbar.make(getActivity().findViewById(android.R.id.content),
                message, Snackbar.LENGTH_SHORT);
        View view = snackbar.getView();
        TextView textView = (TextView) view.findViewById(android.support.design.R.id.snackbar_text);
        view.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.colorPrimary));
        textView.setTextColor(ContextCompat.getColor(getActivity(), R.color.white));
        textView.setGravity(Gravity.CENTER);
        snackbar.show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }

    @Override
    public void showLoading() {
        hideLoading();
        progressDialog = CommonUtils.showLoadingDialog(getActivity());
    }

    @Override
    public void hideLoading() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.cancel();
        }
    }

    @Override
    public void onError(int resId) {
        onError(getString(resId));
    }

    @Override
    public void onError(String message) {
        if (message != null) {
            showSnackBar(message);
        } else {
            showSnackBar("Undefined error!");
        }
    }

    @Override
    public void showMessage(String message) {
        Log.i(TAG, "Message = "+message);
    }

    @Override
    public void showMessage(@StringRes int resId) {
        Log.i(TAG, "Message = "+getString(resId));
    }

    @Override
    public boolean isNetworkConnected() {
        return NetworkUtils.isNetworkConnected(getActivity().getApplicationContext());
    }

}
