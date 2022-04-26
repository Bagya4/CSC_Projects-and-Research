package edu.ncsu.csc216.product_backlog.model.io;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.product_backlog.model.product.Product;


/**
 * test class for ProductsReader
 * @author Bagya Maharajan
 *
 */

public class ProductsReaderTest {
	
	/** Valid task file with 2 products */
	private final String validTestFile = "test-files/tasks1.txt";
	/** Invalid task file with missing product name */
	private final String invalidTestFile = "test-files/tasks3.txt";
	
	/**
	 * Resets tasks2.txt for use in other tests.
	 */
	@Before
	public void setUp() throws Exception {
		//Reset course_records.txt so that it's fine for other needed tests
		Path sourcePath = FileSystems.getDefault().getPath("test-files", "starter_tasks2.txt");
		Path destinationPath = FileSystems.getDefault().getPath("test-files", "tasks2.txt");
		try {
			Files.deleteIfExists(destinationPath);
			Files.copy(sourcePath, destinationPath);
		} catch (IOException e) {
			fail("Unable to reset files");
		}
	}	
	
	/**
	 * testing if products file is read correctly
	 */
	@Test
	public void testReadValidProductsFile() {
		try {
			ArrayList<Product> products = ProductsReader.readProductsFile(validTestFile);
			assertEquals(2, products.size());
			
		} catch (IllegalArgumentException e) {
			fail();
		}
	}
	
	/**
	 * Tests readInvalidProductFile().
	 */
	@Test
	public void testReadInvalidProductsFile() {
		ArrayList<Product> products;
		try {
			products = ProductsReader.readProductsFile(invalidTestFile);
			assertEquals(0, products.size());
			
		} catch (IllegalArgumentException e) {
			//handled in ProductReader
		}
	}
	
	/**
	 * Tests readValidProductFile().
	 */
	@Test
	public void testReadValidProductsFile2() {
		ArrayList<Product> products = new ArrayList<Product>();
		try {
			products = ProductsReader.readProductsFile("test-files/tasks25.txt");
			ProductsWriter.writeProductsToFile("test-files/saved_tasks_file.txt", products);
			assertEquals(0, products.size());
			
		} catch (IllegalArgumentException e) {
			//handled in ProductReader
		}
	}

}
