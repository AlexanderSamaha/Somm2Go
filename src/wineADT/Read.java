package wineADT;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

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
	public static void main(String[] args) throws IOException {
		//String[] file_adr = new String[args.length];
		String[] file_adr = { "data/winemag-data_first150k.txt" };
		ArrayList<String> arr_list = new ArrayList<String>();
		/*
		for (int i = 0; i < file_adr.length; i++) {
			file_adr[i] = args[i];
		}
		*/
		int k = 0;
		while (k < file_adr.length) {
			File f = new File(file_adr[k]);
			Scanner sc = new Scanner(f);
			sc.nextLine();
			while (sc.hasNextLine()) {
				//StringTokenizer st = new StringTokenizer(sc.nextLine(), ",");
				String st = new String(sc.nextLine());
				arr_list.add(st);
			}
			
			k++;
			sc.close();
		}
		
		for (int i = 0; i < arr_list.size(); i++) {
			System.out.println(arr_list.get(i));
		}
		
	}
}
