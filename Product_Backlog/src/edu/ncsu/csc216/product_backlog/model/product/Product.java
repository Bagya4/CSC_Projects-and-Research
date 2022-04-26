/**
 * 
 */
package edu.ncsu.csc216.product_backlog.model.product;

import java.util.ArrayList;

import edu.ncsu.csc216.product_backlog.model.command.Command;
import edu.ncsu.csc216.product_backlog.model.task.Task;
import edu.ncsu.csc216.product_backlog.model.task.Task.Type;

/**
 * Class Product to maintain a list of tasks
 * @author Bagya Maharajan 
 *
 */
public class Product {
	/** variable to hold product name*/
	private String productName;
	/** counter variable to hold task IDs*/
	private int counter;
	
	/** variable to hold list of tasks*/
	private ArrayList<Task> tasks;
	
	/**
	 * constructor of class
	 * @param productName is name of product
	 * @throws IllegalArgumentException for invalid name
	 */
	
	public Product(String productName) {
		
		setProductName(productName);
		emptyList();		
		
		setTaskCounter();
	}
	
	/**
	 * setter for productName
	 * @param productName is name of product
	 * @throws IllegalArgumentException for invalid name
	 */
	public void setProductName(String productName) {
		if("".equals(productName) || productName == null)
			throw new IllegalArgumentException("Invalid product name.");
		this.productName = productName.trim();
	}
	
	/**
	 * getter for product name
	 * @return product name
	 */	
	public String getProductName() {
		return productName;
	}
	
	/**
	 * finds the largest task id in the task list as max and sets the counter field to the max + 1
	 * if list is empty counter is set to 1
	 */	
	private void setTaskCounter() {
		
		if(tasks.size() == 0)
			counter = 1;
			
		else {
			int maxId = 0;		
			for(int i = 0; i < tasks.size(); i++) {
				
				if(tasks.get(i).getTaskId() > maxId) {
					maxId = tasks.get(i).getTaskId();
				}				
					
			}
			counter = maxId + 1;
		}
		
	}
	
	/**
	 * method to empty the list
	 */	
	private void emptyList() {
		tasks = new ArrayList<Task>();
	}
	
	/**
	 * method to add task to the list in sorted order by id
	 * @param task is task passed
	 * @throws IllegalArgumentException if task exists with given id
	 */	
	public void addTask(Task task) {
		
		if(tasks.size() == 0) {
			tasks.add(task);
		}
		
		else {
			for(int i = 0; i < tasks.size(); i++) {
				if(task.getTaskId() == tasks.get(i).getTaskId()) {
					throw new IllegalArgumentException("Task cannot be added.");
				}				
			} // end of for i			
			
			//algorithm to add task in order of ID
			int idxToAddTask = tasks.size();
			int count = 0;
			for(int j = 0; j < tasks.size(); j++) {
				if(tasks.get(j).getTaskId() > task.getTaskId()) {
					for(int y = 0; y < tasks.size(); y++) {
						if(tasks.get(j).getTaskId() >= tasks.get(y).getTaskId())
							count++;
					}
					
					if(count == tasks.size() || count == 1) {
						idxToAddTask = j;
						break;
					}
				}
			}
			tasks.add(idxToAddTask, task);
		} //end of else
		
		
		setTaskCounter();
		
			

	}
	
	/**
	 * method to create new task from details
	 * @param title of task
	 * @param type of task
	 * @param creator of task
	 * @param note for task
	 */	
	public void addTask(String title, Type type, String creator, String note) {
		
				
		Task taskToBeCreated = new Task(counter, title, type, creator, note);
		addTask(taskToBeCreated);
		
		setTaskCounter();
	}
	
	/**
	 * method to return list of tasks
	 * @return list of tasks
	 */	
	public ArrayList<Task> getTasks() {
		return tasks;
	}
	
	/**
	 * method to return the Task in the list with the given id
	 * @param id of task
	 * @return task with that id
	 */	
	public Task getTaskById(int id) {
		
		for(int i = 0; i < tasks.size(); i++) {
			if(id == tasks.get(i).getTaskId())
				return tasks.get(i);
		}
		//for no task with that id
		return null;
	}
	
	/**
	 * finds task with the given id and updates with respect to the Command
	 * @param id of task
	 * @param c passed in
	 */	
	public void executeCommand(int id, Command c) {
		for(int i = 0; i < tasks.size(); i++) {
			if(id == tasks.get(i).getTaskId())
				tasks.get(i).update(c);
		}
	}
	
	/**
	 * removes the Task with the given id from the list
	 * @param id of task
	 */	
	public void deleteTaskById(int id) {
		for(int i = 0; i < tasks.size(); i++) {
			if(id == tasks.get(i).getTaskId())
				tasks.remove(i);
		}
	}

}
