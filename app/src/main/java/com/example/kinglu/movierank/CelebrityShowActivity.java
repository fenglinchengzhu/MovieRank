package com.example.kinglu.movierank;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class CelebrityShowActivity extends AppCompatActivity {
    private static final String API_KEY = "?apikey=0b2bdeda43b5688921839c8ecb20399b";
    private static final String CELEBRITY_URL = "https://movie.douban.com/celebrity/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_celebrity_show);
        WebView webView = (WebView) findViewById(R.id.celebrity_web);
        Intent intent = getIntent();
        String celebrityUrl = intent.getStringExtra("celebrity_detail");
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(CELEBRITY_URL + celebrityUrl);
    }
}
