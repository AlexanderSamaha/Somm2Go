package graphTNotes;

import java.util.ArrayList;
import java.util.Arrays;

import wineADT.*;

/**
 * Class to calculate edges and weights.
 * 
 * @author David Carrie
 * 
 * @version Last modified 09/04/2019.
 *
 */
public class CalcEW {

	private static final double thresHold = 2; // Threshold to be reached for an Edge to be made

	private static SetWeight[] specialWeights; // Special weights array.

	// Not to be instantiated.
	private CalcEW() {
	}

	/**
	 * Calculate the edges between wines
	 * 
	 * @param array
	 * @return
	 */
	public static Edge[] compute(Wine[] array) {
		return build(array);
	}

	/**
	 * Set the list of of special weighted tasteNotes.
	 * 
	 * @param sw array of specially weighted taste notes.
	 */
	public static void setWeights(SetWeight[] sw) {
		if (sw != null)
			specialWeights = sw;
	}

	// Build the array of edges
	private static Edge[] build(Wine[] array) {

		ArrayList<Edge> e = new ArrayList<Edge>();
		Edge[] edges;
		// ArrayList<Wine> w = new ArrayList<Wine>();

		// For each vertex, calculate edges and weights.
		for (int i = 0; i < array.length; i++) {
			e = calculate(e, array, i);
		}

		edges = e.toArray(new Edge[e.size()]);
		return edges;
	}

	// Calculate all the edges for this wine
	private static ArrayList<Edge> calculate(ArrayList<Edge> e, Wine[] array, int index) {

		double weight;
		Edge t;
		
		if (array[index].get_taste_noteslist().length == 0) {
			return e;
		}
		// For each wine in the array, calculate edges
		for (int i = index + 1; i < array.length; i++) {
			
			weight = weight(array[index], array[i]);
			// If weight is less then threshold, add edge to graph
			if (weight < thresHold) {
				t = new Edge(index, i, weight);
				e.add(t);
			}
		}
		return e;

	}

	// Compare wines and generate weight
	private static double weight(Wine w, Wine x) {
		double weight = 0;
		double sw;
		String[] base = w.get_taste_noteslist();
		String[] comp = x.get_taste_noteslist();
		// Sort taste notes
		Arrays.sort(base);
		Arrays.sort(comp);
		/*
		 * // For each note in the wine, compare to the other for (int i = 0; i <
		 * base.length; i++) { for (int k = 0; k < comp.length; k++) { // Test if the
		 * wines have the same taste note. if (base[i].equalsIgnoreCase(comp[k])) { //
		 * See if not is uniquely weighted, if so subtract from total weight sw =
		 * sWeight(base[i]); if (!(sw == -1.0)) { weight = weight - sw; } else { // if
		 * not uniquely weighted add weight weight = weight + 1; } } } }
		 */
		int i = 0, k = 0;
		while (i < base.length && k < comp.length) {
			if (base[i].compareTo(comp[k]) > 0) {

				weight = weight + 1;
				k++;
			} else if (base[i].compareTo(comp[k]) < 0) {
				weight = weight + 1;
				i++;
			} else {
				sw = sWeight(base[i]);
				if (!(sw == -1.0)) {
					weight = weight - sw;
				}else {
					weight = weight - 1;
				}
				k++;
				i++;
			}
		}
		// Ensure weights aren't negative
		if (weight < 0)
			weight = 0;
		return weight;
	}

	// See if this taste note is uniquely weighted, if so return weight else return
	// -1;
	private static double sWeight(String s) {
		if (specialWeights != null) {
			for (int i = 0; i < specialWeights.length; i++) {
				if (s.equalsIgnoreCase(specialWeights[i].getNote())) {
					return specialWeights[i].getWeight();
				}
			}
		}
		return -1.0;
	}
}
