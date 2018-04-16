package com.example.matthewtucker.ist412application;

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

import java.sql.Time;

public class EmergencySignal extends AppCompatActivity {

    private TableLayout submissionTable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency_signal);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
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

    private void submitForm()
    {
        Switch onOff = (Switch) findViewById(R.id.onOff);
        TextView phoneNumber = (TextView) findViewById(R.id.phoneNumber);
        TextView seconds = (TextView) findViewById(R.id.seconds);
        String phone = (String) phoneNumber.getText();
        String sec = (String) seconds.getText();

        emergencyForm newSignal = new emergencyForm(phone, true, sec);

    }



}
