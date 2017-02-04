package edu.oregonstate.cs361.battleship;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import spark.Spark;
import spark.utils.IOUtils;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static spark.Spark.awaitInitialization;


/**
 * Created by michaelhilton on 1/26/17.
 */
class MainTest {

    final String sample = "{\n" +
                "    \"aircraftCarrier\": {\n" +
                "        \"name\": \"AircraftCarrier\",\n" +
                "        \"length\": 5,  \n" +
                "        \"start\": { \"Across\": 0,\"Down\": 0 },\n" +
                "        \"end\": {\"Across\": 0, \"Down\": 0}\n" +
                "    },\n" +
                "    \"battleship\": {\n" +
                "        \"name\": \"Battleship\",\n" +
                "        \"length\": 4,\n" +
                "        \"start\": { \"Across\": 0,\"Down\": 0 },\n" +
                "        \"end\": {\"Across\": 0, \"Down\": 0}\n" +
                "    },\n" +
                "    \"cruiser\": {\n" +
                "        \"name\": \"Cruiser\",\n" +
                "        \"length\": 3,\n" +
                "        \"start\": { \"Across\": 0,\"Down\": 0 },\n" +
                "        \"end\": {\"Across\": 0, \"Down\": 0}\n" +
                "    },\n" +
                "    \"destroyer\": {\n" +
                "        \"name\": \"Destroyer\",\n" +
                "        \"length\": 2,\n" +
                "        \"start\": { \"Across\": 0,\"Down\": 0 },\n" +
                "        \"end\": {\"Across\": 0, \"Down\": 0}\n" +
                "    },\n" +
                "    \"submarine\": {\n" +
                "        \"name\": \"Submarine\",\n" +
                "        \"length\": 2,\n" +
                "       \"start\": { \"Across\": 0,\"Down\": 0 },\n" +
                "        \"end\": {\"Across\": 0, \"Down\": 0}\n" +
                "    },\n" +
                "    \"computer_aircraftCarrier\": {\n" +
                "        \"name\": \"Computer_AircraftCarrier\",\n" +
                "        \"length\": 5,\n" +
                "        \"start\": { \"Across\": 2,\"Down\": 2 },\n" +
                "        \"end\": {\"Across\": 2, \"Down\": 7}\n" +
                "    },\n" +
                "    \"computer_battleship\": {\n" +
                "        \"name\": \"Computer_Battleship\",\n" +
                "        \"length\": 4,\n" +
                "        \"start\": { \"Across\": 2,\"Down\": 8 },\n" +
                "        \"end\": {\"Across\": 6, \"Down\": 8}\n" +
                "    },\n" +
                "    \"computer_cruiser\": {\n" +
                "        \"name\": \"Computer_Cruiser\",\n" +
                "        \"length\": 3,\n" +
                "        \"start\": { \"Across\": 4, \"Down\": 1 },\n" +
                "        \"end\": {\"Across\": 4, \"Down\": 4}\n" +
                "    },\n" +
                "    \"computer_destroyer\": {\n" +
                "        \"name\": \"Computer_Destroyer\",\n" +
                "        \"length\": 2,\n" +
                "        \"start\": { \"Across\": 7, \"Down\": 3 },\n" +
                "        \"end\": {\"Across\": 7, \"Down\": 5}\n" +
                "    },\n" +
                "    \"computer_submarine\": {\n" +
                "        \"name\": \"Computer_Submarine\",\n" +
                "        \"length\": 2,\n" +
                "        \"start\": { \"Across\": 9, \"Down\": 6 },\n" +
                "        \"end\": {\"Across\": 9, \"Down\": 8}\n" +
                "    },\n" +
                "    \"playerHits\": [],\n" +
                "    \"playerMisses\": [],\n" +
                "    \"computerHits\": [],\n" +
                "    \"computerMisses\": []\n" +
                "}\n";

