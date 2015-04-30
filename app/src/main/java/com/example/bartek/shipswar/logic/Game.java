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

    public void setShot(int[][] tab, int x, int y){
        tab[x][y]= SHOT;
    }
}
