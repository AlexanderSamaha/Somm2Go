package foodPairing;

import java.util.ArrayList;

import searchsort.Filtering;
import searchsort.Searching;
import userProfile.ProfileManager;
import wineADT.Read;
import wineADT.Wine;

/**
 * Class for pairing food and wines
 * @author Mengxi Lei and Alexander Samaha
 * @version Created 2019/03/16, Last modified 2019/04/09
 */
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
	private static String[] styles = {
		"pinot noir", "chardonnay", "champagne", "cabernet sauvignon",
		"sauvignon blanc", "dry rosé", "pinot grigio", "malbec", "moscato",
		"syrah", "grüner veltliner", "zinfandel", "riesling", "rosé champagne",
		"merlot", "shiraz", "gewürztraminer", "albariño", "malvasia", "müller-thurgau",
		"roussanne", "viognier", "marsanne", "sparkling", "prosecco", "cava", "st. laurent",
		"zweigelt", "gamay", "meritage", "tempranillo", "sangiovese", "grenache", "monastrell",
		"aglianico", "semillon", "muscadelle"
	};
	
	private static String[] foods = {
		"vegetables", "roasted vegetables", "soft cheese", "hard cheese", "starch", "light fish", "rich fish",
		"white meat", "red meat", "cured meat", "sweets", "earthy", "salt", "tangy", "rich cheese", "sweet-spicy",
		"fruit", "spiced", "fresh herbs"
	};
	
	private static String[][] pairings = {
			/*vegetables*/{"sauvignon blanc", "grüner veltliner", "pinot grigio", "albariño", "champagne", "prosecco", "cava"},
			/*roasted vegetables*/{"sauvignon blanc", "grüner veltliner", "pinot grigio", "albariño", "st. laurent", "pinot noir", "zweigelt", "gamay", "meritage", "tempranillo", "sangiovese", "zinfandel", "grenache", "merlot"},
			/*soft cheese*/{"gewürztraminer", "müller-thurgau", "malvasia", "moscato", "riesling", "champagne", "prosecco", "cava", "chardonnay", "roussanne", "marsanne", "viognier", "ice wine", "late harvest", "sherry", "port", "semillon"},
			/*hard cheese*/{"gewürztraminer", "müller-thurgau", "malvasia", "moscato", "riesling", "champagne", "prosecco", "cava", "cabernet sauvignon", "monastrell", "aglianico", "malbec", "syrah", "meritage", "tempranillo", "sangiovese", "zinfandel", "grenache", "merlot"},
			/*starch*/{"sauvignon blanc", "grüner veltliner", "pinot grigio", "albariño", "champagne", "prosecco", "cava", "chardonnay", "roussanne", "marsanne", "viognier", "st. laurent", "pinot noir", "zweigelt", "gamay", "tempranillo", "sangiovese", "zinfandel", "grenache", "merlot", "cabernet sauvignon", "monastrell", "aglianico", "malbec", "syrah", "ice wine", "late harvest", "sherry", "port"},
			/*light fish*/{"sauvignon blanc", "grüner veltliner", "pinot grigio", "albariño", "chardonnay", "roussanne", "marsanne", "viognier", "champagne", "prosecco", "cava"},
			/*rich fish*/{"st. laurent", "pinot noir", "zweigelt", "gamay", "chardonnay", "roussanne", "marsanne", "viognier"},
			/*white meat*/{"meritage", "chardonnay", "roussanne", "marsanne", "viognier", "st. laurent", "pinot noir", "zweigelt", "gamay", "tempranillo", "sangiovese", "zinfandel", "grenache", "merlot"},
			/*red meat*/{"meritage", "tempranillo", "sangiovese", "zinfandel", "grenache", "merlot", "cabernet sauvignon", "monastrell", "aglianico", "malbec", "syrah", "shiraz"},
			/*cured meat*/{"gewürztraminer", "müller-thurgau", "malvasia", "moscato", "riesling", "st. laurent", "pinot noir", "zweigelt", "gamay", "meritage", "tempranillo", "sangiovese", "zinfandel", "grenache", "merlot", "cabernet sauvignon", "ice wine", "late harvest", "sherry", "port"},
			/*sweets*/{"gewürztraminer", "müller-thurgau", "malvasia", "moscato", "riesling", "ice wine", "late harvest", "sherry", "port"},
			/*earthy foods*/{"pinot noir", "merlot", "gamay", "grenache", "sauvignon blanc"},
			/*salt*/{"champagne", "sauvignon blanc", "pinot grigio", "dry rosé", "gewürztraminer", "riesling"},
			/*tangy*/{"sauvignon blanc", "albariño", "chardonnay", "gamay", "zweigelt", "pinot noir", "dry rosé"},
			/*rich cheese*/{"dry rosé", "chardonnay", "champagne", "sauvignon blanc", "riesling", "grüner veltliner", "semillon", "marsanne", "roussanne", "pinot noir", "gamay", "zweigelt"},
			/*sweet-spicy*/{"malbec", "riesling"},
			/*fruit*/{"moscato", "syrah"},
			/*spiced*/{"syrah", "zinfandel", "cabernet sauvignon", "pinot grigio", "merlot", "riesling", "gewürztraminer", "rosé champagne"},
			/*fresh herbs*/{"grüner veltliner", "syrah"}
	};
	
	/**
	 * Recommend wines base on the given food
	 * @param food Food that is given
	 * @return Array of recommended wines
	 */
	public static Wine[] wineRecommand (String food) {
		int index = -1;
		Wine[] wines = null;
		Wine[] temp;
		ArrayList<Wine> tempWine = new ArrayList<Wine>();
		double[] price;
		for (int i = 0; i < foods.length; i++) {
			if (food.equals(foods[i]))
				index = i;
		}
		if (index == -1) {
			wines = new Wine[0];
			return wines;
		}
		for (int i = 0; i < pairings[index].length; i++) {
			temp = Searching.linear_search(Read.wines, pairings[index][i], "variety");
			for (int j = 0; j < temp.length; j++)
				tempWine.add(temp[j]);
		}
		wines = tempWine.toArray(wines);
		wines = Filtering.linear_filtering(wines, "price", Double.toString(ProfileManager.getProfile().getPriceRange()[0]),
										   Double.toString(ProfileManager.getProfile().getPriceRange()[1]));
		return wines;
	}
	
	/**
	 * Recommend food base on the given wine
	 * @param wine Wine that is given
	 * @return Array of recommended food
	 */
	public static String[] foodRecommend (Wine wine) {
		String variety = wine.get_variety();
		String[] food = null;
		ArrayList<String> tempFood = new ArrayList<String>();
		for (int i = 0; i < pairings.length; i++) {
			for (int j = 0; j < pairings[i].length; j++) {
				if (variety.toLowerCase().contains(pairings[i][j].toLowerCase())) {
					tempFood.add(foods[i]);
					break;
				}
			}
		}
		//food = tempFood.toArray(food);
		food = new String[tempFood.size()];
		food = tempFood.toArray(food);
		return food;
	}
	
	/**
	 * Accessor for foods list
	 * @return foods list
	 */
	public static String[] getFoods() {
		return foods;
	}
	
}
