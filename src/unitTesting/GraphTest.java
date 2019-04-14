package unitTesting;

import java.util.Arrays;

import org.junit.Test;

import graphTNotes.EWGraph;
import graphTNotes.GraphTNotes;
import graphTNotes.SetWeight;
import wineADT.Read;
import wineADT.Wine;

public class GraphTest {

	// Basic to string method for Wine
	public static String toString(Wine w) {
		String temp = "";
		String [] list = w.get_taste_noteslist();
		for (int i = 0; i < list.length -1; i++) {
			temp = temp + list [i] + ", ";
		}
		temp = temp + list [list.length - 1] + ".";
		return temp;
	}

	/*@Test
	public void test() {
		Wine[] test = Read.wines;
		SetWeight[] x = null;
		GraphTNotes G1 = new GraphTNotes(test, x);
		Wine[] out = G1.firstTen(test[1]);
		Wine [] out2 = G1.all(test[1]);
		System.out.println(out2.length);
		System.out.println(test[1].toString());
		for (int i = 0; i < out.length; i++) {
			System.out.println(out[i].toString());
		}
	}*/
	
	@Test
	public void test() {
		Wine[] test = Read.wines;
		Read.read_tasteNotes(test);
		EWGraph t = new EWGraph (test.length);
		SetWeight[] x = null;
		test = Arrays.copyOfRange(test, 0, 1000);
		/*Edge [] edges = CalcEW.compute(test);
		for (int i = 0; i < edges.length; i++) {
			t.add(edges[i]);
		}*/
		GraphTNotes G1 = new GraphTNotes(test, x);
		Wine[] out = G1.firstTen(test[1]);
		Wine [] out2 = G1.all(test[1]);
		System.out.println(out2.length);
		System.out.println(toString(test[1]));
		for (int i = 0; i < out.length; i++) {
			System.out.println(toString(out[i]));
		}
		


	}

}
