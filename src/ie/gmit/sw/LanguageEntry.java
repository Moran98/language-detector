package ie.gmit.sw;

/**
 * 
 * @author Aaron Moran
 * @version 1.0 
 *
 */

public class LanguageEntry implements Comparable<LanguageEntry> {
	private int kmer;
	private int frequency;
	private int rank;

	/**
	 * This is the #1 constructor for the 
	 * LanguageEntry Class.
	 * 
	 * @param kmer
	 * @param frequency
	 */
	//Constructor #1
	public LanguageEntry(int kmer, int frequency) {
		super();
		this.kmer = kmer;
		this.frequency = frequency;
	}
	
	/**
	 * Below is the 3 required GETTERS
	 * for kmers, freq and rank.
	 * 
	 * @return 
	 */
	
	//GETTERS
	public int getKmer() {
		return kmer;
	}
	
	public int getFrequency() {
		return frequency;
	}
	
	public int getRank() {
		return rank;
	}
	
	/**
	 * The 3 required SETTERS 
	 * for kmers, freq and rank.
	 * 
	 * @param kmer
	 */

	//SETTERS
	public void setKmer(int kmer) {
		this.kmer = kmer;
	}

	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	@Override
	public int compareTo(LanguageEntry next) {
		return - Integer.compare(frequency, next.getFrequency());
	}
	
	@Override
	public String toString() {
		return "[" + kmer + "/" + frequency + "/" + rank + "]";
	}
}