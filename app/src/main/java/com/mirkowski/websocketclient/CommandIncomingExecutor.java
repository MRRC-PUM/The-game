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
              //obs³uga comend systemu
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
                            //wyswietl komunikat o tym ze przeciwnik przerwa³ gre i opuœæ okno Gry
                        }else if(tempMessageType.equals(InfoParameter.WIN.toString())){
                            //wyswietl komunikat o tym ze wygra³eœ gre i opuœæ okno Gry
                        }else if(tempMessageType.equals(InfoParameter.DEFEAT.toString())){
                            //wyswietl komunikat o tym ze przegra³eœ gre i opuœæ okno Gry
                        }

                }else if(tempMessageType.equals(Command.GAMEREQUEST.toString())){
                    //sprawdz czy przeciwnik trafi³ jeœli tak to do message+"1"; jeœli nie message+"0";
                }
                else if(tempMessageType.equals(Command.GAMERESPONSE)){
                    //sprawdz message.getChar[0] == X && message.getChar[0] == Y  i message.getChar[0] == wartoœæ pola
                }
            }

        } else if(transportUnit instanceof MessagePlayersList){
                //wywo³uje metode ustawiaj¹c¹ liste graczy
        }
    }
}
