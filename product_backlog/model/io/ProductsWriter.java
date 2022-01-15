/**
 * 
 */
package edu.ncsu.csc216.product_backlog.model.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
//import java.util.List;

import edu.ncsu.csc216.product_backlog.model.product.Product;

/**
 * class to write products to a file
 * @author Bagya Maharajan
 *
 */
public class ProductsWriter {
	
	/**
	 * Constructor of class
	 */	
	public ProductsWriter() {
		//constructor
		
	}
	
	/**
	 * method writeProductsToFile receives file name and a List of Products to write to a file
	 * @param fileName of file
	 * @param products in a list
	 * @throws IllegalArgumentException for any errors or exceptions
	 */	
	public static void writeProductsToFile(String fileName, ArrayList<Product> products) {
		
		try {
			PrintStream fileWriter = new PrintStream(new File(fileName));
			
			for (int i = 0; i < products.size(); i++) {
				String toBePrintedProduct = "# " + products.get(i).getProductName();
				fileWriter.println(toBePrintedProduct);
				Product currentProd = products.get(i);
				for(int j = 0; j < products.get(i).getTasks().size(); j++) {
					String taskDetailsToPrint = currentProd.getTasks().get(j).toString();
					fileWriter.println(taskDetailsToPrint); 
				}
			}
		
			fileWriter.close();
		}
		catch(FileNotFoundException f) {
			throw new IllegalArgumentException();
		}
		
		
	    
	}

}
