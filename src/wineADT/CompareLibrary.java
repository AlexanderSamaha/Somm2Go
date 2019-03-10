package wineADT;

public class CompareLibrary {
	
	public static int compare_price(Wine i, Wine j) {
		return i.get_price().compareTo(j.get_price());
	}
	
	public static int compare_variety(Wine i, Wine j) {
		return i.get_variety().compareTo(j.get_variety());
	}
	
	public static int compare_rating(Wine i, Wine j) {
		return i.get_rating().compareTo(j.get_rating());
	}
	
	public static int compare_name(Wine i, Wine j) {
		return i.get_designation().compareTo(j.get_designation());
	}
	
	public static int compare_country(Wine i, Wine j) {
		return i.get_country().compareTo(j.get_country());
	}
	
	public static int compare_province(Wine i, Wine j) {
		return i.get_province().compareTo(j.get_province());
	}
	
	public static int compare_geo(Wine i, Wine j) {
		if (compare_country(i, j) == 0) {
			return compare_province(i, j);
		}
		else {
			return compare_country(i, j);
		}
	}
	
	
}
