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
 * test class for RejectedState
 * @author Bagya Maharajan
 */
public class RejectedStateTest {
	
	/** constant for ID */	
	private static final int ID = 4;	

	/** Constant string for REJECTED state*/
	private static final String REJECTED_NAME = "Rejected";
	
	/**title of task */	
	private static final String TITLE = "Simulate cart";
	
	/** Constant string for technical work type- short name*/
	private static final String T_TECHNICAL_WORK = "TW";
	
	/** constant for creator */	
	private static final String CREATOR = "mscott";

	/** constant for verified true*/	
	private static final String VERIFIED_TRUE = "true";

	/** constant for notes list */	
	private static final ArrayList<String> NOTES = new ArrayList<String>(Arrays.asList("test note"));

	/** enumeration value for type TECHNICAL_WORK */
	private static final Type TECHNICAL_WORK = Type.TECHNICAL_WORK;

	
	/** Constant string for a task not owned*/
	private static final String UNOWNED = "unowned";
	
	/**
	 * tests updating state and getting state name
	 */	
	@Test
	public void testUpdateState() {
		Task t3 = assertDoesNotThrow(
				() -> new Task(ID, REJECTED_NAME, TITLE, T_TECHNICAL_WORK, CREATOR, UNOWNED, VERIFIED_TRUE, NOTES),
				"Should not throw exception");
		
		assertAll("Task", 
				() -> assertEquals(ID, t3.getTaskId(), "correct ID"), 
				() -> assertEquals(TITLE, t3.getTitle(), "correct TITLE"),
				() -> assertEquals(TECHNICAL_WORK, t3.getType(), "correct type"),
				() -> assertEquals(CREATOR, t3.getCreator(), "correct creator"),
				() -> assertEquals(REJECTED_NAME, t3.getStateName(), "correct state"),
				() -> assertEquals(T_TECHNICAL_WORK, t3.getTypeShortName(), "correct type"),
				() -> assertTrue(t3.isVerified(), "correct verification"),
				() -> assertEquals(UNOWNED, t3.getOwner(), "correct owner")); 
		
		Command c4 = new Command(CommandValue.BACKLOG, null, "note");
		assertDoesNotThrow(() -> t3.update(c4)); 
	}

}
