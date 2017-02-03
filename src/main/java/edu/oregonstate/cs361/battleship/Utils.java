package edu.oregonstate.cs361.battleship;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

/**
 * Created by seal on 02/02/2017.
 */
public class Utils {

    public static boolean isHit(BattleshipModel model, int row, int col) {
        return isHit(model.computerAircraftCarrier, row, col)
                || isHit(model.computerBattleship, row, col)
                || isHit(model.computerCruiser, row, col)
                || isHit(model.computerDestroyer, row, col)
                || isHit(model.computerSubmarine, row, col);

    }

    public static boolean isComputerHit(BattleshipModel model, Position pos) {
        int row = pos.across;
        int col = pos.down;
        return isHit(model.aircraftCarrier, row, col)
                || isHit(model.battleship, row, col)
                || isHit(model.cruiser, row, col)
                || isHit(model.destroyer, row, col)
                || isHit(model.submarine, row, col);

    }

    public static boolean isHit(Ship ship, int row, int col) {
        boolean result = ship.start.across <= row
                && ship.end.across >= row
                && ship.start.down <= col
                && ship.end.down >= col;
        System.out.println("hist result " + result);
        return result;
    }

    public static Position computerFire(BattleshipModel model) {

        Random random = new Random();
        ArrayList<Position> selected = new ArrayList<>();
        // FIXME js take playerMisses as computerMisses
        selected.addAll(model.playerHits);
        // FIXME js take playerMisses as computerMisses
        selected.addAll(model.playerMisses);

        HashSet<Integer> selectedInteger = new HashSet<>();
        selected.forEach(pos -> {
            selectedInteger.add((pos.across - 1) + (pos.down - 1) * 10);
        });

        ArrayList<Integer> availableInteger = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            if (!selectedInteger.contains(i)) {
                availableInteger.add(i);
            }
        }

        int index = random.nextInt(availableInteger.size());

        int row = availableInteger.get(index) % 10 + 1;
        int col = availableInteger.get(index) / 10 + 1;

        return new Position(row, col);
    }
}
