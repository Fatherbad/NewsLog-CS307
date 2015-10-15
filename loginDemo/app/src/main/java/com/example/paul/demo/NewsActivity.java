package com.example.paul.demo;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Picture;

import android.os.AsyncTask;
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
import android.widget.RelativeLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by stephen on 10/10/15.
 */
public class NewsActivity extends AppCompatActivity {

    //NEED 3 NEWS PAGES --- KEEP IN MIND FOR DEV
    private String pageNextLiked = "http://www.nytimes.com/2015/10/07/business/international/vw-diesel-emissions-job-cuts.html";
    private String currPage; //= "http://www.nytimes.com/2015/10/07/technology/the-hardware-side-of-microsoft-unveils-a-pile-of-new-devices.html";
    private String pageNextDisliked = "http://www.nytimes.com/aponline/2015/10/06/world/asia/ap-as-skorea-earns-samsung-electronics-.html";

    private GestureDetectorCompat mDetector;
    private static final float SWIPE_THRESHOLD = 200;
    private float x1, x2, dx, dy, y1, y2;
    private WebView webView;
    private String category;
    private String email;
    private Socket sock = com.example.paul.demo.SocketHandler.getSocket();
    private GetNews getNews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
        category = intent.getStringExtra("CATEGORY");
        email = intent.getStringExtra("EMAIL");
        //^MAYBE ERASE

        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_activity);

        getNews = new GetNews(email,category.toLowerCase());
        getNews.execute();
        try {
            getNews.get();
        } catch (Exception ex) {ex.printStackTrace();}

        webView = (WebView) findViewById(R.id.webView1);

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);


        webView.setWebViewClient(new Callback());
        webView.loadUrl(currPage);
        webView.canGoBack();
        webView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        x1 = event.getX();
                        y1 = event.getY();
                        break;
                    /*
                        UNCOMMENT WHEN ADDING SWIPE MOTION
                    case MotionEvent.ACTION_MOVE:

                        dx = event.getX();

                        dy = event.getY();
                        if (x1 >= webView.getMeasuredWidth() - 15 && dx < x1) {
                            animatePage();
                        }*/
                    case MotionEvent.ACTION_UP:
                        x2 = event.getX();
                        y2 = event.getY();

                        float diffX = x1 - x2;
                        float diffY = y1 - y2;
                        if (Math.abs(diffX) > Math.abs(diffY) + 10 && Math.abs(x1 - x2) > SWIPE_THRESHOLD) {
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

    private class Callback extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading (WebView webView, String Url) {
            return (false);
        }
    }


    public void onSwipeRight() {
        /*getNews.execute();
        try {
            getNews.get();
        } catch (Exception ex) {ex.printStackTrace();}
        */
        webView.loadUrl(pageNextLiked);

    }

    public void onSwipeLeft() {
       /* getNews.execute();
        try {
            getNews.get();
        } catch (Exception ex) {ex.printStackTrace();}
        */
        webView.loadUrl(pageNextDisliked);
    }

    public class GetNews extends AsyncTask<Void, Void, Boolean> {

        private final String mEmail;
        private final String mCategory;

        GetNews(String email, String category) {
            mEmail = email;
            mCategory = category;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            // TODO: attempt authentication against a network service.

            try {
                // Simulate network access.
                PrintWriter writer = new PrintWriter(sock.getOutputStream());
                //Create JSON object containing the needed user info
                JSONObject newsInfo = new JSONObject();
                //newsInfo.put("email", mEmail);
                newsInfo.put("category", mCategory);

                String info = newsInfo.toString();
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~ "+info+"~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

                //Write userInfo to server and close the OutputStream
                writer.println(info);
                writer.flush();

                InputStreamReader reader = new InputStreamReader(sock.getInputStream());
                BufferedReader bfRead = new BufferedReader(reader);
                String msg;
                while((msg = bfRead.readLine()) != null){
                    currPage = msg;
                    System.out.println(msg);

                    break;
                }
                reader.close();
                //ObjectInputStream news = new ObjectInputStream(sock.getInputStream());

            } catch (UnknownHostException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return true;
        }
    }

/*
UNCOMMENT WHEN READY TO ANIMATE SWIPE
    private void animatePage(){
        //CREATE BITMAP ON PAGE

        System.out.println("~~~~~~~~HERERERE~~~~~~H: " + webView.getMeasuredHeight() + " ~~~~");
        Bitmap img = Bitmap.createBitmap(webView.getWidth(), webView.getMeasuredHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(img);
        webView.draw(canvas);
    }*/
}
