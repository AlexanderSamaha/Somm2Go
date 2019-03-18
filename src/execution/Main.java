package execution;

import searchsort.*;
import wineADT.*;

public class Main {
	//Basic to string method for Wine
	public static String toString(Wine w) {
		String temp = "";
		temp = temp + w.get_country() + ", ";
		temp = temp + w.get_province() + ", ";
		temp = temp + w.get_designation() + ", ";
		temp = temp + w.get_winery() + ", ";
		temp = temp + w.get_price() + ", ";
		temp = temp + w.get_rating() + ", ";
		temp = temp + w.get_uniqueID();
		return temp;
	}
	//Print first 5 wines
	public static void testPrint (Wine [] wa) {
		for (int i = 0; i < 5; i ++) {
			System.out.println(toString(wa[i]));
		}
	}
	//Print last 5 wines
	public static void testPrintRV(Wine [] wa) {
		for (int i = wa.length - 1; i > wa.length - 6; i --) {
			System.out.println(toString(wa[i]));
		}
	}
	public static void main(String[] args) {
		
		Wine [] test1 = Read.wines;
		Wine [] test2, test3, test5, test6;
		Wine test4;
		Sorting.sort(test1, "country");
		System.out.printf("Sorted by Country:\n");
		testPrint(test1);
		testPrintRV(test1);
		
		Sorting.sort(test1, "price");
		System.out.printf("\nSorted by Price:\n");
		testPrint(test1);
		testPrintRV(test1);
		
		Sorting.sort(test1,  "rating");
		System.out.printf("\nSorted by rating:\n");
		testPrint(test1);
		testPrintRV(test1);
		
		System.out.printf("\nTest search 1:\n");
		test2 = Searching.linear_name_search(test1, "Preludio Barrel" );
		testPrint(test2);
		testPrintRV(test2);
		
		System.out.printf("\nTest search 2:\n");
		test3 = Searching.linear_name_search(test1, "Ice" );
		testPrint(test3);
		testPrintRV(test3);
		
		Sorting.sort(test1, "unique_ID");
		System.out.printf("\nTest search 3:\n");
		test4 = Searching.binary_search(test1, 10 );
		System.out.println(toString(test4));
		
		System.out.printf("\nTest search 4:\n");
		test5 = Searching.linear_search(test1, "50", "price" );
		testPrint(test5);
		testPrintRV(test5);
		
		Sorting.sort(test1, "province");
		System.out.printf("\nTest search 5:\n");
		test6 = Searching.linear_search(test1, "California", "province" );
		testPrint(test6);
		testPrintRV(test6);
		
	}

}
