package userProfile;

import java.util.ArrayList;

import searchsort.Searching;
import wineADT.Read;
import wineADT.Wine;


/**
 * Class representing the user's profile
 * @author Mengxi Lei
 * @version Created 2019/03/07, Last Modified 2019/04/09
 */
public class Profile {
	
	//Declare variables
	private String profile;
	private ArrayList<String> taste;
	private ArrayList<Integer> wines;
	private double[] priceRange;
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
		priceRange = new double[2];
		priceRange[1] = 100;
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
		String[] temp = new String[taste.size()];
		return taste.toArray(temp);
	}
	/**
	 * Accessor method for user's favorite wines
	 * @return favorite as a array (not arraylist)
	 */
	public Wine[] getWines() {
		Wine[] temp = new Wine[wines.size()];
		for (int i = 0; i < temp.length; i++)
			temp[i] = Searching.binary_search(Read.idSorted, wines.get(i));
		return temp;
	}
	/**
	 * Accessor method for the user's price range
	 * @return user's price range
	 */
	public double[] getPriceRange() {
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
	 * @param element Integer representing the wine being added
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
	 * @param element Integer representing the wine being deleted
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
	protected void setPriceRange(double[] array) {
		priceRange = array;
	}
	
	/**
	 * Update the price range
	 * If no wine, then price range is 0 to 100
	 * If there is only one wine, the price range is price of wine +/- 15%
	 * Otherwise, the range is the price of highest wine and price of lowest wine +/- 5%
	 */
	private void checkPriceChange() {
		double price;
		int size = wines.size();
		if (size == 0) {
			priceRange = new double[2];
			priceRange[1] = 100;
		}
		else if (size == 1) {
			price = Searching.binary_search(Read.idSorted, wines.get(0)).get_price();
			priceRange[0] = price * 0.85;
			priceRange[1] = price * 1.15;
		}
		else {
			priceRange[0] = Double.MAX_VALUE;
			priceRange[1] = 0;
			for (int i = 0; i < size; i++) {
				price = Searching.binary_search(Read.idSorted, wines.get(i)).get_price();
				if (price < priceRange[0])
					priceRange[0] = price;
				if (price > priceRange[1])
					priceRange[1] = price;
			}
			priceRange[0] *= 0.95;
			priceRange[1] *= 1.05;
		}
	}
	
}
