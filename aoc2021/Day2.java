package aoc2021;

import java.io.*;
import java.util.*;

public class Day2 implements DayTemplate{
	
	public String solve(boolean part1, Scanner in) throws FileNotFoundException {
		int depth1 = 0;
		int depth2 = 0;
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
				depth2 += amount * aim;
			}
			if(str.contains("up")) {
				depth1-= amount;
				aim-= amount;
			}
			if(str.contains("down")) {
				depth1+= amount;
				aim+= amount;
			}
		}
		in.close();
		return "" + (part1?depth1:depth2) * forward;
	}
}
