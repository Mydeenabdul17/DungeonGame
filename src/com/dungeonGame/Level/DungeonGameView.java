package com.dungeonGame.Level;

import java.util.Scanner;

public class DungeonGameView {

	private DungeonViewModel model;
	
	public DungeonGameView() {
		model = new DungeonViewModel(this);
	}
	
	Scanner sc = new Scanner(System.in);
	public void startGame() {
		System.out.println("Enter the Dimention of Dungeon with space");
		int x = sc.nextInt();
		int y = sc.nextInt();
		System.out.println("Enter the position of Adventure with sapce");
		int aX = sc.nextInt();
		int aY = sc.nextInt();
		System.out.println("Enter the position of Gold with sapce");
		int gX = sc.nextInt();
		int gY = sc.nextInt();
		model.createArea(x,y);
		model.findSteps(aX,aY,gX,gY);
	}
	public void steps(int steps) {
		System.out.println(steps);
	}
	public void inValid() {
		System.out.println("Position Invalid");
	}
	
}
