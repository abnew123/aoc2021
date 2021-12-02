package aoc2021;

import java.io.File;
import java.lang.reflect.Method;
import java.util.Scanner;

public class MasterSolver {
	public static void main(String[] args) throws Exception {
		int day = 1;
		boolean part1 = true;
		File file = new File("./aoc2021/day" + day + ".txt");
		Scanner in = new Scanner(file);
		Class<?> cls = Class.forName("aoc2021.Day" + day);
		Method m = cls.getDeclaredMethod("solve", boolean.class, Scanner.class);
		String answer = (String) m.invoke(cls.getDeclaredConstructor().newInstance(), part1, in);
		System.out.println("Day " + day + " part " + (part1?1:2) + " solution: " + answer);
		in.close();
	}
}
