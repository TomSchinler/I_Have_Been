package com.sca.ihavebeen;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.sca.ihavebeen.R;
import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

public class LogInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        Firebase.setAndroidContext(this);

        TextView welcomeText = (TextView) findViewById(R.id.logInWelcomeTextView);
        TextView titleText = (TextView) findViewById(R.id.loginTitleTextView);
        TextView pleaseSignIn = (TextView) findViewById(R.id.loginPleaseSignInTextView);
        Button button = (Button) findViewById(R.id.googleSignInButton);

        welcomeText.setVisibility(View.GONE);
        titleText.setVisibility(View.GONE);
        pleaseSignIn.setVisibility(View.GONE);
        button.setVisibility(View.GONE);

        Firebase firebase = new Firebase(Constants.FIREBASE_URL);
        if (firebase.getAuth() == null || isExpired(firebase.getAuth())){
            welcomeText.setVisibility(View.VISIBLE);
            titleText.setVisibility(View.VISIBLE);
            pleaseSignIn.setVisibility(View.VISIBLE);
            button.setVisibility(View.VISIBLE);

        }
        else {
            Intent intent = new Intent(this, NewUserProfile.class);
            startActivity(intent);
        }






        // Configure Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
    }

    private boolean isExpired(AuthData authData) {
        return (System.currentTimeMillis() / 1000) >= authData.getExpires();
    }


}
