package execution;

import wineADT.FoodMatchesLibrary;
import wineADT.Read;
import wineADT.Wine;

public class Main {

	public static void main(String[] args) {
		
		Wine[] test1 = Read.wines;
		Read.read_tasteNotes(test1);
		
		
		/*
		for (int i = 0; i < test1.length; i++) {
			System.out.println(test1[i].get_variety());
		}
		*/
		
		for (String key : FoodMatchesLibrary.get_hashmap().keySet() ) {
			System.out.println(key);
		}
		
		
	}

}
