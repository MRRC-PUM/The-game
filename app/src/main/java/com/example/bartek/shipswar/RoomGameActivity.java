package com.example.bartek.shipswar;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
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
import com.mirkowski.websocketclient.WebSocketConnector;
import com.mirkowski.websocketclient.Message;

import java.util.List;


public class RoomGameActivity extends ActionBarActivity {

    TextView messageView;
    EditText textMessage;
    Spinner spinner;
    EditText userNeme;
    Controller controller = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        controller = (Controller)intent.getParcelableExtra("controler");
        controller.setRoomGameActivity(this);
        setPlayersList(controller.getPlayersList());
        setContentView(R.layout.activity_room_game);
        messageView = (TextView) findViewById(R.id.textMessageView);
        textMessage = (EditText) findViewById(R.id.editMessage);
        spinner = (Spinner) findViewById(R.id.spinner);
        userNeme = (EditText) findViewById(R.id.editUserName);


        Button buttonRozpocznij = (Button) findViewById(R.id.buttonRozpocznijGre);
        buttonRozpocznij.setEnabled(false);


        Spinner spinner = (Spinner) findViewById(R.id.spinner);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                        R.array.players_test_names, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);


    }
//    public SharedPreferences getSharedPreferences(){
//        return getSharedPreferences("com.mirkowski.settings", Context.MODE_PRIVATE);
//    }

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
    }

    public void sendMessage(View view) {
        controller.sendChatMessage("ALL",textMessage.getText().toString());
    }

    ///po rozmowie
    public void setPlayersList(List list_get){
        spinner = (Spinner) findViewById(R.id.spinner);
        List<String> list = list_get;

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);

        spinner.setAdapter(dataAdapter);

    }

    //Kamil zamienilem to tak jak napisalem w documencie na stronie
    private String getSelectedPlayer(){
        spinner = (Spinner) findViewById(R.id.spinner);
        return String.valueOf(spinner.getSelectedItem());
    }

    public void invite(){
        getSelectedPlayer();
        controller.setImHost(true);
    }

    public void setLabelText(String text){
        TextView textView = (TextView) findViewById(R.id.textViewOtherPLayerWaiting);
        textView.setText(text);
    }

    public void setEnabled(Boolean flag){
        Button buttonRozpocznij = (Button) findViewById(R.id.buttonRozpocznijGre);
        buttonRozpocznij.setEnabled(true);
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


}
