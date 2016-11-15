package com.wildwolf.mydzh.ui.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.wildwolf.mydzh.R;
import com.wildwolf.mydzh.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by ${wild00wolf} on 2016/11/15.
 */
public class AboutActivity extends AppCompatActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.tv_github)
    TextView tvGithub;
    @Bind(R.id.tv_jianshu)
    TextView tvJianshu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_about);

        ButterKnife.bind(this);
        toolbar.setTitle("一个。。。");
        setSupportActionBar(toolbar);
    }

    @OnClick({R.id.tv_github, R.id.tv_jianshu})
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_github:
                Intent it1 = new Intent(Intent.ACTION_VIEW, Uri.parse(tvGithub.getText().toString()));
                it1.setClassName("com.android.browser", "com.android.browser.BrowserActivity");
                startActivity(it1);
                break;
            case R.id.tv_jianshu:
                Intent it2 = new Intent(Intent.ACTION_VIEW, Uri.parse(tvJianshu.getText().toString()));
                it2.setClassName("com.android.browser", "com.android.browser.BrowserActivity");
                startActivity(it2);
                break;
        }
    }
}
