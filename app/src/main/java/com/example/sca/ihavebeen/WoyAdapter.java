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
public class WoyAdapter extends ParseQueryAdapter<Game>{


    public WoyAdapter(Context context, final String fbId) {
        super(context, new ParseQueryAdapter.QueryFactory<Game>() {

            public ParseQuery<Game> create() {
                //Log.v("var fbid is ", fbId);
                ParseQuery query = new ParseQuery("Game");
                query.whereEqualTo("Opponent_Id", fbId);
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
            view = View.inflate(getContext(), R.layout.waiting_on_you_layout, null);
            Log.v("WOY is this", "running");
        }

        super.getItemView(game, view, parent);

        ProfilePictureView friendPic = (ProfilePictureView) view.findViewById(R.id.woyFbPic);
        String friendId = game.getCreatorId();
        if(friendId != null){
            friendPic.setProfileId(friendId);
        }

        TextView oppName = (TextView)view.findViewById(R.id.woyNameText);
        oppName.setText(game.getCreatorFbName());

        TextView oppScore = (TextView)view.findViewById(R.id.woyScoreNumber);
        oppScore.setText(game.getCreatorScore());

        TextView GOTV = (TextView)view.findViewById(R.id.gameObjectHiddenTextView);
        GOTV.setText(game.getObjectId());


        return view;
    }
}
