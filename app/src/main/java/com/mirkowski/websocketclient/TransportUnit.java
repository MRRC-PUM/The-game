package com.mirkowski.websocketclient;

/**
 * Created by Kamil on 2015-04-20.
 */
public interface TransportUnit {
    public String getSenderName();
    public String getRecipientName();
    public String getMessageType();
    public String getMessage();
}
