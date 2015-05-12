package com.mirkowski.management;


import android.content.Intent;

import com.example.bartek.shipswar.MainActivity;
import com.example.bartek.shipswar.MapGameActivity;
import com.example.bartek.shipswar.MapPlayActivity;
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
public class Controller {

    private Settings settings = new Settings();// ustawienia apki
    private ConnectionManager connectionManager = null;
    private String opponentName = null;
    private Game game = null;
    private boolean isReady= false;
    private boolean isOpponentReady = false;
    private boolean isEnableGmae = false;
    private boolean imHost = false;

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

        this.connectionManager = new ConnectionManager(this,new WebSocketConnector(settings.getServerAdress()),settings.getUserName());
    }


//    public void setOpponentName(String opponentName) {
//        this.opponentName = opponentName;
//    }


    // metoda do ustawiena nazwu usera na tak¹ pod jak¹ zosta³ zarejstrowny na servie
    public void setOwnerName(String ownerName){
        settings.setUserName(ownerName);
    }

    // metoda do wyswietlania info od servera
    public void viewInfo(String senderName, String message){
        // dodaj metode do ustawienia textu na lebelu :)
    }

    public String getOpponentName() {
        return opponentName;
    }
    // t¹ metode podepnij do listy tak ¿êby przy karzdym rozwijaniu ³adowa³o ca³¹ liste graczy
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

    // metoda do echa , nie wiem czy bêdzie potrzebna ale nie kasowa³em
    public void echo(String result){

    }

    //metoda wywo³ywanna przy wyjœciu z okna gry b¹dz z okna RoomGame
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

    // metoda wywo³ywana przy zgodzie obu graczy na gre
    public void enableGame(){
        isEnableGmae = true;
        if(roomGameActivity!=null){
            roomGameActivity.setEnabled(true);           // ustaw button rozpocznij gre na enabled
            createGame();                                // i wywo³aj metode createGame();
        }
    }

    public void createGame(){
        Game game = new Game();// create nowy obiekt game
        //?????????????????????????
        // game = gameFactory.createGame() // coœ takiego uchwyt do game juz jest w deklaracjach zmiennych klasy
        ready();// jek stowrzysz to wywao³aj metode ready()
    }

    public void startTheGame(){

                //wystartuj activity na ktorym gramy
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


    //sprawdza czy wróg trafil i odpowiada mu
    public void onRequest(String message){
        int x = Integer.valueOf(message.charAt(0));
        int y = Integer.valueOf(message.charAt(1));

       if (game.OpponentShot(x, y)) {        //sprawdzanie czy przeciwnik trafi³ , wys³anie responsa i zmaian trybu z nas³uchu na nadawanie
           response(""+x+""+y, true);       // masz do wys³ana metode response np response(message,jakaœ metoda sprawdxzaj¹ca zwracaj¹ca boolean(message))
       } else response(""+x+""+y, false);



    }

    //konwertuje poszczegolne znaki Stringa na wspó³rzêdne i status trafienia,
    //ustawia strzal/pudlo w tablicy
    //zmienia na nasluch
    public void onResponse(String message){
        int x = Integer.valueOf(message.charAt(0));
        int y = Integer.valueOf(message.charAt(1));
        int status = Integer.valueOf(message.charAt(2));

            game.setShotOpponentMap(x, y, status);        // sprawdzanie czy my trafiismy iustawienie tego na naszej mapce Opponenta
            game.setMode(game.listen);       //i zmana trybu na nas³uch


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

    public void setImHost(boolean flag) {
        this.imHost=flag;
    }
}
