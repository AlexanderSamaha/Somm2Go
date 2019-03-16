package wineADT;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Delivers the method that reads our dataset.
 * 
 * @author Alexander Samaha
 * 
 * @version Last modified 12/03/2019.
 *
 */
public class Read {
	
	public static Wine[] wines = read();
	
	
	/**
	 * Reads files from dataset and creates a wine ADT object for the wine bottle.
	 * 
	 * @return An array containing wine objects.
	 * @throws IOException fails if there is a file error.
	 */
	private static Wine[] read() {
		// We can add files we would like to parse in the following array. We use an array list
		// because it allows us to add dynamically.
		String[] file_adr = { "data/winemag-data_first150k.txt" };
		ArrayList<Wine> arr_list = new ArrayList<Wine>();

		int k = 0;
		while (k < file_adr.length) {
			File f = new File(file_adr[k]);
			Scanner sc = null;
			try {
				sc = new Scanner(f, "UTF-8");
			} catch (FileNotFoundException e) {}
			sc.nextLine();
			Integer id_count = 0;
			while (sc.hasNextLine()) {
				String scanned = sc.nextLine();
				// if there is a blank line, skip it before a fail.
				if (scanned.isEmpty()) {
					scanned = sc.nextLine();
				}
				// use this instead of StringTokenizer because it won't skip empty fields.
				String[] st = scanned.split(",");
				
				/* was put here to make sure all fields show up.
				String toString = "";
				for (int i = 0; i < st.length; i++) {
					toString += st[i] + ", ";
				}
				*/

				String country = st[1];
				String description = "";
				// This piece grabs our entire description! this paragraph has our delimiters so it gets split.
				int count = 0;
				for (int i = 2; i < (st.length - 10) + 2; i++) {
					if (st[i].endsWith("\"")) {
						description += st[i];
					}
					else {
						description += st[i] + ", ";
					}
					count++;
					
				}
				
				String designation = st[count+2];
				
				// next two fields will fail if the field is empty, so make sure we assign it something.
				Integer points = !(st[count+3].isEmpty()) ? Integer.parseInt(st[count+3]) : -1;
				
				Double price = !(st[count+4].isEmpty()) ? Double.parseDouble(st[count+4]) : -1.0;
				
				String province = st[count+5];
				String[] region = {
						st[count+6],
						st[count+7]
				};
				String variety = st[count+8];
				String winery = st[count+9];
				//System.out.println(id_count);
				// unique ID system because some wine bottles have empty names.
				Integer unique_id = id_count++;
				
				String[] items = {
						country,
						description,
						designation,
						province,
						winery,
						variety
				};
				
				String[] taste = {};
				// Object constructor.
				Wine curr_obj = new Wine(items, taste, region, points, unique_id, price);
				
				arr_list.add(curr_obj);
				
			}
			
			k++;
			sc.close();
		}
		
		// We no longer need an array list. we have our size required. Put into an array.
		Wine[] array_wines = new Wine[arr_list.size()];
		array_wines = arr_list.toArray(array_wines);
		
		FoodMatchesLibrary.read_hashmap();
		return array_wines;
	
	}
	
	public static void read_tasteNotes(Wine[] array_wines) {
		for (int i = 0; i < array_wines.length; i++) {
			array_wines[i].insert_taste_notes();
			// System.out.println(array_wines[i].get_taste_notes());
		}
		return;
	}
	

}
