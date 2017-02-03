package edu.oregonstate.cs361.battleship;

import com.google.gson.annotations.SerializedName;


public class Position {
	@SerializedName("Across")
	public int across;
	@SerializedName("Down")
	public int down;

	public Position() { this(across: 0, down: 0); }
	public Position(int across, int down) {
		this.across = across;
		this.down = down;
	}
}
