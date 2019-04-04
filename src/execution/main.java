package execution;
import java.util.Scanner;
import searchsort.*;
import wineADT.*;


public class main {
	
	private static boolean cont = true;
	private static boolean done = false;
	
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
	//Print wines
	public static void testPrint (Wine [] wa) {
		boolean ten = false;
		//prints a partial list of returned wines if over 10 returned
		if(wa.length > 10) {
			ten = true;
			System.out.println("Your current searching/filtering found over ten wines. You may want to consider narrowing down your search.");
			System.out.println("Here are the first ten of the total requested wines:");
			for (int i = 0; i < 10; i ++) {
				System.out.println(toString(wa[i]));
			}
		}
		else {
			System.out.println("Here are the requested wines:");
			for (int i = 0; i < wa.length; i ++) {
				System.out.println(toString(wa[i]));
			}
		}
		//check if user is satisfied with result wines
		if(ten == true) {
			System.out.println("Would you like to see all the requested wines? (Yes/No)");
			Scanner myObj = new Scanner(System.in);
			String answer = myObj.nextLine();
			if(answer .equals("Yes") | answer .equals("yes")) {
				System.out.println("Here are the requested wines:");
				for (int i = 0; i < wa.length; i ++) {
					System.out.println(toString(wa[i]));
				}
			}
			System.out.println("Would you like to continue narrowing your searching down?");
			answer = myObj.nextLine();
			if(answer .equals("Yes") | answer .equals("yes")) {
				return;
			}
			else {
				System.out.println("Enjoy the wine!");
				//after current wine search is done
				System.out.println("Would you like to perform a new wine search?");
				answer = myObj.nextLine();
				myObj.close();
				if(answer .equals("No") | answer .equals("no")) {
					done = true;
					cont = false;
				}
			}
		}
		else if(ten == false) {
			System.out.println("Enjoy the wine!");
			
			//after current wine search is done
			System.out.println("Would you like to perform a new wine search?");
			Scanner myObj = new Scanner(System.in);
			String answer = myObj.nextLine();
			if(answer .equals("No") | answer .equals("no")) {
				done = true;
				cont = false;
			}
		}
	}

	public static void main(String args[]) {			
			//Opening pre-amble
			System.out.println("Welcome to Somm2Go!");
		while(cont == true) {
			done = false;
			//initializing values
			Wine [] full_list = Read.wines;
			Wine [] searched_list;
			Wine wine;
			
			System.out.println("To begin discovering wines, please regard the categories below.");
			System.out.println("1. country");
			System.out.println("2. description");
			System.out.println("3. taste notes");
			System.out.println("4. designation");
			System.out.println("5. rating");
			System.out.println("6. price");
			System.out.println("7. province");
			System.out.println("8. region");
			System.out.println("9. variety");
			System.out.println("10. winery");
			System.out.println("11. name");
			System.out.println("10. wine id");
			
			while(done == false) {
				
				//User decides between filtering and searching
				System.out.println("Would you like to filter wines by PRICE or RATING, or search wines by category? (enter filter or search)");
				Scanner myObj = new Scanner(System.in);
				String answer = myObj.nextLine();
				if(answer .equals("search")){
			
					//User enters first search query
					System.out.println("Enter a category:");
					String category = myObj.nextLine();
					System.out.println("Now enter the search query: ");
					String query = myObj.nextLine();
					
					//return wines based on user's search
					if(category .equals("taste notes")) {
						searched_list = Searching.linear_taste_notes_search(full_list, query );
						full_list = searched_list;
						testPrint(searched_list);
					}
					else if(category .equals("name")) {
						searched_list = Searching.linear_name_search(full_list, query );
						full_list = searched_list;
						testPrint(searched_list);
					}
					else if(category .equals("wine id")) {
						wine = Searching.binary_search(Sorting.sort(full_list, "unique_ID"), Integer.parseInt(query));
						System.out.println(toString(wine));
					}
					else {
						searched_list = Searching.linear_search(full_list, query, category );
						full_list = searched_list;
						testPrint(searched_list);
					}
				}
				
				else if(answer .equals("filter")) {
					//User enters first search query
					System.out.println("Would you like to filter by price or rating?:");
					String category = myObj.nextLine();
					System.out.println("Now enter the lower bound: ");
					String low = myObj.nextLine();
					System.out.println("Now enter the upper bound: ");
					String high = myObj.nextLine();
					searched_list = Filtering.linear_filtering(full_list, category, low, high);
					full_list = searched_list;
					testPrint(searched_list);
				}
				
				else {
					System.out.println("Please check the spelling of 'searching' or 'filtering' and re enter.");
					continue;
				}
			
			}

		}
		
	}

}
