package aoc2021;

import java.util.*;

public class Day10 {
	
	public String solve(boolean part1, Scanner in) {
		Map<String, Integer> convToInt = new HashMap<>(){{
			put("(",0);
			put("[",1);
			put("{",2);
			put("<",3);
			put(")",4);
			put("]",5);
			put("}",6);
			put(">",7);
		}};
		int[] convert1 = new int[] {3, 57, 1197, 25137};
		List<String> inps = new ArrayList<>();
		while(in.hasNext()) {
			inps.add(in.nextLine());
		}
		Stack<Integer> stack = new Stack<>();
		long answer = 0;
		List<Long> scores = new ArrayList<>();
		for(String inp: inps) {
			stack = new Stack<>();
			String[] letters = inp.split("");
			boolean corrupted = false;
			for(String letter: letters) {
				int let = convToInt.get(letter);
				if(let < 4) {
					stack.push(let);
				}
				if(let>=4){
					if(stack.pop() != (let % 4)) {
						corrupted = true;
						answer+= convert1[let%4];
						break;
					}
				}
			}
			if(!part1 && !corrupted) {
				answer = 0;
				while(!stack.isEmpty()) {
					answer *= 5;
					answer += stack.pop() + 1;
				}
				scores.add(answer);
			}
		}
		Collections.sort(scores);
		return "" + (part1?answer:scores.get((scores.size()/2)));
	}
}

	
	