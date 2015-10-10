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

        sportsButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                Intent intent = new Intent(CategoryActivity.this, NewsActivity.class);
                startActivity(intent);
            }
        });

    }
}














;
