package searchsort;
import java.util.*;

public class Searching {
	
	//private static boolean wine_price = false;
	//private static boolean wine_variety = false;
	//private static boolean wine_rating = false;
	//private static boolean wine_name = false;
	//private static boolean wine_country = false;
	//private static boolean wine_province = false;
	//private static boolean wine_geo = false;
	//private static boolean wine_taste_notes = false;
	//private static boolean wine_designation = false;
	//private static boolean wine_description = false;
	//private static boolean wine_winery = false;
	
	public static int wine_adt_categories(Wine i, Wine j, String category) {
		if(category == "id")
		{
			return CompareLibrary.compare_uniqueID(i, j);
		}
		else if(category == "price")
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
		else if(category == "name")
		{
			return CompareLibrary.compare_name(i, j);
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
		else if(category == "taste notes")
		{
			return CompareLibrary.compare_taste_notes(i, j);
		}
		else if(category == "description")
		{
			return CompareLibrary.compare_description(i, j);
		}
		else if(category == "designation")
		{
			return CompareLibrary.compare_designation(i, j);
		}
		else if(category == "winery")
		{
			return CompareLibrary.compare_winery(i, j);
		}
	}
		
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
	
	public static Comparable[] binary_linear_search(Comparable[] x, String argument, String category) {
		//length of input WineADT ADT List
		int n = x.length;
		
		//converts input argument into Integer
		Integer intArgument = new Integer(argument);
		
		//array to place search hits into
		ArrayList<Comparable> searched = new ArrayList<Comparable>();
		
		//binary search to find one matching WineADT
		int oneIndex = indexOf(x, intArgument, category);
		
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
	public static int indexOf(Comparable[] x, Integer key, String category) {
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
	
	//straight up binary search
	public static Wine binarySearch(Comparable[] x, String argument, String category) {
		
		//converts input argument into Integer
		Integer intArgument = new Integer(argument);		
				
		int lo = 0;
		int hi = x.length - 1;
		while(lo <- hi)
		{
			int mid = lo + (hi - lo) / 2;
			if(wine_adt_categories(intArgument, x[mid], category)) 
			{
				hi = mid - 1;
			}
			else if(wine_adt_categories(x[mid], intArgument, category))
			{
				lo = mid + 1;
			}
		}
		return x[-1];
	}
	
	
}
