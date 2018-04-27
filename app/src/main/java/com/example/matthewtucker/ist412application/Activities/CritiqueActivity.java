package com.example.matthewtucker.ist412application.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.matthewtucker.ist412application.R;
import com.example.matthewtucker.ist412application.Util.RunData;

// Matt Peron took the lead on this class with help from Matt Tucker and Adam Warfield
public class CritiqueActivity extends AppCompatActivity {
    private RunData data;
    private double perfectSpeed;
    private double perfectHeight;
    private double perfectRunTime;
    private TextView speed;
    private TextView height;
    private TextView time;
    private TextView speedCritique;
    private TextView heightCritique;
    private TextView timeCritique;
    private Button getCritique;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_critique);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        data = RunData.getInstance();
        perfectSpeed = 25.5;
        perfectHeight = 20.5;
        perfectRunTime = 55;

        speed = (TextView) findViewById(R.id.speed_view);
        speed.setText("Max Speed: "+data.getMaxSpeed()+" MPH");
        height = (TextView) findViewById(R.id.height_view);
        height.setText("Max Height: "+data.getMaxHeight()+ " Feet");
        time = (TextView) findViewById(R.id.time_view);
        time.setText("Run Time: "+ data.getTime()+" seconds");
        speedCritique = (TextView) findViewById(R.id.speed_critique_view);
        speedCritique.setText("Speed Critique:");
        heightCritique = (TextView) findViewById(R.id.height_critique_view);
        heightCritique.setText("Height Critique:");
        timeCritique = (TextView) findViewById(R.id.time_critique);
        timeCritique.setText("Time Critique:");
        getCritique = (Button) findViewById(R.id.get_critique);
        getCritique.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(data.getMaxSpeed()<perfectSpeed){
                    speedCritique.setText("Speed Critique: Bend your knees to be more aerodynamic");
                }
                else{
                    speedCritique.setText("Speed Critique: Speed looks good");
                }

                if(data.getMaxHeight()<perfectHeight){
                    heightCritique.setText("Height Critique: Bend you knees and jump forward off of the snow jump");
                }
                else{
                    heightCritique.setText("Height Critique: Height looks good");
                }

                timeCritique.setText("Time is dependant on trail length");
            }
        });




    }

}
