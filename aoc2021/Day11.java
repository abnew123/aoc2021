package aoc2021;

import java.util.*;

public class Day11 {
	
	public String solve(boolean part1, Scanner in) {
		List<String> lines = new ArrayList<>();
		while(in.hasNext()) {
			lines.add(in.nextLine());
		}
		Map<Coord, Integer> inps = new HashMap<>();
        for (int i = 0; i < lines.size(); i++) {
            for (int j = 0; j < lines.get(i).length(); j++) {
                inps.put(new Coord(i, j), Integer.parseInt(lines.get(i).split("")[j]));
            }
        }
		int answer = 0;
		for(int i = 0; i < 10000; i++) {
			if(!part1 && check(inps)) {
				return "" + i;
			}
			if(part1 && i == 100) {
				return "" + answer;
			}
			answer += iterate(inps);
		}
		return "" + answer;
	}
	
	private int iterate(Map<Coord, Integer> inps) {
		inps.keySet().stream().forEach(a->inps.put(a, inps.get(a) + 1));
		int cycles = 0;
		List<Coord> flashed = new ArrayList<>();
		while(flash(inps, flashed)){
			cycles++;
		}
		flashed.stream().forEach(a->inps.put(a, 0));
		return cycles;
	}
	
	private boolean flash(Map<Coord, Integer> inps, List<Coord> flashed) {
		Optional<Coord> coord = inps.keySet().stream().filter(a-> (inps.get(a) > 9) && !flashed.contains(a)).findFirst();
		if(!coord.isPresent()) {
			return false;
		}
		flashed.add(coord.get());
		coord.get().adjacents().stream().filter(inps::containsKey).forEach(a->inps.put(a, inps.get(a) + 1));
		return true;
	}
	
	private boolean check(Map<Coord, Integer> inps) {
		return inps.keySet().stream().mapToInt(a -> inps.get(a)).sum() == 0;
	}
	
	private record Coord(int x, int y) {
        List<Coord> adjacents() {
        	List<Coord> neighbors = new ArrayList<>();
        	int[] xs = new int[] {1,1,1,0,0,-1,-1,-1};
    		int[] ys = new int[] {1,0,-1,1,-1,1,0,-1};
    		for(int k = 0; k < xs.length; k++) {
				neighbors.add(new Coord(x + xs[k], y + ys[k]));
    		}
            return neighbors;
        }
    }
}

	
	