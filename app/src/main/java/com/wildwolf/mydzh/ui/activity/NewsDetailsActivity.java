package com.wildwolf.mydzh.ui.activity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Message;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.wildwolf.mydzh.R;
import com.wildwolf.mydzh.model.NewsDetailsEntity;
import com.wildwolf.mydzh.presenter.NewsDetailsPresenter;
import com.wildwolf.mydzh.view.NewsDetailsView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by ${wild00wolf} on 2016/11/15.
 */
public class NewsDetailsActivity extends BaseMvpActivity<NewsDetailsPresenter> implements NewsDetailsView {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    private String id;
    @Bind(R.id.web_view)
    WebView webView;
    @Bind(R.id.iv_web_img)
    ImageView webImg;
    @Bind(R.id.tv_img_title)
    TextView imgTitle;
    @Bind(R.id.tv_img_source)
    TextView imgSource;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        toolbar.setTitle("");
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        id = getIntent().getStringExtra("id");
        mvpPresenter.getDetailNews(id);

        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                showProgressDialog();
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                dismissProgressDialog();
            }
        });
    }

    @Override
    protected NewsDetailsPresenter createPresenter() {
        return new NewsDetailsPresenter(this);
    }

    @Override
    public void getNewsDetailsSuccess(NewsDetailsEntity newsEntity) {
        toolbar.setTitle(newsEntity.getTitle());
        String head = "<head>\n" +
                "\t<link rel=\"stylesheet\" href=\"" + newsEntity.getCss()[0] + "\"/>\n" +
                "</head>";
        String img = "<div class=\"headline\">";
        String html = head + newsEntity.getBody().replace(img, " ");
        webView.loadDataWithBaseURL(null, html, "text/html", "utf-8", null);
        Picasso.with(this).load(newsEntity.getImage()).into(webImg);

        imgTitle.setText(newsEntity.getTitle());
        imgSource.setText("来自:" + newsEntity.getImage_source());
    }

    @Override
    public void getNewsDetailsFail(String msg) {
        toastShow(msg);
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


}
