package com.example.paul.demo;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;


/**
 * Created by xaldin560 on 11/22/2015.
 */
public class LibraryActivity extends AppCompatActivity {

    public ListView mDrawerList;
    public ListView favList;
    public ArrayAdapter<String> mAdapter;
    private ArrayAdapter<String> favAdapter;
    protected Socket sock;
    private ArrayList<String> favs;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sock = com.example.paul.demo.SocketHandler.getSocket();

        mDrawerList = (ListView) findViewById(R.id.navList);
        addDrawerItems();

        favList = (ListView)findViewById(R.id.favArticles);

        GetFavs getFavs = new GetFavs();
        getFavs.execute();
        try {
            getFavs.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        favAdapter = new ArrayAdapter<String>(this, R.layout.list_item, favs);
        favList.setAdapter(favAdapter);

    }

    private void addDrawerItems() {
        String[] osArray = {"Select Category", "Manage Account","Sign Out"};
        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, osArray);
        mDrawerList.setAdapter(mAdapter);

        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent;
                switch ((int)id) {

                    case 0:
                        intent = new Intent(LibraryActivity.this,NewsActivity.class);
                        startActivity(intent);
                        break;

                    case 1:
                        intent = new Intent(LibraryActivity.this, AccountManagementActivity.class);
                        startActivity(intent);
                        break;

                    case 2:
                        com.example.paul.demo.SocketHandler.closeSocket();
                        intent = new Intent(LibraryActivity.this, LoginActivity.class);
                        startActivity(intent);
                        break;


                }

            }
        });

    }

    public class GetFavs extends AsyncTask<Void, Void, Boolean> {

        private final String mRequest;

        GetFavs() {
            mRequest = "library";
        }

        @Override
        protected Boolean doInBackground(Void... params) {

            try {
                if (sock.isClosed()) {
                    System.out.println("Socket is not open, this is printed from LibraryActivity");
                }
                // Simulate network access.
//                sock = SocketHandler.getSocket();
                PrintWriter writer = new PrintWriter(sock.getOutputStream());
                //Create JSON object containing the needed user info
                JSONObject newsInfo = new JSONObject();
                newsInfo.put("request", mRequest);


                String info = newsInfo.toString();
                writer.println(info);
                writer.flush();

                InputStreamReader reader = new InputStreamReader(sock.getInputStream());
                BufferedReader bfRead = new BufferedReader(reader);
                String msg;
                //Edit limit on for loop to increase or decrease amount of files fed from server
                //while(true) {
                //for (int i = 0; i < 20; i++) {
                while(true) {

                    msg = bfRead.readLine();

                    if (msg.equals("end")) {
                        break;
                    } else if (msg == null || !msg.contains("http")) {

                    } else {
                        System.out.println("ADDING: " + msg);
                        favs.add(msg);
                    }
                    //break;
                }

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

}

