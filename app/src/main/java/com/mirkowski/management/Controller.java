package com.mirkowski.management;


import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import com.example.bartek.shipswar.MainActivity;
import com.example.bartek.shipswar.MapGameActivity;
import com.example.bartek.shipswar.RoomGameActivity;
import com.example.bartek.shipswar.SettingsActivity;
import com.example.bartek.shipswar.StatisticsActivity;
import com.example.bartek.shipswar.logic.Game;
import com.mirkowski.management.command.GameCommand;
import com.mirkowski.management.command.SystemCommand;
import com.mirkowski.settings.Settings;
import com.mirkowski.websocketclient.Message;
import com.mirkowski.websocketclient.WebSocketConnector;


import java.util.ArrayList;

/**
 * Created by Kamil on 2015-04-28.
 */
public class Controller implements Parcelable {

    private Settings settings = new Settings();// ustawienia apki
    private ConnectionManager connectionManager = null;
    private String opponentName = null;
    private Game game = null;
    private boolean isReady = false;
    private boolean isOpponentReady = false;
    private boolean isEnableGmae = false;

    //-----------------Activitys-----------------
       private MainActivity mainActivity = null;
       private RoomGameActivity roomGameActivity = null;
       private SettingsActivity settingsActivity = null;
       private StatisticsActivity statisticsActivity = null;
       private MapGameActivity mapGameActivity = null;
    //-------------------------------

    public void setMainActivity(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
        settings = new Settings(mainActivity.getSharedPreferences());
    }

    public void setRoomGameActivity(RoomGameActivity roomGameActivity) {
        this.roomGameActivity = roomGameActivity;
        Log.d("INFO",settings.getServerAdress());

    }

    public void setSettingsActivity(SettingsActivity settingsActivity) {
        this.settingsActivity = settingsActivity;
    }

    public void setStatisticsActivity(StatisticsActivity statisticsActivity) {
        this.statisticsActivity = statisticsActivity;
    }

    public void setMapGameActivity(MapGameActivity mapGameActivity) {
        this.mapGameActivity = mapGameActivity;
    }

    public Controller(){

        this.connectionManager = new ConnectionManager(this,new WebSocketConnector("ws://192.168.43.109:8080/WebSocketGlassfish/chat"),settings.getUserName());
//        this.connectionManager = new ConnectionManager(this,new WebSocketConnector(settings.getServerAdress()),settings.getUserName());
    }


//    public void setOpponentName(String opponentName) {
//        this.opponentName = opponentName;
//    }
    // metoda do ustawiena nazwu usera na takï¿½ pod jakï¿½ zostaï¿½ zarejstrowny na servie
    public void setOwnerName(String ownerName){
        settings.setUserName(ownerName);
    }

    // metoda do wyswietlania info od servera
    public void viewInfo(String senderName, String message){
        // dodaj metode do ustawienia textu na lebelu :)
        Log.d("INFO", message);
        roomGameActivity.setLabelText(senderName + " " + message);
    }



    public String getOpponentName() {
        return opponentName;
    }
    // tï¿½ metode podepnij do listy tak ï¿½ï¿½by przy karzdym rozwijaniu ï¿½adowaï¿½o caï¿½ï¿½ liste graczy

    public ArrayList<String> getPlayersList() {
        return connectionManager.getPlayerList();
    }


    // metoda do zapraszania
    public void inviteToGame(String playerName){
        connectionManager.sendMessage(new Message(settings.getUserName(),"System",SystemCommand.StartGameRequest.toString(),playerName));
    }
    // metoda do odpowiedzi na zaproszenie
    public void responseOnInviteToGame(boolean isAgree,String playerName){
        if(isAgree) {
            opponentName = playerName;
            connectionManager.sendMessage(new Message(settings.getUserName(), "System", SystemCommand.StartGameResponse.toString(), playerName));
        }
        else {
            connectionManager.sendMessage(new Message(settings.getUserName(), "System", SystemCommand.NoStartGameResponse.toString(), playerName));
        }
    }

    // zwarca sam widzisz co
    public boolean isReady() {
        return isReady;
    }
    // connector ustawia jesli przeciwnik jest gotowy
    public void setIsOpponentReady(boolean isOpponentReady) {
        this.isOpponentReady = isOpponentReady;
    }

