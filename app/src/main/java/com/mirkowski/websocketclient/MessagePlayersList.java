package com.mirkowski.websocketclient;

import java.util.ArrayList;

/**
 * Created by Kamil on 2015-04-20.
 */
public class MessagePlayersList implements TransportUnit {
    private String senderName;
    private String recipientName;
    private String messageType;
    private ArrayList<String> playersList;

    public MessagePlayersList(String senderName, String recipientName, String messageType, ArrayList<String> message) {
        this.senderName = senderName;
        this.recipientName = recipientName;
        this.messageType = messageType;
        this.playersList = message;

    }

    public String getSenderName() {
        return senderName;
    }
    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }
    public String getRecipientName() {
        return recipientName;
    }
    public void setRecipientName(String recipientName) {
        this.recipientName = recipientName;
    }
    public String getMessageType() {
        return messageType;
    }
    public ArrayList<String> getPlayersList() {
        return playersList;
    }
    public void setPlayersList(ArrayList<String> playersList) {
        this.playersList = playersList;
    }
    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public String getMessage() {
        return listToString(playersList);
    }
    private String listToString(ArrayList<String> array){
        String resultString = "[ ";
        for (String iterator:array){
            resultString += iterator +" , ";
        }
        resultString += " ]";
        return resultString;
    }
}
