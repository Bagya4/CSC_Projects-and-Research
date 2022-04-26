/**
 * 
 */
package edu.ncsu.csc216.product_backlog.model.backlog;



//import java.io.FileNotFoundException;
import java.util.ArrayList;

import edu.ncsu.csc216.product_backlog.model.command.Command;
import edu.ncsu.csc216.product_backlog.model.io.ProductsReader;
import edu.ncsu.csc216.product_backlog.model.io.ProductsWriter;
import edu.ncsu.csc216.product_backlog.model.product.Product;
import edu.ncsu.csc216.product_backlog.model.task.Task;
import edu.ncsu.csc216.product_backlog.model.task.Task.Type;

/**
 * This class implements the Singleton pattern and is for maintaining the backlog of tasks
 * @author Bagya Maharajan
 *
 */
public class BacklogManager {
	
	/** variable to list of products*/
	private ArrayList<Product> products;
	/** variable to hold current product*/
	private Product currentProduct;
	/** variable to hold singleton variable*/
	private static BacklogManager singleton;
	
	/**
	 * Constructor of class
	 */	
	private BacklogManager() {
		products = new ArrayList<Product>();
		currentProduct = null;
	}
	
	/**
	 * method to get instance of BacklogManager. If singleton is null, an instance is created through constructor 
	 * @return BacklogManager instance
	 */	
	public static BacklogManager getInstance() {
		
		if(singleton == null) {
			singleton = new BacklogManager();
			return singleton;
		}
		
		return singleton;
	}
	
	/**
	 * writes Products to file using ProductWriter class
	 * @param fileName to save details to
	 * @throws IllegalArgumentException when no Task in current Product or product is null
	 */	
	public void saveToFile(String fileName) {
		
		if(currentProduct == null)
			throw new IllegalArgumentException("Unable to save file.");
		
		
		if(currentProduct == null || currentProduct.getTasks().size() == 0)
			throw new IllegalArgumentException("Unable to save file.");
		
		try {
			ProductsWriter.writeProductsToFile(fileName, products);
		} 
		catch(IllegalArgumentException f) {
			throw new IllegalArgumentException("The file cannot be saved.");
		}
	}
	
	/**
	 * uses ProductReader to read fileName and first product returned from ProductsReader is made currentProduct
	 * @param fileName of file to read in from
	 */
	public void loadFromFile(String fileName) {
		
		try {
			ArrayList<Product> productsToBeAdded = ProductsReader.readProductsFile(fileName);
			for(int i = 0; i < productsToBeAdded.size(); i++) {
				products.add(productsToBeAdded.get(i));
			}
			
						
			currentProduct = products.get(0);
		}
		catch(IllegalArgumentException e) {
			throw new IllegalArgumentException("Unable to load file.");
		}
		
	}
	
	/**
	 * finds product with given name which becomes current product	
	 * @param productName of product
	 * @throws IllegalArgumentException product name does not exist in list
	 */
	public void loadProduct(String productName) {
		
		int count = 0;
		
		
		for(int i = 0; i <  products.size(); i++) {
			String existingProdName = products.get(i).getProductName();
			if(productName.equals(existingProdName)) {
				currentProduct = products.get(i);
				count++;
				break;
			}
				
		}
		
		if(count == 0)
			throw new IllegalArgumentException("Product not available.");
			
	}
	
	/**
	 * checks if duplicate product exists with same name 
	 * checks for null or empty string names too
	 * @param productName of product
	 */
	private void isDuplicateProduct(String productName) {
		
		for(int i = 0; i < products.size(); i++) {
			if(productName.equalsIgnoreCase(products.get(i).getProductName())) {
				throw new IllegalArgumentException();
			}
				
		}
		
		
	}
	
	/**
	 * method to get tasks in a 2D string array	
	 * @return String[][] of tasks
	 */
	public String[][] getTasksAsArray() {
		
		if(currentProduct == null)
			return null;
		
		String[][] arrayOfTasks = new String[currentProduct.getTasks().size()][4];
		ArrayList<Task> tasksForProduct = currentProduct.getTasks();
		
		for(int i = 0; i < tasksForProduct.size(); i++) {
			Task t = tasksForProduct.get(i);
			arrayOfTasks[i] = getTableForDisplay(t);			
		}
		
		return arrayOfTasks; 
	}
	
