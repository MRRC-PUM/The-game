package com.example.bartek.shipswar;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.mirkowski.management.Controller;
import com.mirkowski.management.connectionType.ConnectionType;


public class ChoseConnectionTypeActivity extends ActionBarActivity {
    private  Controller controller = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chose_connection_type);
        controller = MainActivity.controller;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_chose_connection_type, menu);
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

    public void onClickConnectionViaInternet(View view) {
        controller.setConnectionManager(ConnectionType.Internet);
        Intent intent = new Intent(this, RoomGameActivity.class);
        startActivity(intent);
    }
}
