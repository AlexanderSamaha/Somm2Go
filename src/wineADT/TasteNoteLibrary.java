package wineADT;

public class TasteNoteLibrary {
	
	/**
	 * This Library is created based upon the scholarly publication found here.
	 * http://cns.bu.edu/~gsc/Articles/WineSpeak-DoesItConveyInformation.pdf
	 * 
	 */
	private static String[] patterns = new String[]{
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
		"prunes", "hard", "vegetative", "thinn", "strange", "volatile", "sad", "oxydised", "smoky", "old",
		"tissue", "stripped", "bred", "flowers", "worm out", "wax", "cuppery", "aged", "rag",
		
		"great", "amazing", "blockbuster", "enjoy", "outstanding", "elegance", "profound", "no filtration", "amber",
		"closed", "earth", "cedar", "dusty", "herbs", "jammy", "clean", "delicious", "varietal"
	};
	
	public static String[] get_patterns() {
		return patterns;
	}
	
	
}