	private String[] getTableForDisplay(Task t) {
		String[] taskDetails = new String[4];
		taskDetails[0] = "" + t.getTaskId();
		taskDetails[1] = "" + t.getStateName();
		taskDetails[2] = "" + t.getTypeLongName();
		taskDetails[3] = "" + t.getTitle();
		return taskDetails;
	}
	
	/**
	 * method to get task by using task ID
	 * @param taskId of task
	 * @return Task for that Id
	 */	
	public Task getTaskById(int taskId) {
		
		if(!(currentProduct == null)) {
			return currentProduct.getTaskById(taskId);
		}
		
		return null;
		
		
	}
	
	/**
	 * method to execute command	
	 * @param id of task
	 * @param c is command passed
	 */
	public void executeCommand(int id, Command c) {
		if(!(currentProduct == null)) {
			currentProduct.executeCommand(id, c);
		}
	}
	
	/**
	 * Method to delete task by its ID
	 * @param taskId of task
	 */	
	public void deleteTaskById(int taskId) {
		if(!(currentProduct == null)) {
			currentProduct.deleteTaskById(taskId);
		}
	}
	
	/**
	 * method add task to a product
	 * @param title of task
	 * @param type of task
	 * @param creator of task
	 * @param note for task
	 */
	public void addTaskToProduct(String title, Type type, String creator, String note) {
		if(!(currentProduct == null)) {
			currentProduct.addTask(title, type, creator, note);
		}
		
	}
	
	/**
	 * returns current product's name
	 * @return String product name
	 */	
	public String getProductName() {
		
		if(currentProduct == null)
			return null;
		
		return currentProduct.getProductName();
	}
	
	/**
	 * method to return array of product names in the order they are in, in products list
	 * @return String[] of products
	 */	
	public String[] getProductList() {
		
		String[] productsList = {};
		
		if(!(currentProduct == null)) {
			productsList = new String[products.size()];
			
			for(int  i = 0; i < products.size(); i++) {
				productsList[i] = products.get(i).getProductName();
			}
		}
		
		return productsList;
	}
	
	/**
	 * resets products to an empty list and current product is set to null
	 */
	public void clearProducts() {
		products = new ArrayList<Product>();
		currentProduct = null;
	}
	
	/**
	 * updates current product name to the new one	
	 * name does not change if new name passed is null, empty, or duplicate
	 * @param updateName for the product whose old name is to be changed
	 * @throws IllegalArgumentException if current product is null when attempting to edit
	 */
	public void editProduct(String updateName) {
		isDuplicateProduct(updateName);
		
		if(currentProduct == null)
			throw new IllegalArgumentException("No product selected.");
		
		currentProduct.setProductName(updateName);
	}
	
	/**
	 * creates new product which is added to end of product list. It is then loaded to as current product 
	 * by calling loadProduct()
	 * @param productName of product to be added
	 * @throws IllegalArgumentException if name is null, empty or duplicate- case insensitive
	 */	
	public void addProduct(String productName) {
		
		if(productName == null || "".equals(productName))
			throw new IllegalArgumentException("Invalid product name.");
		
		try {
			isDuplicateProduct(productName);	
		}
		catch(IllegalArgumentException i) {
			throw new IllegalArgumentException("Invalid product name.");
		}
				
		Product p = new Product(productName);
		products.add(p);
		loadProduct(productName);
	}
	
	/**
	 * deletes current product and new current product is updated to product at index 0 of list 
	 * or null if none are left. 
	 * @throws IllegalArgumentException if current product is null when attempting to delete 
	 */	
	public void deleteProduct() {
		if(currentProduct == null)
			throw new IllegalArgumentException("No product selected.");	
		
		else {
			products.remove(currentProduct);
			if(products.size() == 0)
				currentProduct = null;
			else
				currentProduct = products.get(0);
		}
		
		
	}
	
	/**
	 * protected to restrict where it can be called. It is mainly for testing the entire class
	 * singleton is set to null
	 */	
	protected void resetManager() {
		singleton = null;
	}

}
