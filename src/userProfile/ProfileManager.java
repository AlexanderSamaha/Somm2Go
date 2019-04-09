package userProfile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;

import javax.swing.JOptionPane;

import wineADT.Wine;

/**
 * Class for managing the profiles
 * @author Mengxi Lei
 * @version Created 2019/03/07, Last Modified 2019/04/08
 */
public class ProfileManager {
	
	//Declare variables
	private final static int MAX_SIZE = 10;
	private final static String PROFILE_LIST = "profile/profile.bin";
	private static int size;
	private static String[] names;
	private static Profile profile;
	
	/**
	 * Constructor method
	 * Setup the profile by creating a new one or reading from an existing one
	 */
	public static void profileInit () {
		//Declare variables
		int userInput;
		boolean doubleBreak;
		String temp;
		read();
		while (true) {
			//Create a new profile if no profile available
			if (size == 0) {
				JOptionPane.showMessageDialog(null, "There are no existing profile, please create a profile to preceed");
				newProfile();
				break;
			}
			//Choose between load a profile or delete a profile (if cannot create more profiles)
			else if (size < MAX_SIZE) {
				doubleBreak = false;
				while(true) {
					temp = "Enter the number corresponding to the option you want to choose:\n" + "    0: Delete a profile\n";
					for (int i = 0; i < size; i++)
						temp = temp + "    " + (i+1) + ": Load profile \"" + names[i] + "\"" + "\n";
					userInput = Integer.parseInt(JOptionPane.showInputDialog(null, temp));
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
						JOptionPane.showMessageDialog(null, "Invalid input, please enter a valid choice.");
				}
				if (doubleBreak)
					break;
			}
			//Choose between load a profile, create a new profile or delete a profile
			else {
				doubleBreak = false;
				while(true) {
					temp = "Enter the number corresponding to the option you want to choose:\n" + "    0: Delete a profile\n";
					for (int i = 0; i < size; i++)
						temp = temp + "    " + (i+1) + ": Load profile \"" + names[i] + "\"" + "\n";
					temp = temp + "    " + (size+1) + ": Create a profile";
					userInput = Integer.parseInt(JOptionPane.showInputDialog(null, temp));
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
						JOptionPane.showMessageDialog(null, "Invalid input, please enter a valid choice.");
				}
				if (doubleBreak)
					break;
			}
		}
	}
	
	/**
	 * Close the profile, make sure to output the profile is it have been modified
	 */
	public static void close() {
		if (profile.getModified())
			writeProfile();
	}
	
	/**
	 * Return the current profile
	 * @return the current profile
	 */
	public static Profile getProfile() {
		return profile;
	}
	
	/**
	 * Read the list of available profiles from file
	 * File name from constant PROFILE_LIST
	 */
	private static void read() {
		File file = new File(PROFILE_LIST);
		Scanner input = null;
		try {
			input = new Scanner(file);
		} catch (FileNotFoundException exception) {
			JOptionPane.showMessageDialog(null, "Cannot find profile.bin");
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
	private static void read(String name) {
		File file = new File("profile/" + name + ".bin");
		Scanner input = null;
		StringTokenizer inputString;
		int length;
		double[] price = new double[2];
		try {
			input = new Scanner(file);
		} catch (FileNotFoundException exception) {
			JOptionPane.showMessageDialog(null, "Cannot find " + name + ".bin");
			System.exit(0);
		}
		profile = new Profile(name);
		if (input.nextLine().equals("Exist")) {
			inputString = new StringTokenizer(input.nextLine(), ",");
			length = inputString.countTokens();
			for (int i = 0; i < length; i++)
				profile.addTaste(inputString.nextToken());
		}
		if (input.nextLine().equals("Exist")) {
			inputString = new StringTokenizer(input.nextLine(), ",");
			length = inputString.countTokens();
			for (int i = 0; i < length; i++)
				profile.addWine(Integer.parseInt(inputString.nextToken()));
		}
		inputString = new StringTokenizer(input.nextLine(), ",");
		price[0] = Double.parseDouble(inputString.nextToken());
		price[1] = Double.parseDouble(inputString.nextToken());
		profile.setPriceRange(price);
		input.close();
	}
	
	/**
	 * Update the list of profiles to file
	 */
	private static void write() {
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
	private static void writeProfile() {
		String[] taste;
		Wine[] wine;
		double[] range;
		File file = new File ("profile/" + profile.getProfile() + ".bin");
		try {
			FileWriter output = new FileWriter(file);
			taste = profile.getTaste();
			if (taste.length == 0)
				output.write("None\r\n");
			else {
				output.write("Exist\r\n");
				for (int i = 0; i < taste.length-1; i++)
					output.write(taste[i] + ",");
				output.write(taste[taste.length-1] + "\r\n");
			}
			wine = profile.getWines();
			if (wine.length == 0)
				output.write("None\r\n");
			else {
				output.write("Exist\r\n");
				for (int i = 0; i < wine.length-1; i++)
					output.write(wine[i].get_uniqueID() + ",");
				output.write(wine[wine.length-1].get_uniqueID() + "\r\n");
			}
			range = profile.getPriceRange();
			output.write(range[0] + "," + range[1]);
			output.close();
		} catch (IOException exception) {}
	}
	
	/**
	 * Create a new profile and set it as the current profile
	 */
	private static void newProfile() {
		boolean duplicate;
		String name;
		do {
			duplicate = false;
			name = JOptionPane.showInputDialog(null, "Please enter profile name");
			for (int i = 0; i < size; i++) {
				if (name.equalsIgnoreCase(names[i])) {
					duplicate = true;
				}
			}
			if (duplicate)
				JOptionPane.showMessageDialog(null, "Name of profile already exit, you cannot use this name");
		} while (duplicate);
		names[size] = name;
		profile = new Profile(name);
		size++;
		profile.setModified(true);
		write();
		JOptionPane.showMessageDialog(null, "New profile created, the profile name is " + name + ".");
	}
	
	/**
	 * Delete a existing profile
	 */
	private static void deleteProfile() {
		int userInput;
		File file;
		String temp;
		while (true) {
			temp = "Enter the number corresponding to the profile you want to delete:\n";
			for (int i = 0; i < size; i++)
				temp = temp + "    " + i + ": \"" + names[i] + "\"" + "\n";
			userInput = Integer.parseInt(JOptionPane.showInputDialog(null, temp));
			if (userInput >= 0 && userInput < size) {
				file = new File("profile/" + names[userInput] + ".bin");
				file.delete();
				for (int i = userInput; i < size-1; i++)
					names[i] = names[i+1];
				names[size-1] = null;
				size--;
				write();
				return;
			}
			else
				JOptionPane.showMessageDialog(null, "Invalid input, please enter a valid choice.");
		}
	}
	
}