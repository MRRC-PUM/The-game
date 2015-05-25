package com.example.bartek.shipswar;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.mirkowski.management.Controller;

import java.util.List;


public class RoomGameActivity extends Activity {

    private TextView messageView;
    private TextView viewInfo;
    private EditText textMessage;
    private Spinner spinner;
    private EditText userNeme;
    private Controller controller = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       controller = MainActivity.controller;
        
        controller.setRoomGameActivity(this);
       // setPlayersList(controller.getPlayersList());
        setContentView(R.layout.activity_room_game);
        viewInfo = (TextView) findViewById(R.id.textViewOtherPLayerWaiting);
        messageView = (TextView) findViewById(R.id.textMessageView);
        textMessage = (EditText) findViewById(R.id.editMessage);
        spinner = (Spinner) findViewById(R.id.spinner);
        userNeme = (EditText) findViewById(R.id.editUserName);
        setEnabled(false);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_room_game, menu);
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

    public void onClickButtonRozpocznijGre(View view) {
        Intent intent = new Intent(this, MapGameActivity.class);
        startActivity(intent);
    }

    public void onClickButtonZapros(View view) {

        controller.inviteToGame(getSelectedPlayer());
    }

    public void sendMessage(View view) {
        String tempname = getSelectedPlayer();
        if("Web".equals(tempname))
             controller.sendChatMessage("ALL",textMessage.getText().toString());
        else controller.sendChatMessage(tempname,textMessage.getText().toString());

        textMessage.setText("");
    }

    ///po rozmowie
    public void setPlayersList(List list_get){
        try {
        spinner = (Spinner) findViewById(R.id.spinner);
        List<String> list = list_get;

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);

            spinner.setAdapter(dataAdapter);
        }catch (NullPointerException npe){
            controller.getPlayersList();
        }catch (Exception e){ }

    }

    //Kamil zamienilem to tak jak napisalem w documencie na stronie
    private String getSelectedPlayer(){
        try {
            spinner = (Spinner) findViewById(R.id.spinner);
            return String.valueOf(spinner.getSelectedItem());
        }catch (NullPointerException npe){
            return null;
        }
    }

    public void invite(){
        getSelectedPlayer();
        controller.setImCurrentPlayer(true);
    }

    public void setLabelText(String text){
        viewInfo.setText(text);
    }

    public void setEnabled(boolean flag){
        Button button = (Button) findViewById(R.id.buttonRozpocznijGre);
        button.setEnabled(flag);
    }

    public void startGame(){
    }

    public void addTextToMessageView(String senderName,String message){
        messageView.setText(messageView.getText().toString() + senderName+": "+message + "\n");
    }

    public void getMessageText(){
    }

    public void sendChatMessage(){
    }


    public void expandList(View view) {

        setPlayersList(controller.getPlayersList());
    }

    public void showDialog(String name){
        FragmentManager manager = getFragmentManager();

        WantPlayFragment invet = new WantPlayFragment();
        invet.setName(name);
        invet.setController(controller);
        invet.show(manager, "WantPlayFragment");
    }


    public void showDialog2(){
        FragmentManager manager = getFragmentManager();

        EndingFragment invet = new EndingFragment();

        invet.setController(controller);
        invet.show(manager, "EndingFragment");
    }
    public void destroy() {
        super.onDestroy();
    }



}
