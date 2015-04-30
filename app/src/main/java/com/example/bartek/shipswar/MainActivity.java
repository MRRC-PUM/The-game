package com.example.bartek.shipswar;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;




public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

    public void onClickButtonGraj(View view) {
        Intent intent = new Intent(this, RoomGameActivity.class);
        startActivity(intent);
    }

    public void onClickButtonOpcje(View view) {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }

    public void onClickButtonStatystyki(View view) {
        Intent intent = new Intent(this, StatisticsActivity.class);

        startActivity(intent);
    }

    public void onClickButtonWyjscie(View view) {
        System.exit(0);
    }

    //po rozmowie
    private void createControler(Activity owner){
        //Controler controler = new Controler(owner);
    }

    //public Controler getControler(){
   //
     //   return ;
    //}
}