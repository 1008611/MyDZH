package com.wildwolf.mydzh.base;

import android.app.Activity;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;

import com.wildwolf.mydzh.utils.LogUtil;

import java.util.List;

import butterknife.ButterKnife;
import retrofit2.Call;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by ${wild00wolf} on 2016/11/11.
 */
public class BaseActivity extends AppCompatActivity {
    private Activity mActivity;
    private CompositeSubscription mCompositeSubscription;
    private List<Call> calls;


    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);
        mActivity = this;
    }

    @Override
    protected void onDestroy() {
        callCancel();
        onUnsubscribe();
        super.onDestroy();


    }

    private void onUnsubscribe() {
        LogUtil.d("onUnsubscribe");
        //
        if (mCompositeSubscription != null&& mCompositeSubscription.hasSubscriptions()){
            mCompositeSubscription.unsubscribe();
        }
    }

    private void callCancel() {
        if (calls!= null&& calls.size()>0){
            for (Call call:calls){
                if (!call.isCanceled()){
                    call.cancel();
                }
            }
            calls.clear();
        }
    }
}
