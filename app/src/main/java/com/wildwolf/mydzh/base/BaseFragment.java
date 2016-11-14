package com.wildwolf.mydzh.base;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.wildwolf.mydzh.R;

import butterknife.ButterKnife;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by ${wild00wolf} on 2016/11/14.
 */
public class BaseFragment  extends Fragment{
    public Activity mActivity;


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this,view);
        mActivity = getActivity();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        onUnsubscribe();

    }

    public Toolbar initToolBar(View view, String title) {
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        toolbar.setTitle(title);
        return toolbar;
    }


    private CompositeSubscription compositeSubscription;
    private void onUnsubscribe() {
        if (compositeSubscription != null){
            compositeSubscription.unsubscribe();
        }
    }

    public void toastShow(String resId) {
        Toast.makeText(mActivity, resId, Toast.LENGTH_SHORT).show();
    }

    public ProgressDialog progressDialog;

    public ProgressDialog showProgressDialog() {
        progressDialog = new ProgressDialog(mActivity);
        progressDialog.setMessage("加载中");
        progressDialog.show();
        return progressDialog;
    }

//    public ProgressDialog showProgressDialog(CharSequence message) {
//        progressDialog = new ProgressDialog(mActivity);
//        progressDialog.setMessage(message);
//        progressDialog.show();
//        return progressDialog;
//    }

    public void dismissProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            // progressDialog.hide();会导致android.view.WindowLeaked
            progressDialog.dismiss();
        }
    }
}
