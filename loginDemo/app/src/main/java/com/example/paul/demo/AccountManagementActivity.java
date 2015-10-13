package com.example.paul.demo;

import android.app.LoaderManager;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.regex.Pattern;

/**
 * Created by stephen on 10/12/15.
 */
public class AccountManagementActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {


    // UI references.
    private EditText mNewPasswordView;
    private EditText mNewPasswordView2;
    private EditText mPasswordView;

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
        changePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetInterests();
            }
        });
        Button deleteAccount = (Button) findViewById(R.id.delete_act);
        changePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteAccount();
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

        // Reset errors.
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
        if (!TextUtils.isEmpty(password) && !isCurrentPassword(password)) {
            //NEED TO IMPLEMENT A GET REQUEST TO DATABASE
            mPasswordView.setError(getString(R.string.error_incorrect_password));
            focusView = mPasswordView;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            changePassword();
        }
    }

    private boolean isPasswordValid(String password) {
        return password.length() > 4;
    }

    private boolean isCurrentPassword(String password){
        //TODO:Check current user password
        return true;
    }

    private void changePassword(){
        //TODO:CHANGE PW IN BACKEND
    }

    private void resetInterests(){
        //TODO:RESET ANY INTERESTS STORED PER USER
    }

    private void deleteAccount(){
        //TODO:DELETE USER FROM BACKEND
    }

}
