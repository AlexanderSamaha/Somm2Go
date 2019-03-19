package searchsort;

import wineADT.*;

/**
 * Contains methods used by Sorting to sort an array of wines. Called interally by Sorting.java, no public access.
 * 
 * @author David Carrie
 * @version Last modified 2019/03/11
 */
class WineSort {
	
	//should not be instantiated
	private WineSort() {}
	/**
	 * Checks to see if the array is sorted succesfully
	 * 
	 * @param array array of wines	
	 * @param y type of sort used
	 * @return - true if sorted, false otherwise
	 */
	protected static boolean isSorted(Wine [] array, int y) {
		for (int i = 1; i < array.length; i++) {
			if (less(array[i], array[i-1], y)) return false; 
		}
		return true;
	}
	
	/**
	 * Method used by Sorting.java to sort the wines by catagory, for internal use.
	 * 
	 * @param array array of wines to be sorted
	 * @param type number representing the catagory to sort by.
	 */
	protected static void sort(Wine [] wines, int type) {
		
		if(isSorted(wines, type)) return;
		mergeSort(wines, type);
		return;
		
	}
	
	//Split the array into base components
	private static Wine [] mergeSort(Wine [] array, int type ) {
		int n = array.length;
		if (n <= 1) return array;
		Wine [] w1 = new Wine [n/2];
		Wine [] w2 = new Wine [n - n/2];
		for (int i = 0; i < w1.length; i++)
			w1[i] = array [i];
		for (int i = 0; i < w2.length; i++)
			w2[i] = array[i + n/2];
		
		mergeSort(w1, type);
		mergeSort(w2, type);
		merge(w1, w2, array, type);
		return array;
		
	}
	//merge two sorted arrays
	private static Wine [] merge(Wine [] w1, Wine [] w2, Wine [] array, int type) {
		int i = 0, j = 0;
		for (int z = 0; z < w1.length + w2.length; z++) {
			if 		(i >= w1.length) 				array[z] = w2[j++];
			else if (j >= w2.length) 				array[z] = w1[i++];
			else if ((less(w1[i], w2[j], type)))	array[z] = w1[i++];
			else									array[z] = w2[j++];
		}
		return array;
	}
	
	
	
	//Function to tell if less based on category
	private static boolean less(Wine v, Wine w, int type) {
		switch(type) {
		case(0): return CompareLibrary.compare_country(v, w) < 0;
		case(1): return CompareLibrary.compare_designation(v, w) < 0;
		case(2): return CompareLibrary.compare_rating(v, w) < 0;
		case(3): return CompareLibrary.compare_price(v, w) < 0;
		case(4): return CompareLibrary.compare_province(v, w) < 0;
		case(5): return CompareLibrary.compare_variety(v, w) < 0;	
		case(6): return CompareLibrary.compare_geo(v, w) < 0;
		case(7): return CompareLibrary.compare_uniqueID(v, w) < 0;
		case(8): return CompareLibrary.compare_winery(v, w) < 0;
		default: return CompareLibrary.compare_country(v, w) < 0;
		}
	}
	
	//Exhchange wines at position i and j
	private static void exch(Wine [] array, int i, int j) {
		Wine temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}
	
}
