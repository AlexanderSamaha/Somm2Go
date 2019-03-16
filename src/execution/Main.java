package execution;

import wineADT.Read;
import wineADT.Wine;

public class Main {

	public static void main(String[] args) {
		
		Wine [] test1 = Read.wines;
		Read.read_tasteNotes(test1);
		
	}

}
