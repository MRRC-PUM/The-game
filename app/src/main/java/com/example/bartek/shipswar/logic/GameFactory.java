package com.example.bartek.shipswar.logic;

import android.util.Log;

/**
 * Created by Bartek on 2015-04-30.
 */
public class GameFactory {

    int playerpoints;

    final int tablength = 10;
    final int nullsector = 0;
    Points pointsStart;
    Points pointsEnd;
    int[][] owner= new int[tablength][tablength];

    Ship ship_1 = new Ship("jednomasztowiec", 1);
    Ship ship_2 = new Ship("dwumasztowiec", 2);
    Ship ship_3 = new Ship("trojmasztowiec", 3);
    Ship ship_4 = new Ship("czteromasztowiec", 4);
    Ship[] ships = {ship_1,ship_1,ship_1,ship_2,ship_3,ship_4};
    final int HowMuchShips = ships.length;
    final int points = 7;//(iles punktow koncowych i poczatkowych statkwo >1 maszt)+(ilosc 1 masztowcw)
    int pointsSettedPoints=1;
    int iterate = 4;


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

    public void CreateGame(int x, int y){
        if (iterate >= 0 ){
            if(ships[iterate].getPoints()!=1){

                if(pointsSettedPoints%2==1){
                    Log.d("GameFactory", "Wielomasztowiec Stawiam pierwszy punkt");
                    //poczatek
                    pointsStart = new Points(x,y);
                    pointsSettedPoints++;

                } else {
                    //koniec
                    pointsEnd = new Points(x,y);
                    pointsSettedPoints++;
                    Log.d("GameFactory", "Wielomasztowiec Stawiam drugi punkt");
                    //sprawdzanie czy mozna postawic statek
               /* if(checkMultiplayOwner(pointsStart, pointsEnd)){
                    if(chechOnLineOwner(pointsStart, pointsEnd)){
                        setOnLineInOwner(pointsStart, pointsEnd);
                    } else iterate++;
                }else iterate++;
                */
                    if(checkMultiplayOwner(pointsStart,pointsEnd)) {
                        if(chechOnLineOwner(pointsStart, pointsEnd)) {
                            setOnLineInOwner(pointsStart, pointsEnd);
                        } else iterate+=2;
                    }


                    iterate--;



                }

            } else {
                if (checkOwner(x,y)){
                    Log.d("GameFacotry", "jednomasztowiec");
                    setOnOwnerOne(x,y);
                    if(iterate!=0)
                        iterate--;
                }
            }

        }


    }

    //metody na ksozt CreateGame()
    private boolean checkOwner(int x, int y){
        if(owner[x][y]==0) return true;
        else return false;
    }

    private void setOnOwnerOne(int x, int y){
        for (int i =(y-1); i<=(y+1); i++)
            for (int j =(x-1); j<=(x+1); j++){
                if (j<0 || j>9) continue;
                else if (i<0 || i>9) continue;
                else owner[j][i]=1;
            }

        owner[x][y]=2;
    }

    private boolean checkMultiplayOwner(Points start, Points end){
        //sprawdzanie poprawnosci ustawienia statku i ewentualna zamiana miejscami pkt
        int whose = 0;
        //sprawdzanie czy poziomo czy pionowo
        if(start.getX() == end.getX()){
            whose=1;
            Log.d("GameFactory", "poziomy");
            ships[iterate].setOrientation(true);
        }
        if(start.getY() == end.getY()){
            whose=2;Log.d("GameFactory", "pionowy");
            ships[iterate].setOrientation(false);
        }

        Log.d("GameFactory", "Sprawdzono ulozenie");
        if(whose!=0){
            //jesli ktores z ustawien
            if(whose==1){
                if(start.getY() > end.getY()){
                    int temp = start.getY();
                    start.setY(end.getY());
                    end.setY(temp);
                }
                if(((end.getY()-start.getY())+1)==ships[iterate].getPoints()){
                    Log.d("GameFactory", "Poprawnie ustawiono1");
                    return true;
                } else{ Log.d("GameFactory", "bledni ustawiono1");return false;}
            }
            if(whose==2){
                if(start.getX() > end.getX()){
                    int temp = start.getX();
                    start.setX(end.getX());
                    end.setX(temp);
                }
                if(((end.getX()-start.getX())+1)==ships[iterate].getPoints()){
                    Log.d("GameFactory", "Poprawnie ustawiono2"); return true;
                }else {Log.d("GameFactory", "bledni ustawiono2");return false;}
            }
        } else return false;//jesli nie jest ani poziomo ani pionowo

        Log.d("GameFactory", "zwykle ok");
        return true;
    }

    private boolean chechOnLineOwner(Points start, Points end){
        Points local;
        int constatns;
        if (ships[iterate].getOrientation()){
            local = new Points(start.getY(),end.getY());
            constatns = start.getX();
        } else {local = new Points(start.getX(),end.getX()); constatns=start.getY();}

        for (int i =local.getX(); i<=local.getY();i++){
            if (ships[iterate].getOrientation()) {
                if(owner[i][constatns]==1 || owner[i][constatns]==2) return false;
            } else {
                if(owner[constatns][i]==1 || owner[constatns][i]==2) return false;
            }
        }

        return true;
    }
//BARTEK !!!!!!!!!!!!!!!!!!!!!!!!!!!!!! PAMIETAJ BY DODAC TU KONTROLE ODSTEPU
//Zostawione przez Rybak dla RYbak nie kasowac dopuki nei zrobie!!!


    private void setOnLineInOwner(Points start, Points end){
        if (ships[iterate].getOrientation()){
            for (int i = start.getY(); i<=end.getY(); i++){
                owner[start.getX()][i]=2;

                //narazie nei daje wstawiania odstepu poprostu z braku czasu
            }
        } else {
            for (int i = start.getX(); i<=end.getX(); i++){
                owner[i][start.getY()]=2;
                Log.d("hor", "Y: " + String.valueOf(start.getY()) + " X: " + String.valueOf(start.getX()));
                //narazie nei daje wstawiania odstepu poprostu z braku czasu
            }

        }
    }

    public String getActualShip(){
        return ships[iterate].getType();
    }


}
