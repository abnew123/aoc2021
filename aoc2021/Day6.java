package aoc2021;

import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

public class Day6 implements DayTemplate{
	
	public String solve(boolean part1, Scanner in) throws FileNotFoundException {
		List<Integer> input = Arrays.asList(in.nextLine().split(",")).stream().map(i -> Integer.parseInt(i)).toList();
		long[] ages = IntStream.range(0, 9).map(index -> Collections.frequency(input, index)).asLongStream().toArray();
		for(int i = 0; i < (part1?80:256); i++) {
			ages = helper(ages);
		}
		return ""+Arrays.stream(ages).sum();
	}
	
	public static long[] helper(long[] ages) {
		long[] result = new long[ages.length];
		for(int i = 0; i < ages.length; i++) {
			result[(i - 1 + ages.length)%ages.length] += ages[i];
		}
		result[6] += ages[0];
		return result;
	}
}

