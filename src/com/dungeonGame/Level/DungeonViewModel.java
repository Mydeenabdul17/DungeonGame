package com.dungeonGame.Level;

import com.dungeonGame.repository.DungeonGameRepository;

public class DungeonViewModel {

	private DungeonGameView dungeonGameView;
	public DungeonViewModel(DungeonGameView dungeonGameView) {
		this.dungeonGameView = dungeonGameView;
	}
	public void createArea(int x, int y) {
		DungeonGameRepository.getInstance().createArea(x,y);
	}
	
	public void findSteps(int aX,int aY,int gX,int gY) {
		if(isValid(aX,aY)&&isValid(gX,gY))
		dungeonGameView.steps(Math.abs(aX-gX)+Math.abs(aY-gY));
		else {
			dungeonGameView.inValid();
		}
	}
	private boolean isValid(int aX, int aY) {
		
		return DungeonGameRepository.getInstance().getX()>=aX&&DungeonGameRepository.getInstance().getY()>=aY;
	}

}
