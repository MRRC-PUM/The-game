package com.example.bartek.shipswar;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;

import com.mirkowski.management.ConnectionManager;
import com.mirkowski.websocketclient.WebSocketConnector;
import com.mirkowski.websocketclient.Message;

import java.util.List;

import de.tavendo.autobahn.WebSocketMessage;


public class RoomGameActivity extends ActionBarActivity {

    TextView messageView;
    EditText textMessage;
    Spinner spinner;
    EditText userNeme;
    Button buttonStartGame;

// Szanowny kamilu jak cos dopisujesz swojego to przynajmniej w konstruktorach przekazuj to co sam wymyslilles by przekazywac
    //dziekuje i pozdrawiam i nie polecam xD
ConnectionManager connectionManagerWYBRAKOWANY;
    String serverURI;
    //i przekazalem je liniie nizej wybacz ale nei mialem jak tego zrobic inaczej bo nei chcialem ci bardziej modzic

    WebSocketConnector connectionManager = new WebSocketConnector(connectionManagerWYBRAKOWANY, serverURI);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_game);
        messageView = (TextView) findViewById(R.id.textMessageView);
        textMessage = (EditText) findViewById(R.id.editMessage);
        spinner = (Spinner) findViewById(R.id.spinner);
        userNeme = (EditText) findViewById(R.id.editUserName);
        buttonStartGame = (Button) findViewById(R.id.buttonRozpocznijGre);
        buttonStartGame.setEnabled(true); //true musi byc dla testow moich :) potem sie ustawi false

        //!!!!!!!!!!!!!!!!!!!!!!!!! connectionManager.setMessageView(messageView);
        /*connectionManager.start();    /*<----KAMIL !!!!!!!!!!!!!!!! jak zakomentuje ta
        linie to przechodzi do okna jak nie jest
        zakomentowana to wykrzacza sie program,
        pewnie kwestia ze nie sa inne rzeczy poustawiane
        ale co test to ja poprostu komentuje narazie a to napisalem tak bys wiedzial.*/


        Spinner spinner = (Spinner) findViewById(R.id.spinner);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                        R.array.players_test_names, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);


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
    }

    public void sendMessage(View view) {
        connectionManager.send(new Message(Build.MANUFACTURER, userNeme.getText().toString(), "Message", textMessage.getText().toString()));
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

    //!!!!!!!!!!!!!!!!!!!!!! czy pasuje Ci
    public void invite(){
        getSelectedPlayer();
    }

    public void setLabelText(String text){
        TextView textView = (TextView) findViewById(R.id.textViewOtherPLayerWaiting);
        textView.setText(text);

    }

    public void setEnabled(Boolean flag){
        buttonStartGame.setEnabled(flag);
    }

    public void startGame(){
    }

    public void addTextToMessageView(String text){ //gdzie text to jest wiadomosc ktora odebralismy
        TextView textMessageView = (TextView) findViewById(R.id.textMessageView);
        String textInMessegeViev = textMessageView.getText().toString();
        textInMessegeViev = textInMessegeViev + text;
        textMessageView.setText(textInMessegeViev);
    }

    public String getMessageText(){
    EditText editText = (EditText) findViewById(R.id.editMessage);
        return  editText.getText().toString();
    }

    public void sendChatMessage(){
    }


}
