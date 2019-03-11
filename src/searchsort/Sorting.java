package searchsort;

import java.util.ArrayList;
import wineADT;

/**
 * Provides methods for sorting an array of type Wine
 * 
 * @author David Carrie
 *
 */
public class Sorting {
	
	static Wine [] result;
	//private static ArrayList<Wine> list;
	
	/**
	 * Filters array returning sorted list based on given parameters (not complete)
	 * 
	 * @param array Wines to be sorted
	 * @param cat catagory on which to sort by and filter
	 * @param req filter parameter
	 * @return Sorted array of Wines
	 */
	public static Wine [] sort(Wine [] array, String cat, String req) {
		//result = filter(array, req);
		result = Sorting.sort(array, cat);
		return result;
	}
	/**
	 * Sorts array of wines based on given catagory
	 * 
	 * @param array Wines to be sorted
	 * @param cat category on which to sort by
	 * @return sorted array of wines
	 */
	public static Wine [] sort(Wine [] array, String cat) {
		result = Sorting.sort(array, cat);
		return result;
	}
	
	/*
	private Wine [] filter (Wine [] array, String req) {
		
		list = new ArrayList();
		for (int i = 0; i < array.length(); i++) {
			if
		}
	}*/
	
	//Function to send the array to be sorted based on catagory
	private static Wine [] sort (Wine [] array, String cat) {
		switch (cat) {
		case "country":
			return WineSort.sort(array, 0);
		case "designation":
			return WineSort.sort(array, 1);
		case "rating":
			return WineSort.sort(array, 2);
		case "price":
			return WineSort.sort(array, 3);
		case "province":  
			return WineSort.sort(array, 4);
		case "variety": 
			return WineSort.sort(array, 5);
		case "geo":
			return WineSort.sort(array, 6);
		default: return array;
			break;
		}
				
	}
	
	
}
