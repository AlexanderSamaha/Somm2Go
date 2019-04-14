package graphTNotes;

/**
 * Weighted undirected Edge ADT that implements comparable. Written with reference to:
 * Algorithms Fourth Edition p.610. Robert Sedgewick and Kevin Wayne.
 *
 * 
 * @author David Carrie
 * 
 * @version Last modified 09/04/2019.
 *
 */
public class Edge implements Comparable<Edge> {
	private final int v;
	private final int w;
	private double weight; // Edge weight, (changeable)
	
	
	/**
	 * Construct a new Edge
	 * 
	 * @param f index of first vertex.
	 * @param s index of second vertex.
	 * @param weight Edge weight.
	 */
	public Edge(int f, int s, double weight) {
		v = f;
		w = s;
		this.weight = weight;
	}

	/**
	 * returns the weight of this Edge.
	 * 
	 * @return weight.
	 */
	public double weight() {
		return weight;
	}

	/**
	 * Returns the index of a vertex.
	 * 
	 * @return vertex index.
	 */
	public int either() {
		return v;
	}

	/**
	 * Returns the index of the vertex not passed in. Throws illegal argument if
	 * Edge does not contain vertex.
	 * 
	 * @param vrtx index of one vertex.
	 * @return index of the other vertex.
	 */
	public int other(int vrtx) {
		if (vrtx == v)
			return w;
		else if (vrtx == w)
			return v;
		else
			throw new IllegalArgumentException();
	}

	/**
	 * Compare this edge's weight to another.
	 * 
	 * @param other edge to compare this edge with
	 * @return returns -1 or 0 based on comparison
	 */
	public int compareTo(Edge other) {
		if (this.weight() < other.weight())
			return -1;
		else if (this.weight() > other.weight())
			return 0;
		else
			return 0;
	}
	
	/**
	 * Customized to String method for Edge
	 * @return returns a string representing Edge
	 */
	public String toString() {
		return String.format("%d-%d %.2f", v, w, weight);
	}

	/**
	 * Change the weight of this Edge.
	 * 
	 * @param weight new weight.
	 */
	public void changeWeight(double weight) {
		this.weight = weight;
	}
}
