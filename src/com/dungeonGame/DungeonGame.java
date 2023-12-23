package com.dungeonGame;

import com.dungeonGame.Level.DungeonGameView;

public class DungeonGame {

	public static void main(String[] args) {
		new DungeonGame().startGame();
	}
	
	public void startGame() {
		new DungeonGameView().startGame();
	}
}
