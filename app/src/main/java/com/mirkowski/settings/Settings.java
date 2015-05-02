package com.mirkowski.settings;

import android.content.SharedPreferences;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.*;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.*;
import org.xml.sax.SAXException;


/**
 * Created by Kamil on 2015-04-30.
 */
public class Settings {

    private String userName = null;
    private String serverAdress = null;
    private int winCount = 0;
    private int defeatCount = 0;
    private SharedPreferences sharedPreferences = null;
    private SharedPreferences.Editor sharedEditor = null;


    public Settings(){

        userName =  "Player";
        serverAdress = "192.168.1.100";
        winCount =  0;
        defeatCount = 0;
    }
    public Settings(SharedPreferences sharedPreferences){
        this.sharedPreferences = sharedPreferences;
        sharedEditor = this.sharedPreferences.edit();

        userName = sharedPreferences.getString("UserName", "Player");
        serverAdress = sharedPreferences.getString("ServerAdress","192.168.1.100");
        winCount = sharedPreferences.getInt("WinCount", 0);
        defeatCount = sharedPreferences.getInt("DefeatCount",0);
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
        sharedEditor.putString("UserName",userName);
    }

    public String getServerAdress() {
        return serverAdress+":8080/WebSocketGlassfish/chat";
    }

    public void setServerAdress(String serverAdress) {
        this.serverAdress = serverAdress;
        sharedEditor.putString("ServerAdress",serverAdress);
    }

    public void incrementWinCount(){
        winCount +=1;
        sharedEditor.putInt("WinCount",winCount);
    }
    public void incrementDefeatCount(){
        defeatCount +=1;
        sharedEditor.putInt("DefeatCount",defeatCount);
    }
}
