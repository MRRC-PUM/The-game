package com.example.bartek.shipswar;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


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

    public void onClickChange(View view) {
    }

    public void onClickButton00(View view) {
    }

    public void onClickButton10(View view) {
    }

    public void onClickButton20(View view) {
    }

    public void onClickButton30(View view) {
    }

    public void onClickButton40(View view) {
    }

    public void onClickButton50(View view) {
    }

    public void onClickButton60(View view) {
    }

    public void onClickButton01(View view) {
    }

    public void onClickButton11(View view) {
    }

    public void onClickButton21(View view) {
    }

    public void onClickButton31(View view) {
    }

    public void onClickButton41(View view) {
    }

    public void onClickButton51(View view) {
    }

    public void onClickButton61(View view) {
    }

    public void onClickButton02(View view) {
    }

    public void onClickButton12(View view) {
    }

    public void onClickButton22(View view) {
    }

    public void onClickButton32(View view) {
    }

    public void onClickButton42(View view) {
    }

    public void onClickButton52(View view) {
    }

    public void onClickButton62(View view) {
    }

    public void onClickButton03(View view) {
    }

    public void onClickButton13(View view) {
    }

    public void onClickButton23(View view) {
    }

    public void onClickButton33(View view) {
    }

    public void onClickButton43(View view) {
    }

    public void onClickButton53(View view) {
    }

    public void onClickButton63(View view) {
    }

    public void onClickButton04(View view) {
    }

    public void onClickButton14(View view) {
    }

    public void onClickButton24(View view) {
    }

    public void onClickButton34(View view) {
    }

    public void onClickButton44(View view) {
    }

    public void onClickButton54(View view) {
    }

    public void onClickButton64(View view) {
    }

    public void onClickButton05(View view) {
    }

    public void onClickButton15(View view) {
    }

    public void onClickButton25(View view) {
    }

    public void onClickButton35(View view) {
    }

    public void onClickButton45(View view) {
    }

    public void onClickButton55(View view) {
    }

    public void onClickButton65(View view) {
    }

    public void onClickButton06(View view) {
    }

    public void onClickButton16(View view) {
    }

    public void onClickButton26(View view) {
    }

    public void onClickButton36(View view) {
    }

    public void onClickButton46(View view) {
    }

    public void onClickButton56(View view) {
    }

    public void onClickButton66(View view) {
    }

    //po rozmowie
    public void setOwnerNameLabel(String NAME){
    }

    public void setOpponentNameLabel(String NAME){
    }

    public void setMode(String NAME){
    }
    //->
    public void onClick(){
    }

    //public void display(Tab[][]) {
    //}

    public void changeView(){
    }
}
