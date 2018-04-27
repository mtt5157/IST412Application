package com.example.matthewtucker.ist412application.Activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.matthewtucker.ist412application.Models.RunDataModel;
import com.example.matthewtucker.ist412application.R;
import com.example.matthewtucker.ist412application.Util.AggregateRunData;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;


//Matt Tucker handled this class
public class AggregateGraphActivity extends AppCompatActivity {
    private AggregateRunData[] rundata = new AggregateRunData[5];
    AggregateRunData data;
    RunDataModel model;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aggregate_graph);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        model = RunDataModel.getInstance();
        double[] speeds = model.getSpeeds();
        GraphView graphView = (GraphView) findViewById(R.id.graph);
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[] {
                new DataPoint(0, speeds[0]),
                new DataPoint(1, speeds[1]),
                new DataPoint(2, speeds[2]),
                new DataPoint(3, speeds[3]),
                new DataPoint(4, speeds[4])
        });
        graphView.addSeries(series);



    }

}
