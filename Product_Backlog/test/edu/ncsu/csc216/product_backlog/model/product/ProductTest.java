package edu.ncsu.csc216.product_backlog.model.product;


import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.product_backlog.model.task.Task.Type;

/**
 * test class for Product
 * @author Bagya Maharajan
 */

public class ProductTest {
	
	/** constant for product name*/	
	private static final String PRODUCT_NAME = "Online shopping";

	/**title of task */	
	private static final String TITLE = "Simulate cart";
	/** enumeration value for type feature */
	private static final Type FEATURE = Type.FEATURE;
	/** constant for creator */	
	private static final String CREATOR = "mscott";
	/** constant for a note in backlog state */	
	private static final String NOTE = "[Backlog] action needed";

	
	/**
	 * testing constructor	
	 */
	@Test
	public void testProductConstructor() {
		Product p1 = assertDoesNotThrow(
				() -> new Product(PRODUCT_NAME),
				"Should not throw exception");
		
		assertAll("Task", 
				() -> assertEquals(PRODUCT_NAME, p1.getProductName(), "correct product name"), 
				() -> assertEquals(0, p1.getTasks().size(), "empty task list"));
		
		//adding product with invalid name
		try {
			Product p2 = new Product(null);
			assertNotEquals(PRODUCT_NAME, p2.getProductName());
			fail();
		}
		catch(IllegalArgumentException i) {
			assertEquals(PRODUCT_NAME, p1.getProductName(), "unchanged p1 name");
			assertEquals("Invalid product name.", i.getMessage());
		}

	}
	
	/**
	 * method to test adding tasks to products
	 * also tests setters and getters, deleting or editing tasks
	 */	
	@Test
	public void testAddingTasksToProduct() {
		Product p1 = assertDoesNotThrow(
				() -> new Product(PRODUCT_NAME),
				"Should not throw exception");
		
		assertDoesNotThrow(
				() -> p1.addTask(TITLE, FEATURE, CREATOR, NOTE),
				"Should not throw exception");
		
		assertEquals(1, p1.getTasks().size());
		assertEquals(1, p1.getTasks().get(0).getTaskId());
		
		assertEquals(TITLE, p1.getTasks().get(0).getTitle());
		
		assertDoesNotThrow(
				() -> p1.addTask("Show prices", FEATURE, CREATOR, NOTE),
				"Should not throw exception");
		
		assertEquals(2, p1.getTasks().size());
		assertEquals(2, p1.getTasks().get(1).getTaskId()); 
		
		
		
	}

}
