package searchsort;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import wineADT.*;

public class SearchingTest {
	
	private static Wine [] wineArray;

	@Before
	public void setUp() throws Exception {
		wineArray = Read.wines;
	}

	@After
	public void tearDown() throws Exception {
		wineArray = null;
	}

	@Test
	public void searchTestCaseOne() {
		Wine wine1;
		Wine [] search2, search3;

		Sorting.sort(wineArray, "unique_ID");
		wine1 = Searching.binary_search(wineArray, 10 );
		
		search2 = Searching.linear_search(wineArray, "10", "unique_ID" );
		
		Sorting.sort(wineArray, "unique_ID");
		search3 = Searching.binary_linear_search(wineArray, "10", "unique_ID" );
		System.out.println(wine1.get_uniqueID());
		System.out.println(search2[0].get_uniqueID());
		System.out.println(search3[0].get_uniqueID());
		
		
		assertTrue(wine1.get_uniqueID() == search2[0].get_uniqueID() && search2[0].get_uniqueID() == search3[0].get_uniqueID());
	}
	
	@Test
	public void searchTestCaseTwo() {
		Wine [] search1, search2;
		
		search1 = Searching.linear_search(wineArray, "96", "price" );
		
		Sorting.sort(wineArray, "price");
		search2 = Searching.binary_linear_search(wineArray, "96", "price" );
		
		for(int i = 0; i < search1.length; i++) {
			System.out.println(search1[i].get_uniqueID());
			System.out.println(search2[i].get_uniqueID());
			System.out.println(search1[i].get_price());
			System.out.println(search2[i].get_price());
			
			assertTrue(CompareLibrary.compare_uniqueID(search1[i], search2[i]) == 0);
		}
	}
	
	@Test
	public void searchTestCaseThree() {
		Wine [] search1, search2;
		
		search1 = Searching.linear_search(wineArray, "Ice", "variety" );
		
		Sorting.sort(wineArray, "variety");
		search2 = Searching.binary_linear_search(wineArray, "Ice", "variety" );
		
		for(int i = 0; i <search1.length; i++) {
			assertTrue(CompareLibrary.compare_uniqueID(search1[i], search2[i]) == 0);
		}
	}
	
	@Test
	public void searchTestCaseFour() {
		Wine [] search1, search2;
		
		search1 = Searching.linear_search(wineArray, "76", "rating" );
		
		Sorting.sort(wineArray, "rating");
		search2 = Searching.binary_linear_search(wineArray, "76", "rating" );
		
		for(int i = 0; i <search1.length; i++) {
			assertTrue(CompareLibrary.compare_uniqueID(search1[i], search2[i]) == 0);
		}
	}
	
	@Test
	public void searchTestCaseFive() {
		Wine [] search1, search2;
		
		search1 = Searching.linear_search(wineArray, "Canada", "country" );
		
		Sorting.sort(wineArray, "country");
		search2 = Searching.binary_linear_search(wineArray, "Canada", "country" );
		
		for(int i = 0; i <search1.length; i++) {
			assertTrue(CompareLibrary.compare_uniqueID(search1[i], search2[i]) == 0);
		}
	}

}
