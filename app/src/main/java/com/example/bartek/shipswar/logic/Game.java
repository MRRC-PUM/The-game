package com.example.bartek.shipswar.logic;

/**
 * Created by Bartek on 2015-04-30.
 */
public class Game {
    int[][] owner;
    int[][] opponent;
    int ownerPoints;
    int opponentPoints;
    boolean mode;
    final int SHOT = 9;
    final int LOSE = 8;
    final public boolean listen = true;
    final public boolean sending = false;


    public Game(int[][] owner, int ownerPoints) {
        this.owner = owner;
        this.ownerPoints = ownerPoints;
        this.opponentPoints = ownerPoints;
        for(int i = 0 ; i<=9 ; i++)
            for(int j = 0; j<=9; j++) opponent[j][i]=0;
    }

    public int[][] getOwner() {
        return owner;
    }

    public void setOwner(int[][] owner) {
        this.owner = owner;
    }

    public int[][] getOpponent() {
        return opponent;
    }

    public void setOpponent(int[][] opponent) {
        this.opponent = opponent;
    }

    public int getOwnerPoints() {
        return ownerPoints;
    }

    public void setOwnerPoints(int ownerPoints) {
        this.ownerPoints = ownerPoints;
    }

    public int getOpponentPoints() {
        return opponentPoints;
    }

    public void setOpponentPoints(int opponentPoints) {
        this.opponentPoints = opponentPoints;
    }

    public boolean isMode() {
        return mode;
    }

    public void setMode(boolean mode) {
        this.mode = mode;
    }

    public void setShotOpponentMap(int x, int y, int status){
      if (status==1) opponent[x][y]=SHOT;
      if (status==0) opponent[x][y]=LOSE;
    }

    public boolean OpponentShot (int x, int y) {
        if (owner[x][y]==2) { owner[x][y] = SHOT; return true;}
        else {owner[x][y] = LOSE; return false;}
    }
}
