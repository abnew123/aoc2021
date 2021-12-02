package aoc2021;

import java.io.*;
import java.util.*;

public class Day1 {
	public static void main(String[] args) throws FileNotFoundException {
		System.out.println("Part 1 solution: " + solve(true, 1));
		System.out.println("Part 2 solution: " + solve(false, 1));
	}
	
	public static int solve(boolean part1, int day) throws FileNotFoundException {
		File file = new File("./aoc2021/day" + day + ".txt");
		Scanner in = new Scanner(file);
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
		in.close();
		return answer;
	}
}
