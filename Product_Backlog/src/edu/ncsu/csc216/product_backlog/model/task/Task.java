/**
 * 
 */
package edu.ncsu.csc216.product_backlog.model.task;

import java.util.ArrayList;

import edu.ncsu.csc216.product_backlog.model.command.Command;
import edu.ncsu.csc216.product_backlog.model.command.Command.CommandValue;

/**
 * Context class Task for handling tasks. Contains TaskState interface
 * and concrete states or the State classes
 * @author Bagya Maharajan
 *
 */
public class Task {
	
	
	/**
	 * Interface for states in the Task State Pattern.  All 
	 * concrete task states must implement the TaskState interface.
	 * 
	 * @author Dr. Sarah Heckman (sarah_heckman@ncsu.edu) 
	 */
	private interface TaskState {
		
		/**
		 * Update the Task based on the given Command
		 * An UnsupportedOperationException is thrown if the Command is not a
		 * is not a valid action for the given state.  
		 * @param c Command describing the action that will update the Task
		 * state.
		 * @throws UnsupportedOperationException if the Command is not a valid action
		 * for the given state.
		 */
		void updateState(Command c);
		
		/**
		 * Returns the name of the current state as a String.
		 * @return the name of the current state as a String.
		 */
		String getStateName();
	
	}
	
	/**
	 * Concrete state- Backlog for a task
	 * Inner class Backlog State
	 */
	public class BacklogState implements TaskState {
		
		/**
		 * Constructor of BacklogState
		 */	
		private BacklogState() {
			//constructor
		}
		
		/**
		 * method to update task state from backlog
		 * @param c is command 
		 * @throws UnsupportedOperationException if command for current state is not appropriate
		 */	
		public void updateState(Command c) {
			
			if(c.getCommand() == CommandValue.CLAIM) {	
				setOwner(c.getOwner());
				setState(OWNED_NAME);
				addNoteToList(c.getNoteText());
			}
			
			else if(c.getCommand() == CommandValue.REJECT) {
				//setOwner(c.getOwner());
				setState(REJECTED_NAME);
				addNoteToList(c.getNoteText());
			}
			else 
				throw new UnsupportedOperationException();
			
		}
		
		/**
		 * method to get current state name as a string
		 * @return state name
		 */	
		public String getStateName() {
			return BACKLOG_NAME;
		}

	}
	
	/**
	 * Concrete state- Owned for a task
	 * inner class for Owned State
	 */
	public class OwnedState implements TaskState {
		
		/**
		 * Constructor of OwnedState
		 */	
		private OwnedState() {
			//constructor
		}
		
		/**
		 * method to update task state from owned
		 * @param c is command 
		 * @throws UnsupportedOperationException if command for current state is not appropriate
		 */	
		public void updateState(Command c) {
			
			if(c.getCommand() == CommandValue.PROCESS) {
				
				setState(PROCESSING_NAME);
				addNoteToList(c.getNoteText());
			}
			
			else if(c.getCommand() == CommandValue.REJECT) {
				
				setState(REJECTED_NAME);
				addNoteToList(c.getNoteText());
			}
			
			else if(c.getCommand() == CommandValue.BACKLOG) {
				setOwner(UNOWNED);				
				setState(BACKLOG_NAME);
				addNoteToList(c.getNoteText());
			}
			else
				throw new UnsupportedOperationException();
		}
		
		/**
		 * method to get current state name as a string
		 * @return state name
		 */	
		public String getStateName() {
			return OWNED_NAME;
		}

	}
	
	/**
	 * Concrete state- Processing for a task
	 * inner class for Processing State
	 */
	public class ProcessingState implements TaskState {
		
		/**
		 * Constructor of ProcessingState
		 */	
		private ProcessingState() {
			//constructor
		}
		
