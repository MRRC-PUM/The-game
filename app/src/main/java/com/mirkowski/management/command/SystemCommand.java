package com.mirkowski.management.command;

/**
 * Created by Kamil on 2015-04-20.
 */
public enum SystemCommand {

    WrongDestinationAddress,UndefinedCommand,EndGame, // only to server
    StartGame,Error,Win,Defeat, //only from Server
    Echo,PlayersList,Registration,StartGameRequest,BusyGameResponse,StartGameResponse,NoStartGameResponse,ChatroomMessage,ChatMessage //to both side



}
