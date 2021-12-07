package aoc2021;

import java.io.*;
import java.util.*;
import java.util.stream.LongStream;

public class Day7 implements DayTemplate{
	
	public String solve(boolean part1, Scanner in) throws FileNotFoundException {
		List<String> inps = Arrays.asList(in.nextLine().split(","));
		return "" + LongStream.range(0, 1000).map(a -> inps.stream().mapToLong(Long::parseLong).map(e -> part1?Math.abs(e - a):(Math.abs(e -a ) * (Math.abs(e - a) + 1)/2)).sum()).min().getAsLong();
	}
	
}

