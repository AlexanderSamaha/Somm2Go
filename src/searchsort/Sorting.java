package searchsort;

import java.util.ArrayList;

/**
 * Provides methods for sorting an array of type Wine
 * 
 * @author David Carrie
 *
 */
public class Sorting {
	
	static Wine [] result;
	private static ArrayList<Wine> list;
	
	/**
	 * Filters array returning sorted list based on given parameters
	 * 
	 * @param array Wines to be sorted
	 * @param cat catagory on which to sort by and filter
	 * @param req filter parameter
	 * @return Sorted array of Wines
	 */
	public static Wine [] sort(Wine [] array, String cat, String req) {
		result = filter(array, cat, req);
		Sorting.sort(result, cat);
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
		Sorting.sort(array, cat);
		return array;
	}
	
	//Filters the array
	private Wine [] filter (Wine [] array, String cat, String req) {
		
		list = new ArrayList<Wine>();
		for (int i = 0; i < array.length(); i++) {
			if (filterTest(array[i], cat, req)) {
				list.add(array[i]);
			}
		}
		result = (Wine)list.toArray();
	}
	
	//Function to send the array to be sorted based on catagory
	private static void sort (Wine [] array, String cat) {
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
		default: 
			return;
		}
				
	}
	
	//Return if the Wine passes the filter, for price its less than or equal to.
	private static boolean filterTest (Wine item, String cat, String req) {
		
		switch(cat) {
		case "country":
			return item.get_country() == req;
		case "taste_notes":
			String [] temp = item.get_taste_notes();
			for (int i = 0; i < temp.length; i++) {
				if (temp[i] == req) {
					return true;
				}
			}
			return false;
		case "designation":
			return item.get_designation() == req;
		case "rating":
			return item.get_rating() == Integer.parseInt(req);
		case "price":
			return item.get_price() <= Double.parseDouble(req);
		case "province":
			return item.get_province() == req;
		case "variety":
			return item.get_variety() == req;
		case "winery":
			return item.get_winery() == req;
		default:
			return true;
		}
		
	}
	
	
}
