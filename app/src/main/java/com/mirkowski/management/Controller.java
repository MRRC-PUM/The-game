package com.mirkowski.management;


import android.util.Log;
import android.widget.Toast;

import com.example.bartek.shipswar.MainActivity;
import com.example.bartek.shipswar.MapGameActivity;
import com.example.bartek.shipswar.MapPlayActivity;
import com.example.bartek.shipswar.RoomGameActivity;
import com.example.bartek.shipswar.SettingsActivity;
import com.example.bartek.shipswar.StatisticsActivity;
import com.example.bartek.shipswar.logic.Game;
import com.mirkowski.management.command.GameCommand;
import com.mirkowski.management.command.SystemCommand;
import com.mirkowski.management.connectionType.ConnectionType;
import com.mirkowski.settings.Settings;
import com.mirkowski.websocketclient.Message;
import com.mirkowski.websocketclient.WebSocketConnector;


import java.util.ArrayList;

/**
 * Created by Kamil on 2015-04-28.
 */
public class Controller {

    private Settings settings = new Settings();// ustawienia apki
    private ConnectionManager connectionManager = null;
    private String opponentName = null;
    private Game game = null;
    private boolean isReady = false;
    private boolean isOpponentReady = false;
    private boolean isEnableGmae = false;
    //kamil dodalem to
       private boolean imCurrentPlayer = false;

    //-----------------Activitys-----------------
       private MainActivity mainActivity = null;
       private RoomGameActivity roomGameActivity = null;
       private SettingsActivity settingsActivity = null;
       private StatisticsActivity statisticsActivity = null;
       private MapGameActivity mapGameActivity = null;
       private MapPlayActivity mapPlayActivity = null;
    //-------------------------------


    public void setRoomGameActivity(RoomGameActivity roomGameActivity) {
        this.roomGameActivity = roomGameActivity;
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

    public void setMapPlayActivity(MapPlayActivity mapPlayActivity){
        this.mapPlayActivity = mapPlayActivity;
    }
    
    public Controller(MainActivity mainActivity){
        this.mainActivity = mainActivity;
        settings = new Settings(mainActivity.getSharedPreferences());
    }

    public void setConnectionManager(ConnectionType connectionType) {
        switch (connectionType) {
            case Internet:
                this.connectionManager = new ConnectionManager(this,new WebSocketConnector(settings.getServerAdress()),settings.getUserName());
                break;
            case Bluetooth:
                // nizioł
                break;
        }
    }

    public void setOpponentName(String opponentName) {
        this.opponentName = opponentName;
    }
    // metoda do ustawiena nazwu usera na tak� pod jak� zosta� zarejstrowny na servie
    public void setOwnerName(String ownerName){
        settings.setUserName(ownerName);
    }

    // metoda do wyswietlania info od servera
    public void viewInfo(String senderName, String message){
        Log.d("INFO", message);
        if(roomGameActivity != null) roomGameActivity.setLabelText(senderName + " " + message);
        else Toast.makeText(mainActivity.getApplicationContext(),message,Toast.LENGTH_LONG);
    }



    public String getOpponentName() {
        return opponentName;
    }
    // t� metode podepnij do listy tak ��by przy karzdym rozwijaniu �adowa�o ca�� liste graczy

    public ArrayList<String> getPlayersList() {
        return connectionManager.getPlayerList();
    }


    // metoda do zapraszania
    public void inviteToGame(String playerName){
        opponentName = playerName;
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

    // metoda do echa , nie wiem czy b�dzie potrzebna ale nie kasowa�em
    public void echo(String result){

    }

    //metoda wywo�ywanna przy wyj�ciu z okna gry b�dz z okna RoomGame
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

    // metoda wywo�ywana przy zgodzie obu graczy na gre
    public void enableGame(){
        isEnableGmae = true;
        roomGameActivity.setEnabled(true);
        // ustaw button rozpocznij gre na enabled
        // i wywo�aj metode createGame();
        //room game
        
    }

    public void createGame(Game game){
        // create nowy obiekt game

        // co� takiego uchwyt do game juz jest w deklaracjach zmiennych klasy
        // co� takiego uchwyt do game juz jest w deklaracjach zmiennych klasy
        this.game = game;// co� takiego uchwyt do game juz jest w deklaracjach zmiennych klasy
        game.setMode(imCurrentPlayer);
              ready();

    }

    public void startTheGame(){
        // tu wstaw wszustko
        mapGameActivity.startMapGameActivity();
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
        roomGameActivity.showDialog(senderName);

    }
    public void onRequest(String message){
        // sprawdzanie czy przeciwnik trafi� , wys�anie responsa i zmaian trybu z nas�uchu na nadawanie
        // masz do wys�ana metode response np response(message,jaka� metoda sprawdxzaj�ca zwracaj�ca boolean(message))
    }

    public void onResponse(String message){
        // sprawdzanie czy my trafiismy  i zmana trybu na nas�uch
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
        else {
                        isReady = true;
                        connectionManager.sendMessage(new Message(settings.getUserName(),opponentName, GameCommand.Ready.toString(),settings.getUserName()));
                    }
    }

    public void changeServerAdress(String newSreverAdress){
        settings.setServerAdress(newSreverAdress);
    }

    public void changeUserName(String newUserName){
        settings.setUserName(newUserName);
    }

    public Game getGame(){
        return this.game;
    }

    public boolean isImCurrentPlayer() {
                return imCurrentPlayer;
            }

        public void setImCurrentPlayer(boolean imCurrentPlayer) {
                this.imCurrentPlayer = imCurrentPlayer;

            }


    public String getServerAdress(){
                return settings.getServerIP();
            }

                public String getUserName(){
                return settings.getUserName();
            }

        public String getWinCount() {
                return settings.getWinCount();
            }

        public String getDefeatCount() {
                return settings.getDefeatCount();
            }

}





