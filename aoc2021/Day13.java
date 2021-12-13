package aoc2021;

import java.io.File;
import java.util.*;

public class Day13 {

	public static void main(String[] args) throws Exception {
		int day = 13;
		boolean part1 = true;
		File file = new File("./aoc2021/day" + day + ".txt");
		Scanner in = new Scanner(file);
		System.out.println(solve(part1, in));
	}
	public static String solve(boolean part1, Scanner in) {
		List<String> inps = new ArrayList<>();
		List<String> instructs = new ArrayList<>();
		while(in.hasNext()) {
			String line = in.nextLine();
			if(line.contains(",")) {
				inps.add(line);
			}
			else {
				if(line.contains("=")) {
					instructs.add(line);
				}
			}
		}
		Grid grid = new Grid(inps);
		int answer = 0;
		boolean printed = false;
		for(String instr: instructs) {
			int[][] arr = grid.fold(Integer.parseInt(instr.split("=")[instr.split("=").length - 1]), instr.contains("x"));
			if(!printed) {
				for(int i = 0; i < arr.length; i++) {
					for(int j = 0; j < arr[0].length; j++) {
						answer+=(arr[i][j] > 0)?1:0;
					}
				}
				printed = true;
			}
			if(Integer.parseInt(instr.split("=")[instr.split("=").length - 1]) == 6) {
				for(int i = 0; i < arr.length; i++) {
					for(int j = 0; j < arr[0].length; j++) {
						System.out.print(arr[i][j]>0?"#":".");
					}
					System.out.println();
				}
			}
		}		
		return "" + answer;
	}
}
	

class Grid{
	int[][] grid = new int[2000][2000];
	
	public Grid(List<String> lines) {
		for(String line: lines) {
			grid[Integer.parseInt(line.split(",")[1])][Integer.parseInt(line.split(",")[0])] = 1;
		}
	}
	
	public int[][] fold(int place, boolean x){
		for(int i = 0; i < (x?grid.length:place); i++) {
			for(int j = 0; j < (x?place:grid[0].length); j++) {
				grid[i][j] += grid[!x?(2 * place - i): i][!x?j:(2 * place - j)];
			}
		}
		int[][] newGrid = new int[x?grid.length:place][x?place:grid[0].length];
		for(int i = 0; i < newGrid.length; i++) {
			for(int j = 0; j < newGrid[0].length; j++) {
				newGrid[i][j] = grid[i][j];
			}
		}
		grid = newGrid;
		return grid;
	}
}
	
	