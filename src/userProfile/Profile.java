package userProfile;

import java.util.ArrayList;

/**
 * Class representing the user's profile
 * @author Mengxi Lei
 * @version Created 2019/03/07, Last Modified 2019/03/08
 */
class Profile {
	
	//Declare variables
	private String name;
	private String profile;
	private ArrayList<String> taste;
	private ArrayList<String> wines;
	private int[] priceRange;
	private boolean modified;
	
	/**
	 * Constructor method for profile
	 * @param name name of user
	 * @param profile profile name
	 */
	protected Profile (String name, String profile) {
		this.name = name;
		this.profile = profile;
		taste = new ArrayList<String>();
		wines = new ArrayList<String>();
		priceRange = new int[2];
		modified = true;
	}
	
	/**
	 * Accessor method for name of user
	 * @return name of user
	 */
	protected String getName () {
		return name;
	}
	/**
	 * Accessor method for profile name
	 * @return profile name
	 */
	protected String getProfile() {
		return profile;
	}
	/**
	 * Accessor method for user's favorite taste
	 * @return taste as a array (not arraylist)
	 */
	protected String[] getTaste() {
		return (String[]) taste.toArray();
	}
	/**
	 * Accessor method for user's favorite wines
	 * @return favorite as a array (not arraylist)
	 */
	protected String[] getWines() {
		return (String[]) wines.toArray();
	}
	/**
	 * Accessor method for if the object is modified
	 * @return true for modified, false otherwise
	 */
	protected boolean getModified() {
		return modified;
	}
	
	/**
	 * Add the given taste to profile
	 * @param element String representing the taste being added
	 */
	protected void addTaste(String element) {
		if (taste.indexOf(element) != -1)
			return;
		taste.add(element);
		modified = true;
	}
	
	/**
	 * Delete the given taste from profile
	 * @param element String representing the taste being deleted
	 */
	protected void deleteTaste(String element) {
		int index = taste.indexOf(element);
		if (index == -1)
			return;
		taste.remove(index);
		modified = true;
	}
	
	/**
	 * Add the given wine to profile
	 * @param element String representing the wine being added
	 */
	protected void addWine(String element) {
		if (wines.indexOf(element) != -1)
			return;
		wines.add(element);
		checkPriceChange();
		modified = true;
	}
	
	/**
	 * Delete the given wine from profile
	 * @param element String representing the wine being deleted
	 */
	protected void deleteWine(String element) {
		int index = wines.indexOf(element);
		if (index == -1)
			return;
		wines.remove(index);
		checkPriceChange();
		modified = true;
	}
	
	/**
	 * Update the price range
	 * If no wine, then price range is 0 to 0
	 * If there is only one wine, the price range is price of wine +/- 15%
	 * Otherwise, the range is the price of highest wine and price of lowest wine +/- 5%
	 */
	private void checkPriceChange() {
		int size = wines.size();
		if (size == 0)
			priceRange = new int[2];
		else if (size == 1) {
			
		}
		else {
			
		}
	}
	
}
