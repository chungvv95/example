package com.example.dell.liverpoolfc;

import android.media.Image;

/**
 * Created by dell on 03/01/2018.
 */

public class FootballPlayer {
    private String namePlayer;
    private String location;
    private int nation;
    private int imagePlayer;


    public FootballPlayer(String namePlayer, String location, int nation,int imagePlayer) {
        this.namePlayer = namePlayer;
        this.location = location;
        this.nation = nation;
        this.imagePlayer = imagePlayer;
    }

    public String getNamePlayer() {
        return namePlayer;
    }

    public void setNamePlayer(String namePlayer) {
        this.namePlayer = namePlayer;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getNation() {
        return nation;
    }

    public void setNation(int nation) {
        this.nation = nation;
    }
    public int getImagePlayer() {
        return imagePlayer;
    }

    public void setImagePlayer(int imagePlayer) {
        this.imagePlayer = imagePlayer;
    }
}
