package aoc2021;

import java.io.*;
import java.util.*;

public class Day1 {
	public static void main(String[] args) throws FileNotFoundException {
		int part = 1;
		int day = 1;
		File file = new File("./aoc2021/day" + day + ".txt");
		Scanner in = new Scanner(file);
		String answer = "";
		String inp = "";
		while(in.hasNext()) {
			inp = in.next();
			//System.out.println(inp);
		}
		
		in.close();
		System.out.println(answer);
	}
}
