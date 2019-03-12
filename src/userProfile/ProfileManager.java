package userProfile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Class for managing the profiles
 * @author Mengxi Lei
 * @version Created 2019/03/07, Last Modified 2019/03/12
 */
public class ProfileManager {
	
	//Declare variables
	private final static int MAX_SIZE = 10;
	private final static String PROFILE_LIST = "profile/profile.bin";
	private int size;
	private String[] names;
	private Profile profile;
	
	/**
	 * Constructor method
	 * Setup the profile by creating a new one or reading from an existing one
	 */
	public ProfileManager () {
		//Declare variables
		int userInput;
		boolean doubleBreak;
		Scanner input = new Scanner(System.in);
		read();
		while (true) {
			//Create a new profile if no profile available
			if (size == 0) {
				System.out.println("There are no existing profile, please create a profile to preceed");
				newProfile();
				break;
			}
			//Choose between load a profile or delete a profile (if cannot create more profiles)
			else if (size < MAX_SIZE) {
				doubleBreak = false;
				while(true) {
					System.out.println("Enter the number corresponding to the option you want to choose:");
					System.out.println("    0: Delete a profile");
					for (int i = 0; i < size; i++)
						System.out.println("    " + (i+1) + ": Load profile \"" + names[i] + "\"");
					userInput = input.nextInt();
					if (userInput == 0) {
						deleteProfile();
						break;
					}
					else if (userInput > 0 && userInput <= size) {
						read(names[userInput-1]);
						doubleBreak = true;
						break;
					}
					else
						System.out.println("Invalid input, please enter a valid choice.");
				}
				if (doubleBreak)
					break;
			}
			//Choose between load a profile, create a new profile or delete a profile
			else {
				doubleBreak = false;
				while(true) {
					System.out.println("Enter the number corresponding to the option you want to choose:");
					System.out.println("    0: Delete a profile");
					for (int i = 0; i < size; i++)
						System.out.println("    " + (i+1) + ": Load profile \"" + names[i] + "\"");
					System.out.println("    " + (size+1) + ": Create a profile");
					userInput = input.nextInt();
					if (userInput == 0) {
						deleteProfile();
						break;
					}
					else if (userInput > 0 && userInput <= size) {
						read(names[userInput-1]);
						doubleBreak = true;
						break;
					}
					else if (userInput == size+1) {
						newProfile();
						doubleBreak = true;
						break;
					}
					else
						System.out.println("Invalid input, please enter a valid choice.");
				}
				if (doubleBreak)
					break;
			}
		}
		input.close();
	}
	
	/**
	 * Close the profile, make sure to output the profile is it have been modified
	 */
	public void close() {
		if (profile.getModified())
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
	
	/**
	 * Load the profile of the given name
	 * @param name name of profile being loaded
	 */
	private void read(String name) {
		File file = new File("profile/" + name + ".bin");
		Scanner input = null;
		StringTokenizer[] inputArray = new StringTokenizer[3];
		int length;
		double[] price = new double[2];
		try {
			input = new Scanner(file);
		} catch (FileNotFoundException exception) {
			System.out.println("Cannot find " + name + ".bin");
			System.exit(0);
		}
		profile = new Profile(name);
		for (int i = 0; i < inputArray.length; i++)
			inputArray[i] = new StringTokenizer(input.nextLine(), ",");
		length = inputArray[0].countTokens();
		for (int i = 0; i < length; i++)
			profile.addTaste(inputArray[0].nextToken());
		length = inputArray[1].countTokens();
		for (int i = 0; i < length; i++)
			profile.addWine(Integer.parseInt(inputArray[1].nextToken()));
		price[0] = Double.parseDouble(inputArray[2].nextToken());
		price[1] = Double.parseDouble(inputArray[2].nextToken());
		profile.setPriceRange(price);
		input.close();
	}
	
	/**
	 * Update the list of profiles to file
	 */
	private void write() {
		File file = new File(PROFILE_LIST);
		try {
			FileWriter output = new FileWriter(file);
			output.write(size + "\r\n");
			for (int i = 0; i < size; i++)
				output.write(names[i] + "\r\n");
			output.close();
		} catch (IOException exception) {}
	}
	
	/**
	 * Write the profile information to file
	 */
	private void writeProfile() {
		String[] taste;
		Integer[] wine;
		double[] range;
		File file = new File ("profile/" + profile.getProfile() + ".bin");
		try {
			FileWriter output = new FileWriter(file);
			taste = profile.getTaste();
			for (int i = 0; i < taste.length-1; i++)
				output.write(taste[i] + ",");
			output.write(taste[taste.length-1] + "\r\n");
			wine = profile.getWines();
			for (int i = 0; i < wine.length-1; i++)
				output.write(wine[i] + ",");
			output.write(wine[wine.length-1] + "\r\n");
			range = profile.getPriceRange();
			output.write(range[0] + "," + range[1]);
			output.close();
		} catch (IOException exception) {}
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
		profile.setModified(true);
		write();
		System.out.println("New profile created, the profile name is " + name + ".");
		input.close();
	}
	
	/**
	 * Delete a existing profile
	 */
	private void deleteProfile() {
		int userInput;
		File file;
		Scanner input = new Scanner(System.in);
		while (true) {
			System.out.println("Enter the number corresponding to the profile you want to delete:");
			for (int i = 0; i < size; i++)
				System.out.println("    " + i + ": \"" + names[i] + "\"");
			userInput = input.nextInt();
			if (userInput >= 0 && userInput < size) {
				file = new File("profile/" + names[userInput] + ".bin");
				file.delete();
				for (int i = userInput; i < size-1; i++)
					names[i] = names[i+1];
				names[size-1] = null;
				size--;
				write();
				input.close();
				return;
			}
			else
				System.out.println("Invalid input, please enter a valid choice.");
		}
	}
	
}
