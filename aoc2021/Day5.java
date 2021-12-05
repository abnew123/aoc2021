package aoc2021;

import java.io.*;
import java.util.*;

public class Day5{
	
	public static void main(String[] args) throws FileNotFoundException {
		int day = 5;
		boolean part1 = true;
		File file = new File("./aoc2021/day" + day + ".txt");
		Scanner in = new Scanner(file);
		System.out.println(solve(part1, in));
	}
	
	public static String solve(boolean part1, Scanner in) throws FileNotFoundException {
		int answer = 0; 
		int[][] grid = new int[2000][2000];
		List<Line> lines = new ArrayList<>();
		while(in.hasNext()) {
			String vals = in.nextLine();
			lines.add(new Line(vals));
		}
		for(Line line: lines) {
			if(!part1 || line.isHoriz() || line.isVert()) {
				line.addPoints(grid);
			}
		}
		for(int i = 0; i < grid.length; i++) {
			for(int j = 0; j < grid.length; j++) {
				if(grid[i][j] >= 2) {
					answer ++;
				}
			}
		}
		return "" + answer;
	}
}


class Line{
	private int x1;
	private int x2;
	private int y1;
	private int y2;
	public Line(String line) {
		String[] parts = line.split("->");
		x1 = Integer.parseInt(parts[0].trim().split(",")[0]);
		y1 = Integer.parseInt(parts[0].trim().split(",")[1]);
		x2 = Integer.parseInt(parts[1].trim().split(",")[0]);
		y2 = Integer.parseInt(parts[1].trim().split(",")[1]);
	}
	
	public boolean isHoriz() {
		return x1 == x2;
	}
	public boolean isVert() {
		return y1 == y2;
	}
	
	public void addPoints(int[][] grid) {
		if(isHoriz()) {
			for(int i = Math.min(y1, y2); i <= Math.max(y1, y2); i++) {
				grid[x1][i] +=1;
			}
		}
		else {
			if(isVert()) {
				for(int i = Math.min(x1, x2); i <= Math.max(x1, x2); i++) {
					grid[i][y1] +=1;
				}
			}
			else {
				if((x1 > x2 && y1 > y2)||(x1 < x2 && y1 < y2)){
					for(int i = 0; i <= Math.max(x1, x2) - Math.min(x1, x2); i++) {
						grid[Math.min(x1, x2) + i][Math.min(y1, y2) + i] +=1;
					}
				}
				else {
					for(int i = 0; i <= Math.max(x1, x2) - Math.min(x1, x2); i++) {
						grid[Math.min(x1, x2) + i][Math.max(y1, y2) - i] +=1;
					}
				}
			}
		}
	}
}

