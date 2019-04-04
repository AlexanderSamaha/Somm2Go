package wineADT;

import java.util.ArrayList;

public class TasteNoteLibrary {
	
	public static SetWeight[] get_special_pat = special_weights(get_special_pattern());
	
	/**
	 * This Library is created based upon the scholarly publication found here.
	 * http://cns.bu.edu/~gsc/Articles/WineSpeak-DoesItConveyInformation.pdf
	 * 
	 */
	public static String[] patterns = new String[]{
		"burgundy", "climate", "heart", "honnest", "bottle", "pleasure",
		"perfect", "feeling", "style", "fleshy", "final", "dark", "intense", "deep",
		"blackcurrant", "cherry", "fruit", "rubis", "raspberry", "spice", "growth",
		"wood", "chateau", "complexity", "brand", "substance", "property", "structure",
		"volume", "personality", "success", "gold", "floral", "fresh", "sweet", "pale",
		"dry", "apricot", "lemon", "honey", "hay", "crisp", "brown", 
		
		"brown", "spyci", "matured",
		"dark", "chocolate", "cedar", "pine", "tabacco", "tuiled", "smoky", "brics", "ripe",
		"soft", "black", "extracted", "tar", "marmelade", "muscle", "round", "nice", "fruity",
		"pleasant", "cherry", "redcurrant", "rubis", "raspberry", "supple", "tender", "light",
		"full", "long", "fat", "coince", "honey", "walnut", "peach", "pear", "grilled", "white",
		"acidic", "butter", "lemon", "apple", "box-wood",
		
		"sugar", "balanced", "woody", "strawberry", "open", "excellent", "meaty", "spicy", "tannins",
		"prunes", "hard", "vegetative", "thinn", "strange", "volatile", "sad", "oxydised", "old",
		"tissue", "stripped", "bred", "flowers", "worm out", "wax", "cuppery", "aged", "rag",
		
		"great", "amazing", "blockbuster", "enjoy", "outstanding", "elegance", "profound", "no filtration", "amber",
		"closed", "earth", "cedar", "dusty", "herbs", "jammy", "clean", "delicious", "varietal"
	};
	
	/*
	 * fruit, floral, sweet, dry, crisp, smoky, fruity, acidic, balanced
	 * 
	 */
	
	private static String[][] special_pat = new String[][] {
			{"fruit", "6.0"}, {"floral", "3.0"}, {"sweet", "8.0"}, {"dry", "8.0"}, {"crisp", "7.0"}, {"smoky","3.0"}, {"fruity", "8.0"}, {"acidic", "8.0"}, {"balanced", "5.0"}	
	};
	
	
	public static String[] get_patterns() {
		return patterns;
	}
	
	public static String[][] get_special_pattern() {
		return special_pat;
	}
	
	private static SetWeight[] special_weights(String[][] pattern) {
		ArrayList<SetWeight> special_pat = new ArrayList<SetWeight>();
		for (int i = 0; i < pattern.length; i++) {
			SetWeight current = new SetWeight(pattern[i][0], Double.parseDouble(pattern[i][1]));
			special_pat.add(current);
		}
		
		SetWeight[] new_arr = new SetWeight[special_weight.size()];
		new_arr = special_weight.toArray(new_arr);
		return new_arr;
	}
	
	
	
}
