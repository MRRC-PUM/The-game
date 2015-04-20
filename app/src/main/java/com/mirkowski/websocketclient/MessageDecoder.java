package com.mirkowski.websocketclient;


import java.io.StringReader;
import java.util.ArrayList;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;

/**
 * Created by Kamil on 2015-04-19.
 */
public class MessageDecoder {
    public static TransportUnit decode(String jsonMessage){
        JsonObject jsonObject = null;
        try{
            jsonObject = Json.createReader(new StringReader(jsonMessage)).readObject();
        } catch(Exception e) {
            return null;
        }
        if(!jsonObject.getString("messageType").equals("PlayersList")) {
            try {
                return new Message(jsonObject.getString("senderName")
                                 , jsonObject.getString("recipientName")
                                 , jsonObject.getString("messageType")
                                 , jsonObject.getString("message"));
            } catch (Exception e) {
                return null;
            }
        }else {
            try {
                return new MessagePlayersList(jsonObject.getString("senderName")
                                             ,jsonObject.getString("recipientName")
                                             ,jsonObject.getString("messageType")
                                             ,getStringList(jsonObject.getJsonArray("message")));
            } catch (Exception e) {
                return null;
            }
        }
    }

    private static  ArrayList<String> getStringList(JsonArray jsonArray){
        ArrayList<String> stringList = null;
        int length = jsonArray.size();
        if(jsonArray!=null){
            stringList = new ArrayList<String>();
            for(int i=0;i<length;i++){
                stringList.add(jsonArray.getString(i));
            }
        }
        return stringList;
    }
}
