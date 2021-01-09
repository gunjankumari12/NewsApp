package com.kanagalatech.newsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.kanagalatech.newsapp.Response.NewsList;

import java.util.ArrayList;
import java.util.List;

public class NewsWebView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_web_view);
        WebView webView = (WebView) findViewById(R.id.webview);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String webUrl = extras.getString("webUrl");
            webView.loadUrl(webUrl);
        }

}
}