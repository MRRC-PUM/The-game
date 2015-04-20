package com.mirkowski.websocketclient;

import java.io.StringWriter;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonWriter;


public class JsonBulider {

	public static String buildJsonMessageData(Message message){
		JsonObject jsonObject = Json.createObjectBuilder().add("senderName", message.getSenderName())
														  .add("recipientName", message.getRecipientName())
														  .add("messageType", message.getMessageType())
														  .add("message", message.getMessage())
														  .build(); 
		
		StringWriter stringWriter = new StringWriter();
        try {
            JsonWriter jsonWriter = Json.createWriter(stringWriter);
            jsonWriter.write(jsonObject);
        } catch (Exception e){

        }
		return stringWriter.toString();
	}
	
	
}
