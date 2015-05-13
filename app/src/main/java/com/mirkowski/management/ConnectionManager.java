package com.mirkowski.management;

import android.util.Log;

import com.mirkowski.management.command.GameCommand;
import com.mirkowski.management.command.SystemCommand;
import com.mirkowski.management.status.ConnectionStatus;
import com.mirkowski.websocketclient.Message;
import com.mirkowski.websocketclient.MessagePlayersList;

import java.util.ArrayList;

/**
 * Created by Kamil on 2015-04-28.
 */
public class ConnectionManager {

    private final String SERVERNAME = "System";

    private Controller controller = null;
    private Connector connector = null;
    private String ownerName = null;
    private ArrayList<String> playerList = null;

    public ConnectionManager(Controller controller , Connector connector,String ownerName){
        this.controller = controller;
        this.connector = connector;
        this.ownerName = ownerName;
        connector.setConnectionMenager(this);
        connector.start();
    }

    public String getOwnerName() {
        return ownerName;
    }

    public ArrayList<String> getPlayerList() {
        if(playerList == null)getPlayersListfromServer("");
        return playerList;
    }

    public void onMessage(Message message){

        if(message.getSenderName().equals(SERVERNAME)){
            systemCommandInterpretation(message);
        } else if(message.getSenderName().equals(controller.getOpponentName())){
            gameCommandInterpretation(message);
        } else {
            if(message.getMessageType().equals(SystemCommand.ChatroomMessage.toString()) || message.getMessageType().equals(SystemCommand.ChatMessage.toString())){
                controller.onChatMessage(message.getSenderName(),message.getMessage());
            } else {
                connector.send(new Message(ownerName, SERVERNAME, SystemCommand.WrongDestinationAddress.toString(), message.getSenderName()));
            }
        }


    }

    public void onPlayersList(MessagePlayersList messagePlayersList){
        this.playerList = messagePlayersList.getPlayersList();
    }

    public void connectionLost(String reason){
        controller.viewInfo("Application ",reason);
    }

    private void getPlayersListfromServer(String pattern){
        sendMessage(new Message(ownerName,"System",SystemCommand.PlayersList.toString(),pattern));
    }
    public void close(){
        connector.send(new Message(ownerName,SERVERNAME,SystemCommand.EndGame.toString(),controller.getOpponentName()));
    }

    public void sendMessage(Message message){
        connector.send(message);
    }

    public ConnectionStatus getConnectionStatus(){

        if(connector.isConnected())return ConnectionStatus.Connected;
        else return ConnectionStatus.Disconnected;
    }


    public void disconnect(){
        if(controller.getOpponentName() != null) close();
        connector.stop();
    }

    private void systemCommandInterpretation(Message message){
        if(message.getMessageType().equals(SystemCommand.Registration.toString())){
            ownerName = message.getMessage();
            controller.setOwnerName(message.getMessage());
            sendMessage(new Message(ownerName, "System", "PlayersList", ""));
        } else if(message.getMessageType().equals(SystemCommand.StartGameRequest.toString())){
//            controller.viewInfo(message.getSenderName(),"Player "+message.getMessage()+" invites you to the game");
            controller.onInvite(message.getMessage());
        } else if(message.getMessageType().equals(SystemCommand.BusyGameResponse.toString())){
            controller.setOpponentName("");
            controller.viewInfo(message.getSenderName(),"Player "+message.getMessage()+" is busy ,try again later");
        } else if(message.getMessageType().equals(SystemCommand.NoStartGameResponse.toString())){
            controller.setOpponentName("");
            controller.viewInfo(message.getSenderName(),"Player "+message.getMessage()+" does not want to play with you");
        } else if(message.getMessageType().equals(SystemCommand.StartGame.toString())){
            if(message.getMessage().equals(controller.getOpponentName())){
                controller.viewInfo(message.getSenderName(),"You can play with "+message.getMessage()+". Start the game button below");
                controller.enableGame();
            } else {
                connector.send(new Message(ownerName, SERVERNAME, SystemCommand.WrongDestinationAddress.toString(), message.getSenderName()));
            }
        } else if(message.getMessageType().equals(SystemCommand.Win.toString())){
                controller.endTheGame(SystemCommand.Win);
        } else if(message.getMessageType().equals(SystemCommand.Defeat.toString())){
                controller.endTheGame(SystemCommand.Defeat);
        } else if(message.getMessageType().equals(SystemCommand.Error.toString())){
            controller.endTheGame(SystemCommand.Error);
        } else if(message.getMessageType().equals(SystemCommand.Echo.toString())){
                if("Error".equals(message.getMessage())){
                    // errrr
                } else {
                    controller.echo(message.getMessage());
                }
        } else {
            connector.send(new Message(ownerName,SERVERNAME,SystemCommand.UndefinedCommand.toString(),SERVERNAME));
        }
    }

    private void gameCommandInterpretation(Message message){
        if(message.getMessageType().equals(GameCommand.GameRequest.toString())){
            controller.onRequest(message.getMessage());
        } else if(message.getMessageType().equals(GameCommand.GameResponse.toString())){
            controller.onResponse(message.getMessage());
        }else if(message.getMessageType().equals(GameCommand.Ready.toString())){
            if(controller.isReady()) {
                controller.startTheGame();
            } else {
                controller.setIsOpponentReady(true);
            }
        }
    }
}
