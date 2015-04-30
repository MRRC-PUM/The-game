package com.example.bartek.shipswar.logic;

/**
 * Created by Bartek on 2015-04-20.
 */
public class Ship {

    private String type;
    private int points;
    private boolean orientation; //0 hor 1 ver
    private int[][] wsp_s_e = new int[2][2];

    public Ship(String type, int points) {
        this.type = type;
        this.points = points;
    }

    public int[][] getWsp_s_e() {
        return wsp_s_e;
    }

    public int[] getWsp_s() {
        int[] wsp_s = {wsp_s_e[1][1], wsp_s_e[1][2]};
        return wsp_s;
    }

    public int[] getWsp_e() {
        int[] wsp_e = {wsp_s_e[2][1],wsp_s_e[2][2]};
        return wsp_e;
    }



    public void setWsp_s_e(int[][] wsp_s_e) {
        this.wsp_s_e = wsp_s_e;
    }

    public int getPoints() {
        return points;
    }

    public boolean getOrientation() {
        return orientation;
    }

    public void setOrientation(boolean orientation) {
        this.orientation = orientation;
    }
}
