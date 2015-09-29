package com.example.sca.ihavebeen;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

//import com.facebook.appevents.AppEventsLogger;
import com.facebook.appevents.AppEventsLogger;
import com.parse.ParseUser;


public class UserProfileActivity extends AppCompatActivity {

    ParseLogic mPl;
    private WfoAdapter wfoAdapter;
    private WoyAdapter woyAdapter;
    private CompletedGameAdapter completedGameAdapter;
    private TicketSystem mTicketsSytem;



    String mMyFbId;
    String mMyFbName;

    int mTickets;

    ListView mWfoListView;
    ListView mWoyListView;
    ListView mCompletedGameListView;
    TextView mmTicketNumber;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        mTicketsSytem = new TicketSystem();
        mTickets = mTicketsSytem.getTickets();
        new getFbDeets(this).execute();

        setContentView(R.layout.activity_user_profile);



        mPl = new ParseLogic();
        mWoyListView = (ListView)findViewById(R.id.waitingForYouListView);
        mWfoListView = (ListView)findViewById(R.id.waitingForOpponentListView);
        mCompletedGameListView = (ListView)findViewById(R.id.completedGameListView);
        mmTicketNumber = (TextView)findViewById(R.id.numberOfTickets);

        mmTicketNumber.setText(String.valueOf(mTickets));

        populateUI();



        FaceBookFriends.getFaceBookFriends();



         //Testing start game remove when building rest of page
        Button button = (Button)findViewById(R.id.startNewGame);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(UserProfileActivity.this, GameStart.class);
                startActivity(intent);


            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Logs 'install' and 'app activate' App Events.
        AppEventsLogger.activateApp(this);

        mTickets = mTicketsSytem.getTickets();
        mmTicketNumber.setText(String.valueOf(mTickets));
        populateWoyListView();
        populateWfoListView();
        populateCompleteListView();


    }

    @Override
    public void onBackPressed(){}


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        //create the logout button in action bar
        switch (item.getItemId()){
            case R.id.logoutButton:
                ParseUser.logOut();
                ParseUser currentUser = ParseUser.getCurrentUser(); // this will now be null
                Intent intent = new Intent(UserProfileActivity.this, WelcomeActivity.class);
                startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    public void populateUI() {
        populateWoyListView();
        populateWfoListView();
        populateCompleteListView();

        mWoyListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String goTV = ((TextView) view.findViewById(R.id.gameObjectHiddenTextView)).getText().toString();
                String score = ((TextView) view.findViewById(R.id.woyScoreNumber)).getText().toString();
                Log.v("Clicked Object Id ", goTV);
                Intent intent = new Intent(UserProfileActivity.this, AreYouReady.class);
                intent.putExtra("Score", score);
                intent.putExtra("Object Id", goTV);

                startActivity(intent);
            }
        });
    }

    private void populateWoyListView() {
        woyAdapter = new WoyAdapter(this ,FaceBookFriends.mMyFbId);
        woyAdapter.loadObjects();
        mWoyListView.setAdapter(woyAdapter);
    }

    private void populateWfoListView() {
        wfoAdapter = new WfoAdapter(this);
        wfoAdapter.loadObjects();
        mWfoListView.setAdapter(wfoAdapter);
    }

    private void populateCompleteListView() {
        completedGameAdapter = new CompletedGameAdapter(this, FaceBookFriends.mMyFbId);
        completedGameAdapter.loadObjects();
        mCompletedGameListView.setAdapter(completedGameAdapter);
    }


    protected class getFbDeets extends AsyncTask<Void, Void, Void>{

        private ProgressDialog dialog;

        public getFbDeets(UserProfileActivity activity){
            dialog = new ProgressDialog(activity);
        }
        @Override
        protected void  onPreExecute(){
            dialog.setMessage("Getting your games!");
            dialog.show();

        }
        @Override
        protected Void doInBackground(Void... params) {

            mMyFbId = FaceBookFriends.mMyFbId;
            mMyFbName = FaceBookFriends.getMyFbName();
            Log.v("getFbDeets() ", "Is Running");
            try{
                Thread.sleep(3000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            //Log.v("mMyFbId after the call ", mMyFbId);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            populateUI();
            if(dialog.isShowing()){
                dialog.dismiss();
            }
        }
    }





}


