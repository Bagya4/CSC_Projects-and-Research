package edu.ncsu.csc216.product_backlog.model.io;

import static org.junit.Assert.fail;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.product_backlog.model.product.Product;

/**
 * test class for ProductsWriter
 * @author Bagya Maharajan
 *
 */

public class ProductsWriterTest {
	
	
	/**
	 * Tests writeProductsToFile()
	 */
	@Test
	public void testWriteProductsRecords() {
		ArrayList<Product> products = new ArrayList<Product>();
		products.add(new Product("PackScheduler"));
		products.add(new Product("WolfScheduler"));
		
		try {
			ProductsWriter.writeProductsToFile("test-files/saved_tasks_file.txt", products);
		} catch (IllegalArgumentException e) {
			fail("Cannot write to product records file");
		}
	}

}
