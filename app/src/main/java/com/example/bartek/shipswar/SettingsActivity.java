package com.example.bartek.shipswar;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.mirkowski.management.Controller;


public class SettingsActivity extends Activity {

    private Controller controller = null;
    private EditText userName = null;
    private EditText serverAdress = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        controller = MainActivity.controller;
        controller.setSettingsActivity(this);

        userName = (EditText) findViewById(R.id.userName);
        serverAdress = (EditText) findViewById(R.id.serverAdress);

        userName.setText(controller.getUserName());
        serverAdress.setText(controller.getServerAdress());

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_settings, menu);
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

    public void save(View view) {
        if(!serverAdress.getText().toString().equals("") && !controller.getServerAdress().equals(serverAdress.getText().toString()))
            controller.changeServerAdress(serverAdress.getText().toString());

        if(!userName.getText().toString().equals("") && !controller.getServerAdress().equals(userName.getText().toString()))
            controller.changeUserName(userName.getText().toString());
    }
}
