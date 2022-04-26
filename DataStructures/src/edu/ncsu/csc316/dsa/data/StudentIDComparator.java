package edu.ncsu.csc316.dsa.data;

import java.util.Comparator;

/**
 * Comparator to compare students based on id number
 * @author Dr. King
 *
 */
public class StudentIDComparator implements Comparator<Student> {
	
	/**
	 * Constructor of class to create an instance	
	 */
	public StudentIDComparator() {
		//creates an instance of the class
	}

	/**
	 * Compares students based on id number in ascending order
	 */
	@Override
	public int compare(Student one, Student two) {
		if(one.getId() < two.getId())
			return -1;
		if(one.getId() > two.getId())
			return 1;
		
		//if equal ID- for the same student
		return 0;
	}

}
