package com.example.paul.demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by stephen on 12/6/2015.
 */
public class ConfirmDelete extends AppCompatActivity {

    private Socket sock = com.example.paul.demo.SocketHandler.getSocket();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.confirm_delete_popup);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width * 0.8), (int) (height * 0.5));

        Button deleteAccountButton = (Button) findViewById(R.id.delete_act_confirm);
        deleteAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                delete();
            }
        });
        Button returnToManager = (Button) findViewById(R.id.return_back);
        returnToManager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                runManager();
            }
        });



    }

    public void runManager(){
        startActivity(new Intent(ConfirmDelete.this, AccountManagementActivity.class));
    }

    public void delete() {
        EditText mNewPasswordView = (EditText) findViewById(R.id.passwordDelete);
        String password = mNewPasswordView.getText().toString();
        JSONObject deleteInfo = new JSONObject();
        String msg = "";

        try {
            PrintWriter writer = new PrintWriter(sock.getOutputStream());
            deleteInfo.put("request", "delete account");
            deleteInfo.put("password", password);
            String deletion = deleteInfo.toString();
            writer.println(deletion);
            writer.flush();

            InputStreamReader reader = new InputStreamReader(sock.getInputStream());
            BufferedReader bfRead = new BufferedReader(reader);
            String temp = bfRead.readLine();
            msg = msg + temp;
            bfRead.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        if (msg.contains("delete")) {
            startActivity(new Intent(ConfirmDelete.this, AccountManagementActivity.class));
        } else {
            runManager();
        }
    }
}
