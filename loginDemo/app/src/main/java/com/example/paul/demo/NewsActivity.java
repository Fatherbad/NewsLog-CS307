package com.example.paul.demo;

import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.os.Bundle;
import android.widget.Button;

/**
 * Created by stephen on 10/10/15.
 */
public class NewsActivity extends AppCompatActivity {
    
    
     
    private WebView webView;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_activity);

        //webView = (WebView) findViewById(R.id.webView1);
        //webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("http://www.google.com");

        String customHtml = "<html><body><h1>Hello, WebView</h1></body></html>";
        webView.loadData(customHtml, "text/html", "UTF-8");

    }

}
