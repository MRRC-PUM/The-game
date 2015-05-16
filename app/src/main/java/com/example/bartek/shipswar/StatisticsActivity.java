package com.example.bartek.shipswar;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.mirkowski.management.Controller;


public class StatisticsActivity extends ActionBarActivity {

    private Controller controller = null;
    private TextView winCount = null;
    private TextView defeatCount = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);
        controller = MainActivity.controller;
        controller.setStatisticsActivity(this);
        winCount = (TextView) findViewById(R.id.textViewWinners);
        defeatCount = (TextView) findViewById(R.id.textViewLosses);
       // winCount.setText(controller.getWinCount());
       // defeatCount.setText(controller.getDefeatCount());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_statistics, menu);
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
