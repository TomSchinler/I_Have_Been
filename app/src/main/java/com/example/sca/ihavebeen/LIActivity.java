package com.example.sca.ihavebeen;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseFacebookUtils;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.Collection;


public class LIActivity extends AppCompatActivity {

    protected EditText mEmailField;
    protected EditText mPasswordField;
    protected Button mButton;
    protected Button mFBButton;
    final Collection<String> permissions = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        //bind member variables to views
        mEmailField = (EditText)findViewById(R.id.LIeMail);
        mPasswordField = (EditText)findViewById(R.id.LIPassword);
        mButton = (Button)findViewById(R.id.LIButton);
        mFBButton = (Button)findViewById(R.id.FbLoginButton);


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
                            Intent intent = new Intent(LIActivity.this, UserProfileActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(LIActivity.this, "Log in did not work", Toast.LENGTH_SHORT).show();
                        }
                    }

                });
            }
        });

        // Facebook Log In button
        mFBButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                permissions.add("user_status");
                permissions.add("user_friends");
                ParseFacebookUtils.logInWithReadPermissionsInBackground(LIActivity.this, permissions, new LogInCallback() {
                    @Override
                    public void done(ParseUser parseUser, com.parse.ParseException e) {
                        if (parseUser == null) {
                            Log.d("MyApp", "Uh oh. The user cancelled the Facebook login.");
                        } else if (parseUser.isNew()) {
                            Log.d("MyApp", "User signed up and logged in through Facebook!");
                            Intent intent = new Intent(LIActivity.this, UserProfileActivity.class);
                            startActivity(intent);
                        } else {
                            Log.d("MyApp", "User logged in through Facebook!");
                            Intent intent = new Intent(LIActivity.this, UserProfileActivity.class);
                            startActivity(intent);
                        }
                    }

                });
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ParseFacebookUtils.onActivityResult(requestCode, resultCode, data);
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