    final String placeShipSample = "{\n" +
            "    \"aircraftCarrier\": {\n" +
            "        \"name\": \"AircraftCarrier\",\n" +
            "        \"length\": 5,  \n" +
            "        \"start\": { \"Across\": 1,\"Down\": 1 },\n" +
            "        \"end\": {\"Across\": 1, \"Down\": 6}\n" +
            "    },\n" +
            "    \"battleship\": {\n" +
            "        \"name\": \"Battleship\",\n" +
            "        \"length\": 4,\n" +
            "        \"start\": { \"Across\": 0,\"Down\": 0 },\n" +
            "        \"end\": {\"Across\": 0, \"Down\": 0}\n" +
            "    },\n" +
            "    \"cruiser\": {\n" +
            "        \"name\": \"Cruiser\",\n" +
            "        \"length\": 3,\n" +
            "        \"start\": { \"Across\": 0,\"Down\": 0 },\n" +
            "        \"end\": {\"Across\": 0, \"Down\": 0}\n" +
            "    },\n" +
            "    \"destroyer\": {\n" +
            "        \"name\": \"Destroyer\",\n" +
            "        \"length\": 2,\n" +
            "        \"start\": { \"Across\": 0,\"Down\": 0 },\n" +
            "        \"end\": {\"Across\": 0, \"Down\": 0}\n" +
            "    },\n" +
            "    \"submarine\": {\n" +
            "        \"name\": \"Submarine\",\n" +
            "        \"length\": 2,\n" +
            "       \"start\": { \"Across\": 0,\"Down\": 0 },\n" +
            "        \"end\": {\"Across\": 0, \"Down\": 0}\n" +
            "    },\n" +
            "    \"computer_aircraftCarrier\": {\n" +
            "        \"name\": \"Computer_AircraftCarrier\",\n" +
            "        \"length\": 5,\n" +
            "        \"start\": { \"Across\": 2,\"Down\": 2 },\n" +
            "        \"end\": {\"Across\": 2, \"Down\": 7}\n" +
            "    },\n" +
            "    \"computer_battleship\": {\n" +
            "        \"name\": \"Computer_Battleship\",\n" +
            "        \"length\": 4,\n" +
            "        \"start\": { \"Across\": 2,\"Down\": 8 },\n" +
            "        \"end\": {\"Across\": 6, \"Down\": 8}\n" +
            "    },\n" +
            "    \"computer_cruiser\": {\n" +
            "        \"name\": \"Computer_Cruiser\",\n" +
            "        \"length\": 3,\n" +
            "        \"start\": { \"Across\": 4, \"Down\": 1 },\n" +
            "        \"end\": {\"Across\": 4, \"Down\": 4}\n" +
            "    },\n" +
            "    \"computer_destroyer\": {\n" +
            "        \"name\": \"Computer_Destroyer\",\n" +
            "        \"length\": 2,\n" +
            "        \"start\": { \"Across\": 7, \"Down\": 3 },\n" +
            "        \"end\": {\"Across\": 7, \"Down\": 5}\n" +
            "    },\n" +
            "    \"computer_submarine\": {\n" +
            "        \"name\": \"Computer_Submarine\",\n" +
            "        \"length\": 2,\n" +
            "        \"start\": { \"Across\": 9, \"Down\": 6 },\n" +
            "        \"end\": {\"Across\": 9, \"Down\": 8}\n" +
            "    },\n" +
            "    \"playerHits\": [],\n" +
            "    \"playerMisses\": [],\n" +
            "    \"computerHits\": [],\n" +
            "    \"computerMisses\": []\n" +
            "}\n";

    @BeforeAll
    public static void beforeClass() {
        Main.main(null);
        awaitInitialization();
    }

    @AfterAll
    public static void afterClass() {
        Spark.stop();
    }

    @Test
    public void testGetModel() {
        TestResponse res = request("GET", "/model");
        assertEquals(200, res.status);
        Gson gson = new Gson();
        Gson pretty = new GsonBuilder().setPrettyPrinting().create();
        assertEquals(pretty.toJson(gson.fromJson(sample, HashMap.class)), pretty.toJson(res.json()));
    }

    @Test
    public void testPlaceShip() {
        TestResponse res = request("POST", "/placeShip/aircraftCarrier/1/1/horizontal", sample);
        assertEquals(200, res.status);
        Gson gson = new Gson();
        Gson pretty = new GsonBuilder().setPrettyPrinting().create();
        assertEquals(pretty.toJson(gson.fromJson(placeShipSample, HashMap.class)), pretty.toJson(res.json()));
    }

    @Test
    public void testFire() {
        TestResponse res = request("POST", "/fire/3/3", sample);
        assertEquals(200, res.status);
        ArrayList<Map<String, Double>> computerMisses = (ArrayList) res.json().get("computerMisses");
        assertEquals(3.0, (double)computerMisses.get(0).get("Across"));
        assertEquals(3.0, (double)computerMisses.get(0).get("Down"));
    }

    private TestResponse request(String method, String path) {
        return request(method, path, null);
    }

    private TestResponse request(String method, String path, String reqBody ) {
        try {
            URL url = new URL("http://localhost:4000" + path);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(method);
            connection.setDoOutput(true);
            if (reqBody != null) {
                new PrintWriter(connection.getOutputStream(), true).println(reqBody);
            }
            connection.connect();
            String body = IOUtils.toString(connection.getInputStream());
            return new TestResponse(connection.getResponseCode(), body);
        } catch (IOException e) {
            e.printStackTrace();
            fail("Sending request failed: " + e.getMessage());
            return null;
        }
    }

    private static class TestResponse {

        public final String body;
        public final int status;

        public TestResponse(int status, String body) {
            this.status = status;
            this.body = body;
        }

        public Map<String, Object> json() {
            return new Gson().fromJson(body, HashMap.class);
        }
    }


}