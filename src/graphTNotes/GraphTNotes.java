package graphTNotes;

import java.util.ArrayList;


import wineADT.*;
/**
 * Calculates relations between wines using taste notes.
 * 
 * @author David Carrie
 *
 */
public class GraphTNotes {
	EWGraph graph;				//Edge weighted graph
	Wine[] wines;				//array of wines

	/**
	 * Construct new graph
	 * @param array wines
	 * @param weights special weights
	 */
	public GraphTNotes(Wine[] array, SetWeight [] weights) {
		//Construct graph
		graph = new EWGraph(array.length);
		wines = array;
		//Set special weights
		CalcEW.setWeights(weights);
		//Calculate and add edges
		Edge[] edges = CalcEW.compute(array);
		for (int i = 0; i < edges.length; i++) {
			graph.add(edges[i]);
		}

	}
	/**
	 * Returns the first 10 wines ranked in similarity to given wine, in non-increasing relation
	 * @param w base wine
	 * @return list of 10 closest wines
	 */
	public Wine[] firstTen(Wine w) {
		
		ArrayList<Wine> search = new ArrayList<Wine>();
		int index = getIndex(w);
		
		if (getIndex(w) != -1) {
			BFS matches = new BFS(graph, index);
			Integer[] list = matches.getDist();
			for (int i = 0; i < 10; i++) {
				search.add(wines[list[i]]);
			}
		}
		return search.toArray(new Wine[search.size()]);
	}
	
	/**
	 * Returns all wines ranked in similarity to given wine in non-increasing relation.
	 * @param w base wine
	 * @return list of all related wines
	 */
	public Wine [] all (Wine w) {
		ArrayList<Wine> search = new ArrayList<Wine>();
		int index = getIndex(w);
		
		if (getIndex(w) != -1) {
			BFS matches = new BFS(graph, index);
			Integer[] list = matches.getDist();
			for (int i = 0; i < list.length; i++) {
				search.add(wines[list[i]]);
			}
		}
		return search.toArray(new Wine[search.size()]);
	}
	
	//private method to get the index of a certain wine.
	private int getIndex(Wine w) {
		for (int i = 0; i < wines.length; i++) {
			if (w.get_uniqueID() == wines[i].get_uniqueID()) {
				return i;
			}
		}
		return -1;
	}

}
