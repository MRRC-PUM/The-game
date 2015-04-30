package com.example.bartek.shipswar.logic;

/**
 * Created by Bartek on 2015-04-30.
 */
public class GameFactory {
    int[][] owner;
    int points;
    final int tablength = 10;
    final int nullsector = 0;

    public GameFactory() {
        for(int i =0; i < tablength; i++)
            for(int j=0; j < tablength; j++) owner[j][i] = nullsector;
    }

    public int[][] getOwner() {
        return owner;
    }

    public int getPoints() {
        return points;
    }

    public Game CreateGame(){
        Game game = new Game();
        //to trzeba Kamil przemyslec
        return game;
    }

}
