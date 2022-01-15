/**
 * 
 */
package edu.ncsu.csc216.product_backlog.model.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
//import java.util.NoSuchElementException;
import java.util.Scanner;

import edu.ncsu.csc216.product_backlog.model.product.Product;
import edu.ncsu.csc216.product_backlog.model.task.Task;

/**
 * Class to handle file input
 * @author Bagya Maharajan
 *
 */
public class ProductsReader {
	
	/**
	 * Constructor of class
	 */	
	public ProductsReader() {		
		//constructor
	}
	
	/**
	 * method to receive fileName and read the file
	 * @param fileName as string input
	 * @return ArrayList of type Product of products with tasks
	 * @throws IllegalArgumentException if file not found
	 */	
	public static ArrayList<Product> readProductsFile(String fileName) {
		
		Scanner fileReader = null;		
		try {
			fileReader = new Scanner(new FileInputStream(fileName));
		}
		catch(FileNotFoundException e) {
			throw new IllegalArgumentException("Unable to load file.");
		}
		
		ArrayList<Product> products = new ArrayList<Product>();	
		String fileAsString = "";
		while(fileReader.hasNextLine()) {			
			fileAsString += fileReader.nextLine() + "\n";			
		}
		
		//check for empty file		
		if("".equals(fileAsString)) {
			throw new IllegalArgumentException();
		}
		
		Scanner lineScanForProduct = new Scanner(fileAsString);		
		lineScanForProduct.useDelimiter("\\r?\\n?[#] ");
		
		while(lineScanForProduct.hasNext()) {
			try {
				Product pFromFile = processProduct(lineScanForProduct.next());
				if(pFromFile.getTasks().size() > 0) {
					products.add(pFromFile);
				}
				
			}
			catch(IllegalArgumentException i) {
				//for invalid line
			}
		}
	
		lineScanForProduct.close();
		fileReader.close();
		return products;
		
		
	}
	
	/**
	 * method to process a product
	 * @param productAndTasks of product to be processed
	 * @return Product that is processed
	 */	
	private static Product processProduct(String productAndTasks) {
		
		Scanner productScanner = new Scanner(productAndTasks);
		String productName = productScanner.nextLine().trim();
		Product p = new Product(productName);
		productScanner.useDelimiter("\\r?\\n?[*] ");
		
		while(productScanner.hasNext()) {
			try {
				//String taskDetails = productScanner.next();
				Task t = processTask(productScanner.next());
				p.addTask(t);
				
			}
			catch(IllegalArgumentException i) {
				//invalid line or cannot add task
			}
			
		}
		
		productScanner.close();
		return p;
	}
	
	/**
	 * method to process a task for a product
	 * @param taskDetails of task to be processed
	 * @return Task that is processed
	 */	
	private static Task processTask(String taskDetails) {
		
		Scanner taskNotes = new Scanner(taskDetails);
		
		taskNotes.useDelimiter("\\r?\\n?[-] ");
		ArrayList<String> notes = new ArrayList<String>();
		String taskInfo = taskNotes.nextLine();
		Scanner taskInfoBeforeNotes = new Scanner(taskInfo);
		taskInfoBeforeNotes.useDelimiter(","); 
		
		try {
			int id = Integer.parseInt(taskInfoBeforeNotes.next());
			String state = taskInfoBeforeNotes.next(); 
			String title = taskInfoBeforeNotes.next();
			String type = taskInfoBeforeNotes.next();
			String creator = taskInfoBeforeNotes.next();
			String owner = taskInfoBeforeNotes.next();
			String verified = taskInfoBeforeNotes.next();	
			
			while(taskNotes.hasNext()) {
				notes.add(taskNotes.next().trim());
			}
			
			taskNotes.close();
			taskInfoBeforeNotes.close();
			Task t = new Task(id, state, title, type, creator, owner, verified, notes);
			return t;
		}
		catch(NoSuchElementException i) {
			//exception thrown
		}
		catch(IllegalArgumentException e) {
			//exception thrown
		}
		
		return new Task(0, null, null, null, null);
			
		
	}

}
