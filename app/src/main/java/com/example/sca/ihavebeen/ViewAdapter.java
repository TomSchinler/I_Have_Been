package com.example.sca.ihavebeen;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sca.ihavebeen.FaceBookLogic;
import com.example.sca.ihavebeen.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Array;

/**
 * Created by Tom Schinler on 9/1/2015.
 */
public class ViewAdapter extends BaseAdapter {

    private Context mContext;
    protected FaceBookLogic mFBL;
    protected JSONObject mFbArray;



    public ViewAdapter(Context context, JSONObject friends) {
        mFBL = new FaceBookLogic();
        mFbArray = mFBL.getFbArray();
        mFbArray = friends;
        mContext = context;

    }



    @Override
    public int getCount() {
        return mFbArray.length;
    }

    @Override
    public Object getItem(int position) {
        return mFbArray[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;  //Not used
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.fb_friends_layout, null);
            holder = new ViewHolder();
            holder.fbPic = (ImageView)convertView.findViewById(R.id.fbPic);
            holder.fbName = (TextView)convertView.findViewById(R.id.fbName);

            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder)convertView.getTag();
        }
        return null;
    }

    private static class ViewHolder {
        ImageView fbPic;
        TextView fbName;
    }



}
