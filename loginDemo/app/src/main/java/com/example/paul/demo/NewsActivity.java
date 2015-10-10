package com.example.paul.demo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.os.Bundle;
import android.webkit.WebViewClient;
import android.widget.Button;

/**
 * Created by stephen on 10/10/15.
 */
public class NewsActivity extends AppCompatActivity {
    
    
     
    private WebView webView;
    private String category;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_activity);

        webView = (WebView) findViewById(R.id.webView1);

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        webView.setWebViewClient(new Callback());
        webView.loadUrl("http://www.google.com");

//        String customHtml = "<html><body><h1>Hello, WebView</h1></body></html>";
//        webView.loadData(customHtml, "text/html", "UTF-8");

    }

    @Override
    public void onStart () {
        super.onStart();
        Intent intent = getIntent();
        category = intent.getStringExtra("CATEGORY");
        System.out.println(category);
    }

    private class Callback extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading (WebView webView, String Url) {
            return (false);
        }
    }

}
