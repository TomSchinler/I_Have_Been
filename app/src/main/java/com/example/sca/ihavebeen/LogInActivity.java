package com.example.sca.ihavebeen;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.parse.LogInCallback;
import com.parse.ParseUser;

import java.text.ParseException;


public class LogInActivity extends ActionBarActivity {

    protected EditText mEmailField;
    protected EditText mPasswordField;
    protected Button mButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        //bind member variables to views
        mEmailField = (EditText)findViewById(R.id.LIeMail);
        mPasswordField = (EditText)findViewById(R.id.LIPassword);
        mButton = (Button)findViewById(R.id.LIButton);

        
        //Log In with Parse on button click
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Email = mEmailField.getText().toString();
                String Password = mPasswordField.getText().toString();
                ParseUser.logInInBackground(Email, Password, new LogInCallback() {
                    @Override
                    public void done(ParseUser parseUser, com.parse.ParseException e) {
                        if (parseUser != null) {
                            Intent intent = new Intent(LogInActivity.this, UserProfileActivity.class);
                            startActivity(intent);
                        } else {
                            // Signup failed. Look at the ParseException to see what happened.
                        }
                    }

                });
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_log_in, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
