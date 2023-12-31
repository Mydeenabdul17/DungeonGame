package com.dungeonGame.Level;

import java.util.Arrays;
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
		System.out.println("Enter the position of Trigger with sapce");
		int tX = sc.nextInt();
		int tY = sc.nextInt();
		System.out.println("Enter the position of Gold with sapce");
		int gX = sc.nextInt();
		int gY = sc.nextInt();
		model.createArea(x,y);
		System.out.println("Enter no of pits");
		int t=sc.nextInt();
		for(int i=0;i<t;i++) {
			System.out.println("Enter the position of pit with sapce");
			int pX = sc.nextInt();
			int pY = sc.nextInt();
			model.addPit(pX,pY);
		}
		model.findStepsCount(aX,aY,gX,gY,mX,mY,tX,tY);
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
	public void steps(String s,List<int[]> stepsAG) {
		System.out.println(s);
		for (int[] is : stepsAG) {
			System.out.println(Arrays.toString(is));
		}
	}
}
