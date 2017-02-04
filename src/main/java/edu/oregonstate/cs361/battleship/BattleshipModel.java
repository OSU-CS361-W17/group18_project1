package edu.oregonstate.cs361.battleship;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by michaelhilton on 1/26/17.
 */
public class BattleshipModel {

    public Ship aircraftCarrier;
    public Ship battleship;
    public Ship cruiser;
    public Ship destroyer;
    public Ship submarine;

    @SerializedName("computer_aircraftCarrier")
    public Ship computerAircraftCarrier;
    @SerializedName("computer_battleship")
    public Ship computerBattleship;
    @SerializedName("computer_cruiser")
    public Ship computerCruiser;
    @SerializedName("computer_destroyer")
    public Ship computerDestroyer;
    @SerializedName("computer_submarine")
    public Ship computerSubmarine;

    public BattleshipModel() {
        this.aircraftCarrier = new Ship("AircraftCarrier", 5, new Position(0, 0), new Position(0, 0));
        this.battleship = new Ship("Battleship", 4, new Position(0, 0), new Position(0, 0));
        this.cruiser = new Ship("Cruiser", 3, new Position(0, 0), new Position(0, 0));
        this.destroyer = new Ship("Destroyer", 2, new Position(0, 0), new Position(0, 0));
        this.submarine = new Ship("Submarine", 2, new Position(0, 0), new Position(0, 0));

        this.computerAircraftCarrier = new Ship("Computer_AircraftCarrier", 5, new Position(2, 2), new Position(2, 7));
        this.computerBattleship = new Ship("Computer_Battleship", 4, new Position(2, 8), new Position(6, 8));
        this.computerCruiser = new Ship("Computer_Cruiser", 3, new Position(4, 1), new Position(4, 4));
        this.computerDestroyer = new Ship("Computer_Destroyer", 2, new Position(7, 3), new Position(7, 5));
        this.computerSubmarine = new Ship("Computer_Submarine", 2, new Position(9, 6), new Position(9, 8));

        this.playerHits = new ArrayList<>();
        this.playerMisses = new ArrayList<>();
        this.computerHits = new ArrayList<>();
        this.computerMisses = new ArrayList<>();
    }

    private Position generateRandomPosition(int length) {
        return null;
    }


    public ArrayList<Position> playerHits;
    public ArrayList<Position> playerMisses;
    public ArrayList<Position> computerHits;
    public ArrayList<Position> computerMisses;
}
