/**
 * 
 */
package edu.ncsu.csc216.wolf_scheduler.course;

/**
 * Class Interface for dealing with time and day conflicts. Implemented by Activity
 * @author Bagya Maharajan
 *
 */
public interface Conflict {
	/**
	 * Abstract method to check for conflicting activity
	 * @param possibleConflictingActivity is activity passed as a parameter
	 * @throws ConflictException for a conflicting activity
	 */
	void checkConflict(Product possibleConflictingActivity) throws ConflictException;


}
