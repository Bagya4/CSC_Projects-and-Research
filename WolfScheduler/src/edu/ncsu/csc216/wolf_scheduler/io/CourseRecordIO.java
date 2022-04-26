package edu.ncsu.csc216.wolf_scheduler.io;

import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
//import java.util.List;

import edu.ncsu.csc216.wolf_scheduler.course.Course;

/**
 * Reads Course records from text files.  Writes a set of CourseRecords to a file.
 * 
 * @author Bagya Maharajan
 */
public class CourseRecordIO {

    /**
     * Reads course records from a file and generates a list of valid Courses.  Any invalid
     * Courses are ignored.  If the file to read cannot be found or the permissions are incorrect
     * a File NotFoundException is thrown.
     * @param fileName file to read Course records from
     * @return a list of valid Courses
     * @throws FileNotFoundException if the file cannot be found or read
     */
    public static ArrayList<Course> readCourseRecords(String fileName) throws FileNotFoundException {
    	Scanner fileReader = new Scanner(new FileInputStream(fileName));  //Create a file scanner to read the file
        ArrayList<Course> courses = new ArrayList<Course>(); //Create an empty array of Course objects
        while (fileReader.hasNextLine()) { //While we have more lines in the file
            try {                                
                Course course = readCourse(fileReader.nextLine()); 

                //Create a flag to see if the newly created Course is a duplicate of something already in the list  
                boolean duplicate = false;
                
                for (int i = 0; i < courses.size(); i++) {
                    
                    Course current = courses.get(i);
                    
                    if (course.getName().equals(current.getName()) &&
                            course.getSection().equals(current.getSection())) {
                        //It's a duplicate!
                        duplicate = true;
                        break; 
                    }
                }
                //If the course is NOT a duplicate
                if (!duplicate) {
                    courses.add(course); //Add to the ArrayList!
                } //Otherwise ignore
            } catch (IllegalArgumentException e) {
                //The line is invalid 
            }
        }
        //Close the Scanner b/c we're responsible with our file handles
        fileReader.close();
        //Return the ArrayList with all the courses we read!
        return courses;

    }
    
    /**
     * method to read in line from file
     * @param line is input
     * @return Course object
     * @throws InputMismatchException for invalid argument
     * @throws IllegalArgumentException for invalid argument
     */
    private static Course readCourse(String line) {
    	
    	//constructing line scanner
    	Scanner lineScanner = new Scanner(line);
    	lineScanner.useDelimiter(",");   	    	

    	   
    	   try {
    		   String name = lineScanner.next();
    		   String title = lineScanner.next();
    		   String section = lineScanner.next();
    		   
    		   if(!(lineScanner.hasNextInt())) {
    			   lineScanner.close();
    			   throw new InputMismatchException();
    		   }
    		   int credits = lineScanner.nextInt();
    		   
    		   
    		   String instructorId = lineScanner.next();
    		   String meetingDays = lineScanner.next();
    		   if("A".equals(meetingDays)) {
    			   if(lineScanner.hasNext()) {
    				   lineScanner.close();
    				   throw new IllegalArgumentException();
    			   }
    				   
    			   else {
    				   Course newObject = new Course(name, title, section, credits, instructorId, meetingDays);
    				   lineScanner.close();
    				   return newObject;
    			   }
    		   }
    		   
    		   //not "A" meetingDays
    		   else {
    			   int startTime = lineScanner.nextInt();
    			   int endTime = lineScanner.nextInt();
    			   if(lineScanner.hasNext()) {
    				   lineScanner.close();
    				   throw new IllegalArgumentException();
    			   }
    			   
    			   else {
    				   Course newObjectNotA = new Course(name, title, section, credits, instructorId, meetingDays, startTime, endTime);
        			   lineScanner.close();
    				   return newObjectNotA;
    			   }
    				   
    			   
    			   
    		   }
    	   }
    	   
    	   catch (NoSuchElementException n) {
    		   //exception thrown
    	   }
    	      		  
    	   catch (IllegalArgumentException e) {
    		   //exceptions already thrown
    	   }
    	   
    	   return new Course("", "", "", 0, "", "", 0, 0);
    	   
    	   
	}

}