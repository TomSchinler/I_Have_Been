package com.example.sca.ihavebeen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.sca.ihavebeen.R;

public class GameStart extends AppCompatActivity {

    protected FaceBookLogic fbl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_start);

        fbl = new FaceBookLogic();


        fbl.getUserFriends();





    }


}
