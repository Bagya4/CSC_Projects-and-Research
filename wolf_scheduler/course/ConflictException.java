/**
 * 
 */
package edu.ncsu.csc216.wolf_scheduler.course;

/**
 * Class to handle custom checked exception for conflicting courses or events
 * which is Conflict Exception
 * @author Bagya Maharajan
 *
 */
public class ConflictException extends Exception {

	/** ID used for serialization. */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Parameterized constructor for ConflictException
	 * @param message passed to parent constructor 
	 */
	public ConflictException(String message) {
		super(message);
	}
	
	/**
	 * Parameterless constructor for ConflictException
	 */
	public ConflictException() {
		this("Schedule conflict.");
	}

}
