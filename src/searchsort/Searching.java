package searchsort;

import java.util.*;

import wineADT.*;

/**
 * A class for searching through a number of Wines
 * @author Daniel William Noorduyn
 * @version Last Modified 14/04/19
 */
public class Searching {
	
	/**
	 * Uses the parameter category to choose which compare function to use.
	 * Compares the wine objects based on that category and a user input argument
	 * @param i an object of type Wine to be compared against the argument
	 * @param argument a String of what is being compared to each wine
	 * @param category a String of which parameter in the Wine objects will be compared
	 * @return returns an int that is 0, less than 0, greater than 0 depending on i compared to argument
	 */
	public static int wine_adt_categories(Wine i, String argument, String category) {
		if(category.equals("price"))
		{
			return i.get_price().compareTo(Double.parseDouble(argument));
		}
		else if(category.equals("variety"))
		{
			return i.get_variety().compareToIgnoreCase(argument);
		}
		else if(category.equals("unique_ID"))
		{
			return i.get_uniqueID().compareTo(Integer.parseInt(argument));
		}
		else if(category.equals("rating"))
		{
			return i.get_rating().compareTo(Integer.parseInt(argument));
		}
		else if(category.equals("country"))
		{
			return i.get_country().compareTo(argument);
		}
		else if(category.equals("province"))
		{
			return i.get_province().compareTo(argument);
		}
		else if(category.equals("geo"))
		{
			return i.get_geo().compareTo(argument);
		}
		else if(category.equals("winery"))
		{
			return i.get_winery().compareTo(argument);
		}
		return 0;
	}
		
	/**
	 * Linear search on wines
	 * @param x an array of Wine objects to be searched
	 * @param argument a String of what is being searched for
	 * @param category a String of which parameter in the Wine objects will be compared
	 * @return returns an array of Wine objects corresponding to the search parameter argument
	 */
	public static Wine [] linear_search(Wine [] x, String argument, String category) {
		//length of input WineADT ADT List
		int n = x.length;
		
		//array to place search hits into
		ArrayList<Wine> searched = new ArrayList<Wine>();
		
		//search linearly through entire inputed array
		for(int i = 0; i < n; i++) {
			if(wine_adt_categories(x[i], argument, category) == 0)
			{
				searched.add(x[i]);
			}
		}
		Wine[] searched_array = new Wine[searched.size()];
		searched_array = searched.toArray(searched_array);
		return searched_array;
	}
	
	/**
	 * A combined binary linear search using Wine.
	 * Use binary search to find one Wine object that corresponds to search parameter argument.
	 * Uses linear search to find index range of corresponding Wine objects.
	 * Used only on an input array previously sorted by the parameter category.
	 * @param x an array of Wine objects to be searched
	 * @param argument a String of what is being searched for
	 * @param category a String of which parameter in the Wine objects will be compared
	 * @return returns an array of Wine objects corresponding to the search parameter argument
	 */
	public static Wine [] binary_linear_search(Wine [] x, String argument, String category) {
		//length of input WineADT ADT List
		int n = x.length;
		
		//array to place search hits into
		ArrayList<Wine> searched = new ArrayList<Wine>();
		
		//binary search to find one matching WineADT
		int oneIndex = index_of(x, argument, category);
		
		//find lower boundary of matching WineADTs
		while(oneIndex > 0) {
			if(wine_adt_categories(x[oneIndex-1], argument, category) == 0)
				oneIndex -= 1;
			else break;
		}
		int lowerBound = oneIndex;
		
		//find upper boundary of matching WineADTs
		while(oneIndex < n-1)
		{
			if(wine_adt_categories(x[oneIndex+1], argument, category) == 0) {
				oneIndex += 1;
			}
			else break;
		}
		int upperBound = oneIndex;
		
		//place found WineADTs into an array
		if(oneIndex >=0) {
			
			for(int i = lowerBound; i <= upperBound; i++) {
				searched.add(x[i]);
			}
		}
		Wine[] searched_array = new Wine[searched.size()];
		searched_array = searched.toArray(searched_array);
		return searched_array;
	}
	
	
	// Binary search for combined binary linear search
	private static int index_of(Wine [] x, String key, String category) {
		int lo = 0;
		int hi = x.length - 1;
		while(lo <= hi)
		{
			int mid = lo + (hi - lo) / 2;
			if(wine_adt_categories(x[mid], key, category) > 0) 
			{
				hi = mid - 1;
			}
			else if(wine_adt_categories(x[mid], key, category) < 0)
			{
				lo = mid + 1;
			}
			else return mid;
		}
		return -1;
	}
	
	/**
	 * Binary search on Wine objects
	 * Used only on input arrays previously sorted by uniqueID
	 * @param x an array of Wine objects to be searched
	 * @param id a String of what is being searched for
	 * @return returns one Wine object corresponding to the search parameter id
	 */
	public static Wine binary_search(Wine[] x, Integer id) {
		int lo = 0;
		int hi = x.length - 1;
		while(lo <= hi)
		{
			int mid = lo + (hi - lo) / 2;
			if(id.compareTo(x[mid].get_uniqueID()) < 0)
			{
				hi = mid - 1;
			}
			else if(id.compareTo(x[mid].get_uniqueID()) > 0)
			{
				lo = mid + 1;
			}
			else return x[mid];
		}
		return null;
	}
	
	/**
	 * Linear search finding wines with a name that contains the passed string.
	 * @param x an array of Wine objects to be searched
	 * @param name a String of the Wine name
	 * @return returns an array of wines corresponding to the searched Wine name
	 */
	public static Wine [] linear_name_search(Wine [] x, String name) {
		//length of input WineADT ADT List
		int n = x.length;
		
		//array to place search hits into
		ArrayList<Wine> searched = new ArrayList<Wine>();
				
		//search linearly through entire inputed array
		for(int i = 0; i < n; i++) {
			if(x[i].get_designation().contains(name))
			{
				searched.add(x[i]);
			}
		}
		Wine[] searched_array = new Wine[searched.size()];
		searched_array = searched.toArray(searched_array);
		return searched_array;
	}
	
	/**
	 * Linear search finding wines with a taste note that contains the passed string.
	 * @param x an array of Wine objects to be searched
	 * @param taste_notes a String of what taste note is being searched for
	 * @return returns an array of wines corresponding to the search parameter taste_notes
	 */
	public static Wine [] linear_taste_notes_search(Wine [] x, String taste_notes) {
		//length of input WineADT ADT List
		int n = x.length;
		
		//array to place search hits into
		ArrayList<Wine> searched = new ArrayList<Wine>();
				
		//search linearly through entire inputed array
		for(int i = 0; i < n; i++) {
			if(x[i].get_taste_notes().contains(taste_notes))
			{
				searched.add(x[i]);
			}
		}
		Wine[] searched_array = new Wine[searched.size()];
		searched_array = searched.toArray(searched_array);
		return searched_array;
	}
	
}
