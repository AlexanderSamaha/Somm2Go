package userProfile;

import java.util.ArrayList;

/**
 * Class representing the user's profile
 * @author Mengxi Lei
 * @version Created 2019/03/07, Last Modified 2019/03/11
 */
public class Profile {
	
	//Declare variables
	private String profile;
	private ArrayList<String> taste;
	private ArrayList<Integer> wines;
	private int[] priceRange;
	private boolean modified;
	
	/**
	 * Constructor method for profile
	 * @param name name of user
	 * @param profile profile name
	 */
	public Profile (String profile) {
		this.profile = profile;
		taste = new ArrayList<String>();
		wines = new ArrayList<Integer>();
		priceRange = new int[2];
		modified = false;
	}

	/**
	 * Accessor method for profile name
	 * @return profile name
	 */
	public String getProfile() {
		return profile;
	}
	/**
	 * Accessor method for user's favorite taste
	 * @return taste as a array (not arraylist)
	 */
	public String[] getTaste() {
		return (String[]) taste.toArray();
	}
	/**
	 * Accessor method for user's favorite wines
	 * @return favorite as a array (not arraylist)
	 */
	public Integer[] getWines() {
		return (Integer[]) wines.toArray();
	}
	/**
	 * Accessor method for the user's price range
	 * @return user's price range
	 */
	public int[] getPriceRange() {
		return priceRange;
	}
	
	/**
	 * Add the given taste to profile
	 * @param element String representing the taste being added
	 */
	public void addTaste(String element) {
		if (taste.indexOf(element) != -1)
			return;
		taste.add(element);
		modified = true;
	}
	
	/**
	 * Delete the given taste from profile
	 * @param element String representing the taste being deleted
	 */
	public void deleteTaste(String element) {
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
	public void addWine(Integer element) {
		if (wines.indexOf(element) != -1)
			return;
		wines.add(element);
		modified = true;
		checkPriceChange();
	}
	
	/**
	 * Delete the given wine from profile
	 * @param element String representing the wine being deleted
	 */
	public void deleteWine(Integer element) {
		int index = wines.indexOf(element);
		if (index == -1)
			return;
		wines.remove(index);
		modified = true;
		checkPriceChange();
	}
	
	/**
	 * Accessor method for if the profile have been modified since last save
	 * @return True if yes, false otherwise
	 */
	protected boolean getModified() {
		return modified;
	}	
	
	/**
	 * Manually set the modified variable of the profile
	 * @param bool Boolean which the modified is being set to
	 */
	protected void setModified(boolean bool) {
		modified = bool;
	}
	
	/**
	 * Manually set user's price range
	 * @param array User's price range
	 */
	protected void setPriceRange(int[] array) {
		priceRange = array;
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
