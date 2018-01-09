package com.example.dell.liverpoolfc;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dell on 03/01/2018.
 */

public class PlayerAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private List<FootballPlayer> footballPlayerList;

    public PlayerAdapter(Context context, int layout, List<FootballPlayer> footballPlayerList) {
        this.context = context;
        this.layout = layout;
        this.footballPlayerList = footballPlayerList;
    }

    @Override
    public int getCount() {
        return footballPlayerList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }
    private class ViewHolder{
        ImageView imagePlayer;
        ImageView imageNation;
        TextView namePlayer;
        TextView location;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;

        if(view ==null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);

            holder = new ViewHolder();
            holder.namePlayer = (TextView) view.findViewById(R.id.namePlayer);
            holder.location = (TextView) view.findViewById(R.id.location);
            holder.imageNation = (ImageView) view.findViewById(R.id.nation);
            holder.imagePlayer = (ImageView) view.findViewById(R.id.imagePlayer);

            view.setTag(holder);
        }else{
            holder = (ViewHolder) view.getTag();
        }

        FootballPlayer footballPlayer = footballPlayerList.get(i);

        holder.namePlayer.setText(footballPlayer.getNamePlayer());
        holder.location.setText(footballPlayer.getLocation());
        holder.imageNation.setImageResource(footballPlayer.getNation());
        holder.imagePlayer.setImageResource(footballPlayer.getImagePlayer());

        Animation animation = AnimationUtils.loadAnimation(context,R.anim.scale);
        view.startAnimation(animation);

        return view;
    }
}
