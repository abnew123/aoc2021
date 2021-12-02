package aoc2021;

import java.io.*;
import java.util.*;

public class Day2 {
	public static void main(String[] args) throws FileNotFoundException {
		System.out.println("Part 1 solution: " + solve(true, 2));
		System.out.println("Part 2 solution: " + solve(false, 2));
	}
	
	public static int solve(boolean part1, int day) throws FileNotFoundException {
		File file = new File("./aoc2021/day" + day + ".txt");
		Scanner in = new Scanner(file);
		int depth = 0;
		int forward = 0;
		int aim = 0;
		List<String> strs = new ArrayList<>();
		while(in.hasNext()) {
			strs.add(in.nextLine());
		}
		for(String str: strs) {
			int amount = Integer.parseInt(str.split(" ")[1]);
			if(str.contains("forward")) {
				forward+= amount;
				if(!part1) {
					depth += amount * aim;
				}
				
			}
			if(str.contains("up")) {
				if(part1) {
					depth-= amount;
				}
				else {
					aim-= amount;
				}
				
			}
			if(str.contains("down")) {
				if(part1) {
					depth+= amount;
				}
				else {
					aim+= amount;
				}
			}
		}
		in.close();
		return depth * forward;
	}
}
