package com.example.sca.ihavebeen;

import android.content.Context;
import android.graphics.Color;
import android.text.Layout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;
import com.parse.ParseUser;

import java.util.Arrays;

/**
 * Created by Tom Schinler on 9/23/2015.
 */
public class CompletedGameAdapter extends ParseQueryAdapter<Game> {

    String mWinnerScore;
    String mWinnerName;
    String mLoserScore;
    String mLoserName;
    String mFbName;


    public CompletedGameAdapter(Context context, final String myFbId) {
        super(context, new ParseQueryAdapter.QueryFactory<Game>() {
            public ParseQuery<Game> create() {
                ParseQuery<Game> queryCreatedBy = new ParseQuery<Game>("Game");
                queryCreatedBy.whereEqualTo("Created_By", ParseUser.getCurrentUser());

                ParseQuery<Game> queryOppOf = new ParseQuery<Game>("Game");
                queryOppOf.whereEqualTo("Opponent_Id", myFbId);

                ParseQuery<Game> query = ParseQuery.or(Arrays.asList(queryCreatedBy, queryOppOf));
                query.whereNotEqualTo("Creator_Score", "");
                query.whereNotEqualTo("Opponent_Score", "");
                query.orderByDescending("updatedAt");
                query.setLimit(4);
                return query;
            }

        });
    }


    @Override
    public View getItemView(Game game, View view, ViewGroup parent) {

        if (view == null) {
            view = View.inflate(getContext(), R.layout.completed_game_layout, null);
        }

        super.getItemView(game, view, parent);
        mFbName = FaceBookFriends.getMyFbName();

        TextView winOrLose = (TextView)view.findViewById(R.id.winOrLose);


        int oppScore = Integer.parseInt(game.getOpponentScore());
        int creatScore = Integer.parseInt(game.getCreatorScore());





        if(oppScore > creatScore){
            mWinnerScore = String.valueOf(oppScore);
            mWinnerName = game.getOpponentName();
            mLoserScore = String.valueOf(creatScore);
            mLoserName = game.getCreatorFbName();
        }
        else {
            mWinnerScore = String.valueOf(creatScore);
            mWinnerName = game.getCreatorFbName();
            mLoserScore = String.valueOf(oppScore);
            mLoserName = game.getOpponentName();
        }

        TextView winnerName = (TextView)view.findViewById(R.id.winnerName);
        winnerName.setText(mWinnerName);

        TextView winnerScore = (TextView)view.findViewById(R.id.winnerScore);
        winnerScore.setText(mWinnerScore);

        TextView loserName = (TextView)view.findViewById(R.id.loserName);
        loserName.setText(mLoserName);

        TextView loserScore = (TextView)view.findViewById(R.id.loserScore);
        loserScore.setText(mLoserScore);

        if(mWinnerName.equals(mFbName)){
            view.setBackgroundColor(Color.GREEN);
            winOrLose.setText("WIN!!");
        }
        else {
            view.setBackgroundColor(Color.RED);
            winOrLose.setText("LOSER!!");
        }


        return view;
    }


}