		/**
		 * method to update task state from processing
		 * @param c is command 
		 * @throws UnsupportedOperationException if command for current state is not appropriate
		 */	
		public void updateState(Command c) {
			
			if(c.getCommand() == CommandValue.VERIFY) {
				if(type == Type.FEATURE || type == Type.BUG || type == Type.TECHNICAL_WORK) {
				
					setState(VERIFYING_NAME);
					addNoteToList(c.getNoteText());
				}					
				else
					throw new UnsupportedOperationException();
			}
			
			else if(c.getCommand() == CommandValue.COMPLETE) {
				if(type == Type.KNOWLEDGE_ACQUISITION) {
					
					setState(DONE_NAME);
					addNoteToList(c.getNoteText());
				}
					
				else
					throw new UnsupportedOperationException();
			}
			
			else if(c.getCommand() == CommandValue.BACKLOG) {
				setOwner(UNOWNED);				
				setState(BACKLOG_NAME);
				addNoteToList(c.getNoteText());
			}
			
			else if(c.getCommand() == CommandValue.PROCESS) {
				setState(PROCESSING_NAME);
				addNoteToList(c.getNoteText());
			}
			
			else
				throw new UnsupportedOperationException();
		}
		
		/**
		 * method to get current state name as a string
		 * @return state name
		 */	
		public String getStateName() {
			return PROCESSING_NAME;
		}

	}
	
	/**
	 * Concrete state- Verifying for a task
	 * inner class for Verifying State
	 */
	public class VerifyingState implements TaskState {
		
		/**
		 * Constructor of VerifyingState
		 */	
		private VerifyingState() {
			//constructor
		}
		
		/**
		 * method to update task state from verifying
		 * @param c is command 
		 * @throws UnsupportedOperationException if command for current state is not appropriate
		 */	
		public void updateState(Command c) {
			
			if(c.getCommand() == CommandValue.COMPLETE) {
				
				setVerified("true");				
				setState(DONE_NAME);
				addNoteToList(c.getNoteText());
			}
			
			else if(c.getCommand() == CommandValue.PROCESS) {
				setState(PROCESSING_NAME);
				addNoteToList(c.getNoteText());
			}
			
			else
				throw new UnsupportedOperationException();
			
		}
		
		/**
		 * method to get current state name as a string
		 * @return state name
		 */	
		public String getStateName() {
			return VERIFYING_NAME;
		}

	}
	
	/**
	 * Concrete state- Done for a task
	 * inner class for Done State
	 */
	public class DoneState implements TaskState {
		
		/**
		 * Constructor of DoneState
		 */	
		private DoneState() {
			//constructor
		}
		
		/**
		 * method to update task state from done
		 * @param c is command 
		 * @throws UnsupportedOperationException if command for current state is not appropriate
		 */	
		public void updateState(Command c) {
			
			if(c.getCommand() == CommandValue.PROCESS) {
				setState(PROCESSING_NAME);
				setVerified("false");
				addNoteToList(c.getNoteText());
			}
			
			else if(c.getCommand() == CommandValue.BACKLOG) {
				setOwner(UNOWNED);				
				setState(BACKLOG_NAME);
				setVerified("false");
				addNoteToList(c.getNoteText());
			}
			
			else
				throw new UnsupportedOperationException();
		}
		
		/**
		 * method to get current state name as a string
		 * @return state name
		 */	
		public String getStateName() {
			return DONE_NAME;
		}

	}
	
	/**
	 * Concrete state- Rejected for a task
	 * inner class for Rejected State
	 */
	public class RejectedState implements TaskState {
		
		/**
		 * Constructor of RejectedState
		 */	
		private RejectedState() {
			//constructor
		}
		
		/**
		 * method to update task state from rejected
		 * @param c is command 
		 * @throws UnsupportedOperationException if command for current state is not appropriate
		 */	
		public void updateState(Command c) {
			if(c.getCommand() == CommandValue.BACKLOG) {
				setOwner(UNOWNED);				
				setState(BACKLOG_NAME);
				addNoteToList(c.getNoteText());
			}
			else
				throw new UnsupportedOperationException();
		}
		
		/**
		 * method to get current state name as a string
		 * @return state name
		 */	
		public String getStateName() {
			return REJECTED_NAME;
		}

	}	
	
	
	/** field for taskID*/
	private int taskId;
	/** field for task title*/
	private String title;
	/** field for task creator*/
	private String creator;
	/** field for task owner*/
	private String owner;
	/** field for if task is Verified*/
	private boolean isVerified;
	/** field for task notes*/
	private ArrayList<String> notes;
	
	/**Current state of task*/
	private TaskState currentState;
	
	/**Type of task type*/
	private Type type;

