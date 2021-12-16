package aoc2021;

import java.util.*;

public class Day12 {
	
	public String solve(boolean part1, Scanner in) {
		List<Link> lines = new ArrayList<>();
		List<String> caves = new ArrayList<>();
		while(in.hasNext()) {
			Link link = new Link(in.nextLine());
			lines.add(link);
			if(!caves.contains(link.start)) {
				caves.add(link.start);
			}
			if(!caves.contains(link.end)) {
				caves.add(link.end);
			}
		}
		int size = caves.size();
		for(int i = 0; i < size; i++) {
			caves.add(caves.get(i));
		}
		caves.remove("start");
		caves.remove("start");
		long answer = recurse(lines, caves, "start", "", part1?true:false);
		return "" + answer;
	}
	
	public long recurse(List<Link> links, List<String> caves, String start, String path, boolean twice) {
		long answer = 0;
		for(Link link: links) {
			if(link.has(start) && caves.contains(link.apply(start))) {
				String next = link.apply(start);
				if(next.equals("end")) {
					answer++;
				}
				else {
					List<String> newCaves = new ArrayList<>();
					boolean newTwice = twice;
					newCaves.addAll(caves);
					if(isStringLowerCase(next)) {
						int nums = 0;
						for(int i = 0; i < caves.size(); i++) {
							if(caves.get(i).equals(next)) {
								nums++;
							}
						}
						if(twice && nums == 1) {
							continue;
						}
						newCaves.remove(next);
						if(newTwice) {
							newCaves.remove(next);
						}
						if(!newCaves.contains(next)) {
							newTwice = true;
						}
					}
					answer += recurse(links, newCaves, next, path + next, newTwice);
				}
			}
		}
		return answer;
	}
	
	private boolean isStringLowerCase(String str) {
        char[] charArray = str.toCharArray();
        for(int i = 0; i < charArray.length; i++) {
            if( !Character.isLowerCase( charArray[i] ))
                return false;
        }
        return true;
    }
}

class Link{
	String start;
	String end;
	boolean bigone;
	boolean bigtwo;
	public Link(String link) {
		start = link.split("-")[0];
		end = link.split("-")[1];
	}
	public boolean has(String test) {
		return test.equals(end) || test.equals(start);
	}
	public String apply(String beginning) {
		if(beginning.equals(start)) {
			return end;
		}
		return start; 
	}
}

	
	