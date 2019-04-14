package execution;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import Recommendations.Recommendations;
import foodPairing.FoodMatchesLibrary;
import searchsort.Filtering;
import searchsort.Searching;
import searchsort.Sorting;
import userProfile.ProfileManager;
import wineADT.Read;
import wineADT.TasteNoteLibrary;
import wineADT.Wine;

/**
 * Execution class, containing the main method that will run the whole program
 * @author Mengxi Lei
 * @version Created 2019/04/08, Last modified 2019/04/14
 */
public class Main {
	
	//Define constants
	private static final int OVERSIZE = 5000;
	
//===============================================================================================================================================================
//==========   Main Method   ====================================================================================================================================
//===============================================================================================================================================================
	
	
	
	/**
	 * Main method that will run the whole program
	 * @param args Command line arguments
	 */
	public static void main(String[] args) {
		
		//Declare variables
		int userInput;
		String temp;
		boolean cont = true;
		
		//Initial program setup
		Read.init();
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
				case 2: search();
						break;
				case 3: recommend();
						break;
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
				case 2: manageTasteNote();
						break;
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
			temp = "Please choose between the following options:\n" + "    1: Search for the wine\n"
																	+ "    2: Add by unique ID of the wine\n"
																	+ "    0: Back to previous menu";
			userInput = Integer.parseInt(displayInput(temp));
			if (userInput == 1)
				addWineSearch();
			else if (userInput == 2) {
				userInput = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter the unique ID of the wine you with to favorite"));
				temp = "Is this the wine you wish to favorite:\n    " + Searching.binary_search(Read.idSorted, userInput) + "\n"
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
				temp = "Please choose between the following options:\n" + "    1: Start by searching\n"
																		+ "    2: Start by filtering (WARNING, the result might take a long time to print)\n"
																		+ "    0: Back to previous menu\n";
				userInput = Integer.parseInt(JOptionPane.showInputDialog(temp));
				switch(userInput) {
					case 1: wines = searching(Read.wines);
							if (wines.length < Read.wines.length)
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
				temp = temp + wines[i] + "\n";
			temp = temp + "\n" + "Please choose between the following options:\n" + "    ID: Add the wine to favorite\n"
																				  + "    -1: Continue Searching\n"
																				  + "    -2: Continue filtering\n"
																				  + "    -3: Continue sorting\n"
																				  + "    -4: Back to previous menu";
			userInput = Integer.parseInt(displayInput(temp));
			switch(userInput) {
				case -1: wines = searching(wines);
						 break;
				case -2: wines = filtering(wines);
						 break;
				case -3: wines = sorting(wines);
						 break;
				case -4: return;
				default: ProfileManager.getProfile().addWine(userInput);
			}
		}
	}
	
	//Function which allow user to ad or delete taste notes
	private static void manageTasteNote() {
		int userInput;
		String temp;
		String[] tasteNote;
		while (true) {
			temp = "";
			tasteNote = ProfileManager.getProfile().getTaste();
			for (int i = 0; i < tasteNote.length; i++)
				temp = temp + (i+1) + ": " + tasteNote[i] + "\n";
			temp = temp + "\n" + "Please choose between the following options:\n" + "    ID of taste note: Delete favorite taste note\n"
					    														  + "    " + (tasteNote.length+1) + ": Add favorite taste note\n"
					    														  + "    0: Back to previous menu";
			userInput = Integer.parseInt(JOptionPane.showInputDialog(null, temp));
			if (userInput == 0)
				return;
			else if (userInput == tasteNote.length+1)
				addTasteNote();
			else if (userInput > 0 && userInput <= tasteNote.length)
				ProfileManager.getProfile().deleteTaste(tasteNote[userInput]);
			else
				JOptionPane.showMessageDialog(null, "Invalid input, please enter a valid choice.");
		}
	}
	
	//Allow user to add a taste note to their favorite
	private static void addTasteNote() {
		int userInput;
		String temp;
		String[] tasteNotes = TasteNoteLibrary.get_patterns();
		while (true) {
			temp = "";
			for (int i = 0; i < tasteNotes.length; i++)
				temp = temp + (i+1) + ": " + tasteNotes[i] + "\n";
			temp = temp + "\n" + "Please choose between the following options:\n" + "    ID of taste note: Add that taste note to favorite\n"
																				  + "    0: Back to previous menu";
			userInput = Integer.parseInt(displayInput(temp));
			if (userInput == 0)
				return;
			else if (userInput > 0 && userInput <= tasteNotes.length)
				ProfileManager.getProfile().deleteTaste(tasteNotes[userInput]);
			else
				JOptionPane.showMessageDialog(null, "Invalid input, please enter a valid choice.");
		}
	}
	
	
	
//===============================================================================================================================================================
//==========   Helper functions - Searching   ===================================================================================================================
//===============================================================================================================================================================
	
	
	
	//Menu for searching, sorting and filtering
	private static void search() {
		int userInput;
		String temp = "";
		Wine[] wines = null;
		boolean first = true;
		//Let the user choose what to search by
		while (true) {
			while (first) {
				temp = "Please choose between the following options:\n" + "    1: Start by searching\n"
																		+ "    2: Start by filtering (WARNING, the result might take a long time to print)\n"
																		+ "    3: Start by sorting (WARNING, the result might take a long time to print)\n"
																		+ "    0: Back to main menu";
				userInput = Integer.parseInt(JOptionPane.showInputDialog(null, temp));
				switch(userInput) {
					case 1: wines = searching(Read.wines);
							if (wines.length < Read.wines.length)
								first = false;
						    break;
					case 2: wines = filtering(Read.wines);
							first = false;
							break;
					case 3: wines = sorting(Read.wines);
							first = false;
							break;
					case 0: return;
					default: JOptionPane.showMessageDialog(null, "Invalid input, please enter a valid choice.");
				}
			}
			temp = "";
			for (int i = 0; i < wines.length; i++)
				temp = temp + wines[i] + "\n";
			temp = temp + "\n" + "Please choose between the following options:\n" + "    1: Continue Searching\n"
																				  + "    2: Continue filtering\n"
																				  + "    3: Continue sorting\n"
																				  + "    4: Reset results\n"
																				  + "    0: Back to previous menu";
			userInput = Integer.parseInt(displayInput(temp));
			switch(userInput) {
				case 1: wines = searching(wines);
						break;
				case 2: wines = filtering(wines);
						break;
				case 3: wines = sorting(wines);
						break;
				case 4: first = true;
						break;
				case 0: return;
				default: JOptionPane.showMessageDialog(null, "Invalid input, please enter a valid choice.");
			}
		}
	}
	
	//Searching
	private static Wine[] searching(Wine[] wines) {
		int userInput;
		String temp = "";
		Wine[] result = wines;
		//Let the user choose which category to search for
		while (true) {
			temp = "Please choose between the following options:\n" + "    1: Search by country\n"
																	+ "    2: Search by taste notes\n"
																	+ "    3: Search by name\n"
																	+ "    4: Search by rating\n"
																	+ "    5: Search by price\n"
																	+ "    6: Search by province\n"
																	+ "    7: Search by variety\n"
																	+ "    8: Search by winery\n"
																	+ "    9: Search by ID\n"
																	+ "    0: Back to previous menu";
			userInput = Integer.parseInt(JOptionPane.showInputDialog(null, temp));
			switch(userInput) {
				case 1:	result = Searching.linear_search(wines, JOptionPane.showInputDialog(null, "Which country you want to search for"), "country");
						if (result.length > OVERSIZE)
							JOptionPane.showMessageDialog(null, "The result contains more than 5000 wines, this might take some time for the program to process.");
						return result;
				case 2:	result = Searching.linear_taste_notes_search(wines, JOptionPane.showInputDialog(null, "Which taste note you want to search for"));
						if (result.length > OVERSIZE)
							JOptionPane.showMessageDialog(null, "The result contains more than 5000 wines, this might take some time for the program to process.");
						return result;
				case 3: result = Searching.linear_name_search(wines, JOptionPane.showInputDialog(null, "Which name you want to search for"));
						if (result.length > OVERSIZE)
							JOptionPane.showMessageDialog(null, "The result contains more than 5000 wines, this might take some time for the program to process.");
						return result;
				case 4: result = Searching.linear_search(wines, JOptionPane.showInputDialog(null, "Which rating you want to search for"), "rating");
						if (result.length > OVERSIZE)
							JOptionPane.showMessageDialog(null, "The result contains more than 5000 wines, this might take some time for the program to process.");
						return result;
				case 5: result = Searching.linear_search(wines, JOptionPane.showInputDialog(null, "Which price you want to search for"), "price");
						if (result.length > OVERSIZE)
							JOptionPane.showMessageDialog(null, "The result contains more than 5000 wines, this might take some time for the program to process.");
						return result;
				case 6: result = Searching.linear_search(wines, JOptionPane.showInputDialog(null, "Which province you want to search for"), "province");
						if (result.length > OVERSIZE)
							JOptionPane.showMessageDialog(null, "The result contains more than 5000 wines, this might take some time for the program to process.");
						return result;
				case 7: result = Searching.linear_search(wines, JOptionPane.showInputDialog(null, "Which variety you want to search for"), "variety");
						if (result.length > OVERSIZE)
							JOptionPane.showMessageDialog(null, "The result contains more than 5000 wines, this might take some time for the program to process.");
						return result;
				case 8: result = Searching.linear_search(wines, JOptionPane.showInputDialog(null, "Which winery you want to search for"), "winery");
						if (result.length > OVERSIZE)
							JOptionPane.showMessageDialog(null, "The result contains more than 5000 wines, this might take some time for the program to process.");
						return result;
				case 9: result = Searching.linear_search(wines, JOptionPane.showInputDialog(null, "Which ID you want to search for"), "unique_ID");
						if (result.length > OVERSIZE)
							JOptionPane.showMessageDialog(null, "The result contains more than 5000 wines, this might take some time for the program to process.");
						return result;
				case 0: return wines;
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
				case 1: result = Filtering.linear_filtering(result, "price", JOptionPane.showInputDialog(null, "Enter the lower bound for price"), 
																			 JOptionPane.showInputDialog(null, "Enter the higher bound for price"));
						if (result.length > OVERSIZE)
							JOptionPane.showMessageDialog(null, "The result contains more than 5000 wines, this might take some time for the program to process.");
						return result;
				case 2: result = Filtering.linear_filtering(result, "rating", JOptionPane.showInputDialog(null, "Enter the lower bound for rating"), 
																			  JOptionPane.showInputDialog(null, "Enter the higher bound for rating"));
						if (result.length > OVERSIZE)
							JOptionPane.showMessageDialog(null, "The result contains more than 5000 wines, this might take some time for the program to process.");
						return result;
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
			temp = "Please choose between the following options:\n" + "    1: Sort by country\n"
																	+ "    2: Sort by geography\n"
																	+ "    3: Sort by name\n"
																	+ "    4: Sort by rating\n"
																	+ "    5: Sort by price\n"
																	+ "    6: Sort by province\n"
																	+ "    7: Sort by variety\n"
																	+ "    8: Sort by winery\n"
																	+ "    9: Sort by ID\n"
																	+ "    0: Back to previous menu";
			userInput = Integer.parseInt(JOptionPane.showInputDialog(null, temp));
			switch(userInput) {
				case 1:	result = Sorting.sort(wines, "country");
						if (result.length > OVERSIZE)
							JOptionPane.showMessageDialog(null, "The result contains more than 5000 wines, this might take some time for the program to process.");
						return result;
				case 2:	result = Sorting.sort(wines, "geo");
						if (result.length > OVERSIZE)
							JOptionPane.showMessageDialog(null, "The result contains more than 5000 wines, this might take some time for the program to process.");
						return result;
				case 3: result = Sorting.sort(wines, "designation");
						if (result.length > OVERSIZE)
							JOptionPane.showMessageDialog(null, "The result contains more than 5000 wines, this might take some time for the program to process.");
						return result;
				case 4: result = Sorting.sort(wines, "rating");
						if (result.length > OVERSIZE)
							JOptionPane.showMessageDialog(null, "The result contains more than 5000 wines, this might take some time for the program to process.");
						return result;
				case 5: result = Sorting.sort(wines, "price");
						if (result.length > OVERSIZE)
							JOptionPane.showMessageDialog(null, "The result contains more than 5000 wines, this might take some time for the program to process.");
						return result;
				case 6: result = Sorting.sort(wines, "province");
						if (result.length > OVERSIZE)
							JOptionPane.showMessageDialog(null, "The result contains more than 5000 wines, this might take some time for the program to process.");
						return result;
				case 7: result = Sorting.sort(wines, "variety");
						if (result.length > OVERSIZE)
							JOptionPane.showMessageDialog(null, "The result contains more than 5000 wines, this might take some time for the program to process.");
						return result;
				case 8: result = Sorting.sort(wines, "winery");
						if (result.length > OVERSIZE)
							JOptionPane.showMessageDialog(null, "The result contains more than 5000 wines, this might take some time for the program to process.");
						return result;
				case 9: result = Sorting.sort(wines, "unique_ID");
						if (result.length > OVERSIZE)
							JOptionPane.showMessageDialog(null, "The result contains more than 5000 wines, this might take some time for the program to process.");
						return result;
				case 0: return result;
			}
		}
	}
	
	
	
//===============================================================================================================================================================
//==========   Helper functions - Recommendation   ==============================================================================================================
//===============================================================================================================================================================
	
	
	
	//Menu for wine recommendation
	private static void recommend() {
		int userInput;
		String temp;
		//Let the user choose between different recommendation option
		while (true) {
			temp = "Please choose between the following options:\n" + "    1: Recommend wine base on food\n"
		                                                            + "    2: Recommend food base on wine\n"
		                                                            + "    3: General wine recommendation\n"
																	+ "    0: Back to main menu";
			userInput = Integer.parseInt(JOptionPane.showInputDialog(null, temp));
			switch(userInput) {
				case 1: wineFromFood();
						break;
				case 2: foodFromWine();
						break;
				case 3: wineRecommend();
						break;
				case 0: return;
				default:
					JOptionPane.showMessageDialog(null, "Invalid input, please enter a valid choice.");
			}
		}
	}
	
	//Recommend wine base on food
	private static void wineFromFood() {
		int userInput;
		String temp;
		String[] foodList = FoodMatchesLibrary.getFoods();
		Wine[] wines;
		ArrayList<String> foods = new ArrayList<String>();
		ArrayList<Wine> tempWine = new ArrayList<Wine>();
		ArrayList<Wine> tempWine2;
		//Let the user choose between favorited wines or searching a wine
		while (true) {
			temp = "The following is the food you currently have added (recommend 1 to 3 food): ";
			for (int i = 0; i < foods.size(); i++)
				temp = temp + foods.get(i) + ", ";
			temp = temp + "\nPlease choose between the following options:\n";
			for (int i = 0; i < foodList.length; i++)
				temp = temp + "    " + i + ": Add " + foodList[i] + " to list of food\n";
			temp += "    -1: Search with current list of food\n";
			temp += "    -2: Back to previous menu";
			userInput = Integer.parseInt(JOptionPane.showInputDialog(null, temp));
			if (userInput == -1) {
				if (foods.size() == 0) {
					JOptionPane.showMessageDialog(null, "You currently have no food added, please add at least one food.");
					break;
				}
				tempWine.addAll(Arrays.asList(FoodMatchesLibrary.wineRecommand(foods.get(0))));
				for (int i = 1; i < foods.size(); i++) {
					tempWine2 = new ArrayList<Wine>();
					wines = FoodMatchesLibrary.wineRecommand(foods.get(i));
					for (Wine element : wines)
						if (tempWine.contains(element))
							tempWine2.add(element);
					tempWine = tempWine2;
				}
				wines = new Wine[tempWine.size()];
				wines = tempWine.toArray(wines);
				recommendDisplay(wines);
				return;
			}
			else if (userInput == -2)
				return;
			else if (userInput >= 0 && userInput < foodList.length) {
				if (!foods.contains(foodList[userInput]))
					foods.add(foodList[userInput]);
			}
			else
				JOptionPane.showMessageDialog(null, "Invalid input, please enter a valid choice.");
		}
	}
	
	//Recommend food base on wine
	private static void foodFromWine() {
		int userInput;
		String temp;
		//Let the user choose between favorited wines or searching a wine
		while (true) {
			temp = "Please choose between the following options:\n" + "    1: Pick a wine from favorite\n"
		                                                            + "    2: Search for a wine\n"
																	+ "    0: Back to previous menu";
			userInput = Integer.parseInt(JOptionPane.showInputDialog(null, temp));
			switch(userInput) {
				case 1: ftwFavorite();
						break;
				case 2: ftwSearch();
						break;
				case 0: return;
				default:
					JOptionPane.showMessageDialog(null, "Invalid input, please enter a valid choice.");
			}
		}
	}
	
	//Recommend the food from a favorited wine
	private static void ftwFavorite() {
		int userInput;
		String temp;
		Wine[] wines = ProfileManager.getProfile().getWines();
		String[] result;
		//Check if they have favorited wine on file
		if (wines.length == 0) {
			JOptionPane.showMessageDialog(null, "You don't have any wine favorited yet, please favorite one first or choose a different option");
			return;
		}
		//Let the user choose which favorited wine they want to use
		while (true) {
			temp = "";
			wines = ProfileManager.getProfile().getWines();
			for (int i = 0; i < wines.length; i++)
				temp = temp + wines[i] + "\n";
			temp = temp + "\n" + "Please choose between the following options:\n" + "    ID: Use this wine for food recommendation\n"
																				  + "    -1: Back to previous menu";
			userInput = Integer.parseInt(displayInput(temp));
			if (userInput == -1)
				return;
			wines = Searching.linear_search(wines, Integer.toString(userInput), "unique_ID");
			if (wines.length == 0)
				JOptionPane.showMessageDialog(null, "Invalid input, please enter a valid choice.");
			else {
				result = FoodMatchesLibrary.foodRecommend(wines[0]);
				temp = "The following is the food that goes with the wine you choose:\n";
				for (int i = 0; i < result.length; i++)
					temp = temp + "     " + result[i] + "\n";
				JOptionPane.showMessageDialog(null, temp);
				return;
			}
		}
	}
	
	//Recommend the food by searching for a wine
	private static void ftwSearch() {
		int userInput;
		String temp = "";
		String result[];
		Wine[] wines = null;
		boolean first = true;
		//Let the user choose what to search by
		while (true) {
			while (first) {
				temp = "Please choose between the following options:\n" + "    1: Start by searching\n"
																		+ "    2: Start by filtering (WARNING, the result might take a long time to print)\n"
																		+ "    0: Back to previous menu\n";
				userInput = Integer.parseInt(JOptionPane.showInputDialog(temp));
				switch(userInput) {
					case 1: wines = searching(Read.wines);
							if (wines.length < Read.wines.length)
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
				temp = temp + wines[i] + "\n";
			temp = temp + "\n" + "Please choose between the following options:\n" + "    ID: Use this wine for food recommendation\n"
																				  + "    -1: Continue Searching\n"
																				  + "    -2: Continue filtering\n"
																				  + "    -3: Continue sorting\n"
																				  + "    -4: Back to previous menu";
			userInput = Integer.parseInt(displayInput(temp));
			switch(userInput) {
				case -1: wines = searching(wines);
						 break;
				case -2: wines = filtering(wines);
						 break;
				case -3: wines = sorting(wines);
						 break;
				case -4: return;
				default:
					wines = Searching.linear_search(wines, Integer.toString(userInput), "unique_ID");
					if (wines.length == 0)
						JOptionPane.showMessageDialog(null, "Invalid input, please enter a valid choice.");
					else {
						result = FoodMatchesLibrary.foodRecommend(wines[0]);
						temp = "The following is the food that goes with the wine you choose:\n";
						for (int i = 0; i < result.length; i++)
							temp = temp + "     " + result[i] + "\n";
						JOptionPane.showMessageDialog(null, temp);
						return;
					}
			}
		}
	}
	
	//General wine recommendation
	private static void wineRecommend() {
		int userInput;
		String temp;
		//Let the user choose between managing favorite wines or tastes
		while (true) {
			temp = "Please choose between the following options:\n" + "    1: Recommend wine from a specific favorited wine\n"
																	+ "    2: Recommend wine from favorited wine\n"
		                                                            + "    3: Recommend wine from a specific wine (through searching)\n"
																	+ "    0: Back to main menu";
			userInput = Integer.parseInt(JOptionPane.showInputDialog(null, temp));
			switch(userInput) {
				case 1: recommendSpecific();
						break;
				case 2: recommendFavorite();
						break;
				case 3: recommendSearching();
						break;
				case 0: return;
				default:
					JOptionPane.showMessageDialog(null, "Invalid input, please enter a valid choice.");
			}
		}
	}
	
	//Recommend wine from a specific favorited wine
	private static void recommendSpecific() {
		int userInput;
		String temp;
		Recommendations recommend;
		Wine[] wines = ProfileManager.getProfile().getWines();
		//Check if they have favorited wine on file
		if (wines.length == 0) {
			JOptionPane.showMessageDialog(null, "You don't have any wine favorited yet, please favorite one first or choose a different option");
			return;
		}
		//Let the user choose which favorited wine they want to use
		while (true) {
			temp = "";
			for (int i = 0; i < wines.length; i++)
				temp = temp + wines[i] + "\n";
			temp = temp + "\n" + "Please choose between the following options:\n" + "    ID: Use this wine for food recommendation\n"
																				  + "    -1: Back to previous menu";
			userInput = Integer.parseInt(displayInput(temp));
			if (userInput == -1)
				return;
			wines = Searching.linear_search(wines, Integer.toString(userInput), "unique_ID");
			if (wines.length == 0)
				JOptionPane.showMessageDialog(null, "Invalid input, please enter a valid choice.");
			else {
				recommend = new Recommendations(Read.wines, wines[0]);
				wines = recommend.get_results();
				recommendDisplay(wines);
				return;
			}
		}
	}
	
	//Recommend wine from favorited wine
	private static void recommendFavorite() {
		Recommendations recommend;
		ArrayList<Wine> temp;
		ArrayList<Wine> temp2;
		Wine[] wines = ProfileManager.getProfile().getWines();
		//Check if they have favorited wine on file
		if (wines.length == 0) {
			JOptionPane.showMessageDialog(null, "You don't have any wine favorited yet, please favorite one first or choose a different option");
			return;
		}
		temp = new ArrayList<Wine>();
		temp2 = new ArrayList<Wine>();
		for (int i = 0; i < wines.length; i++) {
			recommend = new Recommendations(Read.wines, wines[i]);
			temp.addAll(Arrays.asList(recommend.get_results()));
		}
		//Check for duplication
		for (Wine object : temp)
			if (!temp2.contains(object))
				temp2.add(object);
		wines = new Wine[temp2.size()];
		wines = temp2.toArray(wines);
		recommendDisplay(wines);
		return;
	}
	
	//Recommend wine from a specific wine from searching
	private static void recommendSearching() {
		int userInput;
		String temp = "";
		Recommendations recommend;
		Wine[] wines = null;
		boolean first = true;
		//Let the user choose what to search by
		while (true) {
			while (first) {
				temp = "Please choose between the following options:\n" + "    1: Start by searching\n"
																		+ "    2: Start by filtering (WARNING, the result might take a long time to print)\n"
																		+ "    0: Back to previous menu\n";
				userInput = Integer.parseInt(JOptionPane.showInputDialog(temp));
				switch(userInput) {
					case 1: wines = searching(Read.wines);
							if (wines.length < Read.wines.length)
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
				temp = temp + wines[i] + "\n";
			temp = temp + "\n" + "Please choose between the following options:\n" + "    ID: Use this wine for food recommendation\n"
																				  + "    -1: Continue Searching\n"
																				  + "    -2: Continue filtering\n"
																				  + "    -3: Continue sorting\n"
																				  + "    -4: Back to previous menu";
			userInput = Integer.parseInt(displayInput(temp));
			switch(userInput) {
				case -1: wines = searching(wines);
						 break;
				case -2: wines = filtering(wines);
						 break;
				case -3: wines = sorting(wines);
						 break;
				case -4: return;
				default:
					wines = Searching.linear_search(wines, Integer.toString(userInput), "unique_ID");
					if (wines.length == 0)
						JOptionPane.showMessageDialog(null, "Invalid input, please enter a valid choice.");
					else {
						recommend = new Recommendations(Read.wines, wines[0]);
						wines = recommend.get_results();
						recommendDisplay(wines);
						return;
					}
			}
		}
	}
	
	//Display the result of the recommendation
	private static void recommendDisplay(Wine[] wines) {
		int userInput;
		String temp = "";
		//Let the user choose what to search by
		while (true) {
			temp = "";
			for (int i = 0; i < wines.length; i++)
				temp = temp + wines[i] + "\n";
			temp = temp + "\n" + "You can reduce the above list through the following ways:\n" + "    1: Continue Searching\n"
																							   + "    2: Continue filtering\n"
																							   + "    3: Continue sorting\n"
																							   + "    4: Filter using favorited taste note\n"
																							   + "    0: Back to previous menu";
			userInput = Integer.parseInt(displayInput(temp));
			switch(userInput) {
				case 1: wines = searching(wines);
						break;
				case 2: wines = filtering(wines);
						break;
				case 3: wines = sorting(wines);
						break;
				case 4: wines = filterTasteNote(wines);
						break;
				case 0: return;
				default: JOptionPane.showMessageDialog(null, "Invalid input, please enter a valid choice.");
			}
		}
	}
	
	//Filter with user's favorite taste note
	private static Wine[] filterTasteNote(Wine[] wines) {
		Wine[] tempWines;
		ArrayList<Wine> temp = new ArrayList<Wine>();
		ArrayList<Wine> temp2 = new ArrayList<Wine>();
		String[] tastes = ProfileManager.getProfile().getTaste();
		//Check if no favorite taste note
		if (tastes.length == 0)
			return wines;
		//Get all the wines that matches the list of taste notes
		for (int i = 0; i < tastes.length; i++) {
			tempWines = Searching.linear_taste_notes_search(wines, tastes[i]);
			temp.addAll(Arrays.asList(tempWines));
		}
		//Check for duplication
		for (Wine object : temp)
			if (!temp2.contains(object))
				temp2.add(object);
		//Return
		tempWines = new Wine[temp2.size()];
		tempWines = temp2.toArray(tempWines);
		return tempWines;
	}
	
	
	
//===============================================================================================================================================================
//==========   Helper functions - Others   ======================================================================================================================
//===============================================================================================================================================================
	
	
	
	//Display long message with scroll bars and ask for input
	private static String displayInput(String message) {
		JTextArea text = new JTextArea(message);
	    text.setEditable(false);
	    JScrollPane scroll = new JScrollPane(text);
	    scroll.setPreferredSize(new Dimension(800, 600));
	    String temp = JOptionPane.showInputDialog(null, scroll);
	    return temp;
	}
	
}
