package edu.oregonstate.cs361.battleship;

import org.junit.jupiter.api.Test;
import static edu.oregonstate.cs361.battleship.Utils.computerFire;
import static edu.oregonstate.cs361.battleship.Utils.isComputerHit;
import static edu.oregonstate.cs361.battleship.Utils.isHit;
import static org.junit.jupiter.api.Assertions.*;
/**
 * Created by seal on 02/02/2017.
 */
public class UtilsTest {
    
    /**
     *
     */
    @Test
    public void testIsHit() {
        
        Ship ship = new Ship("test", 3, new Position(3, 3), new Position(3, 6));
        assertEquals(true, isHit(ship, 3, 3), "should hit");
        assertEquals(false, isHit(ship, 3, 9), "should not hit");
        
        BattleshipModel model = new BattleshipModel();
        assertEquals(true, isHit(model, 2, 3), "model should hit");
        assertEquals(false, isHit(model, 2, 9), "model should not hit");
    }
    
    @Test
    public void testComputerFire() {
        
        BattleshipModel model = new BattleshipModel();
        
        for (int i = 0; i < 1000; i++) {
            Position pos = computerFire(model);
            assertEquals(true, pos.across < 11);
            assertEquals(true, pos.across > 0);
            assertEquals(true, pos.down < 11);
            assertEquals(true, pos.down > 0);
        }
        
        model.playerHits.add(new Position(1, 1));
        model.playerMisses.add(new Position(2, 2));
        
        
        for (int i = 0; i < 1000; i++) {
            Position newPos = computerFire(model);
            assertEquals(false, newPos.across == 1 && newPos.down == 1);
            assertEquals(false, newPos.across == 2 && newPos.down == 2);
        }
        
        
    }
    
    @Test
    public void testIsComputerHit() {
        BattleshipModel model = new BattleshipModel();
        Ship ship = new Ship("test", 3, new Position(3, 3), new Position(3, 6));
        model.aircraftCarrier = ship;
        assertEquals(true, isComputerHit(model, new Position(3, 3)), "model should hit");
        assertEquals(false, isComputerHit(model, new Position(3, 9)), "model should not hit");
    }
}
