package com.dungeonGame.Level;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import com.dungeonGame.repository.DungeonGameRepository;

public class DungeonViewModel {

	private DungeonGameView dungeonGameView;

	public DungeonViewModel(DungeonGameView dungeonGameView) {
		this.dungeonGameView = dungeonGameView;
	}

	public void createArea(int x, int y) {
		DungeonGameRepository.getInstance().createArea(x, y);
	}

	public void findStepsCount(int aX, int aY, int gX, int gY, int mX, int mY, int tX, int tY) {
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
		if (isValid(aX, aY) && isValid(gX, gY) && isValid(mX, mY)) {
			int[][] area = DungeonGameRepository.getInstance().getArea();
			List<int[]> stepsAG = findCount(area, aX - 1, aY - 1, gX - 1, gY - 1);
			if (stepsAG != null) {
				if (stepsAG.size() <= Math.abs(mX - gX) + Math.abs(mY - gY)) {
					dungeonGameView.steps(stepsAG.size());
					dungeonGameView.steps("A=>G",stepsAG);
				}
					
				else {
					List<int[]> tSteps = findCount(area, aX - 1, aY - 1, tX - 1, tY - 1);
					List<int[]> gSteps = findCount(area, tX - 1, tY - 1, gX - 1, gY - 1);
					if (gSteps != null) {
						dungeonGameView.steps(tSteps.size() + gSteps.size()-2);
						dungeonGameView.steps("A=>T",tSteps);
						dungeonGameView.steps("T=>G",gSteps);
					} else
						dungeonGameView.notPosible();
				}

			} else {
				dungeonGameView.notPosible();
			}
		} else {
			dungeonGameView.inValid();
		}
	}

	private List<int[]> findCount(int[][] grid, int aX, int aY, int gX, int gY) {
		int[][] area = copyOf(grid);

		Queue<List<int[]>> queue = new LinkedList<>();
		List<int[]> l= new ArrayList<>();
		l.add(new int[] { aX, aY});
		queue.offer(l);
		
		while (!queue.isEmpty()) {
			List<int[]> current = queue.poll();
			int x = current.get(current.size()-1)[0];
			int y = current.get(current.size()-1)[1];
			if (x == gX && y == gY) {
				return current;
			}
			if (isValidMove(x - 1, y, area)) {
				List<int[]> temp =new ArrayList<>(current);
				temp.add(new int[] { x - 1, y});
				queue.offer(temp);
				area[x - 1][y] = 1;
			}
			if (isValidMove(x + 1, y, area)) {
				List<int[]> temp =new ArrayList<>(current);
				temp.add(new int[] { x + 1, y});
				queue.offer(temp);
				area[x + 1][y] = 1;
			}
			if (isValidMove(x, y - 1, area)) {
				List<int[]> temp =new ArrayList<>(current);
				temp.add(new int[] { x , y-1});
				queue.offer(temp);
				area[x][y - 1] = 1;
			}
			if (isValidMove(x, y + 1, area)) {
				List<int[]> temp =new ArrayList<>(current);
				temp.add(new int[] { x , y+1});
				queue.offer(temp);
				area[x][y + 1] = 1;
			}
			
		}

		// No path found
		return null;

	}

	private int[][] copyOf(int[][] grid) {
		int[][] copy = new int[grid.length][grid[0].length];
		for (int i = 0; i < copy.length; i++) {
			for (int j = 0; j < copy[0].length; j++) {
				copy[i][j] = grid[i][j];
			}
		}
		return copy;
	}

	private static boolean isValidMove(int x, int y, int[][] grid) {
        return x >= 0 && x < grid.length && y >= 0 && y < grid[0].length && grid[x][y] != 1;
    }


//	private void findSteps(int aX,int aY,int gX,int gY, int mX, int mY) {
//		List<List<List<Integer>>> adventureSteps=new ArrayList<>();
//	    adventureSteps(new ArrayList<>(),adventureSteps,aX,aY,gX,gY);
//	    List<List<List<Integer>>> monsterSteps=new ArrayList<>();
//	    adventureSteps(new ArrayList<>(), monsterSteps, mX, mY, gX, gY);
//	    List<List<Integer>> res = validPath(adventureSteps,monsterSteps);
//	    dungeonGameView.validPath(res);
//	}
//	private List<List<Integer>> validPath(List<List<List<Integer>>> adventureSteps, List<List<List<Integer>>> monsterSteps) {
//		for (List<List<Integer>> list : adventureSteps) {
//			boolean b=true;
//			outer:for(List<List<Integer>> List2:monsterSteps) {
//				for (List<Integer> list3 : list) {
//					for(int i=0;i<List2.size()-1;i++) {
//						if(list3.equals(List2.get(i))) {
//							b=false;
//							break outer;
//						}
//					}
//				}
//			}
//			if(b) {
//				return list;
//			}
//		}
//		return null;
//	}
//	private void adventureSteps(List<List<Integer>> l,List<List<List<Integer>>> adventureSteps,int aX, int aY, int gX, int gY) {
//		List<Integer> pos=new ArrayList<>();
//		pos.add(aX);pos.add(aY);
//		l.add(pos);
//		if(aX==gX&&aY==gY) {
//			List<List<Integer>> temp=new ArrayList<>(l);
//			adventureSteps.add(temp);
//			return;
//		}
//		if(aX!=gX&&aX<gX) {
//			adventureSteps(l,adventureSteps,aX+1,aY,gX,gY);
//			l.remove(l.size()-1);
//		}else if(aX!=gX&&aX>gX){
//			adventureSteps(l,adventureSteps,aX-1,aY,gX,gY);
//			l.remove(l.size()-1);
//		}
//		if(aY!=gY&&aY<gY) {
//			adventureSteps(l,adventureSteps,aX,aY+1,gX,gY);
//			l.remove(l.size()-1);
//		}else if(aY!=gY&&aY>gY){
//			adventureSteps(l,adventureSteps,aX,aY-1,gX,gY);
//			l.remove(l.size()-1);
//		}
//		
//	}
	private boolean isValid(int aX, int aY) {

		return DungeonGameRepository.getInstance().getX() >= aX && DungeonGameRepository.getInstance().getY() >= aY;
	}

	public void addPit(int pX, int pY) {
		if (isValid(pX, pY)) {
			DungeonGameRepository.getInstance().addPit(pX, pY);
		}
	}

	public int[][] getArea() {
		return DungeonGameRepository.getInstance().getArea();
	}

}
