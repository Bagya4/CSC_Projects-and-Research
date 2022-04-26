/**
 * 
 */
package edu.ncsu.csc216.product_backlog.model.task;

//import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.product_backlog.model.command.Command;
import edu.ncsu.csc216.product_backlog.model.command.Command.CommandValue;
import edu.ncsu.csc216.product_backlog.model.task.Task.Type;

/**
 * test class for Task
 * @author Bagya Maharajan
 */
public class TaskTest {
	
	/** constant for ID */	
	private static final int ID = 4;
	
	/** Constant string for Backlog state*/
	private static final String BACKLOG_NAME = "Backlog";
	/** Constant string for Owned state*/
	private static final String OWNED_NAME = "Owned";
	/** Constant string for PROCESSING state*/
	private static final String PROCESSING_NAME = "Processing";
	/** Constant string for VERIFYING state*/
	private static final String VERIFYING_NAME = "Verifying";
	/** Constant string for DONE state*/
	private static final String DONE_NAME = "Done";
	/** Constant string for REJECTED state*/
	private static final String REJECTED_NAME = "Rejected";
	
	/**title of task */	
	private static final String TITLE = "Simulate cart";
	
	/** Constant string for bug type- short name*/
	private static final String T_BUG = "B";
	/** Constant string for technical work type- short name*/
	private static final String T_TECHNICAL_WORK = "TW";
	/** Constant string for Knowledge Acquisition type- short name*/
	private static final String T_KNOWLEDGE_ACQUISITION = "KA";
	
	/** constant for creator */	
	private static final String CREATOR = "mscott";
	/** constant for owner */	
	private static final String OWNER = "jhalper";
	/** constant for verified true*/	
	private static final String VERIFIED_TRUE = "true";

	/** constant for notes list */	
	private static final ArrayList<String> NOTES = new ArrayList<String>(Arrays.asList("test note"));
	/** constant for a note in backlog state */	
	private static final String NOTE = "[Backlog] action needed";
	
	/** enumeration value for type feature */
	private static final Type FEATURE = Type.FEATURE;
	/** enumeration value for type BUG */
	private static final Type BUG = Type.BUG;
	/** enumeration value for type TECHNICAL_WORK */
	private static final Type TECHNICAL_WORK = Type.TECHNICAL_WORK;
	/** enumeration value for type KNOWLEDGE_ACQUISITION */
	private static final Type KNOWLEDGE_ACQUISITION = Type.KNOWLEDGE_ACQUISITION;
	
	/** Constant string for a task not owned*/
	private static final String UNOWNED = "unowned";
	
	
	
	
	/**
	 * testing Task constructor with 5 parameters
	 */
	@Test
	public void testShortTaskConstrcutor() {
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
	 * test Task Constructor with all fields
	 */
	@Test
	public void testLongTaskConstrcutor() {
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
		
		Task t2 = assertDoesNotThrow(
				() -> new Task(ID, PROCESSING_NAME, TITLE, T_TECHNICAL_WORK, CREATOR, OWNER, VERIFIED_TRUE, NOTES),
				"Should not throw exception");
		
		assertAll("Task", 
				() -> assertEquals(ID, t2.getTaskId(), "correct ID"), 
				() -> assertEquals(TITLE, t2.getTitle(), "correct TITLE"),
				() -> assertEquals(TECHNICAL_WORK, t2.getType(), "correct type"),
				() -> assertEquals(CREATOR, t2.getCreator(), "correct creator"),
				() -> assertEquals(PROCESSING_NAME, t2.getStateName(), "correct state"),
				() -> assertEquals(T_TECHNICAL_WORK, t2.getTypeShortName(), "correct type"),
				() -> assertTrue(t2.isVerified(), "correct verification"),
				() -> assertEquals(OWNER, t2.getOwner(), "correct owner")); 
		
		Command c3 = new Command(CommandValue.BACKLOG, null, "note");
		assertDoesNotThrow(() -> t2.update(c3)); 		
	}
	
	/**
	 * test to update command for different states
	 */	
	@Test
	public void testUpdateCommand() {
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
	
	/**
	 * testing invalid task with null note
	 */	
	@Test
	public void testInvalidTaskConstruction() {
		try {
			Task t1 = new Task(ID, TITLE, FEATURE, CREATOR, null);
			assertNotEquals(ID, t1.getTaskId());
			fail();
		}
		catch(IllegalArgumentException i) {
			
			assertEquals("Invalid task information.", i.getMessage());
		}
		
	}
	
	/**
	 * testing backlog state
	 */	
	@Test
	public void testBacklogState() {
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
	
	/**
	 * testing processing state	
	 */
	@Test
	public void testProcessingState() {
		Task t2 = assertDoesNotThrow(
				() -> new Task(ID, PROCESSING_NAME, TITLE, T_TECHNICAL_WORK, CREATOR, OWNER, VERIFIED_TRUE, NOTES),
				"Should not throw exception");
		
		assertAll("Task", 
				() -> assertEquals(ID, t2.getTaskId(), "correct ID"), 
				() -> assertEquals(TITLE, t2.getTitle(), "correct TITLE"),
				() -> assertEquals(TECHNICAL_WORK, t2.getType(), "correct type"),
				() -> assertEquals(CREATOR, t2.getCreator(), "correct creator"),
				() -> assertEquals(PROCESSING_NAME, t2.getStateName(), "correct state"),
				() -> assertEquals(T_TECHNICAL_WORK, t2.getTypeShortName(), "correct type"),
				() -> assertTrue(t2.isVerified(), "correct verification"),
				() -> assertEquals(OWNER, t2.getOwner(), "correct owner")); 
		
		Command c3 = new Command(CommandValue.VERIFY, null, "note");
		assertDoesNotThrow(() -> t2.update(c3)); 
		assertEquals(VERIFYING_NAME, t2.getStateName());
	}
	
	/**
	 * testing processing state	
	 */
	@Test
	public void test2ProcessingState() {
		Task t2 = assertDoesNotThrow(
				() -> new Task(ID, PROCESSING_NAME, TITLE, T_KNOWLEDGE_ACQUISITION, CREATOR, OWNER, VERIFIED_TRUE, NOTES),
				"Should not throw exception");
		
		assertAll("Task", 
				() -> assertEquals(ID, t2.getTaskId(), "correct ID"), 
				() -> assertEquals(TITLE, t2.getTitle(), "correct TITLE"),
				() -> assertEquals(KNOWLEDGE_ACQUISITION, t2.getType(), "correct type"),
				() -> assertEquals(CREATOR, t2.getCreator(), "correct creator"),
				() -> assertEquals(PROCESSING_NAME, t2.getStateName(), "correct state"),
				() -> assertEquals(T_KNOWLEDGE_ACQUISITION, t2.getTypeShortName(), "correct type"),
				() -> assertTrue(t2.isVerified(), "correct verification"),
				() -> assertEquals(OWNER, t2.getOwner(), "correct owner")); 
		
		Command c3 = new Command(CommandValue.COMPLETE, null, "note");
		assertDoesNotThrow(() -> t2.update(c3)); 
		assertEquals(DONE_NAME, t2.getStateName());
	}
	


}
