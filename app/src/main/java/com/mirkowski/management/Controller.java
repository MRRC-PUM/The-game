package com.mirkowski.management;


import com.mirkowski.settings.Settings;
import com.mirkowski.websocketclient.WebSocketConnector;

/**
 * Created by Kamil on 2015-04-28.
 */
public class Controller {

    private Settings settings = new Settings();
    private ConnectionManager connectionManager = null;
    private String opponentName = null;

    public Controller(){
        this.connectionManager = new ConnectionManager(this,new WebSocketConnector(connectionManager,settings.getServerAdress()),settings.getUserName());
    }


    public void setOpponentName(String opponentName) {
        this.opponentName = opponentName;
    }

    public String getOpponentName() {
        return opponentName;
    }

    public void getPlayersList() {

    }


}
