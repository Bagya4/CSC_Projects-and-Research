package edu.ncsu.csc216.product_backlog.model.command;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.product_backlog.model.command.Command.CommandValue;

/**
 * test class for Command
 * all getters and setters will be tested through other methods
 * @author Bagya Maharajan
 */
public class CommandTest {
	
	/** enumeration value for backlog **/
	private static final CommandValue BACKLOG = CommandValue.BACKLOG;
	/** enumeration value for claim **/
	private static final CommandValue CLAIM = CommandValue.CLAIM;
	/** enumeration value for PROCESS **/
	private static final CommandValue PROCESS = CommandValue.PROCESS;
	
	/** variable for holding notes of a task */	
	private static final String NOTE = "[Backlog] add owner";
	/** variable for holding invalid notes of a task */	
	private static final String INVALID_NOTE = "";
	/** owner of task*/
	private static final String OWNER = "unowned";
	/** invalid owner of task*/
	private static final String INVALID_OWNER = null;
	/** default command error message*/
	private static final String COMMAND_ERROR_MESSAGE = "Invalid command.";
	
	
	/**
	 * testing constructor
	 */	
	@Test
	public void testValidCommandConstructor() {
		
		Command testC1 = assertDoesNotThrow(
				() -> new Command(BACKLOG, null, NOTE),
				"Should not throw exception");
		
		assertAll("Command", 
				() -> assertEquals(BACKLOG, testC1.getCommand(), "correct command"), 
				() -> assertEquals(null, testC1.getOwner(), "correct owner"),
				() -> assertEquals(NOTE, testC1.getNoteText(), "correct note")); 		
		
	}
	
	/**
	 * testing null or empty strings
	 */
	@Test
	public void testInvalidStrings() {
		Exception e1 = assertThrows(IllegalArgumentException.class,
				() -> new Command(CLAIM, INVALID_OWNER, NOTE));
		assertEquals(COMMAND_ERROR_MESSAGE, e1.getMessage(), " exception for null owner");
		
		Exception e2 = assertThrows(IllegalArgumentException.class,
				() -> new Command(PROCESS, OWNER, INVALID_NOTE));
		assertEquals(COMMAND_ERROR_MESSAGE, e2.getMessage(), " exception for empty note");
	}

}

