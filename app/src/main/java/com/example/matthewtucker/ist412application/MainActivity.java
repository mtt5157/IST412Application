package com.example.matthewtucker.ist412application;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button connectToBoard;
    private Button switchToRunData;
    private Button switchToAggData;
    private Button switchToCritique;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        connectToBoard = (Button) findViewById(R.id.switch_to_connect);

        connectToBoard.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                System.out.println("clicked");
                startActivity(new Intent(MainActivity.this, ClientActivity.class));
            }
        });

       switchToRunData = (Button) findViewById(R.id.switch_to_run_data);
       switchToRunData.setOnClickListener(new View.OnClickListener(){
           @Override
           public void onClick(View v){
               startActivity(new Intent(MainActivity.this,ViewBasicRunDataActivity.class));
           }
       });

        switchToAggData= (Button) findViewById(R.id.switch_to_agg_data);

        switchToAggData.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                System.out.println("clicked");
                startActivity(new Intent(MainActivity.this, ViewAggRunData.class));
            }
        });

        switchToCritique  = (Button) findViewById(R.id.switch_to_critique);

        switchToCritique.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(MainActivity.this, CritiqueActivity.class));
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
