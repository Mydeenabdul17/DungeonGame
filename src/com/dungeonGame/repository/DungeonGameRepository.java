package com.dungeonGame.repository;

public class DungeonGameRepository {

	private static DungeonGameRepository repository;

	private int[][] dungeonArea;
	
	private DungeonGameRepository() {

	}

	public static DungeonGameRepository getInstance() {
		if (repository == null) {
			repository = new DungeonGameRepository();
		}
		return repository;
	}

	public void createArea(int x, int y) {
		dungeonArea = new int[x][y];
	}

	public int getX() {
		return dungeonArea.length;
	}

	public int getY() {
		return dungeonArea[0].length;
	}

	public void addPit(int pX, int pY) {
		dungeonArea[pX-1][pY-1]=1;
	}

	public int[][] getArea() {
		return dungeonArea;
	}
}
