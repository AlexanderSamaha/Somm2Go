package execution;

import java.awt.Dimension;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import searchsort.Filtering;
import searchsort.Searching;
import searchsort.Sorting;
import userProfile.ProfileManager;
import wineADT.Read;
import wineADT.Wine;

/**
 * Execution class, containing the main method that will run the whole program
 * @author Mengxi Lei
 * @version Created 2019/04/08, Last modified 2019/04/09
 * Need to work on: main and manageProfile
 */
public class Main {
	
//===============================================================================================================================================================
//==========   Main Method   ====================================================================================================================================
//===============================================================================================================================================================
	
	
	
	/**
	 * Main method that will run the whole program
	 * @param args Commend line arguments
	 */
	public static void main(String[] args) {
		
		//Declare variables
		int userInput;
		String temp;
		boolean cont = true;
		
		//Initial program setup
		JOptionPane.showMessageDialog(null, "Welcome to Somm2Go!");
		ProfileManager.profileInit();
		
		//Let the user choose between searching for wine, recommendation or profile management
		while (cont) {
			temp = "Please choose between the following options:\n" + "    1: Manage your profile\n" 
		                                                            + "    2: Search Wines\n" 
					                                                + "    3: Wine Recommendation\n"
		                                                            + "    0: Exit the program";
			userInput = Integer.parseInt(JOptionPane.showInputDialog(null, temp));
			switch(userInput) {
				case 1: manageProfile();
						break;
				case 2: break;
				case 3: break;
				case 0: cont = false;
						break;
				default:
					JOptionPane.showMessageDialog(null, "Invalid input, please enter a valid choice.");
			}
		}
		
		//Termination of program
		ProfileManager.close();
		
	}
	
	
	
//===============================================================================================================================================================
//==========   Helper functions - Profiles   ====================================================================================================================
//===============================================================================================================================================================
	
	
	
	//Menu for profile management
	private static void manageProfile() {
		int userInput;
		String temp;
		//Let the user choose between managing favorite wines or tastes
		while (true) {
			temp = "Please choose between the following options:\n" + "    1: Manage favorite wines\n"
		                                                            + "    2: Manage favorite tastes\n"
		                                                            + "    0: Back to main menu";
			userInput = Integer.parseInt(JOptionPane.showInputDialog(null, temp));
			switch(userInput) {
				case 1: manageWine();
						break;
				case 2: break;
				case 0: return;
				default:
					JOptionPane.showMessageDialog(null, "Invalid input, please enter a valid choice.");
			}
		}
	}
	
	//Function which allow user to add or delete wines
	private static void manageWine() {
		int userInput;
		String temp;
		Wine[] wines;
		//Let the user choose between add or delete wines
		while (true) {
			temp = "";
			wines = ProfileManager.getProfile().getWines();
			for (int i = 0; i < wines.length; i++)
				temp = temp + wines[i] + "\n";
			temp = temp + "\n" + "Please choose between the following options:\n" + "    -1: Add favorite wines\n"
																				  + "    ID of wine: Delete favorite tastes\n"
																				  + "    -2: Back to previous menu";
			userInput = Integer.parseInt(displayInput(temp));
			switch(userInput) {
				case -1: addWine();
						 break;
				case -2: return;
				default:
					ProfileManager.getProfile().deleteWine(userInput);
			}
		}
	}
	
	//Allow the user to add a wine to their favorite
	private static void addWine() {
		int userInput;
		String temp = "";
		//Let the user choose how to add the wine
		while (true) {
			temp = "Please choose between the following options:\n" + "    1: Search for the wine"
																	+ "    2: Add by unique ID of the wine"
																	+ "    0: Back to previous menu";
			userInput = Integer.parseInt(displayInput(temp));
			if (userInput == 1)
				addWineSearch();
			else if (userInput == 2) {
				userInput = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter the unique ID of the wine you with to favorite"));
				temp = "Is this the wine you wish to favorite:\n    " + Searching.binary_search(Read.wines, userInput) + "\n"
						+ "Enter yes to confirm, anything else to abondon the change.";
				if (displayInput(temp).equalsIgnoreCase("yes"))
					ProfileManager.getProfile().addWine(userInput);
			}
			else if (userInput == 0)
				return;
			else
				JOptionPane.showMessageDialog(null, "Invalid input, please enter a valid choice.");
		}
	}
	
