package aoc2021;

import java.util.*;

public class Day14 {
	
	public String solve(boolean part1, Scanner in) {
		List<String> inps = new ArrayList<>();
		String start = in.nextLine();
		Map<String, Long> vals = new HashMap<>();
		for(int i = 0; i < start.length() - 1; i++) {
			vals.putIfAbsent(start.substring(i, i+2), (long) 0);
			vals.put(start.substring(i, i+2), vals.get(start.substring(i, i+2)) + 1);
		}
		in.nextLine();
		while(in.hasNext()) {
			inps.add(in.nextLine());
		}
		RuleBook book = new RuleBook(inps);
		int count = 0;
		while(count < (part1?10:40)) {
			vals = iterate(vals, book);
			count++;
		}
		long max = 0;
		long min = (long)1000 * 1000 * 1000 * 1000 * 100;
		Map<String, Long> map = new HashMap<>();
		for(String key: vals.keySet()) {
			map.merge(key.substring(0,1), vals.get(key), (a,b) -> a+b);
			map.merge(key.substring(1,2), vals.get(key), (a,b) -> a+b);
		}
		for(String key: map.keySet()) {
			max = Math.max(map.get(key),max);
			min = Math.min(map.get(key),min);
		}
		return (max - min)/2 + "";
	}
	
	private Map<String, Long> iterate(Map<String, Long> vals, RuleBook book){
		Map<String, Long> newVals = new HashMap<>();
		for(String key: vals.keySet()) {
			String[] pats = book.apply(key);
			newVals.merge(pats[0], vals.get(key), (a,b) -> a+b);
			newVals.merge(pats[1], vals.get(key), (a,b) -> a+b);
		}
		return newVals;
	}
}

class RuleBook{
	Map<String, String> map = new HashMap<>();
	public RuleBook(List<String> rules) {
		for(String rule: rules) {
			map.put(rule.split(" -> ")[0], rule.split(" -> ")[1]);
		}
	}
	
	public String[] apply(String rule) {
		return new String[] {rule.substring(0,1) + map.get(rule), map.get(rule) + rule.substring(1,2)};
	}
}

