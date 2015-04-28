package com.mirkowski.websocketclient;

import com.mirkowski.management.ConnectionManager;
import com.mirkowski.management.Connector;

import de.tavendo.autobahn.WebSocketConnection;
import de.tavendo.autobahn.WebSocketException;
import de.tavendo.autobahn.WebSocketHandler;

/**
 * Created by Kamil on 2015-04-20.
 */
public class WebSocketConnector implements Connector{

    private ConnectionManager connectionManager = null;

    private final WebSocketConnection mConnection = new WebSocketConnection();
    private String serverURI = null;

    public WebSocketConnector(ConnectionManager connectionManager, String serverURI){
        this.connectionManager = connectionManager;
        this.serverURI = serverURI;
    }


    public void start() {

          final String wsuri = serverURI;

        try {
            mConnection.connect(wsuri, new WebSocketHandler() {

                @Override
                public void onOpen() {
                    mConnection.sendTextMessage(MessageEncoder.encode(new Message(connectionManager.getOwnerName(),"","","")));
                }

                @Override
                public void onTextMessage(String jsonMessage) {

                    if (MessageDecoder.decode(jsonMessage) instanceof Message)
                        connectionManager.onMessage((Message) MessageDecoder.decode(jsonMessage));
                    else if (MessageDecoder.decode(jsonMessage) instanceof MessagePlayersList){
                        connectionManager.onPlayersList((MessagePlayersList) MessageDecoder.decode(jsonMessage));
                    }
                }

                @Override
                public void onClose(int code, String reason) {
                        connectionManager.connectionLost("Connection was close");
                }
            });
        } catch (WebSocketException e) {
            connectionManager.connectionLost("Connection error");
        }
    }

    public void send(Message message) {
        mConnection.sendTextMessage(MessageEncoder.encode(message));
    }

    @Override
    public Boolean isConnected() {
        return mConnection.isConnected();
    }

    public void stop(){
        mConnection.disconnect();
    }



}
