package com.example.bartek.shipswar;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class MapGameActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_game);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_map_game, menu);
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





    //po rozmowie
    public void setOwnerNameLabel(String NAME){
        TextView textView = (TextView) findViewById(R.id.texViewCurrentPlayer);
        textView.setText(NAME);
    }

    public void setOpponentNameLabel(String NAME){
        TextView textView = (TextView) findViewById(R.id.texViewCurrentPlayer);
        textView.setText(NAME);
    }

    public void setMode(String NAME){
    }
    //->
    public void onClickButton(View view) {
        Toast.makeText(this, "kliknales mnei", Toast.LENGTH_SHORT).show();
    }

    public void display(int[][] tab) {
    int currentCell = R.id.imageView00;
        for (int i = 0; i<tab.length; i++)
           for (int j = 0; i<tab.length; j++) {
               ImageView pole = (ImageView) findViewById(currentCell);
               if (tab[j][i]==0) pole.setBackgroundColor(0xFF0000); // to bedzie jak beda grafiki... pole.setImageBitmap(Bitmap bm);
               else if (tab[j][i]==1) pole.setBackgroundColor(0xFF0011);//gdy obok statku
               else if (tab[j][i]==2) pole.setBackgroundColor(0xFF00FF);//gdy statek\

           }

    }

    public void changeView(){
    }
}