    // metoda do echa , nie wiem czy bï¿½dzie potrzebna ale nie kasowaï¿½em
    public void echo(String result){

    }

    //metoda wywoï¿½ywanna przy wyjï¿½ciu z okna gry bï¿½dz z okna RoomGame
    public void destroy(){
        if(game != null){
            if(isOpponentReady == false){
                connectionManager.sendMessage(new Message(settings.getUserName(),"System",SystemCommand.LongTimeWaitingForPlayer.toString(),opponentName));
                game =null;
                opponentName =null;
                isOpponentReady = false;
                isReady = false;
                isEnableGmae =false;
            } else {
                connectionManager.close();
                game =null;
                opponentName =null;
                isOpponentReady = false;
                isReady = false;
                isEnableGmae =false;
            }
        } else if(opponentName != null && isEnableGmae){
            connectionManager.close();
            game =null;
            opponentName =null;
            isOpponentReady = false;
            isReady = false;
            isEnableGmae =false;
        } else if(opponentName != null ){
            connectionManager.sendMessage(new Message(settings.getUserName(),"System",SystemCommand.LongTimeWaitingForPlayer.toString(),opponentName));
            game =null;
            opponentName =null;
            isOpponentReady = false;
            isReady = false;
            isEnableGmae =false;
        }
    }

    private void destroy(SystemCommand result){
        game = null;
        opponentName = null;
        isOpponentReady = false;
        isReady = false;
        isEnableGmae = false;
    }

    // metoda wywoï¿½ywana przy zgodzie obu graczy na gre
    public void enableGame(){
        isEnableGmae = true;
        // ustaw button rozpocznij gre na enabled
        // i wywoï¿½aj metode createGame();
    }

    public void createGame(){
        // create nowy obiekt game
        // game = gameFactory.createGame() // coï¿½ takiego uchwyt do game juz jest w deklaracjach zmiennych klasy
        // jek stowrzysz to wywaoï¿½aj metode ready()
    }

    public void startTheGame(){
        // tu wstaw wszustko
    }

    public void endTheGame(SystemCommand result){
        switch (result){
            case Error:
                destroy(SystemCommand.Error);
                break;
            case Win:
                destroy(SystemCommand.Win);
                settings.incrementWinCount();
                break;
            case Defeat:
                destroy(SystemCommand.Defeat);
                settings.incrementDefeatCount();
                break;
            default:
        }
    }

    public void onChatMessage(String sender,String message){
        roomGameActivity.addTextToMessageView(sender,message);
    }
    public void sendChatMessage(String recipentName,String message){
        if("ALL".equals(recipentName))connectionManager.sendMessage(new Message(settings.getUserName(),recipentName,SystemCommand.ChatroomMessage.toString(),message));
        else connectionManager.sendMessage(new Message(settings.getUserName(),recipentName,SystemCommand.ChatMessage.toString(),message));
    }

    //gdy przujdzie zaproszenie
    public void onInvite(String senderName){
       // tutaj dodaj wyœwietlanie dialogu
       // responseOnInviteToGame(tutaj rezultat dialogu,senderName );

    }
    public void onRequest(String message){
        // sprawdzanie czy przeciwnik trafiï¿½ , wysï¿½anie responsa i zmaian trybu z nasï¿½uchu na nadawanie
        // masz do wysï¿½ana metode response np response(message,jakaï¿½ metoda sprawdxzajï¿½ca zwracajï¿½ca boolean(message))
    }

    public void onResponse(String message){
        // sprawdzanie czy my trafiismy  i zmana trybu na nasï¿½uch
    }

    public void request(String coordinates){
        if(game != null){
            connectionManager.sendMessage(new Message(settings.getUserName(),opponentName, GameCommand.GameRequest.toString(),coordinates));
        }
    }

    public void response(String coordinates,boolean result){
        if(game != null){
            if(result)coordinates += "1";
            else coordinates += "0";
            connectionManager.sendMessage(new Message(settings.getUserName(),opponentName, GameCommand.GameResponse.toString(),coordinates));
        }
    }

    public void ready(){
        if(isOpponentReady)startTheGame();
        else isReady = true;
    }

    public void changeServerAdress(String newSreverAdress){
        settings.setServerAdress(newSreverAdress);
    }

    public void changeUserName(String newUserName){
        settings.setUserName(newUserName);
    }


    // nadpisane
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }
}
