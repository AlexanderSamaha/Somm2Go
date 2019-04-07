package graphTNotes;

import java.util.ArrayList;

import wineADT.Wine;

/**
 * calculates all paths in order of distance from given source. Written with reference to:
 * Algorithms Fourth Edition p.541. Robert Sedgewick and Kevin Wayne.
 * 
 * @author David Carrie
 *
 */
public class BFS {
	
	private boolean[] marked;
	private int [] edgeTo;
	private final int s;
	private Integer [] byDist;
	
	
	public BFS (EWGraph graph, int src) {
		marked = new boolean [graph.V()];
		edgeTo = new int [graph.V()];
		s = src;
		byDist = bfs(graph, s);
		
	}
	
	private Integer [] bfs (EWGraph G, int s) {
		ArrayList<Integer> winesIndexs = new ArrayList<Integer>();
		
		Queue<Integer> queue = new Queue<Integer>();
		marked [s] = true;
		queue.enqueue(s);
		while (!queue.isEmpty()) {
			int v = queue.dequeue();
			for (Edge w: G.adj(v)) {
				if (!marked[w.other(v)]) {
					marked[w.other(v)] = true;
					edgeTo[w.other(v)] = v;
					winesIndexs.add(w.other(v));
					queue.enqueue(w.other(v));
				}
			}
		}
		return winesIndexs.toArray(new Integer[winesIndexs.size()]);
	}
	
	public Integer [] getDist() {
		return this.byDist;
	}
}
