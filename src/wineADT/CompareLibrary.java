package wineADT;

/**
 * 
 * Contains methods called to ease comparison of wine bottles. These are all the
 * characteristics we care about when comparing wine.
 * 
 * @author Alexander Samaha
 * 
 * @version last modified 11/03/2019.
 *
 */
public class CompareLibrary {
	
	/**
	 * Compares the unique ID given to each member of the dataset.
	 * 
	 * @param i Wine object with characteristics.
	 * @param j Wine object with characteristics.
	 * @return An integer that corresponds to lexicographical position.
	 */
	public static int compare_uniqueID(Wine i, Wine j) {
		return i.get_uniqueID().compareTo(j.get_uniqueID());
	}
	
	/**
	 *   Compares the price between two bottles.
	 * 
	 * @param i Wine object with characteristics.
	 * @param j Wine object with characteristics.
	 * @return An integer that corresponds to lexicographical position.
	 */
	public static int compare_price(Wine i, Wine j) {
		return i.get_price().compareTo(j.get_price());
	}
	
	/**
	 *   Compares the variety of a bottle (style) between two bottles.
	 * 
	 * @param i Wine object with characteristics.
	 * @param j Wine object with characteristics.
	 * @return An integer that corresponds to lexicographical position.
	 */
	public static int compare_variety(Wine i, Wine j) {
		return i.get_variety().compareTo(j.get_variety());
	}
	
	/**
	 *   Compares the rating between two bottles of wine.
	 * 
	 * @param i Wine object with characteristics.
	 * @param j Wine object with characteristics.
	 * @return An integer that corresponds to lexicographical position.
	 */
	public static int compare_rating(Wine i, Wine j) {
		return i.get_rating().compareTo(j.get_rating());
	}
	
	/**
	 *   Compares between origin country of a bottle of wine.
	 * 
	 * @param i Wine object with characteristics.
	 * @param j Wine object with characteristics.
	 * @return An integer that corresponds to lexicographical position.
	 */
	public static int compare_country(Wine i, Wine j) {
		return i.get_country().compareTo(j.get_country());
	}
	
	/**
	 *   Compares between province wine is from.
	 * 
	 * @param i Wine object with characteristics.
	 * @param j Wine object with characteristics.
	 * @return An integer that corresponds to lexicographical position.
	 */
	public static int compare_province(Wine i, Wine j) {
		return i.get_province().compareTo(j.get_province());
	}
	
	/**
	 *   Compares between name of wine bottle.
	 * 
	 * @param i Wine object with characteristics.
	 * @param j Wine object with characteristics.
	 * @return An integer that corresponds to lexicographical position.
	 */
	public static int compare_designation(Wine i, Wine j) {
		return i.get_designation().compareTo(j.get_designation());
	}
	
	/**
	 *   Compares between winery of origin.
	 * 
	 * @param i Wine object with characteristics.
	 * @param j Wine object with characteristics.
	 * @return An integer that corresponds to lexicographical position.
	 */
	public static int compare_winery(Wine i, Wine j) {
		return i.get_winery().compareTo(j.get_winery());
	}
	
	/**
	 *   Compares between country value, if the same, compares between province.
	 *  
	 * @param i Wine object with characteristics.
	 * @param j Wine object with characteristics.
	 * @return An integer that corresponds to lexicographical position.
	 */
	public static int compare_geo(Wine i, Wine j) {
		if (compare_country(i, j) == 0) {
			return compare_province(i, j);
		}
		else {
			return compare_country(i, j);
		}
	}
	
	
}
