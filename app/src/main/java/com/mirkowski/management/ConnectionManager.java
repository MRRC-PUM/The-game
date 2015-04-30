package com.mirkowski.management;

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
        connector.start();
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void onMessage(Message message){

        if(message.getSenderName().equals(SERVERNAME)){
            systemCommandInterpretation(message);
        } else if(message.getSenderName().equals(controller.getOpponentName())){
            gameCommandInterpretation(message);
        } else {
            if(message.getMessageType().equals(SystemCommand.ChatroomMessage.toString()) || message.getMessageType().equals(SystemCommand.ChatMessage.toString())){

            } else {
                connector.send(new Message(ownerName, SERVERNAME, SystemCommand.WrongDestinationAddress.toString(), message.getSenderName()));
            }
        }


    }

    public void onPlayersList(MessagePlayersList messagePlayersList){
        this.playerList = messagePlayersList.getPlayersList();
    }

    public void connectionLost(String reason){

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
        } else if(message.getMessageType().equals(SystemCommand.StartGameRequest.toString())){

        } else if(message.getMessageType().equals(SystemCommand.StartGameResponse.toString())){

        } else if(message.getMessageType().equals(SystemCommand.BusyGameResponse.toString())){

        } else if(message.getMessageType().equals(SystemCommand.NoStartGameResponse.toString())){

        } else if(message.getMessageType().equals(SystemCommand.StartGame.toString())){

        } else if(message.getMessageType().equals(SystemCommand.Win.toString())){

        } else if(message.getMessageType().equals(SystemCommand.Defeat.toString())){

        } else if(message.getMessageType().equals(SystemCommand.Error.toString())){

        } else if(message.getMessageType().equals(SystemCommand.Echo.toString())){

        } else {
            connector.send(new Message(ownerName,SERVERNAME,SystemCommand.UndefinedCommand.toString(),SERVERNAME));
        }
    }

    private void gameCommandInterpretation(Message message){
        if(message.getMessageType().equals(GameCommand.GameRequest.toString())){

        } else if(message.getMessageType().equals(GameCommand.GameResponse.toString())){

        }
    }
}
