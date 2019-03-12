package searchsort;

/**
 * @brief Contains methods used by Sorting to sort an array of wines. Called interally by Sorting.java, no public access.
 * 
 * @author David Carrie
 * @version Last modified 2019/03/11
 */
class WineSort {
	
	//should not be instantiated
	private WineSort() {}
	
	/**
	 * @brief Method used by Sorting.java to sort the wines by catagory, for internal use.
	 * 
	 * @param array array of wines to be sorted
	 * @param type number representing the catagory to sort by.
	 */
	
	protected static void sort(Wine [] array, int type) {
		sort(array, 0, array.length - 1, type );		
	}
	
	private static void sort(Wine [] array, int hi, int lo, int type) {
		if (hi <= lo)
			return;
		int j = partition(array, lo, hi, type);
		sort(array, lo, j-1, type);
		sort(array, j+1, hi, type);
		
	}
	
	private static int partition (Wine [] array, int lo, int hi, int type) {
		int i = lo;
		int j = hi + 1;
		Wine v = array[lo];
		while (true) {
			
			//Find lower item thats out of place
			while (less(array[++i], v, type)) {
				if (i == hi)
					break;
			}
			//Find higher item thats out of place
			while (less(v, array[--j], type)) {
				if (j == lo)
					break;
			}
			
			//Check if boundaries are crossed
			if (i >= j)
				break;
			
			exch(array, i, j);
		}
		
		//Place partitioning item at array[j]
		exch(array, lo, j);
		return j;
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
		default: return CompareLibrary.compare_country(v, w) < 0;
		}
	}
	
	//Exhcnage wines at position i and j
	private static void exch(Wine [] array, int i, int j) {
		Wine temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}
	
}
