package com.mirkowski.management;

import com.mirkowski.management.status.ConnectionStatus;
import com.mirkowski.websocketclient.Message;
import com.mirkowski.websocketclient.MessagePlayersList;

/**
 * Created by Kamil on 2015-04-28.
 */
public class ConnectionManager {

    private Controller controller = null;
    private Connector connector = null;
    private String ownerName = null;

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

    }

    public void onPlayersList(MessagePlayersList messagePlayersList){

    }

    public void connectionLost(String reason){

    }

    public  Message close(){
        return new Message(ownerName,"System","EndGame",controller.getOpponentName());
    }

    public void sendMessage(Message message){
        connector.send(message);
    }

    public ConnectionStatus getConnectionStatus(){

        if(connector.isConnected())return ConnectionStatus.Connected;
        else return ConnectionStatus.Disconnected;
    }


    public void disconnect(){
        connector.stop();
    }

}
