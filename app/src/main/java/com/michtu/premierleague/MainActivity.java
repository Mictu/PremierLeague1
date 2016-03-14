package com.michtu.premierleague;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private static Button goToPlayersActivity;
    //private static Button goToTournamenteActivity;
    //private static Button goToRanglisteActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        OnClickButtonListener();
    }

    public void OnClickButtonListener(){
        goToPlayersActivity = (Button)findViewById(R.id.goToPlayersActivity);
        goToPlayersActivity.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent("com.michtu.premierleague.PlayersActivity");
                        startActivity(intent);
                    }
                }
        );
    }
}
