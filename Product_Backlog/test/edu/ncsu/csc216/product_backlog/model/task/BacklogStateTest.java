/**
 * 
 */
package edu.ncsu.csc216.product_backlog.model.task;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.product_backlog.model.command.Command;
import edu.ncsu.csc216.product_backlog.model.command.Command.CommandValue;
import edu.ncsu.csc216.product_backlog.model.task.Task.Type;

/**
 * test class for BacklogState
 * @author Bagya Maharajan
 *
 */
public class BacklogStateTest {
	
	/** constant for ID */	
	private static final int ID = 4;
	
	/** Constant string for Backlog state*/
	private static final String BACKLOG_NAME = "Backlog";
	/**title of task */	
	private static final String TITLE = "Simulate cart";
	
	/** constant for creator */	
	private static final String CREATOR = "mscott";

	/** constant for a note in backlog state */	
	private static final String NOTE = "[Backlog] action needed";
	
	/** enumeration value for type feature */
	private static final Type FEATURE = Type.FEATURE;
	
	/** Constant string for a task not owned*/
	private static final String UNOWNED = "unowned";
	
	/**
	 * tests updating state and getting state name
	 */	
	@Test
	public void testUpdateState() {
		Task t1 = assertDoesNotThrow(
				() -> new Task(ID, TITLE, FEATURE, CREATOR, NOTE),
				"Should not throw exception");
		
		assertAll("Task", 
				() -> assertEquals(ID, t1.getTaskId(), "correct ID"), 
				() -> assertEquals(TITLE, t1.getTitle(), "correct TITLE"),
				() -> assertEquals(FEATURE, t1.getType(), "correct type"),
				() -> assertEquals(CREATOR, t1.getCreator(), "correct creator"),
				() -> assertEquals(BACKLOG_NAME, t1.getStateName(), "correct state"),
				() -> assertEquals(UNOWNED, t1.getOwner(), "correct owner")); 
		
		Command c1 = new Command(CommandValue.CLAIM, "andyNard", "note");
		assertDoesNotThrow(() -> t1.update(c1));
	}
	
	/**
	 * testing backlog state
	 */	
	@Test
	public void testBacklogState1() {
		Task t1 = assertDoesNotThrow(
				() -> new Task(ID, TITLE, FEATURE, CREATOR, NOTE),
				"Should not throw exception");
		
		assertAll("Task", 
				() -> assertEquals(ID, t1.getTaskId(), "correct ID"), 
				() -> assertEquals(TITLE, t1.getTitle(), "correct TITLE"),
				() -> assertEquals(FEATURE, t1.getType(), "correct type"),
				() -> assertEquals(CREATOR, t1.getCreator(), "correct creator"),
				() -> assertEquals(BACKLOG_NAME, t1.getStateName(), "correct state"),
				() -> assertEquals(UNOWNED, t1.getOwner(), "correct owner")); 
		
		Command c1 = new Command(CommandValue.REJECT, null, "note");
		assertDoesNotThrow(() -> t1.update(c1));
		assertEquals(UNOWNED, t1.getOwner());
	}

}
