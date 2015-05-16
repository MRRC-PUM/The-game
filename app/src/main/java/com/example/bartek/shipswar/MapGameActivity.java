package com.example.bartek.shipswar;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bartek.shipswar.logic.GameFactory;


public class MapGameActivity extends ActionBarActivity {

    GameFactory gameFactory = new GameFactory();
    int currentCell=-1;

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
        int x=0, y=0;
        switch (view.getId()){
            case R.id.imageView00: x = 0; y = 0; break;
            case R.id.imageView01: x = 0; y = 1; break;
            case R.id.imageView02: x = 0; y = 2; break;
            case R.id.imageView03: x = 0; y = 3; break;
            case R.id.imageView04: x = 0; y = 4; break;
            case R.id.imageView05: x = 0; y = 5; break;
            case R.id.imageView06: x = 0; y = 6; break;
            case R.id.imageView07: x = 0; y = 7; break;
            case R.id.imageView08: x = 0; y = 8; break;
            case R.id.imageView09: x = 0; y = 9; break;

            case R.id.imageView10: x = 1; y = 0; break;
            case R.id.imageView11: x = 1; y = 1; break;
            case R.id.imageView12: x = 1; y = 2; break;
            case R.id.imageView13: x = 1; y = 3; break;
            case R.id.imageView14: x = 1; y = 4; break;
            case R.id.imageView15: x = 1; y = 5; break;
            case R.id.imageView16: x = 1; y = 6; break;
            case R.id.imageView17: x = 1; y = 7; break;
            case R.id.imageView18: x = 1; y = 8; break;
            case R.id.imageView19: x = 1; y = 9; break;

            case R.id.imageView20: x = 2; y = 0; break;
            case R.id.imageView21: x = 2; y = 1; break;
            case R.id.imageView22: x = 2; y = 2; break;
            case R.id.imageView23: x = 2; y = 3; break;
            case R.id.imageView24: x = 2; y = 4; break;
            case R.id.imageView25: x = 2; y = 5; break;
            case R.id.imageView26: x = 2; y = 6; break;
            case R.id.imageView27: x = 2; y = 7; break;
            case R.id.imageView28: x = 2; y = 8; break;
            case R.id.imageView29: x = 2; y = 9; break;

            case R.id.imageView30: x = 3; y = 0; break;
            case R.id.imageView31: x = 3; y = 1; break;
            case R.id.imageView32: x = 3; y = 2; break;
            case R.id.imageView33: x = 3; y = 3; break;
            case R.id.imageView34: x = 3; y = 4; break;
            case R.id.imageView35: x = 3; y = 5; break;
            case R.id.imageView36: x = 3; y = 6; break;
            case R.id.imageView37: x = 3; y = 7; break;
            case R.id.imageView38: x = 3; y = 8; break;
            case R.id.imageView39: x = 3; y = 9; break;

            case R.id.imageView40: x = 4; y = 0; break;
            case R.id.imageView41: x = 4; y = 1; break;
            case R.id.imageView42: x = 4; y = 2; break;
            case R.id.imageView43: x = 4; y = 3; break;
            case R.id.imageView44: x = 4; y = 4; break;
            case R.id.imageView45: x = 4; y = 5; break;
            case R.id.imageView46: x = 4; y = 6; break;
            case R.id.imageView47: x = 4; y = 7; break;
            case R.id.imageView48: x = 4; y = 8; break;
            case R.id.imageView49: x = 4; y = 9; break;

            case R.id.imageView50: x = 5; y = 0; break;
            case R.id.imageView51: x = 5; y = 1; break;
            case R.id.imageView52: x = 5; y = 2; break;
            case R.id.imageView53: x = 5; y = 3; break;
            case R.id.imageView54: x = 5; y = 4; break;
            case R.id.imageView55: x = 5; y = 5; break;
            case R.id.imageView56: x = 5; y = 6; break;
            case R.id.imageView57: x = 5; y = 7; break;
            case R.id.imageView58: x = 5; y = 8; break;
            case R.id.imageView59: x = 5; y = 9; break;

            case R.id.imageView60: x = 6; y = 0; break;
            case R.id.imageView61: x = 6; y = 1; break;
            case R.id.imageView62: x = 6; y = 2; break;
            case R.id.imageView63: x = 6; y = 3; break;
            case R.id.imageView64: x = 6; y = 4; break;
            case R.id.imageView65: x = 6; y = 5; break;
            case R.id.imageView66: x = 6; y = 6; break;
            case R.id.imageView67: x = 6; y = 7; break;
            case R.id.imageView68: x = 6; y = 8; break;
            case R.id.imageView69: x = 6; y = 9; break;

            case R.id.imageView70: x = 7; y = 0; break;
            case R.id.imageView71: x = 7; y = 1; break;
            case R.id.imageView72: x = 7; y = 2; break;
            case R.id.imageView73: x = 7; y = 3; break;
            case R.id.imageView74: x = 7; y = 4; break;
            case R.id.imageView75: x = 7; y = 5; break;
            case R.id.imageView76: x = 7; y = 6; break;
            case R.id.imageView77: x = 7; y = 7; break;
            case R.id.imageView78: x = 7; y = 8; break;
            case R.id.imageView79: x = 7; y = 9; break;

            case R.id.imageView80: x = 8; y = 0; break;
            case R.id.imageView81: x = 8; y = 1; break;
            case R.id.imageView82: x = 8; y = 2; break;
            case R.id.imageView83: x = 8; y = 3; break;
            case R.id.imageView84: x = 8; y = 4; break;
            case R.id.imageView85: x = 8; y = 5; break;
            case R.id.imageView86: x = 8; y = 6; break;
            case R.id.imageView87: x = 8; y = 7; break;
            case R.id.imageView88: x = 8; y = 8; break;
            case R.id.imageView89: x = 8; y = 9; break;

            case R.id.imageView90: x = 9; y = 0; break;
            case R.id.imageView91: x = 9; y = 1; break;
            case R.id.imageView92: x = 9; y = 2; break;
            case R.id.imageView93: x = 9; y = 3; break;
            case R.id.imageView94: x = 9; y = 4; break;
            case R.id.imageView95: x = 9; y = 5; break;
            case R.id.imageView96: x = 9; y = 6; break;
            case R.id.imageView97: x = 9; y = 7; break;
            case R.id.imageView98: x = 9; y = 8; break;
            case R.id.imageView99: x = 9; y = 9; break;



        }

        Log.d("MapGameActivity", "Kliknieto X: " + x + " Y: " + y);
        gameFactory.CreateGame(x, y);
        Log.d("MapGameActivity", "gameFactory.CreateGame: succes");
        display(gameFactory.getOwner());
        Log.d("MapGameActivity", "display: succes");


    }



    public void display(int[][] tab) {

        if(currentCell==-1 || currentCell == R.id.imageView99) { currentCell = R.id.imageView00;}
        for (int i = 0; i<9; i++)
           for (int j = 0; j<9; j++) {
               ImageView pole = (ImageView) findViewById(currentCell++);
               if (tab[j][i]==0) pole.setBackgroundColor(0xFF0000); // to bedzie jak beda grafiki... pole.setImageBitmap(Bitmap bm);
               else if (tab[j][i]==1) pole.setBackgroundColor(0xFF0011);//gdy obok statku
               else if (tab[j][i]==2) pole.setBackgroundColor(0xFF00FF);//gdy statek\

           }

    }

    public void changeView(){
    }


    public void onClickStartGame(View view) {

    }
}
