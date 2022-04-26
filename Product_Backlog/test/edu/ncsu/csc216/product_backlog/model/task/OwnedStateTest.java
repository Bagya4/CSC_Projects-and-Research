/**
 * 
 */
package edu.ncsu.csc216.product_backlog.model.task;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.product_backlog.model.command.Command;
import edu.ncsu.csc216.product_backlog.model.command.Command.CommandValue;
import edu.ncsu.csc216.product_backlog.model.task.Task.Type;

/**
 * test class for OwnedState
 * @author Bagya Maharajan
 */
public class OwnedStateTest {
	
	/** constant for ID */	
	private static final int ID = 4;
	

	/** Constant string for Owned state*/
	private static final String OWNED_NAME = "Owned";
	/** Constant string for PROCESSING state*/
	private static final String PROCESSING_NAME = "Processing";

	/** Constant string for REJECTED state*/
	private static final String REJECTED_NAME = "Rejected";
	
	/**title of task */	
	private static final String TITLE = "Simulate cart";
	
	/** Constant string for bug type- short name*/
	private static final String T_BUG = "B";

	
	/** constant for creator */	
	private static final String CREATOR = "mscott";
	/** constant for owner */	
	private static final String OWNER = "jhalper";
	/** constant for verified true*/	
	private static final String VERIFIED_TRUE = "true";

	/** constant for notes list */	
	private static final ArrayList<String> NOTES = new ArrayList<String>(Arrays.asList("test note"));

	/** enumeration value for type BUG */
	private static final Type BUG = Type.BUG;
	
	/**
	 * tests updating state and getting state name
	 */	
	@Test
	public void testUpdateState() {
		Task t1 = assertDoesNotThrow(
				() -> new Task(ID, OWNED_NAME, TITLE, T_BUG, CREATOR, OWNER, VERIFIED_TRUE, NOTES),
				"Should not throw exception");
		
		assertAll("Task", 
				() -> assertEquals(ID, t1.getTaskId(), "correct ID"), 
				() -> assertEquals(TITLE, t1.getTitle(), "correct TITLE"),
				() -> assertEquals(BUG, t1.getType(), "correct type"),
				() -> assertEquals(CREATOR, t1.getCreator(), "correct creator"),
				() -> assertEquals(OWNED_NAME, t1.getStateName(), "correct state"),
				() -> assertEquals(T_BUG, t1.getTypeShortName(), "correct type"),
				() -> assertTrue(t1.isVerified(), "correct verification"),
				() -> assertEquals(OWNER, t1.getOwner(), "correct owner")); 
		
		Command c2 = new Command(CommandValue.BACKLOG, null, "note");
		assertDoesNotThrow(() -> t1.update(c2));
	}
	
	/**
	 * test for owned state	
	 */
	@Test
	public void testOwnedState1() {
		Task t1 = assertDoesNotThrow(
				() -> new Task(ID, OWNED_NAME, TITLE, T_BUG, CREATOR, OWNER, VERIFIED_TRUE, NOTES),
				"Should not throw exception");
		
		assertAll("Task", 
				() -> assertEquals(ID, t1.getTaskId(), "correct ID"), 
				() -> assertEquals(TITLE, t1.getTitle(), "correct TITLE"),
				() -> assertEquals(BUG, t1.getType(), "correct type"),
				() -> assertEquals(CREATOR, t1.getCreator(), "correct creator"),
				() -> assertEquals(OWNED_NAME, t1.getStateName(), "correct state"),
				() -> assertEquals(T_BUG, t1.getTypeShortName(), "correct type"),
				() -> assertTrue(t1.isVerified(), "correct verification"),
				() -> assertEquals(OWNER, t1.getOwner(), "correct owner")); 
		
		Command c2 = new Command(CommandValue.PROCESS, null, "note");
		assertDoesNotThrow(() -> t1.update(c2));
		assertEquals(PROCESSING_NAME, t1.getStateName());
		
	}
	
	/**
	 * test for owned state for a command value reject
	 */
	@Test
	public void testOwnedState2() {
		Task t1 = assertDoesNotThrow(
				() -> new Task(ID, OWNED_NAME, TITLE, T_BUG, CREATOR, OWNER, VERIFIED_TRUE, NOTES),
				"Should not throw exception");
		
		assertAll("Task", 
				() -> assertEquals(ID, t1.getTaskId(), "correct ID"), 
				() -> assertEquals(TITLE, t1.getTitle(), "correct TITLE"),
				() -> assertEquals(BUG, t1.getType(), "correct type"),
				() -> assertEquals(CREATOR, t1.getCreator(), "correct creator"),
				() -> assertEquals(OWNED_NAME, t1.getStateName(), "correct state"),
				() -> assertEquals(T_BUG, t1.getTypeShortName(), "correct type"),
				() -> assertTrue(t1.isVerified(), "correct verification"),
				() -> assertEquals(OWNER, t1.getOwner(), "correct owner")); 
		
		Command c2 = new Command(CommandValue.REJECT, null, "note");
		assertDoesNotThrow(() -> t1.update(c2));
		assertEquals(REJECTED_NAME, t1.getStateName());
		
	}

}
