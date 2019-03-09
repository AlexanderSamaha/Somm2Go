package userProfile;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Class for managing the profiles
 * @author Mengxi Lei
 * @version Created 2019/03/07, Last Modified 2019/03/08
 */
public class ProfileManager {
	
	//Declare variables
	private final static int MAX_SIZE = 10;
	private final static String PROFILE_LIST = "profile.bin";
	private int size;
	private String[] names;
	private Profile profile;
	private boolean modified;
	
	/**
	 * Constructor method
	 * Setup the profile by creating a new one or reading from an existing one
	 */
	public ProfileManager () {
		modified = false;
		read();
		while (true) {
			if (size == 0) {
				System.out.println("There are no existing profile, please create a profile to preceed");
				newProfile();
				break;
			}
			else if (size < MAX_SIZE) {
			}
			else {
			}
		}
		
	}
	
	/**
	 * Close the profile, make sure to output the profile is it have been modified
	 */
	public void close() {
		if (modified)
			writeProfile();
	}
	
	/**
	 * Read the list of available profiles from file
	 * File name from constant PROFILE_LIST
	 */
	private void read() {
		File file = new File(PROFILE_LIST);
		Scanner input = null;
		try {
			input = new Scanner(file);
		} catch (FileNotFoundException exception) {
			System.out.println("Cannot find profile.bin");
			System.exit(0);
		}
		size = Integer.parseInt(input.nextLine());
		if (size < MAX_SIZE)
			names = new String[size+1];
		else
			names = new String[MAX_SIZE];
		for (int i = 0; i < size; i++) {
			names[i] = input.nextLine();
		}
		input.close();
	}
	
	private void read(String name) {
	}
	
	private void write() {
	}
	
	private void writeProfile() {
	}
	
	/**
	 * Create a new profile and set it as the current profile
	 */
	private void newProfile() {
		Scanner input = new Scanner(System.in);
		boolean duplicate;
		String name;
		do {
			duplicate = false;
			System.out.println("Please enter profile name");
			name = input.nextLine();
			for (int i = 0; i < size; i++) {
				if (name.equalsIgnoreCase(names[i])) {
					duplicate = true;
				}
			}
			if (duplicate)
				System.out.println("Name of profile already exit, you cannot use this name");
		} while (duplicate);
		names[size] = name;
		profile = new Profile(name);
		size++;
		modified = true;
		write();
		System.out.println("New profile created, the profile name is " + name + ".");
		input.close();
	}
	
}
