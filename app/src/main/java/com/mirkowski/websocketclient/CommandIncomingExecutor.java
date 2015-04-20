package com.mirkowski.websocketclient;

import com.mirkowski.websocketclient.command.Command;
import com.mirkowski.websocketclient.command.InfoParameter;
import com.mirkowski.websocketclient.command.ReplayParameter;

import de.tavendo.autobahn.WebSocketConnection;

/**
 * Created by Kamil on 2015-04-20.
 */
public class CommandIncomingExecutor {
    public void execute(TransportUnit transportUnit,WebSocketConnection mConnection){
        String tempMessageType = null;
        String tempMessage = null;

        if(transportUnit instanceof Message){
            tempMessageType = transportUnit.getMessageType();
            if(transportUnit.getSenderName().equals("System")){
              //obs�uga comend systemu
            }else{
                if(tempMessageType.equals(Command.QUERY.toString())){
                         //ustaw powiadomienie zapytania o gre
                }else if(tempMessageType.equals(Command.REPLY.toString())){

                        if(tempMessageType.equals(ReplayParameter.YES.toString())){
                            //ustaw powiadomienie o zgodzie na gre
                        }else if(tempMessageType.equals(ReplayParameter.NO.toString())){
                            //ustaw powiadomienie o braku zgody na gre
                        }

                }else if(tempMessageType.equals(Command.INFO.toString())){

                        if(tempMessageType.equals(InfoParameter.START.toString())){
                            //aktywuj button "rozpocznij gre"
                        }else if(tempMessageType.equals(InfoParameter.ABORT.toString())){
                            //wyswietl komunikat o tym ze przeciwnik przerwa� gre i opu�� okno Gry
                        }else if(tempMessageType.equals(InfoParameter.WIN.toString())){
                            //wyswietl komunikat o tym ze wygra�e� gre i opu�� okno Gry
                        }else if(tempMessageType.equals(InfoParameter.DEFEAT.toString())){
                            //wyswietl komunikat o tym ze przegra�e� gre i opu�� okno Gry
                        }

                }else if(tempMessageType.equals(Command.GAMEREQUEST.toString())){
                    //sprawdz czy przeciwnik trafi� je�li tak to do message+"1"; je�li nie message+"0";
                }
                else if(tempMessageType.equals(Command.GAMERESPONSE)){
                    //sprawdz message.getChar[0] == X && message.getChar[0] == Y  i message.getChar[0] == warto�� pola
                }
            }

        } else if(transportUnit instanceof MessagePlayersList){
                //wywo�uje metode ustawiaj�c� liste graczy
        }
    }
}
