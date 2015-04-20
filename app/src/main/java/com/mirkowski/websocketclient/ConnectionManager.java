package com.mirkowski.websocketclient;

import android.os.Build;

import de.tavendo.autobahn.WebSocketConnection;
import de.tavendo.autobahn.WebSocketException;
import de.tavendo.autobahn.WebSocketHandler;

/**
 * Created by Kamil on 2015-04-20.
 */
public class ConnectionManager {

    CommandIncomingExecutor executor = null;



    private final WebSocketConnection mConnection = new WebSocketConnection();

    private void start() {

//        final String wsuri = "ws://192.168.2.100:8080/WebSocketGlassfish/chat";
        final String wsuri = "ws://10.2.35.131:8080/WebSocketGlassfish/chat";
        try {
            mConnection.connect(wsuri, new WebSocketHandler() {

                @Override
                public void onOpen() {
//                    Log.d(TAG, "Status: Connected to " + wsuri);
                    mConnection.sendTextMessage(Build.MANUFACTURER + ":" +Build.MODEL);
//                    mConnection.sendTextMessage(MessageEncoder.encode(new Message("Kamil"
//                                                                                    ,"System"
//                                                                                    ,"Registration"
//                                                                                    ,"Helo")));
                }

                @Override
                public void onTextMessage(String jsonMessage) {
//                    Log.d(TAG, "Got echo: " + jsonMessage);
                    //  messageView.setText(messageView.getText().toString()+jsonMessage+"\n");
//                    messageView.setText(messageView.getText().toString()+MessageDecoder.decode(jsonMessage).getSenderName()+":"+MessageDecoder.decode(jsonMessage).getMessage()+"\n");
//                    Log.d("Class: ", MessageDecoder.decode(jsonMessage).getClass().getSimpleName());
                }

                @Override
                public void onClose(int code, String reason) {
//                    Log.d(TAG, "Connection lost.");
                }
            });
        } catch (WebSocketException e) {
//            Log.d(TAG, e.toString());

        }
    }

    public void sendMessage(Message message) {

        mConnection.sendTextMessage(MessageEncoder.encode(message));

    }

    public void stop(){
        mConnection.disconnect();
    }
}
