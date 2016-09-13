package com.sca.ihavebeen;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.example.sca.ihavebeen.R;

public class GameChoiceActivity extends AppCompatActivity {

    private final User mUser = User.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_choice);

        CardView mNewGameCard = (CardView) findViewById(R.id.newRandomGameCardView);
        CardView mFriendGameCard = (CardView) findViewById(R.id.newFriendsGameCardView);
        TextView mFriendsGameText = (TextView) findViewById(R.id.newFriendsGameTextView);
        CardView mCreatorGameCard = (CardView) findViewById(R.id.newCreatorsGameCardView);
        TextView mCreatorGameText = (TextView) findViewById(R.id.creatorsGameTextView);

        mNewGameCard.setClickable(true);
        mFriendGameCard.setClickable(true);
        mCreatorGameCard.setClickable(false);


        //Set behavior for card view
        mNewGameCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO create intent to start new game
            }
        });

        //Check to see if social aspect has been instantiated and act accordingly
        if(mUser.mIsFriendly){
            mFriendsGameText.setText(R.string.start_new_friend_game_text);
            mFriendGameCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //TODO create intent to bring up friends list
                }
            });

        }
        else {
            mFriendsGameText.setText(R.string.notConnectedToSocial);
            mFriendGameCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //TODO create intent to sign in with Facebook or Twitter
                }
            });
        }


        //Check to see if user can play creator and act accordingly.
        if(mCreatorGameCard.isClickable()) {
            mCreatorGameText.setText(R.string.try_the_creators);
            mCreatorGameCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //TODO create intent to start a new game against a creator
                }
            });
        }
        else {
            mCreatorGameText.setText(R.string.cant_play_creators);
        }

    }

}
