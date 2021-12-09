package aoc2021;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Day9 implements DayTemplate{
	
	
	public String solve(boolean part1, Scanner in) {
		List<String> inps = new ArrayList<>();
		while(in.hasNext()) {
			inps.add(in.nextLine());
		}
		int[][] grid = helper(inps);
		if(part1) {
			return "" + part1(grid);
		}
		boolean[][] checked = new boolean[100][100];
		for(int i = 0; i < grid.length; i++) {
			for(int j = 0; j < grid[0].length; j++) {
				if(grid[i][j] == 9) {
					checked[i][j] = true;
				}
			}
		}
		List<Integer> basinSizes = new ArrayList<>();
		for(int i = 0; i < grid.length; i++) {
			for(int j = 0; j < grid[0].length; j++) {
				basinSizes.add(dfs(i,j,grid,checked));
			}
		}
		Collections.sort(basinSizes, Collections.reverseOrder()); 
		return "" + (basinSizes.get(0) * basinSizes.get(1) * basinSizes.get(2));
	}
	
	private int[][] helper(List<String> inps){
		int[][] grid = new int[100][100];
		for(int i = 0; i  < inps.size(); i++) {
			char[] row = inps.get(i).toCharArray();
			for(int j = 0; j < row.length; j++) {
				grid[i][j] = row[j] - '0';
			}
		}
		return grid;
	}
	
	private int part1(int[][] grid) {
		int answer = 0;
		for(int i = 0; i < grid.length; i++) {
			for(int j = 0; j < grid[0].length; j++) {
				if(lowest(grid,i,j)) {
					answer+= grid[i][j] + 1;
				}
			}
		}
		return answer;
	}
	
	private boolean lowest(int[][]grid, int i, int j) {
		int val = grid[i][j];
		boolean lowest = true;
		if(i > 0) {
			lowest &= grid[i - 1][j] > val;
		}
		if(j > 0) {
			lowest &= grid[i][j - 1] > val;
		}
		if(i < grid.length - 1) {
			lowest &= grid[i + 1][j] > val;
		}
		if(j < grid[0].length - 1) {
			lowest &= grid[i][j + 1] > val;
		}
		return lowest;
	}
	
	private boolean OutOfBounds(int i, int j, int[][] grid) {
		return !(i >= 0 && j >= 0 && i < grid.length && j < grid[0].length);
	}
	
	private int dfs(int i, int j, int[][] grid,boolean[][] checked) {
		if(OutOfBounds(i,j,grid) || checked[i][j]) {
			return 0;
		}
		checked[i][j] = true; 
		int answer = 1;
		answer += dfs(i+1, j, grid,checked);
		answer += dfs(i-1, j, grid,checked);
		answer += dfs(i, j+1, grid,checked);
		answer += dfs(i, j-1, grid,checked);
		return answer;
	}
}