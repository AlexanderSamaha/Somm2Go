package graphTNotes;

/**
 * Edge weighted graph. Written with reference to: Algorithms Fourth Edition
 * p.611. Robert Sedgewick and Kevin Wayne.
 * 
 * @author David Carrie
 *
 */
public class EWGraph {
	private int V;				//Number of vertices.
	private int E;				//Number of edges.
	private Bag<Edge>[] adj;	//Adjacency list.
	
	/**
	 * Construct a new Edge Weighted Graph with a given amount of vertices.
	 * 
	 * @param numVrtx the number of vertices.
	 */
	public EWGraph(int numVrtx) {
		V = numVrtx;
		E = 0;
		adj = (Bag<Edge>[]) new Bag[V];
		for (int i = 0; i < V; i++) {
			adj[i] = new Bag<Edge>();
		}
	}
	/**
	 * Get the number of vertices in the graph
	 * @return number of vertices.
	 */
	public int V() {
		return V;
	}
	/**
	 * Get the number of edges in the graph
	 * @return number of edges
	 */
	public int E() {
		return E;
	}

	/**
	 * add an edge to the graph
	 * 
	 * @param e Edge to add.
	 */
	public void add(Edge e) {
		int v = e.either();
		int w = e.other(v);
		adj[v].add(e);
		adj[w].add(e);
		E++;
	}

	/**
	 * Returns and iterable of all adjacent edges from a given vertex.
	 * 
	 * @param v vertex index.
	 * @return all adjacent edges.
	 */
	public Iterable<Edge> adj(int v) {
		return adj[v];
	}

	/**
	 * Returns an iterable of all the graph's edges (ignoring self loops)
	 * 
	 * @return iterable of edges.
	 */
	public Iterable<Edge> edges() {
		Bag<Edge> bag = new Bag<Edge>();
		for (int v = 0; v < V; v++) {
			for (Edge e : adj[v])
				if (e.other(v) > v)
					bag.add(e);
		}
		return bag;
	}
}
