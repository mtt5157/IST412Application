package com.example.matthewtucker.ist412application.Activities;

import android.content.Intent;
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

import com.example.matthewtucker.ist412application.Models.RunDataModel;
import com.example.matthewtucker.ist412application.R;
import com.example.matthewtucker.ist412application.Util.AggregateRunData;
import com.example.matthewtucker.ist412application.Util.RunData;

import java.sql.Time;
import java.util.Random;

public class ViewAggRunData extends AppCompatActivity {

    private TableLayout dataTable;
    private final Random random = new Random();
    private AggregateRunData[] rundata = new AggregateRunData[5];
    private RunDataModel model;
    private Button switchToGraph;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_agg_run_data);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        model = RunDataModel.getInstance();
        dataTable = (TableLayout) findViewById(R.id.runDataTable);
        generateRunData();
        addRunData();

        switchToGraph = (Button) findViewById(R.id.switch_to_graph);
        switchToGraph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ViewAggRunData.this, AggregateGraphActivity.class));
            }
        });



    }

    private void addRunData()
    {
        //row1 TIME
        TableRow row1 = new TableRow(this);
        TextView r1tv1 = new TextView(this);
        r1tv1.setText("Total Time: ");
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
        int maxSpeed = rundata[0].getMaxSpeed();
        for (int i = 1;i<5;i++) {
            if (rundata[i].getMaxSpeed() > maxSpeed) {
                maxSpeed = rundata[i].getMaxSpeed();
                model.addSpeed(i,rundata[i].getMaxSpeed());
            }
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
        float totalSpeed = 0;
        for (int i=0;i<5;i++)
        {
            totalSpeed += rundata[i].getAverageSpeed();
        }
        float avgSpeed = totalSpeed/5;

        TextView r3tv2 = new TextView(this);
        r3tv2.setText(Float.toString(avgSpeed));
        row3.addView(r3tv1);
        row3.addView(r3tv2);

        //row4 MAXHEIGHT
        TableRow row4 = new TableRow(this);
        TextView r4tv1 = new TextView(this);
        r4tv1.setText("Max Height: ");
        //row4 find random max height
        int maxHeight = rundata[0].getMaxHeight();
        for (int i = 1;i<5;i++)
            if (rundata[i].getMaxHeight()>maxHeight)
                maxHeight = rundata[i].getMaxHeight();

        TextView r4tv2 = new TextView(this);
        r4tv2.setText(Integer.toString(maxHeight));
        row4.addView(r4tv1);
        row4.addView(r4tv2);

        dataTable.addView(row1);
        dataTable.addView(row2);
        dataTable.addView(row3);
        dataTable.addView(row4);
    }

    private void generateRunData()
    {
        for (int i=0;i<5;i++)
            rundata[i] = new AggregateRunData();
    }

}
