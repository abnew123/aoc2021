package aoc2021;

import java.util.*;


public class Day15 {
	
	public String solve(boolean part1, Scanner in) {
		List<String> inps = new ArrayList<>();
		while(in.hasNext()) {
			inps.add(in.nextLine());
		}
		int size = part1?inps.size():inps.size() * 5;
		int[][] grid = new int[size][size];
		for(int i = 0; i < size; i++) {
			for(int j = 0; j < size;j++) {
				grid[i][j] = (Integer.parseInt(inps.get(i%inps.size()).split("")[j%inps.size()]) + i/inps.size() + j/inps.size() - 1) % 9 + 1;
			}
		}
		Set<Node> unvisited = initialize(grid);
        Set<Node> visited = solve(unvisited);
        for(Node n: visited) {
        	if(n.name.equals((size - 1) + " " + (size - 1))) {
        		return "" + n.distance;
        	}
        }
		return "" + -1;
	}
	
	private boolean inBounds(int[][] grid, int x, int y) {
    	return x >= 0 && y >=0 && x < grid.length && y < grid[0].length;
    }
	
	 private Set<Node> initialize(int[][] grid) {
	    	Set<Node> unvisited = new HashSet<Node>();
	    	Node[][] nodes = new Node[grid.length][grid[0].length];
	    	for(int i = 0 ; i < grid.length; i++) {
	    		for(int j = 0; j < grid.length; j++) {
	    			nodes[i][j] = new Node(i + " " + j);
	    		}
	    	}
	    	for(int i = 0 ; i < grid.length; i++) {
	    		for(int j = 0; j < grid[0].length; j++) {
	    			if(inBounds(grid,i+1,j)) {
	    				nodes[i][j].connect(nodes[i+1][j], grid[i+1][j]);
	    			}
	    			if(inBounds(grid,i-1,j)) {
	    				nodes[i][j].connect(nodes[i-1][j], grid[i-1][j]);
	    			}
	    			if(inBounds(grid,i,j-1)) {
	    				nodes[i][j].connect(nodes[i][j-1], grid[i][j-1]);
	    			}
	    			if(inBounds(grid,i,j+1)) {
	    				nodes[i][j].connect(nodes[i][j+1], grid[i][j+1]);
	    			}
	    		}
	    	}
	    	
	        nodes[0][0].distance = 0;
	        unvisited.add(nodes[0][0]);
	        return unvisited;
	    }

	    private Set<Node> solve(Set<Node> unvisited) {

	        Set<Node> visited = new HashSet<Node>();

	        while (!unvisited.isEmpty()) {
	            Node current = findNodeWithSmallestDistance(unvisited);
	            updateNeighbors(current);
	            for (Edge edge : current.edges) {   
	            	if(!edge.getNeighbor(current).visited) {
	            		unvisited.add(edge.getNeighbor(current));
	            	}
	            }
	            current.visited = true;
	            unvisited.remove(current);
	            visited.add(current);
	        }

	        return visited;
	    }   

	    private void updateNeighbors(Node current) {

	        for (Edge edge : current.edges) {    
	            Node neighbor = edge.getNeighbor(current);
	            if (!neighbor.visited) {
	                int tentativeNeighborDistance = current.distance + edge.length;
	                if (tentativeNeighborDistance < neighbor.distance) {
	                    neighbor.distance = tentativeNeighborDistance;
	                }
	            }
	        }
	    }

	    private Node findNodeWithSmallestDistance(Set<Node> nodes) {
	        Node smallest = null;
	        for (Node node : nodes) {
	            if (smallest == null || node.distance < smallest.distance) {
	                smallest = node;
	            }
	        }
	        return smallest;
	    }
}

class Node {
    String name;
    Integer distance = Integer.MAX_VALUE;
    boolean visited;
    Set<Edge> edges = new HashSet<Edge>();

    Node(String name) {
        this.name = name;
    }

    Edge connect(Node destination, int length) {
        Edge edge = new Edge();
        edge.length = length;
        edge.from = this;
        edge.to = destination;
        edges.add(edge);
        return edge;
    }
}

class Edge {
    int length;
    Node from;
    Node to;

    Node getNeighbor(Node origin) {
        return from == origin?to:from;
    }
}
