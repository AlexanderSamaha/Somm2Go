package execution;

import java.awt.Dimension;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import searchsort.Searching;
import userProfile.ProfileManager;
import wineADT.Read;
import wineADT.Wine;

/**
 * Execution class, containing the main method that will run the whole program
 * @author Mengxi Lei
 * @version Created 2019/04/08, Last modified 2019/04/09
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
		Wine[] wines;
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
		boolean runned = false;
		String temp = "";
		Wine[] result;
		//Let the user choose which category to search for
		while (true) {
			temp = "Please choose between the following options:\n" + "    1: Search by country"
																	+ "    2: Search by description"
																	+ "    3: Search by taste notes"
																	+ "    4: Search by designation"
																	+ "    5: Search by rating"
																	+ "    6: Search by price"
																	+ "    7: Search by province"
																	+ "    8: Search by region"
																	+ "    9: Search by variety"
																	+ "    10: Search by winery"
																	+ "    11: Search by name"
																	+ "    12: Search by ID"
																	+ "    0: Back to previous menu";
			userInput = Integer.parseInt(JOptionPane.showInputDialog(null, temp));
			switch(userInput) {
				case 1: wines = 
				case 0: 
					if (runned)
						return result;
					else
						return wines;
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
