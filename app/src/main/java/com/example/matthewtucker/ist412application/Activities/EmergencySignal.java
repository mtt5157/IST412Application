package com.example.matthewtucker.ist412application.Activities;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.example.matthewtucker.ist412application.R;

import java.sql.Time;

public class EmergencySignal extends AppCompatActivity {

    private TableLayout submissionTable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency_signal);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        submissionTable = (TableLayout) findViewById(R.id.submitData);
        setTable();
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private void setTable()
    {
        //row1 ON/OFF
        TableRow row1 = new TableRow(this);
        TextView r1tv1 = new TextView(this);
        r1tv1.setText("Emergency signal: ");
        Switch onOff = new Switch(this);
        row1.addView(r1tv1);
        row1.addView(onOff);


        //row2 PHONENUMBER
        TableRow row2 = new TableRow(this);
        TextView r2tv1 = new TextView(this);
        r2tv1.setText("Phone Number: ");
        EditText phoneNumber = new EditText(this);
        row2.addView(r2tv1);
        row2.addView(phoneNumber);


        //row3 SECONDS
        TableRow row3 = new TableRow(this);
        TextView r3tv1 = new TextView(this);
        r3tv1.setText("How many seconds until emergency services are contacted: ");
        EditText seconds = new EditText(this);
        row3.addView(r3tv1);
        row3.addView(seconds);

        //row3 SUBMIT
        TableRow row4 = new TableRow(this);
        Button submission = new Button(this);
        submission.setText("submit");
        row4.addView(submission);

        submissionTable.addView(row1);
        submissionTable.addView(row2);
        submissionTable.addView(row3);
        submissionTable.addView(row4);
    }

}
