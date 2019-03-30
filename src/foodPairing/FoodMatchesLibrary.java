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
 * @version Created 2019/03/16, Last modified 2019/03/29
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
		"aglianico"
	};
	
	private static String[] foods = {
		"vegetables", "roasted vegetables", "soft cheese", "hard cheese", "starch", "light fish", "rich fish",
		"white meat", "red meat", "cured meat", "sweets", "earthy", "salt", "tangy", "rich cheese", "sweet-spicy",
		"fruit", "spiced", "fresh herbs"
	};
	
	private static String[][] pairings = {
			{},
			{},
			{},
			{},
			{},
			{},
			{},
			{},
			{},
			{},
			{},
			{},
			{},
			{},
			{},
			{},
			{},
			{},
			{}
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
				if (variety.equals(pairings[i][j])) {
					tempFood.add(foods[i]);
					break;
				}
			}
		}
		food = tempFood.toArray(food);
		return food;
	}
	
}
