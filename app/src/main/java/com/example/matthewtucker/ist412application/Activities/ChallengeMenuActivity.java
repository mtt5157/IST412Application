package com.example.matthewtucker.ist412application.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.example.matthewtucker.ist412application.R;

public class ChallengeMenuActivity extends AppCompatActivity {
    private Button switchToSpeedChallenge;
    private Button switchToHeighChallenge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_challenge_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        switchToSpeedChallenge = (Button) findViewById(R.id.speed_challenge_button);
        switchToHeighChallenge = (Button) findViewById(R.id.height_challenge_button);

        switchToSpeedChallenge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ChallengeMenuActivity.this, ChallengeMode.class));
            }
        });


    }

}
