/**
 * 
 */
package edu.ncsu.csc216.wolf_scheduler.scheduler;



import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import com.sun.javadoc.Type;

import edu.ncsu.csc216.wolf_scheduler.course.Product;
import edu.ncsu.csc216.wolf_scheduler.course.ConflictException;
import edu.ncsu.csc216.wolf_scheduler.course.Course;
import edu.ncsu.csc216.wolf_scheduler.course.Event;
import edu.ncsu.csc216.wolf_scheduler.io.ActivityRecordIO;
import edu.ncsu.csc216.wolf_scheduler.io.CourseRecordIO;

/**
 * Class for wolf scheduler which handles adding, removing and other operations on 
 * courses and events. Refers to methods from Course, Event and Activity
 * @author Bagya Maharajan
 *
 */
public class WolfScheduler {
	
	/** course catalog of type Activity*/
	ArrayList<Course> catalog = new ArrayList<Course>();
	
	/** schedule of courses*/
	ArrayList<Product> schedule;
	
	/** schedule title*/	
	String title;
	
	
	
	/**
	 * constructor of class
	 * @param filename is parameter passed
	 * @throws IllegalArgumentException for invalid parameter
	 */
	public WolfScheduler(String filename) {
		
		schedule = new ArrayList<Product>();
		this.title = "My Schedule";
		
		try {
			catalog = CourseRecordIO.readCourseRecords(filename);
			throw new IllegalArgumentException("Cannot find file.");
		}
		
		catch(IllegalArgumentException e) {
			e.getMessage();
		}
		
		catch(FileNotFoundException e) {
			//exception thrown in CourseIO
		}
		
	}

		
	/**
	 * method to get course catalog
	 * @return String [][]
	 */
	public String[][] getCourseCatalog() {				
				
		String[][] arrayOfCourseCatalog = new String[catalog.size()][];
		int length = catalog.size();
		
		for(int i = 0; i < length; i++) {
			Course c = catalog.get(i);
			
			//re-factoring to use getShortDisplayArray to add fourth column
			arrayOfCourseCatalog[i] = c.getShortDisplayArray();		
		}		
			
		return arrayOfCourseCatalog;
	}
	
	/**
	 * method to get scheduled courses
	 * @return String [][]
	 */
	public String[][] getScheduledActivities() {
		
		int length = schedule.size();
		if(length == 0) {
			String[][] nullArray = {};
			return nullArray;
		}
		
		String[][] arrayOfScheduledCourses = new String[schedule.size()][3];
		
		for(int i = 0; i < schedule.size(); i++) {
			Product a = schedule.get(i);	
			
			//re-factoring to use getShortDisplayArray to add fourth column
			arrayOfScheduledCourses[i] = a.getShortDisplayArray();			
			
		}		
			
		return arrayOfScheduledCourses;
		
	}
	
	/**
	 * method to get full scheduled courses
	 * @return String [][]
	 */
	public String[][] getFullScheduledActivities() {
		
		int length = schedule.size();		
		if(length == 0) {
			String[][] nullArr = {};
			return nullArr;
		}
		
		
		String[][] arrayOfFullSchedule = new String[schedule.size()][6];
		
		for(int i = 0; i < length; i++) {
			Product a = schedule.get(i);
			
			//re-factoring to use getLongDisplayArray
			arrayOfFullSchedule[i] = a.getLongDisplayArray();				
			
		} //end of for loop	
			
		return arrayOfFullSchedule;
	}
	
	/**
	 * method to get schedule title
	 * @return String
	 */
	public String getScheduleTitle() {		
		return this.title;
	}
	
	/**
	 * method to export schedule
	 * @param filename is input
	 * @throws IllegalArgumentException for invalid parameter
	 */
	public void exportSchedule(String filename) {
		try {
			ActivityRecordIO.writeActivityRecords(filename, schedule);			
		}
		
		catch(IOException i) {
			throw new IllegalArgumentException("The file cannot be saved.");
		}
		
	}
	
	/**
	 * method to get course form catalog
	 * @param courseName is input 1
	 * @param courseSection is input 2
	 * @return Course 
	 */
	public Course getCourseFromCatalog(String courseName, String courseSection) {
		
		int length = catalog.size();		
		for(int i = 0; i < length; i++) {
			Course c = catalog.get(i);
			if(c.getName().equals(courseName) && c.getSection().equals(courseSection))
				return c;
		}			
		
		return null;
	}
	
	/**
	 * method to add course to schedule
	 * @param name is input 1
	 * @param section is input 2
	 * @return boolean
	 * @throws IllegalArgumentException for invalid parameter
	 */
	public boolean addCourseToSchedule(String name, String section) throws IllegalArgumentException {		

		Course given = getCourseFromCatalog(name, section);
		  
		 if(given == null) 
			 return false;			 
		
		  
			 for(int i = 0; i < schedule.size(); i++) { 
				 //using isDuplicate method to check for course duplicates				 			 
				 boolean duplicateCheck = given.isDuplicate(schedule.get(i));
				 
				 if(duplicateCheck)
					 throw new IllegalArgumentException("You are already enrolled in " + name);				 

		 }
			 			 
			 try {
				 for(int j = 0; j < schedule.size(); j++) {
					 Product current = schedule.get(j);
					 current.checkConflict(given);
				 }
			 }
			 catch(ConflictException c) {
				 throw new IllegalArgumentException("The course cannot be added due to a conflict.");
			 }
		
		 schedule.add(given); 
		 return true;
		  
	}
	
	/**
	 * method to remove course to schedule
	 * @param idx TODO
	 * @return boolean
	 */
	public boolean removeActivityFromSchedule(int idx) {
		
		try {
			schedule.remove(idx);
			return true;
		}
		
		catch (IndexOutOfBoundsException i) {
			return false;
		} //end of try-catch block				
	}
	
	/**
	 * method to reset schedule
	 */

	public void resetSchedule() {
		
		schedule = new ArrayList<Product>();		
	}
	
	/**
	 * method to set title
	 * @param title input
	 * @throws IllegalArgumentException for invalid parameter
	 */
	public void setScheduleTitle(String title) {
		if(title == null)
			throw new IllegalArgumentException("Title cannot be null");
		
		this.title = title;
		
	}	
	
	/**
	 * method to add events to schedule and checking for duplicates
	 * @param eventTitle is title of event
	 * @param eventMeetingDays of event
	 * @param eventStartTime of event
	 * @param eventEndTime of event
	 * @param eventDetails of event
	 * @throws IllegalArgumentException for invalid parameter
	 */
	public void addEventToSchedule(String eventTitle, String eventMeetingDays, int eventStartTime, int eventEndTime, String eventDetails) {
		
		Event given = new Event(eventTitle, eventMeetingDays, eventStartTime, eventEndTime, eventDetails);
		
		for(int i = 0; i < schedule.size(); i++) { 
			
			 //using isDuplicate method to check for course duplicates				 			 
			 boolean duplicateCheck = given.isDuplicate(schedule.get(i));
			 
			 if(duplicateCheck)
				 throw new IllegalArgumentException("You have already created an event called " + eventTitle);	
	 } //end of for loop
		
		try {
			 for(int j = 0; j < schedule.size(); j++) {
				 Product current = schedule.get(j);
				 current.checkConflict(given);
			 }
		 }
		 catch(ConflictException c) {
			 throw new IllegalArgumentException("The event cannot be added due to a conflict.");
		 }
		
		schedule.add(given);
			 
	 
	}

}
