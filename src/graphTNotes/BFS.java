package graphTNotes;

import java.util.ArrayList;

import wineADT.Wine;

/**
 * Calculates all paths using breadth first search in order of distance from given source. Written with reference to:
 * Algorithms Fourth Edition p.541. Robert Sedgewick and Kevin Wayne.
 * 
 * @author David Carrie
 * 
 * Last modified 09/04/2019.
 *
 */
public class BFS {
	
	private boolean[] marked;
	private int [] edgeTo;
	private final int s;
	private Integer [] byDist;
	
	/**
	 * Consturct a new BFS (breadth first search object) with given edge weighted graph and starting vertex
	 * @param graph edge weighted graph to run the search on
	 * @param src source vertex to originate search from
	 */
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
	/**
	 * Get the nodes connected to the source in order of distance in number of edges
	 * @return array of integers that represent node values
	 */
	public Integer [] getDist() {
		return this.byDist;
	}
}
