package ie.gmit.sw;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.applet.Applet;
import java.awt.*;
import java.awt.image.*; 
import javax.imageio.*; 
import java.io.*;

/**
 * @author Aaron Moran
 * @version 1.0 
 * 
 * The main purpose of this class is to parse
 * the text from a Query file that you wish to
 * determine its Language. The class <i>implements</i>
 * Runnable which overrides its run method.
 */

public class Parser implements Runnable {
	private Database db = null;
	private static String file;
	private static String query;
	private int k;
	private static String choice;
    private Scanner s = new Scanner(System.in);  // Create a Scanner object
	private static NumberFormat format;
	private float startTime;
	private float endTime;
	private float totalTime;
	
	//Constructor 
	public Parser(String file, int k) {
		this.file=file;
		this.k=k;
	}
	
	public void setDb(Database db) {
		this.db=db;
	}
	  
	@Override
	public void run() {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			String line = null;
			
			while ((line = br.readLine())!=null) {
				String[] record = line.trim().split("@");
				if(record.length!=2)continue;
				parse(record[0],record[1]);
			}//while
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Parser method used to parse the 
	 * WiLi DataLocation file. Code provided
	 * by lecturer Dr. John Healy.
	 * @param text
	 * @param lang
	 * @param ks
	 */
	private void parse(String text, String lang, int... ks) {
		//parsing the WiLi datalocation
		Language language = Language.valueOf(lang);
		for (int i = 0; i <= text.length()- k; i++) {
			CharSequence kmer = text.substring(i, i+k);
			db.add(kmer, language);		
		}
	}
	
	/**
	 * Receives the prompted input of
	 * a Query file from the user and 
	 * 
	 * 
	 * @param q
	 * @throws IOException
	 */
	//ANALYSE QUERY
	public void analyseQuery(String q) throws IOException {		
		//Variables
		int val = 0;
		int freq = 1;
		CharSequence kmer;
		Map<Integer, LanguageEntry> query = new TreeMap<>();
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(q)));
		
		//Start nanoTime() for analysing the text
		startTime = System.nanoTime();		
		
			while ((q = br.readLine())!=null) {
				for (int i = 0; i <= q.length() - k; i++) {
					kmer = q.substring(i, i + k);
					val = kmer.hashCode();
					if(query.containsKey(val)) {
						freq+=1;
						freq += query.get(val).getFrequency();
					}
					else {
						freq=1;
					}
					//inserting specific key and its value into a map
					query.put(val, new LanguageEntry(val, freq));
				}//for
			}//while
			br.close();
			//display language
			System.out.println("The text appears to be written in " + db.getLanguage(query));	
			
			// ADDITIONS - EXECUTION TIME IN nanoTime()
			// Dividing by 1,000,000,000 displays the output in seconds
			endTime = System.nanoTime();
			totalTime = endTime - startTime;
			System.out.println("=========================================================================");
			System.out.println("The Running time was : "+ totalTime + " ns");	
			final float seconds = totalTime/1000000000;
			format = new DecimalFormat("#0.00000");
			System.out.println("The time in seconds is : "+ seconds +" s");
	}
}