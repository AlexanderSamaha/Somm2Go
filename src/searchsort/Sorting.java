package searchsort;

import java.util.ArrayList;

import wineADT.*;

/**
 * Provides methods for sorting an array of type Wine.
 * 
 * @author David Carrie
 * @version Last modified 2019/03/11
 */
public class Sorting {
	
	static Wine [] result;
	private static ArrayList<Wine> list;
	
	/**
	 * Sorts array of wines based on given catagory.
	 * 
	 * @param array Wines to be sorted
	 * @param cat category on which to sort by
	 * @return sorted array of wines
	 */
	public static Wine [] sort(Wine [] array, String cat) {
		Sorting.sort2(array, cat);
		return array;
	}
	
	
	
	//Function to send the array to be sorted based on catagory
	private static void sort2 (Wine [] array, String cat) {
		switch (cat) {
		case "country":WineSort.sort(array, 0);
			return;
		case "designation":WineSort.sort(array, 1);
			return;
		case "rating": WineSort.sort(array, 2);
			return;
		case "price":WineSort.sort(array, 3);
			return; 
		case "province": WineSort.sort(array, 4);  
			return; 
		case "variety": WineSort.sort(array, 5); 
			return; 
		case "geo": WineSort.sort(array, 6);
			return; 
		case "unique_ID": WineSort.sort(array, 7);
			return; 
		case "winery": WineSort.sort(array, 8);
			return;
		default: 
			return;
		}
				
	}
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////Everything below this is useless for now////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Filters array returning sorted list based on given parameters
	 * 
	 * @param array Wines to be sorted
	 * @param cat catagory on which to sort by and filter
	 * @param req filter parameter
	 * @return Sorted array of Wines
	 
	public static Wine [] sort(Wine [] array, String cat, String req) {
		result = filter(array, cat, req);
		Sorting.sort(result, cat);
		return result;
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
	*/
	
	
}
