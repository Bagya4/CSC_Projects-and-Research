package edu.ncsu.csc216.product_backlog.model.backlog;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

//import java.io.File;
//import java.io.IOException;
//import java.util.Scanner;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.product_backlog.model.command.Command;
import edu.ncsu.csc216.product_backlog.model.command.Command.CommandValue;
import edu.ncsu.csc216.product_backlog.model.task.Task.Type;
/**
 * test class for BacklogManager
 * @author Bagya Maharajan
 *
 */
public class BacklogManagerTest {
	
	/** constant for product name*/	
	private static final String PRODUCT_NAME = "Online shopping";	
	
	/** enumeration value for type feature */
	private static final Type FEATURE = Type.FEATURE;
	
	/**
	 * test to add, edit and delete products with and without using Id
	 * includes getting product list and product name and resetManager()
	 */	
	@Test
	public void testAddDeleteClearProduct() {
		BacklogManager bm = BacklogManager.getInstance();
		bm.resetManager();
	
		assertDoesNotThrow(
				() -> bm.addProduct(PRODUCT_NAME),
				"Should not throw exception");
		
		assertAll("Product",
				() -> assertEquals(PRODUCT_NAME, bm.getProductName(), "correct current product name")); 
		
		bm.editProduct("Amazon shopping");
		assertEquals("Amazon shopping", bm.getProductName(), "correct current product name after editing");
		
		assertDoesNotThrow(
				() -> bm.addProduct("Video games"),
				"Should not throw exception");
		
		assertEquals(2, bm.getProductList().length, "2 products added to list");
		
		bm.deleteProduct();
		assertEquals(1, bm.getProductList().length, "1 out of 2 products removed");
		
		bm.clearProducts();
		assertEquals(0, bm.getProductList().length, "cleared products list");
		
		bm.resetManager();
	}
	
	/**
	 * test to add tasks to product
	 */	
	@Test
	public void testAddTaskToProduct() {
		
		BacklogManager bm = BacklogManager.getInstance();
		bm.resetManager();
	
		assertDoesNotThrow(
				() -> bm.addProduct(PRODUCT_NAME),
				"Should not throw exception");
		
		assertAll("Product",
				() -> assertEquals(PRODUCT_NAME, bm.getProductName(), "correct current product name")); 
		
		//adding task		
		assertDoesNotThrow(
				() -> bm.addTaskToProduct("title", FEATURE, "theCreator", "some note"),
				"Should not throw exception");	
		
		assertDoesNotThrow(
				() -> bm.deleteTaskById(1),
				"Should not throw exception");		
		
	}
	
	
	/**
	 * testing product names which 
	 */
	@Test
	public void testAddingInvalidProduct() {
		BacklogManager bm = BacklogManager.getInstance();
		bm.resetManager();
		try {
			bm.addProduct(null);
			fail();
		}
		catch(IllegalArgumentException e ) {
			assertEquals("Invalid product name.", e.getMessage());
		}
	}
	
	/**
	 * test for duplicate product name
	 */	
	@Test
	public void testisDuplicateProduct() {
		BacklogManager bm = BacklogManager.getInstance();	
		bm.resetManager();
		assertDoesNotThrow(
				() -> bm.addProduct(PRODUCT_NAME),
				"Should not throw exception");
		
		assertAll("Product", 
				() -> assertEquals(PRODUCT_NAME, bm.getProductName(), "correct current product name")); 
		
		try {
			bm.addProduct(PRODUCT_NAME);
			fail();
		}
		catch(IllegalArgumentException e) {
			assertEquals("Invalid product name.", e.getMessage());
						
		}
		
		bm.resetManager();
	}
	
	/**
	 * test to get product list as string array
	 */	
	@Test
	public void testGetProductList() {
		BacklogManager bm = BacklogManager.getInstance();
		bm.resetManager();
		assertDoesNotThrow(
				() -> bm.addProduct(PRODUCT_NAME),
				"Should not throw exception");
		
		assertAll("Product", 
				() -> assertEquals(PRODUCT_NAME, bm.getProductName(), "correct current product name"),
				() -> assertEquals(1, bm.getProductList().length, "product list length")); 
		
		
		
		bm.resetManager();
	}
	
	/**
	 * Test BM.saveToFile()
	 */
	@Test
	public void testSaveToFile() {
		//Test that empty products file is saved correctly
		BacklogManager bm = BacklogManager.getInstance();
		bm.resetManager();
		
		try {
			bm.saveToFile("test-files/empty_tasks.txt");
			fail();
		}
		catch(IllegalArgumentException i) {
			assertEquals("Unable to save file.", i.getMessage());
		}

		
		bm.clearProducts();
		//Add courses and test that exports correctly
		bm.addProduct("Shopping Cart Simulation");
		assertEquals(1, bm.getProductList().length);
		
		//trying to add product without any tasks		
		try {
			bm.saveToFile("test-files/saved_tasks_file");
		}
		catch(IllegalArgumentException i) {
			assertEquals("Unable to save file.", i.getMessage());
		}
		
		bm.addTaskToProduct("title", FEATURE, "God", "note");
		bm.saveToFile("test-files/saved_tasks_file");
		
		bm.resetManager();
	}
	
	/**
	 * test to load from file
	 */	
	@Test
	public void testLoadFile() {
		BacklogManager bm2 = BacklogManager.getInstance();
		bm2.resetManager();
		
		bm2.loadFromFile("test-files/tasks1.txt");
		assertEquals(2, bm2.getProductList().length);
		bm2.deleteProduct();
		assertEquals("WolfScheduler", bm2.getProductName());
		assertEquals("Add Event", bm2.getTaskById(5).getTitle());
		
		String[][] array = bm2.getTasksAsArray();
		assertEquals("2", array[0][0]);
		assertEquals("Rejected", array[0][1]);
		
		Command c = new Command(CommandValue.REJECT, null, "note1");		
		bm2.executeCommand(5, c);
		assertEquals("Rejected", bm2.getTaskById(5).getStateName());
		bm2.resetManager();
	}
}
