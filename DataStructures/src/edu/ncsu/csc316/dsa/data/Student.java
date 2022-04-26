package edu.ncsu.csc316.dsa.data;

/**
 * A student is comparable and identifiable.
 * Students have a first name, last name, id number, 
 * number of credit hours, gpa, and unityID.
 * 
 * @author Bagya Maharajan
 *
 */
public class Student implements Comparable<Student>, Identifiable {
	/** Stores first name of the student */
	private String first;
	/** Stores last name of the student */
	private String last;
	/** Stores student ID */
	private int id;
	/** Stores credit hours of the student */
	private int creditHours;
	/** Stores gpa of the student */
	private double gpa;
	/** Stores unityID of the student */
	private String unityID;
	
	/**
	 * Constructor of class which creates a student object with the following data
	 * @param first name
	 * @param last name
	 * @param id of student
	 * @param creditHours of student
	 * @param gpa of student
	 * @param unityID of student
	 */
	public Student(String first, String last, int id, int creditHours, double gpa, String unityID) {
		setFirst(first);
		setLast(last);
		setId(id);
		setCreditHours(creditHours);
		setGpa(gpa);
		setUnityID(unityID);
	}

	/**
	 * Getter for first name
	 * @return the first name
	 */
	public String getFirst() {
		return first;
	}

	/**
	 * Setter for first name
	 * @param first the first to set
	 */
	public void setFirst(String first) {
		this.first = first;
	}

	/**
	 * Getter for last name
	 * @return the last name
	 */
	public String getLast() {
		return last;
	}

	/**
	 * Setter for last name
	 * @param last the last to set
	 */
	public void setLast(String last) {
		this.last = last;
	}

	/**
	 * Getter for id
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Setter for student id
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Getter for credit hours
	 * @return the creditHours
	 */
	public int getCreditHours() {
		return creditHours;
	}

	/**
	 * Setter for credit hours
	 * @param creditHours the creditHours to set
	 */
	public void setCreditHours(int creditHours) {
		this.creditHours = creditHours;
	}

	/**
	 * Getter for gpa
	 * @return the gpa
	 */
	public double getGpa() {
		return gpa;
	}

	/**
	 * Setter for gpa
	 * @param gpa the gpa to set
	 */
	public void setGpa(double gpa) {
		this.gpa = gpa;
	}

	/**
	 * Getter for unityID
	 * @return the unityID
	 */
	public String getUnityID() {
		return unityID;
	}

	/**
	 * Setter for unityID
	 * @param unityID the unityID to set
	 */
	public void setUnityID(String unityID) {
		this.unityID = unityID;
	}
	
	/**
	 * Hash code for the Student class
	 * @return the hash code
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + creditHours;
		result = prime * result + ((first == null) ? 0 : first.hashCode());
		long temp;
		temp = Double.doubleToLongBits(gpa);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + id;
		result = prime * result + ((last == null) ? 0 : last.hashCode());
		result = prime * result + ((unityID == null) ? 0 : unityID.hashCode());
		return result;
	}
	
	/**
	 * Checks if one object is an instance of the student object
	 * Two students are equal if they have the same first and last names, and IDs
	 * @param obj is object that is checked for equality
	 * @return true if both objects are equal
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		Student other = (Student) obj;
		if(first.equals(other.getFirst()) && last.equals(other.getLast()) && id == other.getId())
			return true;
		return false;
	} 
	
	/**
	 * Compares two student objects for ordering based on last name, first name and then id
	 * @param student to be compared with current student
	 * @return an integer value which indicates the ordering
	 */
	public int compareTo(Student student) {
		//different last names
		if(!(this.last.equals(student.getLast()))) {
			int com = last.compareTo(student.getLast());
			return com;
		}
			
		//different first names
		if(!(first.equals(student.getFirst())))
			return this.first.compareTo(student.getFirst());
		//different student IDs
		if(this.id < student.getId())
			return -1;
		else if(this.id > student.getId())
			return 1;
		return 0;
			
		
	}
	
	/**
	 * Returns a String version of a student
	 * @return STring version of student
	 */
	@Override
	public String toString() {
		return "Student [first=" + getFirst() + ", last=" + getLast() + ", id=" + getId() + 
				", creditHours=" + getCreditHours() + ", gpa=" + getGpa() + ", unityID=" + getUnityID() + "]";
	}
	
	
	
}
