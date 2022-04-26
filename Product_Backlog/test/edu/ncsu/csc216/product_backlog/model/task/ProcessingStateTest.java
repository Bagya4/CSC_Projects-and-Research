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
 * test class for ProcessingState
 * @author Bagya Maharajan
 */
public class ProcessingStateTest {
	
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

	/** enumeration value for type TECHNICAL_WORK */
	private static final Type TECHNICAL_WORK = Type.TECHNICAL_WORK;
	/** enumeration value for type KNOWLEDGE_ACQUISITION */
	private static final Type KNOWLEDGE_ACQUISITION = Type.KNOWLEDGE_ACQUISITION;
	

	
	/**
	 * tests updating state and getting state name for CV backlog
	 */	
	@Test
	public void testUpdateState() {
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
	 * testing processing state	for CV verify
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
	 * testing processing state	for CV complete
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
	
	/**
	 * testing processing state	for CV processing
	 */
	@Test
	public void test3ProcessingState() {
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
		
		Command c3 = new Command(CommandValue.PROCESS, null, "note");
		assertDoesNotThrow(() -> t2.update(c3)); 
		assertEquals(PROCESSING_NAME, t2.getStateName());
	}

}
