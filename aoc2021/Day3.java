package aoc2021;

import java.io.*;
import java.util.*;

public class Day3{
	
	public String solve(boolean part1, Scanner in) throws FileNotFoundException {
		List<String> inps = new ArrayList<>();
		while(in.hasNext()) {
			inps.add(in.nextLine());
		}
		int[] differentials = new int[12];
		for(String inp: inps) {
			char[] chars = inp.toCharArray();
			for(int i = 0; i < differentials.length; i++) {
				differentials[i] += chars[i] == '1'?1:-1;
			}
		}
		if(!part1) {
			List<String> newNums = new ArrayList<>();
			for(String num: inps) {
				newNums.add(num);
			}
			return "" + Integer.parseInt(helper(inps, "",true),2) * Integer.parseInt(helper(newNums,"",false),2);
		}
		String epsilon = "";
		String gamma = "";
		for(int i = 0; i < differentials.length; i++) {
			gamma+=differentials[i] > 0?"1":"0";
			epsilon+=differentials[i] > 0?"0":"1";
		}
		return "" + Integer.parseInt(gamma,2) * Integer.parseInt(epsilon,2);
	}
	
	public static String helper(List<String> nums, String prefix, boolean most) {
		int differential = 0;
		for(int i = nums.size() - 1; i>=0;i--) {
			if(!nums.get(i).startsWith(prefix)) {
				nums.remove(i);
			}
		}
		if(nums.size() == 1) {
			return nums.get(0);
		}
		for(String num: nums) {
			differential += num.charAt(prefix.length()) == '1'?1:-1;
		}
		if(differential >= 0) {
			prefix += most? "1":"0";
		}
		else {
			prefix+=most?"0":"1";
		}
		return helper(nums,prefix, most);
		
	}
}
