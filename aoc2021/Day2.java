package aoc2021;

import java.io.*;
import java.util.*;

public class Day2 implements DayTemplate{
	
	public String solve(boolean part1, Scanner in) throws FileNotFoundException {
		int depth = 0;
		int forward = 0;
		int aim = 0; // aim is depth for part 1
		List<String> strs = new ArrayList<>();
		while(in.hasNext()) {
			strs.add(in.nextLine());
		}
		for(String str: strs) {
			int amount = Integer.parseInt(str.split(" ")[1]);
			if(str.contains("forward")) {
				forward+= amount;
				depth += amount * aim;
			}
			if(str.contains("up")) {
				aim-= amount;
			}
			if(str.contains("down")) {
				aim+= amount;
			}
		}
		return "" + (part1?aim:depth) * forward;
	}
}
