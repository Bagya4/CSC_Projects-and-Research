/**
 * 
 */
package edu.ncsu.csc216.wolf_scheduler.course;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Test class for customized exception ConflictException
 * @author Bagya Maharajan
 *
 */
class ConflictExceptionTest {	
	
	/**
	 * Test method for parameterized constructor of ConflictException
	 */
	@Test
	public void testConflictExceptionString() {
	    ConflictException ce = new ConflictException("Schedule conflict.");
	    assertEquals("Schedule conflict.", ce.getMessage());
	}
	
	/**
	 * Test method for parameterless constructor of ConflictException
	 */
	@Test
	public void testConflictException() {
	    ConflictException ce = new ConflictException();
	    assertEquals("Schedule conflict.", ce.getMessage());
	}	

}
