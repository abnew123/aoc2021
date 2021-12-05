package aoc2021;

import java.io.*;
import java.util.*;

public class Day5 implements DayTemplate{
	
	public String solve(boolean part1, Scanner in) throws FileNotFoundException {
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
		return "" + Arrays.stream(grid).flatMapToInt(x -> Arrays.stream(x)).filter(x-> x>=2).count();
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
		int deltax =x1-x2;
		int deltay =y1-y2;
		if(deltax == 0) {
			for(int i = Math.min(y1, y2); i <= Math.max(y1, y2); i++) {
				grid[x1][i]++;
			}
		}
		else {
			int slope = (deltay*deltax)>0?1:(deltay*deltax)<0?-1:0;
			for(int i = 0; i <= Math.abs(deltax); i++) {
				grid[Math.min(x1, x2) + i][((slope <0)?Math.max(y1, y2):Math.min(y1, y2)) + (i * slope)]++;
			}
		}
	}
}

