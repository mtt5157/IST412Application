package com.example.matthewtucker.ist412application.Activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.matthewtucker.ist412application.R;

public class ChallengeMode extends AppCompatActivity {

    private TableLayout table;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_challenge_mode);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        table = (TableLayout) findViewById(R.id.challengeTable);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private void fillTable()
    {
        //row 1 CHALLENGE
        TableRow row1 = new TableRow(this);
        TextView r1tv1 = new TextView(this);
        r1tv1.setText("Challenge: Try to get 25 MPH on this run.");
        row1.addView(r1tv1);

        //row 2 CURRENT SPEED
        TableRow row2 = new TableRow(this);
        TextView r2tv1 = new TextView(this);
        r2tv1.setText("Current Speed: 23 MPH");
        row2.addView(r2tv1);

        //row 3 SUCCESSFUL?
        TableRow row3 = new TableRow(this);
        TextView r3tv1 = new TextView(this);
        r3tv1.setText("Unsuccessful.");
        row3.addView(r3tv1);


        table.addView(row1);
        table.addView(row2);
        table.addView(row3);
    }

}
