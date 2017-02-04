package edu.oregonstate.cs361.battleship;

/**
 * Created by seal on 02/02/2017.
 */
public class Ship {
        public String name;
        public int length;
        public Position start;
        public Position end;

        public Ship(String name, int length, Position start, Position end) {
                this.name = name;
                this.length = length;
                this.start = start;
                this.end = end;
        }

        public Ship(String name, int length) {
                this(name, length, new Position(), new Position());
        }

}
