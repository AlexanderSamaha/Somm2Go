package Recommendations;

import java.util.Arrays;

import graphTNotes.GraphTNotes;
import graphTNotes.SetWeight;
import searchsort.Filtering;
import searchsort.Searching;
import searchsort.Sorting;
import wineADT.Wine;

public class Recommendations {

	private Wine base;
	private GraphTNotes graph;
	/**
	 * Construct a new recommendation object
	 * @param arr array of wines
	 * @param x base wine to be recommended
	 */
	public Recommendations (Wine [] arr, Wine x) {
		this.base = x;
		arr = filter(arr, x);
		Sorting.sort(arr, "rating");
		//SetWeight [] sw = TasteNoteLibrary.special_weights(TasteNoteLibrary.get_special_patterns());
		SetWeight [] sw = null;
		this.graph = new GraphTNotes(arr, sw);
		
	}
	
	private Wine [] filter (Wine [] arr, Wine x) {
		String hi = "" + (x.get_price() + 5);
		String lo = "" + (x.get_price() - 5);
		Wine [] temp;
		temp = Filtering.linear_filtering(arr, "price", lo, hi);
		if (temp.length < 1000 && temp.length > 10)
			return temp;
		else if (temp.length > 1000) {
			arr = temp;
		}
		temp = Searching.linear_search(arr, x.get_variety(), "variety");
		if (temp.length < 1000 && temp.length > 10)
			return temp;
		else if (temp.length > 1000) {
			arr = temp;
		}
		temp = Searching.linear_search(arr, x.get_country(), "country");
		if (temp.length < 1000 && temp.length > 10)
			return temp;
		else if (temp.length > 1000) {
			arr = temp;
		}
		temp = Searching.linear_search(arr, x.get_province(), "province");
		if (temp.length < 1000 && temp.length > 10)
			return temp;
		else if (temp.length > 1000) {
			arr = temp;
		}
		arr = Arrays.copyOfRange(arr, 0, 1000);
		return arr;
		
	}
	
	public Wine [] get_results() {
		return graph.all(base);
	}
}
