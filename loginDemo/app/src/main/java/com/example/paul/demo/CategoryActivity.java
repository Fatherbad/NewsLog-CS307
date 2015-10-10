package com.example.paul.demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by nathan on 10/10/15.
 */
public class CategoryActivity extends AppCompatActivity {

    public static final String CATEGORY = "com.example.paul.demo.CATEGORY";

    public Button sportsButton;
    public Button politicsButton;
    public Button techButton;
    public Button scienceButton;
    public Button economyButton;
    public Button entertainmentButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        sportsButton = (Button)findViewById(R.id.SportsButton);
        sportsButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                //TODO:Add Category Tag
                Intent intent = new Intent(CategoryActivity.this, NewsActivity.class);
                intent.putExtra((String) sportsButton.getTag(), CATEGORY);
                startActivity(intent);
            }
        });

        politicsButton = (Button)findViewById(R.id.PoliticsButton);
        politicsButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                //TODO: Add category tag
                Intent intent = new Intent(CategoryActivity.this, NewsActivity.class);
                intent.putExtra((String)politicsButton.getTag(), CATEGORY);
                startActivity(intent);
            }
        });

        techButton = (Button)findViewById(R.id.TechButton);
        techButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                //TODO: Add category tag
                Intent intent = new Intent(CategoryActivity.this, NewsActivity.class);
                intent.putExtra((String)techButton.getTag(), CATEGORY);
                startActivity(intent);
            }
        });

        scienceButton = (Button)findViewById(R.id.ScienceButton);
        scienceButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                //TODO: Add category tag
                Intent intent = new Intent(CategoryActivity.this, NewsActivity.class);
                intent.putExtra((String)scienceButton.getTag(), CATEGORY);
                startActivity(intent);
            }
        });

        economyButton = (Button)findViewById(R.id.EconomyButton);
        economyButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                //TODO: Add category tag
                Intent intent = new Intent(CategoryActivity.this, NewsActivity.class);
                intent.putExtra((String)economyButton.getTag(), CATEGORY);
                startActivity(intent);
            }
        });

        entertainmentButton = (Button)findViewById(R.id.EntertainmentButton);
        entertainmentButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                //TODO: Add category tag
                Intent intent = new Intent(CategoryActivity.this, NewsActivity.class);
                intent.putExtra((String)sportsButton.getTag(), CATEGORY);
                startActivity(intent);
            }
        });
    }
}














;
