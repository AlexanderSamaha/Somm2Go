package testing;

import wineADT.*;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import searchsort.Sorting;
import searchsort.WineSort;

public class SortingTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	

	@Test
	public void sort() {
		Wine [] test = Read.wines;
		Sorting.sort(test, "country");
		assert(WineSort.isSorted(test, 0));
		Sorting.sort(test, "designation");
		assert(WineSort.isSorted(test, 1));
		Sorting.sort(test, "rating");
		assert(WineSort.isSorted(test, 2));
		Sorting.sort(test, "price");
		assert(WineSort.isSorted(test, 3));
		Sorting.sort(test, "province");
		assert(WineSort.isSorted(test, 4));
		Sorting.sort(test, "variety");
		assert(WineSort.isSorted(test, 5));
		Sorting.sort(test, "geo");
		assert(WineSort.isSorted(test, 6));
		Sorting.sort(test, "unique_ID");
		assert(WineSort.isSorted(test, 7));
		Sorting.sort(test, "winery");
		assert(WineSort.isSorted(test, 8));
	}
	
	/*@Test
	public void searchTestCaseOne() {
		
		Wine [] wineArray = Read.wines;
		Sorting.sort(wineArray, "unique_ID");
		Wine wine1;
		Wine [] search2, search3;

		Sorting.sort(wineArray, "unique_ID");
		wine1 = Searching.binary_search(wineArray, 10 );
		
		search2 = Searching.linear_search(wineArray, "10", "unique_ID" );
		
		Sorting.sort(wineArray, "unique_ID");
		search3 = Searching.binary_linear_search(wineArray, "10", "unique_ID" );
		
		assertTrue(wine1.get_uniqueID() == search2[0].get_uniqueID() && search2[0].get_uniqueID() == search3[0].get_uniqueID());
		
	}
	*/
}
