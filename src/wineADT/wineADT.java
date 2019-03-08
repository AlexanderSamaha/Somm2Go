package wineADT;

public class wineADT {
	// declaring the characteristics of our set.
	private String country;
	private String description;
	private String designation;
	private Integer rating;
	private Double price;
	private String province;
	private String[] region;
	private String variety;
	private String winery;
	
	// Some of these wine bottles have a review and reviewer.
	private String reviewer;
	private String twitter;
	
	/**
	 * Method accesses the rating of a wine bottle.
	 * 
	 * @return returns Integer relating to rating.
	 */
	public Integer get_rating() {
		return rating;
	}
	
	/**
	 * Method accesses the price of a wine bottle.
	 * 
	 * @return returns Double relating to price.
	 */
	public Double get_price() {
		return price;
	}
	
	/**
	 * Method accesses the geographical characteristic of a bottle.
	 * 
	 * @return returns a String with the region(s), province and country of a wine bottle.
	 */
	public String get_geo() {
		String location = new String();
		for (int i = 0; i < region.length; i++) {
			location += region[i];
		}
		return location + ", " + province + ", " + country;
	}
	
	/**
	 * Method accesses the original winery where the bottle originates.
	 * 
	 * @return returns String relating to winery.
	 */
	public String get_winery() {
		return winery;
	}
	
	/**
	 * Method accesses the description of a wine as given by an expert.
	 * 
	 * @return returns String relating to description.
	 */
	public String get_description() {
		return description;
	}
	
	/**
	 * Method accesses the designation of a wine bottle (name).
	 * 
	 * @return returns String relating to designation.
	 */
	public String get_designation() {
		return designation;
	}
	
	/**
	 * Method accesses the variety of a wine bottle.
	 * 
	 * @return returns String relating to variety of a wine bottle.
	 */
	public String get_variety() {
		return variety;
	}
	
	/**
	 * Method accesses the name of the expert who reviewed the wine bottle.
	 * 
	 * @return returns a String relating to a name.
	 */
	public String get_reviewer_name() {
		return reviewer;
	}
	
	public String get_name_and_twitter() {
		String combined_name = new String();
		combined_name = reviewer + ", " + twitter;
		return combined_name;
	}
	
	
}