	//Search for a wine and add it
	private static void addWineSearch() {
		int userInput;
		String temp = "";
		Wine[] wines = null;
		boolean first = true;
		//Let the user choose what to search by
		while (true) {
			while (first) {
				first = false;
				temp = "Please choose between the following options:\n" + "    1: Start by searching"
																		+ "    2: Start by filtering"
																		+ "    0: Back to previous menu";
				userInput = Integer.parseInt(JOptionPane.showInputDialog(temp));
				switch(userInput) {
					case 1: wines = searching(Read.wines);
							first = false;
						    break;
					case 2: wines = filtering(Read.wines);
							first = false;
							break;
					case 0: return;
					default: JOptionPane.showMessageDialog(null, "Invalid input, please enter a valid choice.");
				}
			}
			temp = "";
			for (int i = 0; i < wines.length; i++)
				
			temp = temp + "\n" + "Please choose between the following options:\n" + "    ID: Add the wine to favorite"
																				  + "    -1: Continue Searching"
																				  + "    -2: Continue filtering"
																				  + "    -3: Continue sorting"
																				  + "    -4: Back to previous menu";
			userInput = Integer.parseInt(JOptionPane.showInputDialog(temp));
			switch(userInput) {
				case -1: wines = searching(wines);
						 break;
				case -2: wines = filtering(wines);
						 break;
				case -3: wines = sorting(wines);
						 break;
				case -4: return;
				default: ProfileManager.getProfile().addWine(userInput);
						 break;
			}
		}
	}
	
	
	
//===============================================================================================================================================================
//==========   Helper functions - Searching   ===================================================================================================================
//===============================================================================================================================================================
	
	
	
	//Searching
	private static Wine[] searching(Wine[] wines) {
		int userInput;
		String temp = "";
		Wine[] result = wines;
		Wine tempWine;
		//Let the user choose which category to search for
		while (true) {
			temp = "Please choose between the following options:\n" + "    1: Search by country"
																	+ "    2: Search by taste notes"
																	+ "    3: Search by name"
																	+ "    4: Search by rating"
																	+ "    5: Search by price"
																	+ "    6: Search by province"
																	+ "    7: Search by variety"
																	+ "    8: Search by winery"
																	+ "    9: Search by ID"
																	+ "    0: Back to previous menu";
			userInput = Integer.parseInt(JOptionPane.showInputDialog(null, temp));
			switch(userInput) {
				case 1:	wines = Searching.linear_search(wines, JOptionPane.showInputDialog(null, "Which country you want to search for"), "country");
						break;
				case 2:	wines = Searching.linear_taste_notes_search(wines, JOptionPane.showInputDialog(null, "Which taste note you want to search for"));
						break;
				case 3: wines = Searching.linear_name_search(wines, JOptionPane.showInputDialog(null, "Which name you want to search for"));
						break;
				case 4: wines = Searching.linear_search(wines, JOptionPane.showInputDialog(null, "Which rating you want to search for"), "rating");
						break;
				case 5: wines = Searching.linear_search(wines, JOptionPane.showInputDialog(null, "Which price you want to search for"), "price");
						break;
				case 6: wines = Searching.linear_search(wines, JOptionPane.showInputDialog(null, "Which province you want to search for"), "province");
						break;
				case 7: wines = Searching.linear_search(wines, JOptionPane.showInputDialog(null, "Which variety you want to search for"), "variety");
						break;
				case 8: wines = Searching.linear_search(wines, JOptionPane.showInputDialog(null, "Which winery you want to search for"), "winery");
						break;
				case 9: tempWine = Searching.binary_search(wines, Integer.parseInt(JOptionPane.showInputDialog(null, "Which ID you want to search for")));
						result = new Wine[1];
						result[0] = tempWine;
						break;
				case 0: return result;
				default: JOptionPane.showMessageDialog(null, "Invalid input, please enter a valid choice.");
			}
		}
	}
	
