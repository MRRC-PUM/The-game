package com.mirkowski.management;

import com.mirkowski.websocketclient.Message;

/**
 * Created by Kamil on 2015-04-28.
 */
public interface Connector {


    public void start();
    public void stop();
    public void send(Message message);
    public Boolean isConnected();
}
