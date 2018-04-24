package com.example.matthewtucker.ist412application.Activities;

import android.content.Intent;
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
import com.example.matthewtucker.ist412application.Models.emergencyForm;

import java.sql.Time;

public class EmergencySignal extends AppCompatActivity {

    private Button submitForm;

    private TableLayout submissionTable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency_signal);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        submitForm = (Button) findViewById(R.id.submit);
        submitForm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitForm();
                startActivity(new Intent(EmergencySignal.this, MainActivity.class));
            }
        });
    }

    private void submitForm()
    {
        Switch onOff = (Switch) findViewById(R.id.onOff);
        TextView phoneNumber = (TextView) findViewById(R.id.phoneNumber);
        TextView seconds = (TextView) findViewById(R.id.seconds);
        String phone = ""+ phoneNumber.getText();
        String sec = ""+ seconds.getText();

        emergencyForm newSignal = new emergencyForm(phone, true, sec);


    }



}
