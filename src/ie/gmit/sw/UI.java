package ie.gmit.sw;

import java.io.IOException;
import java.util.Scanner;

/**
 * @author Aaron Moran
 * @version 1.0 
 * 
 * This class is the core of the programs
 * execution, provides the user with a UI and 
 * also requires them to prompt the information needed
 * to analyse a Query.
 *
 */
public class UI {
	//variables
	Scanner console = new Scanner(System.in);
	private String choice;
	private String file;
	private String query;

	//header
	/**
	 * Basic header to display the UI design.
	 */
	public void header(){
		System.out.println("=========================================================================");
		System.out.println("*\t   GMIT - Dept. Computer Science & Applied Physics\t\t*");
		System.out.println("*\t\t\t\t\t\t\t\t\t*");
		System.out.println("*\t\t     Text Language Detector\t\t\t\t*");
		System.out.println("*\t\t\t\t\t\t\t\t\t*");
		System.out.println("=========================================================================");
	}
	
	/**
	 * This method is used for the user
	 * to determine the WiLi DataLocation
	 * and the Query text file. 
	 * 
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public void setup() throws IOException, InterruptedException {
		
		System.out.println("Enter the WiLi Data Location...");
		file = console.next();
		System.out.println("Building subject database...please wait...");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		
		/* wili-2018-Large-117500-Edited.txt
		 * wili-2018-small-11750-Edited.txt
		 * 
		 * Very accurate detection with the 
		 * Large text file, unfortunately not
		 * as accurate or successful with the
		 * smaller file.
		 * 
		 */
		
		//Parser object passing in file name
		Parser p = new Parser(file, 3);
		Database db = new Database();
		p.setDb(db);
		Thread t = new Thread(p);
		t.start();
		t.join();
		//setting database capacity to 300
		db.resize(300);
		
		System.out.println("Enter the Query File location...");
		query = console.next();
		
		//Calling the analyseQuery method passing "Query" text file
		p.analyseQuery(query);
		
		//Additional user options
		 System.out.println("Q) Do you want test a new Query? (Y/N)");
		 choice = console.next();
		   
		 if(choice.equalsIgnoreCase("Y")) {
			System.out.println("Enter the WiLi Data Location...");
			file = console.next();
			System.out.println("Building subject database...please wait...");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
				
		    Parser p1 = new Parser(file, 3);					
			Database db1 = new Database();
			p1.setDb(db1);
			Thread t1 = new Thread(p1);
			t1.start();
			t1.join();
			db1.resize(300);
			System.out.println("Enter the Query File location...");
			query = console.next();
			
			p1.analyseQuery(query);
				
			//Additional user options
			System.out.println("Q) Do you want test a new Query? (Y/N)");
			choice = console.next();

		 }
		 else if(choice.equalsIgnoreCase("N")) {
			//program end
			System.out.println("Task Complete.");
		}
	}
}
