package aoc2021;

import java.io.*;
import java.util.*;

public class Day4 implements DayTemplate{
	
	public String solve(boolean part1, Scanner in) throws FileNotFoundException {
		String[] initial_numbers = in.nextLine().split(",");
		List<String> inps = new ArrayList<>();
		int answer = 0; 
		in.nextLine();
		List<BingoBoard> boards = new ArrayList<>();
		while(in.hasNext()) {
			String vals = in.nextLine();
			if(vals.equals("")) {
				boards.add(new BingoBoard(inps));
				inps = new ArrayList<>();
			}
			else {
				inps.add(vals);
			}
		}
		boards.add(new BingoBoard(inps));
		for(int i = 0; i < initial_numbers.length; i++) {
			for(int j = boards.size() - 1; j >= 0; j--) {
				BingoBoard b= boards.get(j);
				int val = Integer.parseInt(initial_numbers[i]);
				if(b.callNum(val)!=-1) {
					if(boards.size() == 1 || part1) {
						return ""+b.callNum(val);
					}
					boards.remove(j);
				}
			}
		}
		return "" + answer;
	}
}

class BingoBoard{
	int[][] board = new int[5][5];
	boolean[][] called = new boolean[5][5];
	public BingoBoard(List<String> lines) {
		for(int i = 0; i < called.length;i ++) {
			String[] nums = lines.get(i).trim().split("\\s+");
			for(int j = 0; j < 5; j++) {
				board[i][j] = Integer.parseInt(nums[j]);
			}
		}
	}
	
	public int callNum(int num) {
		for(int i = 0; i < called.length; i++) {
			for(int j = 0; j < called[0].length; j++) {
				if(board[i][j] == num) {
					called[i][j] = true;
				}
			}
		}
		if(won()) {
			return num * sumothers();
		}
		return -1;
	}
	private int sumothers() {
		int total = 0;
		for(int i = 0; i < called.length; i++) {
			for(int j = 0; j < called[0].length; j++) {
				if(!called[i][j]) {
					total+=board[i][j];
				}
			}
		}
		return total;
	}
	private boolean won() {
		for(int i = 0; i < called.length; i++) {
			boolean row = true;
			boolean column = true;
			for(int j = 0; j < called[0].length; j++) {
				if(!called[i][j]) {
					row = false;
				}	
				if(!called[j][i]) {
					column = false;
				}
			}
			if(row || column) {
				return true;
			}
		}
		return false;
	}
}
