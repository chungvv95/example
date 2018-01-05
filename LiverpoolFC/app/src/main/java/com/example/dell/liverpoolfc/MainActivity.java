package com.example.dell.liverpoolfc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<FootballPlayer> arrayPlayer;
    PlayerAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Anhxa();
        adapter = new PlayerAdapter(this,R.layout.player,arrayPlayer);
        listView.setAdapter(adapter);
    }

    private void Anhxa() {
        listView = (ListView) findViewById(R.id.listView);
        arrayPlayer = new ArrayList<>();
        arrayPlayer.add(new FootballPlayer("J.Klopp","HLV",R.drawable.duc,R.drawable.klopp));
        arrayPlayer.add(new FootballPlayer("P.Coutinho","Tien Ve",R.drawable.cell,R.drawable.coutinho));
        arrayPlayer.add(new FootballPlayer("R.Firmino","Tien dao",R.drawable.cell,R.drawable.firmino));
        arrayPlayer.add(new FootballPlayer("E.Can","Tien ve",R.drawable.duc,R.drawable.can));
        arrayPlayer.add(new FootballPlayer("J.Milner","Tien ve",R.drawable.england,R.drawable.milner263));
        arrayPlayer.add(new FootballPlayer("Hendorson","Tien ve",R.drawable.england,R.drawable.hendo1));
        arrayPlayer.add(new FootballPlayer("A.Lallana","Tien ve",R.drawable.england,R.drawable.lallana));
    }
}
