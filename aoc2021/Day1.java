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
		List<Integer> nums = new ArrayList<>();
		List<Integer> nums2 = new ArrayList<>();
		while(in.hasNext()) {
			nums.add(in.nextInt());
		}
		for(int i = 0; i < nums.size()-2; i++) {
			nums2.add(nums.get(i) + nums.get(i+1) + nums.get(i+2));
		}
		if(part1) {
			for(int i = 1; i < nums.size(); i++) {
				if(nums.get(i) > nums.get(i-1)) {
					answer+=1;
				}
			}
		}
		else {
			for(int i = 1; i < nums2.size(); i++) {
				if(nums2.get(i) > nums2.get(i-1)) {
					answer+=1;
				}
			}
		}
		in.close();
		return answer;
	}
}
