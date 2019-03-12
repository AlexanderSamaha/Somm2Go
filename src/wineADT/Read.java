package wineADT;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @brief Delivers the method that reads our dataset.
 * 
 * @author Alexander Samaha
 * 
 * @date Last modified 11/03/2019.
 *
 */
public class Read {
	
	// change this
	/**
	 * @brief Reads files from dataset and creates a wine ADT object for the wine bottle.
	 * 
	 * @return An array containing wine objects.
	 * @throws IOException fails if there is a file error.
	 */
	public static Object[] read() throws IOException {
		//String[] file_adr = new String[args.length];
		String[] file_adr = { "data/winemag-data_first150k.txt" };
		ArrayList<Wine> arr_list = new ArrayList<Wine>();
		/*
		for (int i = 0; i < file_adr.length; i++) {
			file_adr[i] = args[i];
		}
		*/
		int k = 0;
		while (k < file_adr.length) {
			File f = new File(file_adr[k]);
			Scanner sc = new Scanner(f, "UTF-8");
			sc.nextLine();
			Integer id_count = 0;
			while (sc.hasNextLine()) {
				String scanned = sc.nextLine();
				System.out.println(scanned);
				if (scanned.isEmpty()) {
					scanned = sc.nextLine();
				}
				String[] st = scanned.split(",");
				
				// String st = new String(sc.nextLine());
				//System.out.println(st.countTokens());
				String toString = "";
				for (int i = 0; i < st.length; i++) {
					toString += st[i] + ", ";
				}
			
				
				
				String country = st[1];
				String description = "";
				int count = 0;
				for (int i = 2; i < (st.length - 10) + 2; i++) {
					if (st[i].endsWith("\",")) {
						description += st[i];
					}
					else {
						description += st[i] + ", ";
					}
					count++;
					
				}
				
				String designation = st[count+2];
				
				Integer points = !(st[count+3].isEmpty()) ? Integer.parseInt(st[count+3]) : 0;
				
				Double price = !(st[count+4].isEmpty()) ? Double.parseDouble(st[count+4]) : 0.0;
				
				String province = st[count+5];
				String[] region = {
						st[count+6],
						st[count+7]
				};
				String variety = st[count+8];
				String winery = st[count+9];
				System.out.println(id_count);
				Integer unique_id = id_count++;
				
				String[] items = {
						country,
						description,
						designation,
						province,
						variety,
						winery
				};
				
				String[] taste = {};
				
				Wine curr_obj = new Wine(items, taste, region, points, unique_id, price);
				
				arr_list.add(curr_obj);
				
			}
			
			k++;
			sc.close();
		}
		Object[] array_wines = arr_list.toArray();
		return array_wines;
		//return array_wines;
		
	}
	
	public static void main(String[] args) throws IOException {
		Object[] array = read();
	}
}
