package edu.oregonstate.cs361.battleship;

import com.google.gson.annotations.SerializedName;


public class Position {
	@SerializedName("Across")
	private int across;
	@SerializedName("Down")
	private int down;

	public Position(){
		this(0, 0);
	}

	public Position(int across, int down) {
		this.across = across;
		this.down = down;
	}

	//Getter and setter functions for easy access to variables
	public int getAcross() { return this.across; }
	public int getDown() { return this.down; }
	public void setAcross(int a) { this.across = a; }
	public void setDown(int d) { this.down = d; }
}
