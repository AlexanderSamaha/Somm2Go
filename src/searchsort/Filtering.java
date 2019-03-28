package searchsort;
import java.util.ArrayList;

import wineADT.*;

/**
 * A class that filters wines by a given range
 * 
 * @author Daniel Noorduyn
 * @version March 21, 2019
 *
 */
public class Filtering {

	/**
	 * Linear search using Wine.
	 * @param x - an array of Wine objects to be searched
	 * @param category - a String of which parameter in the Wine objects will be compared
	 * @param low - a String of the lower boundary of the desired filtering range
	 * @param high - a String of the higher boundary of the desired filtering range
	 * @return returns an array of Wine objects corresponding to the search parameter argument
	 */
	public static Wine [] linear_filtering(Wine [] x, String category, String low, String high) {
		int n = x.length;
		
		//array to place search hits into
		ArrayList<Wine> searched = new ArrayList<Wine>();
		
		//search linearly through entire inputed array
		for(int i = 0; i < n; i++) {
			if(Searching.wine_adt_categories(x[i], high, category) < 0 && Searching.wine_adt_categories(x[i], low, category) > 0)
			{
				searched.add(x[i]);
			}
		}
		Wine[] searched_array = new Wine[searched.size()];
		searched_array = searched.toArray(searched_array);
		Sorting.sort(searched_array, category);
		return searched_array;
	}

}
