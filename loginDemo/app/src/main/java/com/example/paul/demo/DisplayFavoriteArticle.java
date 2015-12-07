package com.example.paul.demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by nathan on 12/2/15.
 */
public class DisplayFavoriteArticle extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_fav);

        Intent intent = getIntent();
        String favURL = (String)intent.getStringExtra("FAVORITE");

        WebView favView = (WebView)findViewById(R.id.favView);

        favView.setWebViewClient(new Callback());
        favView.loadUrl(favURL);
        favView.canGoBack();


    }

    private class Callback extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading (WebView webView, String Url) {
            return (false);
        }
    }

}
