package com.wildwolf.mydzh.ui.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.wildwolf.mydzh.R;
import com.wildwolf.mydzh.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by ${wild00wolf} on 2016/11/14.
 */
public class WebViewActivity extends BaseActivity {

    @Bind(R.id.webView)
    WebView webView;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    private String urlPath;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        toolbar.setTitle("视频详情");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        urlPath = getIntent().getStringExtra("url");
        setSettings(webView.getSettings());
        webView.loadUrl(urlPath);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                showProgressDialog();
                view.loadUrl(urlPath);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                dismissProgressDialog();
            }
        });
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onReceivedTitle(WebView view, String title) {
                toolbar.setTitle(title);
                setSupportActionBar(toolbar);
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);

            }
        });
    }

    private void setSettings(WebSettings settings) {
        settings.setJavaScriptEnabled(true);
        settings.setBuiltInZoomControls(true);
        settings.setDatabaseEnabled(false);
        settings.setSupportZoom(true);

        settings.setDomStorageEnabled(true);
        settings.setDatabaseEnabled(true);

        settings.setLoadWithOverviewMode(true);
        settings.setUseWideViewPort(true);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK){
            if (webView.canGoBack()){
                webView.goBack();
                return true;
            }else {
                finish();
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onPause() {
        super.onPause();
        webView.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        webView.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        webView.destroy();
    }
}
