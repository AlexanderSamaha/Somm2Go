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
	private static int size;
	private static String[] names;
	private static Profile profile;
	
	/**
	 * Constructor method
	 */
	public ProfileManager () {
		Scanner input = new Scanner(System.in);
		read();
	}
	
	/**
	 * Close the profile, make sure to output the profile is it have been modified
	 */
	public void close() {
		if (profile.getModified())
			write(profile.getProfile());
	}
	
	private void read() {
		File file = new File("profile.bin");
		Scanner input = null;
		try {
			input = new Scanner(file);
		} catch (FileNotFoundException exception) {
			System.out.println("Cannot find profile.bin");
			System.exit(0);
		}
		size = Integer.parseInt(input.nextLine());
		names = new String[size+1];
	}
	
	private void read(String name) {
	}
	
	private void write(String name) {
	}
	
}
