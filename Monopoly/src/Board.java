import java.time.LocalTime;
import java.util.ArrayList;

import java.util.Collections;

public class Board{
    Timer timer = new Timer();
    private ArrayList<Cities> isOwnedcityList = new ArrayList<>();
    private ArrayList<Cities> unOwnedCities = new ArrayList<>();
    private ArrayList<Players> playersList = new ArrayList<>();
   

    LocalTime starttime = LocalTime.now();

    public ArrayList<Cities> getIsOwnedcityList() {
        return isOwnedcityList;
    }

    public void setIsOwnedcityList(ArrayList<Cities> isOwnedcityList) {
        this.isOwnedcityList = isOwnedcityList;
    }

    public void addOwnedCity(Cities city) {
        isOwnedcityList.add(city);
        city.setOwned(true);
    }

    public ArrayList<Cities> getUnOwnedCities() {
        return unOwnedCities;
    }

    public void setUnOwnedCities(ArrayList<Cities> unOwnedCities) {
        this.unOwnedCities = unOwnedCities;
    }   

    public ArrayList<Players> getPlayersList() {
        return playersList;
    }

    public void setPlayersList(ArrayList<Players> playersList) {
        this.playersList = playersList;
    }

    public void addPlayers (Players player){
        playersList.add(player);
    }
    public void sortArray(){
        Collections.sort(unOwnedCities);
    }

    public boolean isGameOver(){
        Timer timer = new Timer();
        if(/*getMoney of other players == 0 */){
            return true;
        }
        if (/* one of players own 4 beaches*/) {
            return true;
        }
        if (/* player own every city on a row */) {
            return true
        }
        if(timer.getRemainingTime() == 0){
            return true; 
        }
        return false;
    }
}
