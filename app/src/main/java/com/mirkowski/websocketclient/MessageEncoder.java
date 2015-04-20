package com.mirkowski.websocketclient;

/**
 * Created by Kamil on 2015-04-19.
 */
public class MessageEncoder {
    public static String encode(Message message){
        try{
        return JsonBulider.buildJsonMessageData(message);
        } catch(Exception e) {
            return null;
        }
    }
}
