package aoc2021;

import java.util.*;

public class Day11NoStream {
	
	public String solve(boolean part1, Scanner in) {
		List<String> inps = new ArrayList<>();
		while(in.hasNext()) {
			inps.add(in.nextLine());
		}
		int[][] octopi = new int[10][10];
		for(int i = 0; i < octopi.length; i ++) {
			for(int j = 0; j < octopi[0].length; j++) {
				octopi[i][j] = Integer.parseInt(inps.get(i).split("")[j]);
			}
		}
		long answer = 0;
		int counter = 0;
		while(counter < (part1?100:100000)) {
			if(!part1 && check(octopi)) {
				return "" + counter;
			}
			answer += iterate(octopi);
			counter++;
		}
		
		return "" + answer;
	}
	
	private int iterate(int[][] octopi) {
		int[][] newarr = new int[octopi.length][octopi[0].length];
		boolean[][] flashed = new boolean[10][10];
		for(int i = 0; i < octopi.length; i ++) {
			for(int j = 0; j < octopi[0].length; j++) {
				newarr[i][j] = octopi[i][j] + 1;
			}
		}
		int cycles = 0;
		while(flash(newarr, flashed)){
			cycles++;
		}
		for(int i = 0; i < octopi.length; i ++) {
			for(int j = 0; j < octopi[0].length; j++) {
				if(flashed[i][j]) {
					flashed[i][j] = false;
					newarr[i][j] = 0;
				}
			}
		}
		for(int i = 0; i < octopi.length; i ++) {
			for(int j = 0; j < octopi[0].length; j++) {
				octopi[i][j] = newarr[i][j];
			}
		}
		return cycles;
	}
	
	private boolean flash(int[][] octopi, boolean[][] flashed) {
		int[] xs = new int[] {1,1,1,0,0,-1,-1,-1};
		int[] ys = new int[] {1,0,-1,1,-1,1,0,-1};
		for(int i = 0; i < octopi.length; i ++) {
			for(int j = 0; j < octopi[0].length; j++) {
				if(octopi[i][j] > 9 && !flashed[i][j]) {
					flashed[i][j] = true;
					for(int k = 0; k < xs.length; k++) {
						if(!OutOfBounds(i + xs[k], j + ys[k], octopi)) {
							octopi[i + xs[k]][j + ys[k]] ++;
						}
					}
					return true;
				}
			}
		}
		return false;
	}
	
	private boolean OutOfBounds(int i, int j, int[][] grid) {
		return !(i >= 0 && j >= 0 && i < grid.length && j < grid[0].length);
	}
	
	private boolean check(int[][] grid) {
		for(int i = 0; i < grid.length; i++) {
			for(int j = 0; j < grid[0].length; j++) {
				if(grid[i][j] != 0) {
					return false;
				}
			}
		}
		return true;
	}
}

	
	