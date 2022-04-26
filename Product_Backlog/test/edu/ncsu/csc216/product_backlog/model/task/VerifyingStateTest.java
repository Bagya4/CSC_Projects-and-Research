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
 * test class for VerifyingState
 * @author Bagya Maharajan
 */
public class VerifyingStateTest {
	
	/** constant for ID */	
	private static final int ID = 4;
	

	/** Constant string for PROCESSING state*/
	private static final String PROCESSING_NAME = "Processing";
	/** Constant string for VERIFYING state*/
	private static final String VERIFYING_NAME = "Verifying";
	/** Constant string for DONE state*/
	private static final String DONE_NAME = "Done";

	
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
				() -> new Task(ID, VERIFYING_NAME, TITLE, T_BUG, CREATOR, OWNER, VERIFIED_TRUE, NOTES),
				"Should not throw exception");
		
		assertAll("Task", 
				() -> assertEquals(ID, t1.getTaskId(), "correct ID"), 
				() -> assertEquals(TITLE, t1.getTitle(), "correct TITLE"),
				() -> assertEquals(BUG, t1.getType(), "correct type"),
				() -> assertEquals(CREATOR, t1.getCreator(), "correct creator"),
				() -> assertEquals(VERIFYING_NAME, t1.getStateName(), "correct state"),
				() -> assertEquals(T_BUG, t1.getTypeShortName(), "correct type"),
				() -> assertTrue(t1.isVerified(), "correct verification"),
				() -> assertEquals(OWNER, t1.getOwner(), "correct owner")); 
		
		Command c2 = new Command(CommandValue.VERIFY, null, "note");
		try {
			t1.update(c2);
		}
		catch(UnsupportedOperationException e) {
			assertEquals("Invalid transition.", e.getMessage());
		}
	}
	
	/**
	 * testing verifying state FOR CV.PROCESS
	 */
	@Test
	public void testVerifyingState() {
		Task t1 = assertDoesNotThrow(
				() -> new Task(ID, VERIFYING_NAME, TITLE, T_BUG, CREATOR, OWNER, VERIFIED_TRUE, NOTES),
				"Should not throw exception");
		
		assertAll("Task", 
				() -> assertEquals(ID, t1.getTaskId(), "correct ID"), 
				() -> assertEquals(TITLE, t1.getTitle(), "correct TITLE"),
				() -> assertEquals(BUG, t1.getType(), "correct type"),
				() -> assertEquals(CREATOR, t1.getCreator(), "correct creator"),
				() -> assertEquals(VERIFYING_NAME, t1.getStateName(), "correct state"),
				() -> assertEquals(T_BUG, t1.getTypeShortName(), "correct type"),
				() -> assertTrue(t1.isVerified(), "correct verification"),
				() -> assertEquals(OWNER, t1.getOwner(), "correct owner")); 
		
		
		Command c2 = new Command(CommandValue.PROCESS, null, "note");
		assertDoesNotThrow(() -> t1.update(c2));
		assertEquals(PROCESSING_NAME, t1.getStateName());		
	}
	
	/**
	 * testing verifying state FOR CV.PROCESS
	 */
	@Test
	public void testVerifyingState2() {
		Task t1 = assertDoesNotThrow(
				() -> new Task(ID, VERIFYING_NAME, TITLE, T_BUG, CREATOR, OWNER, VERIFIED_TRUE, NOTES),
				"Should not throw exception");
		
		assertAll("Task", 
				() -> assertEquals(ID, t1.getTaskId(), "correct ID"), 
				() -> assertEquals(TITLE, t1.getTitle(), "correct TITLE"),
				() -> assertEquals(BUG, t1.getType(), "correct type"),
				() -> assertEquals(CREATOR, t1.getCreator(), "correct creator"),
				() -> assertEquals(VERIFYING_NAME, t1.getStateName(), "correct state"),
				() -> assertEquals(T_BUG, t1.getTypeShortName(), "correct type"),
				() -> assertTrue(t1.isVerified(), "correct verification"),
				() -> assertEquals(OWNER, t1.getOwner(), "correct owner")); 
		
		
		Command c2 = new Command(CommandValue.COMPLETE, null, "note");
		assertDoesNotThrow(() -> t1.update(c2));
		assertEquals(DONE_NAME, t1.getStateName());	
		assertTrue(t1.isVerified());
	}
	
	

}
