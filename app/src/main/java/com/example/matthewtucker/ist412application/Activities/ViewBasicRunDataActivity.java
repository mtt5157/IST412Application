package com.example.matthewtucker.ist412application.Activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.matthewtucker.ist412application.R;

import java.sql.Time;
import java.util.Random;

public class ViewBasicRunDataActivity extends AppCompatActivity {

    private TableLayout dataTable;
    private final Random random = new Random();
    private Button shareRideData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_basic_run_data);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        dataTable = (TableLayout) findViewById(R.id.submitData);
        addRandomData();



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private void addRandomData()
    {
        //row1 TIME
        TableRow row1 = new TableRow(this);
        TextView r1tv1 = new TextView(this);
        r1tv1.setText("Time: ");
        //row1 random time
        int millisInDay = 24*60*60*1000;
        Time time = new Time((long)random.nextInt(millisInDay));
        TextView r1tv2 = new TextView(this);
        r1tv2.setText(time.toString());
        row1.addView(r1tv1);
        row1.addView(r1tv2);


        //row2 MAXSPEED
        TableRow row2 = new TableRow(this);
        TextView r2tv1 = new TextView(this);
        r2tv1.setText("Max Speed: ");
        //row2 random speed array
        int[] speeds = new int[5];
        int maxSpeed = 0;
        for (int i=0;i<5;i++)
        {
            speeds[i] = random.nextInt(50);
            if (speeds[i] > maxSpeed)
                maxSpeed = speeds[i];
        }

        TextView r2tv2 = new TextView(this);
        r2tv2.setText(Integer.toString(maxSpeed));
        row2.addView(r2tv1);
        row2.addView(r2tv2);

        //row3 AVGSPEED
        TableRow row3 = new TableRow(this);
        TextView r3tv1 = new TextView(this);
        r3tv1.setText("Average Speed: ");
        //row3 find avg speed
        int totalSpeed = 0;
        for (int i=0;i<5;i++)
        {
            totalSpeed += speeds[i];
        }
        int avgSpeed = totalSpeed/5;

        TextView r3tv2 = new TextView(this);
        r3tv2.setText(Integer.toString(avgSpeed));
        row3.addView(r3tv1);
        row3.addView(r3tv2);

        //row4 MAXHEIGHT
        TableRow row4 = new TableRow(this);
        TextView r4tv1 = new TextView(this);
        r4tv1.setText("Max Height: ");
        //row4 find random max height
        int maxHeight = random.nextInt(15);

        TextView r4tv2 = new TextView(this);
        r4tv2.setText(Integer.toString(maxHeight));
        row4.addView(r4tv1);
        row4.addView(r4tv2);

        dataTable.addView(row1);
        dataTable.addView(row2);
        dataTable.addView(row3);
        dataTable.addView(row4);
    }

}