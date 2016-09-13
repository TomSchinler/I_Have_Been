package com.sca.ihavebeen;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.sca.ihavebeen.R;

public class NewUserProfile extends AppCompatActivity {

    public String mUserName;
    public int mTickets;


    private TextView mUserNameTextView;
    private TextView mTicketTextView;
    private FloatingActionButton mNewGameFAB;

    private final User mUser = User.getInstance();
    private final TicketSystem mTicketSystem = TicketSystem.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user_profile);




        mUserName = mUser.getmUserName();
        mTickets = mTicketSystem.getTickets();

        mNewGameFAB = (FloatingActionButton) findViewById(R.id.newGameFab);

        mTicketTextView = (TextView) findViewById(R.id.ticketTextView);
        mTicketTextView.setText(String.valueOf(mTickets));

        mUserNameTextView = (TextView) findViewById(R.id.userName);
        mUserNameTextView.setText(mUserName);
        Log.i("User Name is: ", mUserName );




        mNewGameFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NewUserProfile.this, GameChoiceActivity.class);
                startActivity(intent);
            }
        });


    }
}
