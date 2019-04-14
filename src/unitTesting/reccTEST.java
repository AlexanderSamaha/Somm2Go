package unitTesting;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Recommendations.Recommendations;
import searchsort.Searching;
import wineADT.Read;
import wineADT.Wine;

public class reccTEST {

	public static String toString(Wine w) {
		String temp = "";
		temp = temp + w.get_country() + ", ";
		temp = temp + w.get_province() + ", ";
		temp = temp + w.get_designation() + ", ";
		temp = temp + w.get_variety() + ", ";
		temp = temp + w.get_winery() + ", ";
		temp = temp + w.get_price() + ", ";
		temp = temp + w.get_rating() + ", ";
		temp = temp + w.get_uniqueID();
		return temp;
	}
	public static String toString2(Wine w) {
		String temp = "";
		String [] list = w.get_taste_noteslist();
		for (int i = 0; i < list.length -1; i++) {
			temp = temp + list [i] + ", ";
		}
		temp = temp + list [list.length - 1] + ".";
		return temp;
	}
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		Wine[] test = Read.wines;
		Read.read_tasteNotes(test);
		Wine [] search = Searching.linear_search(test, "125000", "unique_ID");
		Wine target = search [0];
		Recommendations testy = new Recommendations (test, target);
		Wine [] res = testy.get_results();
		
		System.out.println("Wines:");
		System.out.println(toString(target));
		for (int i = 0; i < res.length; i++) {
			System.out.println(toString(res[i]));
		}
		System.out.println("TNotes:");
		System.out.println(toString2(target));
		for (int i = 0; i < res.length; i++) {
			System.out.println(toString2(res[i]));
		}

	}

}
