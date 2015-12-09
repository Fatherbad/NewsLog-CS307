package com.example.paul.demo;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.net.Socket;

/**
 * Created by nathan on 10/10/15.
 */
public class CategoryActivity extends AppCompatActivity {

    public static final String CATEGORY = "CATEGORY";
    public static final String EMAIL ="EMAIL";

    String email;

    public Button sportsButton;
    public Button politicsButton;
    public Button techButton;
    public Button scienceButton;
    public Button economyButton;
    public Button entertainmentButton;
    public ListView mDrawerList;
    public ArrayAdapter<String> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        mDrawerList = (ListView)findViewById(R.id.navList);
        addDrawerItems();

        sportsButton = (Button)findViewById(R.id.SportsButton);
        sportsButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                //Add category tag to intent and pass to NewsActivity
                Intent intent = new Intent(CategoryActivity.this, NewsActivity.class);
                intent.putExtra(CATEGORY, (String) sportsButton.getTag());
                intent.putExtra(EMAIL, email);
                startActivity(intent);
            }
        });

        politicsButton = (Button)findViewById(R.id.PoliticsButton);
        Drawable loginActivityBackground2 = findViewById(R.id.SportsButton).getBackground();
        loginActivityBackground2.setAlpha(250);
        politicsButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                //Add category tag to intent and pass to NewsActivity
                Intent intent = new Intent(CategoryActivity.this, NewsActivity.class);
                intent.putExtra(CATEGORY, (String) politicsButton.getTag());
                intent.putExtra(EMAIL, email);
                startActivity(intent);
            }
        });

        techButton = (Button)findViewById(R.id.TechButton);
        techButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                //Add category tag to intent and pass to NewsActivity
                Intent intent = new Intent(CategoryActivity.this, NewsActivity.class);
                intent.putExtra(CATEGORY, (String) techButton.getTag());
                intent.putExtra(EMAIL, email);
                startActivity(intent);
            }
        });

        scienceButton = (Button)findViewById(R.id.ScienceButton);
        scienceButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                //Add category tag to intent and pass to NewsActivity
                Intent intent = new Intent(CategoryActivity.this, NewsActivity.class);
                intent.putExtra(CATEGORY, (String) scienceButton.getTag());
                intent.putExtra(EMAIL, email);
                startActivity(intent);
            }
        });

        economyButton = (Button)findViewById(R.id.EconomyButton);
        economyButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                //Add category tag to intent and pass to NewsActivity
                Intent intent = new Intent(CategoryActivity.this, NewsActivity.class);
                intent.putExtra(CATEGORY, (String) economyButton.getTag());
                intent.putExtra(EMAIL, email);
                startActivity(intent);
            }
        });

        entertainmentButton = (Button)findViewById(R.id.EntertainmentButton);
        entertainmentButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                //Add category tag to intent and pass to NewsActivity
                Intent intent = new Intent(CategoryActivity.this, NewsActivity.class);
                intent.putExtra(CATEGORY, (String) entertainmentButton.getTag());
                intent.putExtra(EMAIL, email);
                startActivity(intent);
            }
        });

    }


    private void addDrawerItems() {
        String[] osArray = { "Manage Account", "My Library", "Sign Out"};
        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, osArray);
        mDrawerList.setAdapter(mAdapter);

        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                System.out.println("--------HERE " + id + " ----------");
                switch ((int)id) {
                    case 0:
                        Intent intent = new Intent(CategoryActivity.this, AccountManagementActivity.class);
                        startActivity(intent);
                        break;

                    case 1:
                        Intent in = new Intent(CategoryActivity.this, LibraryActivity.class);
                        startActivity(in);
                        break;
                    case 2:
                        com.example.paul.demo.SocketHandler.closeSocket();
                        Intent i = new Intent(CategoryActivity.this, LoginActivity.class);
                        startActivity(i);
                        break;
                }
//                This pops up a small dialog at the bottom of the screen
//                Toast.makeText(CategoryActivity.this, "Time for an upgrade!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onStart () {
        super.onStart();
        Intent intent = getIntent();
        email = (String)intent.getStringExtra("EMAIL");
    }
}














;
