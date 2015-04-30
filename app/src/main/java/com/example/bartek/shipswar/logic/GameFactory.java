package com.example.bartek.shipswar.logic;

/**
 * Created by Bartek on 2015-04-30.
 */
public class GameFactory {
    int[][] owner;
    int playerpoints;

    final int tablength = 10;
    final int nullsector = 0;
        Ship ship_1 = new Ship("jednomasztowiec", 1);
        Ship ship_2 = new Ship("dwumasztowiec", 1);
        Ship ship_3 = new Ship("trojmasztowiec", 1);
        Ship ship_4 = new Ship("czteromasztowiec", 1);
            Ship[] ships = {ship_1,ship_1,ship_1,ship_2,ship_3,ship_4};
            final int HowMuchShips = ships.length;
            final int points = 7;//(iles punktow koncowych i poczatkowych statkwo >1 maszt)+(ilosc 1 masztowcw)

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
        if(ships[iterate].getPoints()!=1){
            Points pointsStart;
            if(points%2==1){
                //poczatek
                pointsStart = new Points(x,y);
                iterate--;
            } else {
                //koniec
                Points pointsEnd = new Points(x,y);
                //sprawdzanie czy mozna postawic statek
                if(checkMultiplayOwner(pointsStart, pointsEnd)){
                    if(chechOnLineOwner(pointsStart, pointsEnd)){
                        setOnLineInOwner(pointsStart, pointsEnd);
                    } else iterate++;
                }else iterate++;
            }

        } else {
            if (checkOwner(x,y)){
                setOnOwnerOne(x,y);
                iterate--;
            }
        }


    }

    //metody na ksozt CreateGame()
    private boolean checkOwner(int x, int y){
        if(owner[x][y]==0) return true;
        else return false;
    }

    private void setOnOwnerOne(int x, int y){
       owner[x][y]=2;
       for (int i =(y-1); i<=(y+1); i++)
           for (int j =(x-1); j<=(x+1); j++){
               if (j<0 || j>9) continue;
               else if (i<0 || i>9) continue;
               else owner[j][i]=1;
           }
    }

    private boolean checkMultiplayOwner(Points start, Points end){
        //sprawdzanie poprawnosci ustawienia statku i ewentualna zamiana miejscami pkt
        int whose = 0;
        //sprawdzanie czy poziomo czy pionowo
        if(start.getX() == end.getX()){
         whose=1;
            ships[iterate].setOrientation(true);
        } else if(start.getY() == end.getY()){
         whose=2;
            ships[iterate].setOrientation(false);
        }

        if(whose!=0){
        //jesli ktores z ustawien
          if(whose==1){
              if(start.getY() > end.getY()){
                  int temp = start.getY();
                  start.setY(end.getY());
                  end.setY(temp);
              }
              if(((end.getY()-start.getY())+1)==ships[iterate].getPoints()){
                  return true;
              } else return false;
          }
          if(whose==2){
            if(start.getX() > end.getX()){
                int temp = start.getX();
                start.setX(end.getX());
                end.setX(temp);
            }
              if(((end.getX()-start.getX())+1)==ships[iterate].getPoints()){
                  return true;
              }else return false;
          }
        } else return false;//jesli nie jest ani poziomo ani pionowo


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
                  if(owner[i][constatns]==1 ||owner[i][constatns]==2) return false;
            } else {
                if(owner[constatns][i]==1 ||owner[constatns][i]==2) return false;
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
                //narazie nei daje wstawiania odstepu poprostu z braku czasu
            }

        }
    }


}
