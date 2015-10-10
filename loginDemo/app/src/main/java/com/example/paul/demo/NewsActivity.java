package com.example.paul.demo;

import android.support.v4.view.GestureDetectorCompat;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.os.Bundle;
import android.webkit.WebViewClient;
import android.widget.Button;

/**
 * Created by stephen on 10/10/15.
 */
public class NewsActivity extends AppCompatActivity {
    
    private GestureDetectorCompat mDetector;
    private static final float SWIPE_THRESHOLD = 100;
    private float x1, x2, y1, y2;
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
        webView.loadUrl("http://www.nytimes.com/2015/10/07/technology/the-hardware-side-of-microsoft-unveils-a-pile-of-new-devices.html");
        webView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        x1 = event.getX();
                        y1 = event.getY();
                        break;
                    case MotionEvent.ACTION_UP:
                        x2 = event.getX();
                        y2 = event.getY();

                        float diffX = x1 - x2;
                        float diffY = y1 - y2;
                        if (Math.abs(diffX) > Math.abs(diffY) + 10 && Math.abs(x1-x2) > SWIPE_THRESHOLD) {
                            if (x1 > x2) {
                                onSwipeRight();
                            } else onSwipeLeft();
                            
                            return true;
                        }
                        break;
                }
                return false;
            }
        });
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
    

    public void onSwipeRight() {
        webView.loadUrl("http://www.nytimes.com/2015/10/07/business/international/vw-diesel-emissions-job-cuts.html");

    }

    public void onSwipeLeft() {
        webView.loadUrl("http://www.nytimes.com/aponline/2015/10/06/world/asia/ap-as-skorea-earns-samsung-electronics-.html");
    }
}
