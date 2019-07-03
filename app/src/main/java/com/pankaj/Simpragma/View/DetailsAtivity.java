package com.pankaj.Simpragma.View;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.pankaj.Simpragma.R;

public class DetailsAtivity extends AppCompatActivity {

    private String postUrl = "http://api.androidhive.info/webview/index.html";
    private WebView webView;
    private ProgressBar progressBar;
    private float m_downX;
    private ImageView imgHeader;
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        webView = (WebView) findViewById(R.id.simpleWebView);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        Bundle bundle = getIntent().getExtras();
        url= bundle.getString("url");
        webView.setWebViewClient(new MyWebViewClient());
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(url);
        webView.setHorizontalScrollBarEnabled(false);

    }
    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url); // load the url
            return true;
        }
    }
}
