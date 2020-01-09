package ie.gmit.sw;

import java.io.IOException;

/**
 * @author Aaron Moran
 * @version 1.0 
 * 
 * The Runner class is where all the 
 * programs execution will begin.
 * 
 */

public class Runner  {
	public static void main(String[] args) throws IOException, InterruptedException {
		//creating object and calling methods
		UI ui = new UI();
		ui.header();
		ui.setup();
  }
}
