/**
 * 
 */
package edu.ncsu.csc216.product_backlog.model.command;

/**
 * The Command class creates objects that aid with state transitions of a task
 * @author Bagya Maharajan
 *
 */
public class Command {
	
	/** variable for holding notes of a task */	
	private String note;
	/** owner of task*/
	private String owner;
	/** default command error message*/
	private static final String COMMAND_ERROR_MESSAGE = "Invalid command.";
	
	/** Enumeration to list possible states of a Task*/
	public enum CommandValue { BACKLOG, CLAIM, PROCESS, VERIFY, COMPLETE, REJECT }
	
	/** variable for holding command value*/
	private CommandValue c;
	
	/**
	 * Constructor for Command
	 * @param c is command value passed
	 * @param owner is owner of task
	 * @param noteText is note for a Task
	 * @throws IllegalArgumentException for incorrect parameters
	 */
	public Command (CommandValue c, String owner, String noteText) {
		
		if(c == null)
			throw new IllegalArgumentException(COMMAND_ERROR_MESSAGE);
		
		
		if(noteText == null || "".equals(noteText))
			throw new IllegalArgumentException(COMMAND_ERROR_MESSAGE);		
		
		if(CommandValue.CLAIM.equals(c)) {
			if(owner == null || "".equals(owner))
				throw new IllegalArgumentException(COMMAND_ERROR_MESSAGE);
		}
		
		//else if c != CLAIM then checking for non null owner
		else if(!(owner == null)){
			throw new IllegalArgumentException(COMMAND_ERROR_MESSAGE);
		}
		
		//setting all fields after checking for invalid inputs
		this.note = noteText;
		this.owner = owner;
		this.c = c;
		
	}
	
	/**
	 * getter for command
	 * @return command value
	 */	
	public CommandValue getCommand() {
		return c;
	}
	
	/**
	 * getter for noteText
	 * @return note for task
	 */	
	public String getNoteText() {
		return note;
	}
	
	/**
	 * getter for owner
	 * @return owner as string
	 */	
	public String getOwner() {
		return owner;
	}
	


}
