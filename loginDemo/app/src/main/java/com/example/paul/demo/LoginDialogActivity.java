package com.example.paul.demo;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class LoginDialogActivity extends Activity implements OnClickListener {

    Button tryAgainButton , registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        //getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.invalid_login_dialog);

        tryAgainButton = (Button) findViewById(R.id.try_again_btn);
        registerButton = (Button) findViewById(R.id.to_register_btn);

        tryAgainButton.setOnClickListener(this);
        registerButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.try_again_btn:

                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);

                break;

            case R.id.to_register_btn:

                Intent in = new Intent (getApplicationContext(), RegistrationActivity.class);
                startActivity(in);

                break;
        }

    }

    void showToastMessage(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT)
                .show();
    }

}