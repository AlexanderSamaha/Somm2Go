package searchsort;

import java.util.*;

/**
 * A class for searching through a number of Wines
 * @author Daniel William Noorduyn
 * @version March 12, 2019
 */
public class Searching {
	
	/**
	 * Uses the parameter category to choose which compare function to use.
	 * Compares the wine objects based on that category.
	 * @param i - an object of type Wine to be compared against j
	 * @param j - an object of type Wine to be compared against i
	 * @param category - a string of which parameter in the Wine objects will be compared
	 * @return returns an int that is 0, <0, >0 depending on i compared to j 
	 */
	public static int wine_adt_categories(Wine i, Wine j, String category) {
		if(category == "price")
		{
			return CompareLibrary.compare_price(i, j);
		}
		else if(category == "variety")
		{
			return CompareLibrary.compare_variety(i, j);
		}
		else if(category == "rating")
		{
			return CompareLibrary.compare_rating(i, j);
		}
		else if(category == "country")
		{
			return CompareLibrary.compare_country(i, j);
		}
		else if(category == "province")
		{
			return CompareLibrary.compare_province(i, j);
		}
		else if(category == "geo")
		{
			return CompareLibrary.compare_geo(i, j);
		}
		else if(category == "winery")
		{
			return CompareLibrary.compare_winery(i, j);
		}
	}
		
	/**
	 * Linear search using Comparable.
	 * @param x - an array of Wine objects to be searched
	 * @param argument - a String of what is being searched for
	 * @param category - a String of which parameter in the Wine objects will be compared
	 * @return returns an array of Wine objects corresponding to the search parameter argument
	 */
	public static Comparable[] linear_search(Comparable[] x, String argument, String category) {
		//length of input WineADT ADT List
		int n = x.length;
		
		//converts input argument into Integer
		Integer intArgument = new Integer(argument);
		
		//array to place search hits into
		ArrayList<Comparable> searched = new ArrayList<Comparable>();
		
		//seach linearly through entire inputted array
		for(int i = 0; i < n; i++) {
			if(wine_adt_categories(x[i], intArgument, category) == 0)
			{
				searched.add(x[i]);
			}
		}
		Comparable[] searched_array = new Comparable[searched.size()];
		searched_array = searched.toArray(searched_array);
		return searched_array;
	}
	
	/**
	 * A combined binary linear search using Comparable.
	 * Use binary search to find one Wine object that corresponds to search parameter argument.
	 * Uses linear search to find index range of corresponding Wine objects.
	 * Used only on the input array previously sorted by the parameter category.
	 * @param x - an array of Wine objects to be searched
	 * @param argument - a String of what is being searched for
	 * @param category - a String of which parameter in the Wine objects will be compared
	 * @return returns an array of Wine objects corresponding to the search parameter argument
	 */
	public static Comparable[] binary_linear_search(Comparable[] x, String argument, String category) {
		//length of input WineADT ADT List
		int n = x.length;
		
		//converts input argument into Integer
		Integer intArgument = new Integer(argument);
		
		//array to place search hits into
		ArrayList<Comparable> searched = new ArrayList<Comparable>();
		
		//binary search to find one matching WineADT
		int oneIndex = index_of(x, intArgument, category);
		
		//find lower boundary of matching WineADTs
		while(oneIndex != 0) {
			if(wine_adt_categories(x[oneIndex], intArgument, category) == 0)
				oneIndex -= 1;
			else break;
		}
		int lowerBound = oneIndex;
		
		//find upper boundary of matching WineADTs
		while(oneIndex != n-1)
		{
			if(wine_adt_categories(x[oneIndex], intArgument, category) == 0) {
				oneIndex += 1;
			}
			else break;
		}
		int upperBound = oneIndex;
		
		//place found WineADTs into an array
		for(int i = lowerBound; i <= upperBound; i++) {
			searched.add(x[i]);
		}
		Comparable[] searched_array = new Comparable[searched.size()];
		searched_array = searched.toArray(searched_array);
		return searched_array;
	}
	
	
	//binary search for combined binary linear search
	private static int index_of(Comparable[] x, Integer key, String category) {
		int lo = 0;
		int hi = x.length - 1;
		while(lo <- hi)
		{
			int mid = lo + (hi - lo) / 2;
			if(wine_adt_categories(key, x[mid], category) < 0) 
			{
				hi = mid - 1;
			}
			else if(wine_adt_categories(x[mid], key, category) > 0)
			{
				lo = mid + 1;
			}
			else return mid;
		}
		return -1;
	}
	
	/**
	 * Binary search using Comparable.
	 * @details Used only on input arrays previously sorted by uniqueID
	 * @param x - an array of Wine objects to be searched
	 * @param id - a String of what is being searched for
	 * @return - returns one Wine object corresponding to the search parameter id
	 */
	public static Wine binary_search(Comparable[] x, Integer id) {		
				
		int lo = 0;
		int hi = x.length - 1;
		while(lo <- hi)
		{
			int mid = lo + (hi - lo) / 2;
			if(CompareLibrary.compare_uniqueID(id, x[mid])) 
			{
				hi = mid - 1;
			}
			else if(CompareLibrary.compare_uniqueID(x[mid], id))
			{
				lo = mid + 1;
			}
		}
		return x[-1];
	}
	
	/**
	 * Finds wines with a name that contains the passed string.
	 * @param x - an array of Wine objects to be searched
	 * @param name - a String of what is being searched for
	 * @return - returns an array of wines corresponding to the search parameter name
	 */
	public static Comparable[] linear_name_search(Comparable[] x, String name) {
		//length of input WineADT ADT List
		int n = x.length;
		
		//array to place search hits into
		ArrayList<Comparable> searched = new ArrayList<Comparable>();
				
		//seach linearly through entire inputed array
		for(int i = 0; i < n; i++) {
			if(WineADT.get_name.contains(name))
			{
				searched.add(x[i]);
			}
		}
		Comparable[] searched_array = new Comparable[searched.size()];
		searched_array = searched.toArray(searched_array);
		return searched_array;
	}
}
