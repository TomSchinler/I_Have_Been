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
import com.parse.SignUpCallback;

import java.security.Permission;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;


public class SignUpActivity extends AppCompatActivity {

    protected EditText mEmailField;
    protected EditText mPasswordField;
    protected EditText mUserName;
    protected Button mButton;
    protected Button mFBButton;
    final Collection<String> permissions = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //bind member variables to views
        mEmailField = (EditText)findViewById(R.id.SUeMail);
        mPasswordField = (EditText)findViewById(R.id.SUPassword);
        mUserName = (EditText)findViewById(R.id.userNameEditText);
        mButton = (Button)findViewById(R.id.SUButton);
        mFBButton = (Button)findViewById(R.id.FBSignUpButton);

        //sign up with Parse on button click
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Email = mEmailField.getText().toString();
                String Password = mPasswordField.getText().toString();
                String UserName = mUserName.getText().toString();
                ParseUser user = new ParseUser();
                user.setUsername(UserName);
                user.setEmail(Email);
                user.setPassword(Password);


                user.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(com.parse.ParseException e) {
                        if (e == null) {
                            Intent intent = new Intent(SignUpActivity.this, UserProfileActivity.class);
                            startActivity(intent);
                        } else {
                            Toast toast = Toast.makeText(getApplicationContext(), "sign up did not work", Toast.LENGTH_LONG);
                            toast.show();
                        }
                    }

                });
            }
        });


        // Facebook Sign Up button
        mFBButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                permissions.add("user_status");
                permissions.add("user_friends");
                ParseFacebookUtils.logInWithReadPermissionsInBackground(SignUpActivity.this, permissions, new LogInCallback() {
                    @Override
                    public void done(ParseUser parseUser, com.parse.ParseException e) {
                        if (parseUser == null) {
                            Log.d("MyApp", "Uh oh. The user cancelled the Facebook login.");
                        } else if (parseUser.isNew()) {
                            Log.d("MyApp", "User signed up and logged in through Facebook!");
                            Intent intent = new Intent(SignUpActivity.this, UserProfileActivity.class);
                            startActivity(intent);
                        } else {
                            Log.d("MyApp", "User logged in through Facebook!");
                            Intent intent = new Intent(SignUpActivity.this, UserProfileActivity.class);
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
        getMenuInflater().inflate(R.menu.menu_sign_up, menu);
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
