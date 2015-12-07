package com.example.paul.demo;

import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.content.res.ColorStateList;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.KeyEvent;
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

import org.json.JSONObject;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.regex.Pattern;

/**
 * Created by stephen on 10/12/15.
 */
public class AccountManagementActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {


    // UI references.
    private EditText mNewPasswordView;
    private EditText mNewPasswordView2;
    private EditText mPasswordView;
    public ListView mDrawerList;
    public ArrayAdapter<String> mAdapter;

    private Socket sock = com.example.paul.demo.SocketHandler.getSocket();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_account_activity);

        populateAutoComplete();

        mNewPasswordView = (EditText) findViewById(R.id.new_password);
        mNewPasswordView2 = (EditText) findViewById(R.id.retype_new_password);
        mPasswordView = (EditText) findViewById(R.id.old_password);

        Button changePassword = (Button) findViewById(R.id.change_pass);
        changePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptPassChange();
            }
        });
        Button resetInterests = (Button) findViewById(R.id.reset_interests);
        resetInterests.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetInterests();
            }
        });
        Button changeTheme = (Button) findViewById(R.id.change_theme);
        changeTheme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeTheme();
            }
        });
        Button deleteAccount = (Button) findViewById(R.id.delete_act);
        deleteAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteAccount();
            }
        });

        mDrawerList = (ListView)findViewById(R.id.navList);
        addDrawerItems();
    }
    private void changeTheme(){
        /*
        //Do something
        Button changeTheme = (Button) findViewById(R.id.change_theme);
        View buttonTheme= (View) findViewById(R.id.potatoe);
        if(buttonTheme == null) {
            //set
            System.out.println("-!-!-! AY\n\n");
        }else {
            System.out.println("cheeeese");
            int colors[] = { 0xff255779, 0xffa6c0cd }
            GradientDrawable gradientDrawable = new GradientDrawable(
                    GradientDrawable.Orientation.TOP_BOTTOM, colors);

            view.setBackgroundDrawable(gradientDrawable);
        }
        //buttonTheme.setEnabled(true);
        System.out.println("~~~~~~~~~~~~~~~~~~~~~CHANGE THEME TEST~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

        // android:id="@+id/themeColor
*/
    }

    private void addDrawerItems() {
        String[] osArray = { "Select Category", "Sign Out"};
        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, osArray);
        mDrawerList.setAdapter(mAdapter);

        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent;
                switch ((int) id) {

                    case 0:
                        intent = new Intent(AccountManagementActivity.this, CategoryActivity.class);
                        startActivity(intent);
                        break;

                    case 1:
                        com.example.paul.demo.SocketHandler.closeSocket();
                        intent = new Intent(AccountManagementActivity.this, LoginActivity.class);
                        startActivity(intent);
                        break;
                }
//                This pops up a small dialog at the bottom of the screen
//                Toast.makeText(CategoryActivity.this, "Time for an upgrade!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void populateAutoComplete() {
        getLoaderManager().initLoader(0, null, this);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return null;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
    private void attemptPassChange() {
        // Password reset errors.
        mNewPasswordView.setError(null);
        mNewPasswordView2.setError(null);
        mPasswordView.setError(null);

        // Store values at the time of the login attempt.
        String newPass = mNewPasswordView.getText().toString();
        String newPass2 = mNewPasswordView2.getText().toString();
        String password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check that new pass is valid, also check old password is correct.
        if (!TextUtils.isEmpty(newPass) && !isPasswordValid(newPass)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }
        if (!TextUtils.isEmpty(newPass2) && !isPasswordValid(newPass2)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }

       /* if (!TextUtils.isEmpty(password) && !isCurrentPassword(password)) {
            //NEED TO IMPLEMENT A GET REQUEST TO DATABASE
            mPasswordView.setError(getString(R.string.error_incorrect_password));
            focusView = mPasswordView;
            cancel = true;

        }*/

        if (cancel) {
            //Print error messages
            focusView.requestFocus();
        } else {
            //Change user password
            changePassword(newPass, password);
        }
    }

    private boolean isPasswordValid(String password) {
        return password.length() > 4;
    }

    private boolean isCurrentPassword(String password){
        //TODO:Check current user password
        return true;
    }

    private void changePassword(String newpass, String password){
        JSONObject passInfo = new JSONObject();

        try{
            PrintWriter writer = new PrintWriter(sock.getOutputStream());
            passInfo.put("request", "change password");
            passInfo.put("old password", password);
            passInfo.put("new password", newpass);
            String passChng = passInfo.toString();
            writer.println(passChng);
            writer.flush();
        } catch (Exception ex) {ex.printStackTrace();}
    }

    private void resetInterests(){
        //TODO:RESET ANY INTERESTS STORED PER USER
    }

    private void deleteAccount(){
        //TODO:DELETE USER FROM BACKEND
    }

}
