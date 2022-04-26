/**
 * 
 */
package edu.ncsu.csc216.wolf_scheduler.course;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Test class to check for schedule conflict
 * @author Bagya Maharajan
 *
 */
class ActivityTest {

	/**
	 * Test method for checkConflict() in Activity when there is no conflict.
	 */
	@Test
	public void testCheckConflict() {		
		Product a1 = new Course("CSC 216", "Software Development Fundamentals", "001", 3, "sesmith5", "MW", 1330, 1445);
		Product a2 = new Course("CSC 216", "Software Development Fundamentals", "001", 3, "sesmith5", "TH", 1330, 1445);
		
		assertDoesNotThrow(() -> a1.checkConflict(a2));
	    assertDoesNotThrow(() -> a2.checkConflict(a1));
	}
	
	/**
	 * Test method for checkConflict() in Activity when there is a conflict
	 * Single day conflict
	 */
	@Test
	public void testCheckConflictWithConflict() {
	    Product a1 = new Course("CSC 216", "Software Development Fundamentals", "001", 3, "sesmith5", "MW", 1330, 1445);
	    Product a2 = new Course("CSC 216", "Software Development Fundamentals", "001", 3, "sesmith5", "M", 1330, 1445);
		
	    Exception e1 = assertThrows(ConflictException.class, () -> a1.checkConflict(a2));
	    assertEquals("Schedule conflict.", e1.getMessage());
		
	    Exception e2 = assertThrows(ConflictException.class, () -> a2.checkConflict(a1));
	    assertEquals("Schedule conflict.", e2.getMessage());
	} 		
	
	/**
	 * Test method for checkConflict() in Activity when there is a conflict
	 * Conflict of startTime and endTime for this and possibleConflictingActivity
	 */
	@Test
	public void testCheckConflictEqualEndStartTime() {
		Product a1 = new Course("CSC 216", "Software Development Fundamentals", "001", 3, "sesmith5", "MW", 1330, 1445);
	    Product a2 = new Course("CSC 216", "Software Development Fundamentals", "001", 3, "sesmith5", "M", 1445, 1545);
		
	    Exception e1 = assertThrows(ConflictException.class, () -> a1.checkConflict(a2));
	    assertEquals("Schedule conflict.", e1.getMessage());
		
	    Exception e2 = assertThrows(ConflictException.class, () -> a2.checkConflict(a1));
	    assertEquals("Schedule conflict.", e2.getMessage());
	}
	
	/**
	 * Test method for checkConflict() in Activity when there is a conflict
	 * Conflict when startTime of possibleConflictingACtivity is before endTime of this 
	 */
	@Test
	public void testCheckConflictStartBeforeEnd() {
		Product a1 = new Course("CSC 216", "Software Development Fundamentals", "001", 3, "sesmith5", "MW", 1330, 1445);
	    Product a2 = new Course("CSC 216", "Software Development Fundamentals", "001", 3, "sesmith5", "M", 1440, 1545);
		
	    Exception e1 = assertThrows(ConflictException.class, () -> a1.checkConflict(a2));
	    assertEquals("Schedule conflict.", e1.getMessage());
		
	    Exception e2 = assertThrows(ConflictException.class, () -> a2.checkConflict(a1));
	    assertEquals("Schedule conflict.", e2.getMessage());
	}
	
	/**
	 * Test for arranged courses
	 */
	@Test
	public void testCheckArrangedCourses() {
		Product a1 = new Course("CSC 216", "Software Development Fundamentals", "001", 3, "sesmith5", "A");
	    Product a2 = new Course("CSC 217", "Software Development Fundamentals Lab", "201", 1, "sesmith5", "A");
	    
	    assertDoesNotThrow(() -> a1.checkConflict(a2));
	    assertDoesNotThrow(() -> a2.checkConflict(a1));
	}

}
