package edu.oregonstate.cs361.battleship;

import com.google.gson.Gson;
import spark.Request;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

import static edu.oregonstate.cs361.battleship.Utils.computerFire;
import static edu.oregonstate.cs361.battleship.Utils.isComputerHit;
import static edu.oregonstate.cs361.battleship.Utils.isHit;
import static spark.Spark.port;
import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.staticFiles;

public class Main {

    public static void main(String[] args) {
    	port(4000);
    	//This will allow us to server the static pages such as index.html, app.js, etc.
        staticFiles.location("/public");

        //This will listen to GET requests to /model and return a clean new model
        get("/model", (req, res) -> newModel());
        //This will listen to POST requests and expects to receive a game model, as well as location to fire to
        post("/fire/:row/:col", (req, res) -> fireAt(req));
        //This will listen to POST requests and expects to receive a game model, as well as location to place the ship
        post("/placeShip/:id/:row/:col/:orientation", (req, res) -> placeShip(req));
    }

    //This function should return a new model
    static String newModel() {
        Gson gson = new Gson();
        return gson.toJson(new BattleshipModel());
    }

    //This function should accept an HTTP request and deseralize it into an actual Java object.
    private static BattleshipModel getModelFromReq(Request req){
        Gson gson = new Gson();
        return gson.fromJson(req.body(), BattleshipModel.class);
    }
    }

    // http://localhost:4567/placeShip/{shipname}/{across}/{down}/{horizontal | vertical
    private static String placeShip(Request req) throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException {
        
        BattleshipModel model = getModelFromReq(req);
        String id = req.params("id");
        int row = Integer.parseInt(req.params("row"));
        int col = Integer.parseInt(req.params("col"));
        String orientation = req.params("orientation");
        
        Class modelClass = Class.forName("edu.oregonstate.cs361.battleship.BattleshipModel");
        Field idField = modelClass.getField(id);
        
        Ship ship = (Ship) idField.get(model);
        ship.start.across = row;
        ship.start.down = col;
        
        if (orientation.equals("horizontal")) {
            ship.end.across = ship.start.across;
            ship.end.down = ship.start.down + ship.length;
        } else if (orientation.equals("vertical")) {
            ship.end.across = ship.start.across + ship.length;
            ship.end.down = ship.start.down;
        }

    //Similar to placeShip, but with firing.
    private static String fireAt(Request req) {
        return null;
    }

}
