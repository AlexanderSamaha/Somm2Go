package execution;

import userProfile.ProfileManager;

public class Main {
	public static void main(String[] args) {
		
		//Declare variables
		
		//Initial program setup
		ProfileManager.profileInit();		
		
		//Termination of program
		ProfileManager.close();
		
	}
}
