package execution;

import javax.swing.JOptionPane;

import userProfile.ProfileManager;

/**
 * Execution class, containing the main method that will run the whole program
 * @author Mengxi Lei
 * @version Created 2019/04/08, Last modified 2019/04/08
 */
public class Main {
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
		ProfileManager.profileInit();
		
		//Let the user choose between searching for wine, recommendation or profile management
		while (cont) {
			temp = "Please choose between the following options:\n" + "    1: Manage your profile\n" 
		                                                            + "    2: Search Wines\n" 
					                                                + "    3: Wine Recommendation\n"
		                                                            + "    0: Exit the program\n";
			userInput = Integer.parseInt(JOptionPane.showInputDialog(null, temp));
			switch(userInput) {
				case 1: break;
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
}
