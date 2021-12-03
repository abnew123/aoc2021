package aoc2021;

import java.io.*;
import java.util.*;

public class Day1 implements DayTemplate{
	
	public String solve(boolean part1, Scanner in) throws FileNotFoundException {
		int answer = 0;
		int offset = part1?1:3; // a+b+c < b+c+d iff a <d
		List<Integer> nums = new ArrayList<>();
		while(in.hasNext()) {
			nums.add(in.nextInt());
		}
		for(int i = 0; i < nums.size() - offset; i++) {
			if(nums.get(i+offset) > nums.get(i)) {
				answer+=1;
			}
		}
		return "" + answer;
	}
}
