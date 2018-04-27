package com.example.matthewtucker.ist412application.Activities;

import android.graphics.Color;
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
import com.example.matthewtucker.ist412application.Util.RunData;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.ValueDependentColor;
import com.jjoe64.graphview.helper.StaticLabelsFormatter;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;


public class ChallengeMode extends AppCompatActivity {

    private TableLayout table;
    private Button startRun;
    private RunData runData;
    private TextView r2tv1;
    private TextView r3tv1;
    private GraphView graph;
    private BarGraphSeries<DataPoint> series;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_challenge_mode);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        runData = RunData.getInstance();
        table = (TableLayout) findViewById(R.id.challengeTable);
        fillTable();
        startRun = (Button) findViewById(R.id.challenge_start_run_button);
        startRun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                graph.addSeries(series);
                r2tv1.setText("Your speed: "+ runData.getMaxSpeed());
                if(runData.getMaxSpeed()>25){
                    r3tv1.setText("Result: Successful");
                }
                else{
                    r3tv1.setText("Result: Unsuccessful");
                }
            }
        });

        // MAtt Tucker created this graph code
        graph = (GraphView) findViewById(R.id.graph1);
        series = new BarGraphSeries<>(new DataPoint[] {
                new DataPoint(1, 25),
                new DataPoint(2, runData.getMaxSpeed())
        });

        StaticLabelsFormatter staticLabelsFormatter = new StaticLabelsFormatter(graph);
        staticLabelsFormatter.setHorizontalLabels(new String[] {"Speed Goal", "Your Speed"});
        graph.getGridLabelRenderer().setLabelFormatter(staticLabelsFormatter);

        series.setValueDependentColor(new ValueDependentColor<DataPoint>() {
            @Override
            public int get(DataPoint data) {
                return Color.rgb((int) data.getX()*255/4, (int) Math.abs(data.getY()*255/6), 100);
            }
        });

        series.setSpacing(30);
        series.setDrawValuesOnTop(true);
        series.setValuesOnTopColor(Color.RED);




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
        r2tv1 = new TextView(this);
        r2tv1.setText("Your Speed: ");
        row2.addView(r2tv1);

        //row 3 SUCCESSFUL?
        TableRow row3 = new TableRow(this);
        r3tv1 = new TextView(this);
        r3tv1.setText("Result: ");
        row3.addView(r3tv1);


        table.addView(row1);
        table.addView(row2);
        table.addView(row3);
    }

}
