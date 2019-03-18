package foodPairing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FoodMatchesLibrary {
	
	/*
	 * here are the links to figure out which style blends with which food.
	 * 
	 * https://www.foodandwine.com/slideshows/15-rules-great-wine-and-food-pairings#15
	 * https://www.huffingtonpost.ca/natalie-maclean/food-and-wine-pairings-guide_b_2505616.html
	 * 
	 * SUPER USEFUL, good picture.
	 * https://winefolly.com/tutorial/basic-wine-and-food-pairing-chart/
	 */
	
	// meritage is a bordeaux-style red blend, make sure both match.
	// bd-style white blend is sauvignon blanc, semillon, or muscadelle.
	private static String[] styles = new String[] {
		"pinot noir", "chardonnay", "champagne", "cabernet sauvignon",
		"sauvignon blanc", "dry rosé", "pinot grigio", "malbec", "moscato",
		"syrah", "grüner veltliner", "zinfandel", "riesling", "rosé champagne",
		"merlot", "shiraz", "gewürztraminer", "albariño", "malvasia", "müller-thurgau",
		"roussanne", "viognier", "marsanne", "sparkling", "prosecco", "cava", "st. laurent",
		"zweigelt", "gamay", "meritage", "tempranillo", "sangiovese", "grenache", "monastrell",
		"aglianico"
	};
	
	private static String[] food = new String[] {
		"vegetables", "roasted vegetables", "soft cheese", "hard cheese", "starch", "light fish", "rich fish",
		"white meat", "red meat", "cured meat", "sweets", "earthy", "salt", "tangy", "rich cheese", "sweet-spicy",
		"fruit", "spiced", "fresh herbs"
	};
	
	private static Map<String, String[]> map = new HashMap<String, String[]>();
	
	public static String[] get_styles_library() {
		return styles;
	}
	
	public static Map<String, String[]> get_hashmap(){
		return map;
	}
	
	public static String[] get_food_library() {
		return food;
	}
	
	public static void read_hashmap() {
		
		for (int i = 0; i < food.length; i++) {
			map.put(food[i], null);
		}
	}
	 
	
}
