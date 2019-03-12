package wineADT;

/**
 * 
 * @brief Contains methods called to ease comparison of wine bottles. These are all the
 * characteristics we care about when comparing wine.
 * 
 * @author Alexander Samaha
 * 
 * @date last modified 11/03/2019.
 *
 */
public class CompareLibrary {
	
	/**
	 * @brief Compares the unique ID given to each member of the dataset.
	 * 
	 * @param i Wine object with characteristics.
	 * @param j Wine object with characteristics.
	 * @return An integer that corresponds to lexicographical position.
	 */
	public static int compare_uniqueID(Wine i, Wine j) {
		return i.get_uniqueID().compareTo(j.get_uniqueID());
	}
	
	/**
	 * @brief Compares the price between two bottles.
	 * 
	 * @param i Wine object with characteristics.
	 * @param j Wine object with characteristics.
	 * @return An integer that corresponds to lexicographical position.
	 */
	public static int compare_price(Wine i, Wine j) {
		return i.get_price().compareTo(j.get_price());
	}
	
	/**
	 * @brief Compares the variety of a bottle (style) between two bottles.
	 * 
	 * @param i Wine object with characteristics.
	 * @param j Wine object with characteristics.
	 * @return An integer that corresponds to lexicographical position.
	 */
	public static int compare_variety(Wine i, Wine j) {
		return i.get_variety().compareTo(j.get_variety());
	}
	
	/**
	 * @brief Compares the rating between two bottles of wine.
	 * 
	 * @param i Wine object with characteristics.
	 * @param j Wine object with characteristics.
	 * @return An integer that corresponds to lexicographical position.
	 */
	public static int compare_rating(Wine i, Wine j) {
		return i.get_rating().compareTo(j.get_rating());
	}
	
	/**
	 * @brief Compares between origin country of a bottle of wine.
	 * 
	 * @param i Wine object with characteristics.
	 * @param j Wine object with characteristics.
	 * @return An integer that corresponds to lexicographical position.
	 */
	public static int compare_country(Wine i, Wine j) {
		return i.get_country().compareTo(j.get_country());
	}
	
	/**
	 * @brief Compares between province wine is from.
	 * 
	 * @param i Wine object with characteristics.
	 * @param j Wine object with characteristics.
	 * @return An integer that corresponds to lexicographical position.
	 */
	public static int compare_province(Wine i, Wine j) {
		return i.get_province().compareTo(j.get_province());
	}
	
	/**
	 * @brief Compares between name of wine bottle.
	 * 
	 * @param i Wine object with characteristics.
	 * @param j Wine object with characteristics.
	 * @return An integer that corresponds to lexicographical position.
	 */
	public static int compare_designation(Wine i, Wine j) {
		return i.get_designation().compareTo(j.get_designation());
	}
	
	/**
	 * @brief Compares between winery of origin.
	 * 
	 * @param i Wine object with characteristics.
	 * @param j Wine object with characteristics.
	 * @return An integer that corresponds to lexicographical position.
	 */
	public static int compare_winery(Wine i, Wine j) {
		return i.get_winery().compareTo(j.get_winery());
	}
	
	/**
	 * @brief Compares between country value, if the same, compares between province.
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