	//Filtering
	private static Wine[] filtering(Wine[] wines) {
		int userInput;
		String temp;
		Wine[] result = wines;
		//Let the user choose between managing favorite wines or tastes
		while (true) {
			temp = "Please choose between the following options:\n" + "    1: Filter by price\n"
		                                                            + "    2: Filter by rating\n"
		                                                            + "    0: Back to main menu";
			userInput = Integer.parseInt(JOptionPane.showInputDialog(null, temp));
			switch(userInput) {
				case 1: Filtering.linear_filtering(result, "price", JOptionPane.showInputDialog(null, "Enter the lower bound for price"), 
																	JOptionPane.showInputDialog(null, "Enter the higher bound for price"));
						break;
				case 2: Filtering.linear_filtering(result, "rating", JOptionPane.showInputDialog(null, "Enter the lower bound for rating"), 
						JOptionPane.showInputDialog(null, "Enter the higher bound for rating"));
						break;
				case 0: return result;
				default:
					JOptionPane.showMessageDialog(null, "Invalid input, please enter a valid choice.");
			}
		}
	}
	
	//Sorting
	private static Wine[] sorting(Wine[] wines) {
		int userInput;
		String temp = "";
		Wine[] result = wines;
		//Let the user choose which category to search for
		while (true) {
			temp = "Please choose between the following options:\n" + "    1: Sort by country"
																	+ "    2: Sort by geography"
																	+ "    3: Sort by name"
																	+ "    4: Sort by rating"
																	+ "    5: Sort by price"
																	+ "    6: Sort by province"
																	+ "    7: Sort by variety"
																	+ "    8: Sort by winery"
																	+ "    9: Sort by ID"
																	+ "    0: Back to previous menu";
			userInput = Integer.parseInt(JOptionPane.showInputDialog(null, temp));
			switch(userInput) {
				case 1:	wines = Sorting.sort(wines, "country");
						break;
				case 2:	wines = Sorting.sort(wines, "geo");
						break;
				case 3: wines = Sorting.sort(wines, "designation");
						break;
				case 4: wines = Sorting.sort(wines, "rating");
						break;
				case 5: wines = Sorting.sort(wines, "price");
						break;
				case 6: wines = Sorting.sort(wines, "province");
						break;
				case 7: wines = Sorting.sort(wines, "variety");
						break;
				case 8: wines = Sorting.sort(wines, "winery");
						break;
				case 9: wines = Sorting.sort(wines, "unique_ID");
						break;
				case 0: return result;
			}
		}
	}
	
	
	
//===============================================================================================================================================================
//==========   Helper functions - Others   ======================================================================================================================
//===============================================================================================================================================================
	
	
	
	//Display long message with scroll bars
	private static void display(String message) {
		JTextArea text = new JTextArea(message);
	    text.setEditable(false);
	    JScrollPane scroll = new JScrollPane(text);
	    scroll.setPreferredSize(new Dimension(800, 600));
	    JOptionPane.showMessageDialog(null, scroll);
	}
	
	//Display long message with scroll bars and ask for input
	private static String displayInput(String message) {
		JTextArea text = new JTextArea(message);
	    text.setEditable(false);
	    JScrollPane scroll = new JScrollPane(text);
	    scroll.setPreferredSize(new Dimension(800, 600));
	    return JOptionPane.showInputDialog(null, scroll);
	}
	
}
