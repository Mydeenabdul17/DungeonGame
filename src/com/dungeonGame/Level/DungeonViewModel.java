package com.dungeonGame.Level;

import java.util.ArrayList;
import java.util.List;

import com.dungeonGame.repository.DungeonGameRepository;

public class DungeonViewModel {

	private DungeonGameView dungeonGameView;
	private int steps=0;
	public DungeonViewModel(DungeonGameView dungeonGameView) {
		this.dungeonGameView = dungeonGameView;
	}
	public void createArea(int x, int y) {
		DungeonGameRepository.getInstance().createArea(x,y);
	}
	
	public void findStepsCount(int aX,int aY,int gX,int gY, int mX, int mY) {
//		if(mX==aX&&mY==aY) {
//			dungeonGameView.notPosible();
//		}
//		else if(isValid(aX,aY)&&isValid(gX,gY)&&isValid(mX, mY)) {
//			if((Math.abs(aX-gX)+Math.abs(aY-gY))<=Math.abs(mX-gX)+Math.abs(mY-gY)) {
//				dungeonGameView.steps(Math.abs(aX-gX)+Math.abs(aY-gY));
////				findSteps(aX,aY,gX,gY,mX,mY);
//			}else if((Math.abs(aX-tX)+Math.abs(aY-tY))<=Math.abs(mX-gX)+Math.abs(mY-gY)){
//				dungeonGameView.steps(Math.abs(aX-tX)+Math.abs(aY-tY)+Math.abs(tX-gX)+Math.abs(tY-gY));
//			}else {
//				dungeonGameView.notPosible();
//			}
//		}
//		
//		else {
//			dungeonGameView.inValid();
//		}
		if(isValid(aX, aY)&&isValid(gX, gY)&&isValid(mX, mY)) {
			int [][] area = DungeonGameRepository.getInstance().getArea();
			findCount(area,0,aX,aY,gX,gY);
			if(steps!=0) {
				if(steps<=Math.abs(mX-gX)+Math.abs(mY-gY))
				dungeonGameView.steps(steps);
				else
					dungeonGameView.notPosible();
			}else {
				dungeonGameView.notPosible();
			}
		}else {
			dungeonGameView.inValid();
		}
	}
	private void findCount(int[][] area,int count, int aX, int aY, int gX, int gY) {
		if(aX==gX&&aY==gY) {
			steps=Math.max(count, steps);
			return;
		}
		if(aX!=gX&&aX<gX&&area[aX][aY-1]!=1) {
			findCount(area, count+1, aX+1, aY, gX, gY);
		}else if(aX!=gX&&aX>gX&&area[aX-2][aY-1]!=1) {
			findCount(area, count+1, aX-1, aY, gX, gY);
		}
		if(aY!=gY&&aY<gY&&area[aX-1][aY]!=1) {
			findCount(area, count+1, aX, aY+1, gX, gY);
		}else if(aY!=gY&&aY>gY&&area[aX-1][aY-2]!=1) {
			findCount(area, count+1, aX, aY-1, gX, gY);
		}
	}
	private void findSteps(int aX,int aY,int gX,int gY, int mX, int mY) {
		List<List<List<Integer>>> adventureSteps=new ArrayList<>();
	    adventureSteps(new ArrayList<>(),adventureSteps,aX,aY,gX,gY);
	    List<List<List<Integer>>> monsterSteps=new ArrayList<>();
	    adventureSteps(new ArrayList<>(), monsterSteps, mX, mY, gX, gY);
	    List<List<Integer>> res = validPath(adventureSteps,monsterSteps);
	    dungeonGameView.validPath(res);
	}
	private List<List<Integer>> validPath(List<List<List<Integer>>> adventureSteps, List<List<List<Integer>>> monsterSteps) {
		for (List<List<Integer>> list : adventureSteps) {
			boolean b=true;
			outer:for(List<List<Integer>> List2:monsterSteps) {
				for (List<Integer> list3 : list) {
					for(int i=0;i<List2.size()-1;i++) {
						if(list3.equals(List2.get(i))) {
							b=false;
							break outer;
						}
					}
				}
			}
			if(b) {
				return list;
			}
		}
		return null;
	}
	private void adventureSteps(List<List<Integer>> l,List<List<List<Integer>>> adventureSteps,int aX, int aY, int gX, int gY) {
		List<Integer> pos=new ArrayList<>();
		pos.add(aX);pos.add(aY);
		l.add(pos);
		if(aX==gX&&aY==gY) {
			List<List<Integer>> temp=new ArrayList<>(l);
			adventureSteps.add(temp);
			return;
		}
		if(aX!=gX&&aX<gX) {
			adventureSteps(l,adventureSteps,aX+1,aY,gX,gY);
			l.remove(l.size()-1);
		}else if(aX!=gX&&aX>gX){
			adventureSteps(l,adventureSteps,aX-1,aY,gX,gY);
			l.remove(l.size()-1);
		}
		if(aY!=gY&&aY<gY) {
			adventureSteps(l,adventureSteps,aX,aY+1,gX,gY);
			l.remove(l.size()-1);
		}else if(aY!=gY&&aY>gY){
			adventureSteps(l,adventureSteps,aX,aY-1,gX,gY);
			l.remove(l.size()-1);
		}
		
	}
	private boolean isValid(int aX, int aY) {
		
		return DungeonGameRepository.getInstance().getX()>=aX&&DungeonGameRepository.getInstance().getY()>=aY;
	}
	public void addPit(int pX, int pY) {
		if(isValid(pX, pY)) {
			DungeonGameRepository.getInstance().addPit(pX,pY);
		}
	}
	public int[][] getArea() {
		return DungeonGameRepository.getInstance().getArea();
	}

}
