package com.dungeonGame.Level;

import java.util.List;
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
		System.out.println("Enter the position of Monster with sapce");
		int mX = sc.nextInt();
		int mY = sc.nextInt();
		System.out.println("Enter the position of Gold with sapce");
		int gX = sc.nextInt();
		int gY = sc.nextInt();
		model.createArea(x,y);
		model.findStepsCount(aX,aY,gX,gY,mX,mY);
	}
	public void steps(int steps) {
		System.out.println(steps);
	}
	public void inValid() {
		System.out.println("Position Invalid");
	}
	public void notPosible() {
		System.out.println("No Posible Solution");
	}
	public void validPath(List<List<Integer>> res) {
		System.out.println(res);
	}
	
}
