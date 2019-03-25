package graphTNotes;

import java.util.ArrayList;

import wineADT.*;

public class GraphTNotes {
	private Wine fav;
	private Wine[] matches;
	private Integer[] hits;
	private double[] priceDif;

	public GraphTNotes(Wine[] array, Wine w) {
		fav = w;
		match(array, w);
		arrange();
		calcPriceDif();
	}
	
	public Wine [] getMatches() {
		return matches;
	}
	public Integer[] getHits() {
		return hits;
	}
	public double [] getPriceDif() {
		return priceDif;
	}
	public Wine getFav() {
		return fav;
	}
	
	private void match(Wine[] array, Wine w) {
		ArrayList<Wine> matchList = new ArrayList<Wine>();
		ArrayList<Integer> hitList = new ArrayList<Integer>();
		int count;
		String[] keys = w.get_taste_noteslist();
		String[] tNotes;
		for (int i = 0; i < array.length; i++) {
			count = 0;
			tNotes = array[i].get_taste_noteslist();
			for (int j = 0; j < keys.length; j++) {
				for (int z = 0; z < tNotes.length; z++) {
					if (keys[j].equals(tNotes[z])) {
						count++;
					}

				}
			}
			if (count > 0) {
				matchList.add(array[i]);
				hitList.add(count);
			}

		}
		matches = (Wine[]) matchList.toArray();
		hits = (Integer[]) hitList.toArray();

	}

	private void arrange() {
		hits = mergeSort(hits, matches);
	}

	// Split the array into base components
	private Integer [] mergeSort(Integer[] hits2, Wine [] matches2) {
		int n = hits2.length;
		if (n <= 1)
			return hits2;
		Integer[] i1 = new Integer[n / 2];
		Integer[] i2 = new Integer[n - n / 2];
		Wine [] w1 = new Wine [n/2];
		Wine [] w2 = new Wine [n - n / 2];
		for (int i = 0; i < w1.length; i++)
			i1[i] = hits2[i];
		for (int i = 0; i < w2.length; i++)
			i2[i] = hits2[i + n / 2];

		mergeSort(i1, w1);
		mergeSort(i2, w2);
		merge(i1, i2, hits2, w1, w2, matches2);
		matches = matches2;
		return hits2;

	}

	// merge two sorted arrays
	private  Integer[] merge(Integer[] i1, Integer[] i2, Integer[] array, Wine [] w1, Wine [] w2, Wine [] matches2) {
		int i = 0, j = 0;
		for (int z = 0; z < w1.length + w2.length; z++) {
			if (i >= i1.length) {
				array[z] = i2[j++];
				matches2[z] = w2[j++];
			}
			else if (j >= i2.length) {
				array[z] = i1[i++];
				matches2[z] = w1[i++];
			}
			else if ((less(i1[i], i2[j], w1[i], w2[j]))) {
				array[z] = i1[i++];
				matches2[z] = w1[i++];
			}
			else {
				array[z] = i2[j++];
				matches2[z] = w2[j++];
			}		
		}
		return array;
	}
	
	private boolean less(Integer v, Integer w, Wine a, Wine b) {
		if (v.compareTo(w) == 0) 
			return (absPDif(a) < absPDif(b));
		else
			return (v.compareTo(w) < 0);
	}
	private double absPDif(Wine w) {
		if (fav.get_price() > w.get_price()) 
			return fav.get_price() - w.get_price();
		else return w.get_price() - fav.get_price();
	}
	
	private double priceDif(Wine w) {
		return w.get_price() - fav.get_price();
	}
	private void calcPriceDif () {
		priceDif = new double [matches.length]; 
		for (int i = 0; i < matches.length; i++) {
			priceDif[i] = priceDif(matches[i]);
		}
	}
}