	/**final instance of BacklogState-inner class*/
	private final BacklogState backlogState = new BacklogState();
	/**final instance of  OwnedState-inner class*/
	private final  OwnedState  ownedState = new OwnedState();
	/**final instance of  ProcessingState-inner class*/
	private final  ProcessingState  processingState =  new ProcessingState();
	/**final instance of  VerifyingState-inner class*/
	private final  VerifyingState  verifyingState =  new VerifyingState();
	/**final instance of  DoneState-inner class*/
	private final  DoneState  doneState = new DoneState();
	/**final instance of  RejectedState-inner class*/
	private final  RejectedState  rejectedState = new RejectedState();
	
	
	
	/** Enumerations for type of task */
	public enum Type { FEATURE, BUG, TECHNICAL_WORK, KNOWLEDGE_ACQUISITION }
	
	/** Constant string for Backlog state*/
	public static final String BACKLOG_NAME = "Backlog";
	/** Constant string for Owned state*/
	public static final String OWNED_NAME = "Owned";
	/** Constant string for PROCESSING state*/
	public static final String PROCESSING_NAME = "Processing";
	/** Constant string for VERIFYING state*/
	public static final String VERIFYING_NAME = "Verifying";
	/** Constant string for DONE state*/
	public static final String DONE_NAME = "Done";
	/** Constant string for REJECTED state*/
	public static final String REJECTED_NAME = "Rejected";
	
	/** Constant string for feature type*/
	public static final String FEATURE_NAME = "Feature";
	/** Constant string for bug type*/
	public static final String BUG_NAME = "Bug";
	/** Constant string for bug type*/
	public static final String TECHNICAL_WORK_NAME = "Technical Work";
	/** Constant string for Knowledge Acquisition type*/
	public static final String KNOWLEDGE_ACQUISITION_NAME = "Knowledge Acquisition";
	
	/** Constant string for feature type- short name*/
	public static final String T_FEATURE = "F";
	/** Constant string for bug type- short name*/
	public static final String T_BUG = "B";
	/** Constant string for technical work type- short name*/
	public static final String T_TECHNICAL_WORK = "TW";
	/** Constant string for Knowledge Acquisition type- short name*/
	public static final String T_KNOWLEDGE_ACQUISITION = "KA";
	
	/** Constant string for a task not owned*/
	public static final String UNOWNED = "unowned";
	
	/**
	 * constructor of class with all field values except array list of notes, state and owner
	 * @param id is id of task
	 * @param title is title of task
	 * @param type is type of task
	 * @param creator is creator of task
	 * @param note is creator of note
	 * @throws IllegalArgumentException for incorrect parameters
	 */
	public Task(int id, String title, Type type, String creator, String note) {
		
		if("".equals(title) || title == null || "".equals(creator) || creator == null || "".equals(note) || note == null)
			throw new IllegalArgumentException("Invalid task information.");
		if(type == null)
			throw new IllegalArgumentException("Invalid task information.");
		if(id <= 0)
			throw new IllegalArgumentException("Invalid task information.");
		
		setTaskId(id);
		setTitle(title);
		setType(type);
		setCreator(creator);
		
		currentState = backlogState;
		setOwner(UNOWNED);
		
		notes = new ArrayList<String>();
		addNoteToList(note);
	}
	
