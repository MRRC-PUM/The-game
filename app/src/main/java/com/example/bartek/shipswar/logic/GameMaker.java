package com.example.bartek.shipswar.logic;

/**
 * Created by Bartek on 2015-04-20.
 */
public class GameMaker {
    final int maxTablePoints = 6;
    private int[][] playerTab = new int[maxTablePoints][maxTablePoints];

    public GameMaker() {
        for (int i = 0; i < maxTablePoints; i++)
            for (int k = 0; k < maxTablePoints; k++) this.playerTab[i][k] = 0 ;
    }

    public int[][] getPlayerTab() {
        return playerTab;
    }


    public int setShipOnTable(Ship ship) {

        if (ship.getWsp_s()[1]==ship.getWsp_e()[1]){
            for ( int i = ship.getWsp_s()[2]; ship.getWsp_e()[2]!=i; i+=( (ship.getWsp_s()[2] <ship.getWsp_e()[2])? 1 : -1)){
                if ((ship.getWsp_s()[2]==ship.getWsp_e()[2])) continue;
                else playerTab[ship.getWsp_s()[1]][i]=ship.getPoints();
            }

        }else if (ship.getWsp_s()[2]==ship.getWsp_e()[2]){
            for ( int i = ship.getWsp_s()[1]; ship.getWsp_e()[1]!=i; i+=( (ship.getWsp_s()[1] <ship.getWsp_e()[1])? 1 : -1)){
                if ((ship.getWsp_s()[1]==ship.getWsp_e()[1])) continue;
                else playerTab[ship.getWsp_s()[2]][i]=ship.getPoints();
            }

        } else return -1;
        return 0;
    }

    public void setShotOnTable(Shot shot) {
        playerTab[shot.getX()][shot.getY()]= 9;
    }



}
