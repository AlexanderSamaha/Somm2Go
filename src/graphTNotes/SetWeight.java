package graphTNotes;
/**
 * ADT for weighting specific taste notes with special weights.
 * 
 * @author David Carrie
 * 
 * @version Last modified 09/04/2019.
 *
 */
public class SetWeight {
	private String note;				//Note to be weighted
	private double weight;				//weight
	
	/**
	 * Construct a new weight for a specific note
	 * @param s taste note
	 * @param weight weight of note
	 */
	public SetWeight(String s, double weight) {
		note = s;
		this.weight = weight;
	}
	
	/**
	 * returns the taste note to receive a special weight
	 * @return taste note
	 */
	public String getNote() {
		return note;
	}
	/**
	 * returns the weight of the taste note
	 * @return weight
	 */
	public double getWeight() {
		return weight;
	}
}