	/**
	 * constructor 2 of class with all field values 
	 * @param id is id of task
	 * @param state is state of task
	 * @param title is title of task
	 * @param type is type of task
	 * @param creator is creator of task
	 * @param owner is owner of task
	 * @param verified is a check
	 * @param notes is creator of note
	 * @throws IllegalArgumentException for incorrect parameters
	 */
	public Task(int id, String state, String title, String type, String creator, String owner, String verified, ArrayList<String> notes) {
		
		if(!("true".equals(verified)) && !("false".equals(verified))) {
			throw new IllegalArgumentException("Invalid task information.");
		}
		
		
		if("".equals(state) || state == null || "".equals(title) || title == null || "".equals(type) || type == null
				|| "".equals(creator) || creator == null || "".equals(owner) || owner == null || "".equals(verified) || verified == null)
			throw new IllegalArgumentException("Invalid task information.");
		if(id <= 0)
			throw new IllegalArgumentException("Invalid task information.");
		
		//checking constraints on task objects		
		if(notes.size() < 1)
			throw new IllegalArgumentException("Invalid task information.");
		
		if(!(state.equals(BACKLOG_NAME)) && !(state.equals(OWNED_NAME)) && !(state.equals(PROCESSING_NAME))
				&& !(state.equals(VERIFYING_NAME)) && !(state.equals(DONE_NAME)) && !(state.equals(REJECTED_NAME)))
			throw new IllegalArgumentException("Invalid task information."); 		
		
		if(!(type.equals(T_FEATURE)) && !(type.equals(T_BUG)) && !(type.equals(T_TECHNICAL_WORK)) && !(type.equals(T_KNOWLEDGE_ACQUISITION)))
			throw new IllegalArgumentException("Invalid task information.");
		
		if((BACKLOG_NAME.equals(state) || REJECTED_NAME.equals(state)) && !(owner.equals(UNOWNED))) {
			throw new IllegalArgumentException("Invalid task information.");				
		}
		
		if((OWNED_NAME.equals(state) || PROCESSING_NAME.equals(state) || VERIFYING_NAME.equals(state) || DONE_NAME.equals(state)) && UNOWNED.equals(owner)) {
			throw new IllegalArgumentException("Invalid task information.");				
		}
		
		if(DONE_NAME.equals(state)) {
			if(type.equals(T_FEATURE) || type.equals(T_BUG) || type.equals(T_TECHNICAL_WORK)) {
				if("false".equals(verified))
					throw new IllegalArgumentException("Invalid task information.");
			}
			
			else if(type.equals(T_KNOWLEDGE_ACQUISITION) && "true".equals(verified)) {
				throw new IllegalArgumentException("Invalid task information.");
			}
		}
				
		
		setTaskId(id);
		setState(state);
		setTitle(title);
		setCreator(creator);
		setOwner(owner);
		setVerified(verified);
		setNotes(notes);
		
		setTypeFromString(type);
		setType(getType());
		
	}
	
	
	

	/**
	 * getter for task ID
	 * @return the taskId
	 */
	public int getTaskId() {
		return taskId;
	}

	/**
	 * getter for task title
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * getter for task creator
	 * @return the creator
	 */
	public String getCreator() {
		return creator;
	}

	/**
	 * getter for task owner
	 * @return the owner
	 */
	public String getOwner() {
		return owner;
	}

	/**
	 * getter for task notes
	 * @return the notes
	 */
	public ArrayList<String> getNotes() {
		return notes;
	}

	/**
	 * getter for task type
	 * @return the type
	 */
	public Type getType() {
		return type;
	}

	/**
	 * setter for task ID
	 * @param taskId the taskId to set
	 */
	private void setTaskId(int taskId) {
		if(taskId <= 0)
			throw new IllegalArgumentException("Invalid task information.");
		this.taskId = taskId;
	}	

	/**
	 * setter for task title
	 * @param title the title to set
	 */
	private void setTitle(String title) {
		if("".equals(title) || title == null)
			throw new IllegalArgumentException("Invalid task information.");
		this.title = title;
	}

	/**
	 * setter for task creator
	 * @param creator the creator to set
	 */
	private void setCreator(String creator) {
		if("".equals(creator) || creator == null)
			throw new IllegalArgumentException("Invalid task information.");
		this.creator = creator;
	}

	/**
	 * setter for task owner
	 * @param owner the owner to set
	 */
	private void setOwner(String owner) {
		if("".equals(owner) || owner == null)
			throw new IllegalArgumentException("Invalid task information.");
		this.owner = owner;
	}
	
	/**
	 * setter for isVerified
	 * @param verified is passed in as a string
	 */
	private void setVerified(String verified) {
		if("".equals(verified) || verified == null)
			throw new IllegalArgumentException("Invalid task information.");
		
		if("true".equals(verified))
			isVerified = true;
		if("false".equals(verified))
			isVerified = false;
	}

	/**
	 * setter for task notes
	 * @param notes the notes to set
	 */
	private void setNotes(ArrayList<String> notes) {
		if(notes.size() < 1)
			throw new IllegalArgumentException("Invalid task information.");
		this.notes = notes;
	}

	/**
	 * setter for task type
	 * @param type the type to set
	 */
	private void setType(Type type) {
		this.type = type;
	}
	
	/**
	 * getter for task state's name
	 * @return the state name
	 */
	public String getStateName() {
		if(currentState == backlogState)
			return BACKLOG_NAME;
		if(currentState == ownedState)
			return OWNED_NAME;
		if(currentState == processingState)
			return PROCESSING_NAME;
		if(currentState == verifyingState)
			return VERIFYING_NAME;
		if(currentState == doneState)
			return DONE_NAME;
		if(currentState == rejectedState)
			return REJECTED_NAME;
		
		return null;
	}
	
