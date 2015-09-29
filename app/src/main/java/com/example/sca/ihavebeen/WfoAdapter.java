package com.example.sca.ihavebeen;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.login.widget.ProfilePictureView;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;
import com.parse.ParseUser;

/**
 * Created by Tom Schinler on 9/22/2015.
 */
public class WfoAdapter extends ParseQueryAdapter<Game> {



    public WfoAdapter(Context context) {
        super(context, new ParseQueryAdapter.QueryFactory<Game>() {

            public ParseQuery<Game> create() {

                ParseQuery query = new ParseQuery("Game");
                query.whereEqualTo("Created_By", ParseUser.getCurrentUser());
                query.whereEqualTo("Opponent_Score", "");
                query.orderByDescending("updatedAt");
                query.setLimit(4);
                return query;
            }
        });
    }

    @Override
    public View getItemView(Game game, View view, ViewGroup parent) {

        if (view == null) {
            view = View.inflate(getContext(), R.layout.waiting_for_opponent_layout, null);
        }

        super.getItemView(game, view, parent);

        ProfilePictureView friendPic = (ProfilePictureView) view.findViewById(R.id.wfoFbPic);
        String friendId = game.getOpponentId();
        if(friendId != null){
           friendPic.setProfileId(friendId);
        }

        TextView oppName = (TextView)view.findViewById(R.id.wfoNameText);
        oppName.setText(game.getOpponentName());

        TextView oppScore = (TextView)view.findViewById(R.id.wfoYourScoreNumber);
        oppScore.setText(game.getCreatorScore());

         return view;
    }


}