	/**
	 * setter for task state name
	 * @param state name
	 */
	private void setState(String state) {
		if(state.equals(BACKLOG_NAME))
			currentState = backlogState;
		if(state.equals(OWNED_NAME))
			currentState = ownedState;
		if(state.equals(PROCESSING_NAME))
			currentState = processingState;
		if(state.equals(VERIFYING_NAME))
			currentState = verifyingState;
		if(state.equals(DONE_NAME))
			currentState = doneState;
		if(state.equals(REJECTED_NAME))
			currentState = rejectedState;
	}
	
	/**
	 * setter for task type from string
	 * @param typeAsString for task type
	 */
	private void setTypeFromString(String typeAsString) {
		if(typeAsString.equals(T_FEATURE))
			setType(Type.FEATURE);
		if(typeAsString.equals(T_BUG))
			setType(Type.BUG);
		if(typeAsString.equals(T_TECHNICAL_WORK))
			setType(Type.TECHNICAL_WORK);
		if(typeAsString.equals(T_KNOWLEDGE_ACQUISITION))
			setType(Type.KNOWLEDGE_ACQUISITION);
	}
	
	/**
	 * method to add note to notes list
	 * @param note to be added
	 * @return integer value of notes size
	 */
	public int addNoteToList(String note) {
		if("".equals(note) || note == null)
			throw new IllegalArgumentException("Invalid task information.");
		
		String noteToBeAdded = "[" + getStateName() + "]" + " " + note;
		notes.add(noteToBeAdded);
		setNotes(notes);
		
		return notes.size();
	}
	
	/**
	 * getter for task type-short name
	 * @return the type's short name
	 */
	public String getTypeShortName() {
		if(type.equals(Type.FEATURE))
			return T_FEATURE;
		if(type.equals(Type.BUG))
			return T_BUG;
		if(type.equals(Type.TECHNICAL_WORK))
			return T_TECHNICAL_WORK;
		if(type.equals(Type.KNOWLEDGE_ACQUISITION))
			return T_KNOWLEDGE_ACQUISITION;
		
		//for invalid type		
		return null;
	}
	
	/**
	 * getter for task type-long name
	 * @return the type's long name
	 */
	public String getTypeLongName() {
		if(type.equals(Type.FEATURE))
			return FEATURE_NAME;
		if(type.equals(Type.BUG))
			return BUG_NAME;
		if(type.equals(Type.TECHNICAL_WORK))
			return TECHNICAL_WORK_NAME;
		if(type.equals(Type.KNOWLEDGE_ACQUISITION))
			return KNOWLEDGE_ACQUISITION_NAME;
		
		//for invalid type		
		return null;
	}
	
	/**
	 * method for verification of task
	 * @return boolean value
	 */
	public boolean isVerified() {
		return isVerified;
	}
	
	/**
	 * method to get notes list as string
	 * @return String of notes
	 */
	public String getNotesList() {
		String notesAsString = "";
		for(int i = 0; i < notes.size(); i++) {
			if(i == notes.size() - 1) {
				notesAsString += "- " + notes.get(i).trim();
			}
			else {
				notesAsString += "- " + notes.get(i).trim() + "\n";
			}			
		}
		return notesAsString.trim();
	}
	
	/**
	 * method to update task
	 * @param c is command passed as input
	 * @throws UnsupportedOperationException if command for current state is not appropriate
	 */
	public void update(Command c) {
		try {
			currentState.updateState(c);
		}
		catch(UnsupportedOperationException i) {
			throw new UnsupportedOperationException("Invalid transition.");
		}
	}
	
	/**
	 * method to get notes as a string array	
	 * @return notes as an array of strings
	 */
	public String[] getNotesArray() {
		String[] notesAsArray = new String[notes.size()];
		for(int i = 0; i < notes.size(); i++) {
			notesAsArray[i] = "" + notes.get(i);
		}
		return notesAsArray;
	}
	
	/**
	 * override method to produce string of all fields together
	 * @return String of task
	 */
	@Override
	public String toString() {
				
		return "* " + getTaskId() + "," + getStateName() + "," + getTitle() + "," + getTypeShortName() + "," 
				+ getCreator() + "," + getOwner() + "," + isVerified() + "\n" + getNotesList();
	}
	
	

}
