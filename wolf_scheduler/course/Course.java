/**
 * 
 */
package edu.ncsu.csc216.wolf_scheduler.course;

/**
 * Class for wolf scheduler to deal with courses and operations with it such 
 * as adding or removing. It is a child class of Activity
 * 
 * @author Bagya Maharajan
 *
 */
public class Course extends Product {

	/** constant for minimum name length */
	private static final int MIN_NAME_LENGTH = 5;

	/** constant for max name length */
	private static final int MAX_NAME_LENGTH = 8;

	/** constant for minimum letter count */
	private static final int MIN_LETTER_COUNT = 1;

	/** constant for max letter count */
	private static final int MAX_LETTER_COUNT = 4;

	/** constant for digit count */
	private static final int DIGIT_COUNT = 3;

	/** constant for section length */
	private static final int SECTION_LENGTH = 3;

	/** constant for max credits */
	private static final int MAX_CREDITS = 5;

	/** constant for minimum credits */
	private static final int MIN_CREDITS = 1;

	/** Course's name. */
	private String name;
	
	/** Course's section. */
	private String section;
	
	/** Course's credit hours */
	private int credits;
	
	/** Course's instructor */
	private String instructorId;
	
	/**
	 * Returns the Course's name.
	 * 
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the Course's name after performing validity checks
	 * 
	 * @param name the name to set
	 *  @throws IllegalArgumentException for invalid parameter
	 */
	private void setName(String name) {

		if (name == null) {
			throw new IllegalArgumentException("Invalid course name.");
		}
		if (name.length() < MIN_NAME_LENGTH || name.length() > MAX_NAME_LENGTH) {
			throw new IllegalArgumentException("Invalid course name.");
		}

		int numLetters = 0;
		int numDigits = 0;
		boolean space = false;

		for (int i = 0; i < name.length(); i++) {
			char current = name.charAt(i);

			if (name.charAt(i) != ' ' && !(space)) {

				if (Character.isLetter(current))
					numLetters++;
				else
					throw new IllegalArgumentException("Invalid course name.");
			} else {
				if (name.charAt(i) == ' ')
					space = true;
				else if (Character.isDigit(current))
					numDigits++;
				else
					throw new IllegalArgumentException("Invalid course name.");
			}
		}

		if (numLetters < MIN_LETTER_COUNT || numLetters > MAX_LETTER_COUNT)
			throw new IllegalArgumentException("Invalid course name.");

		if (numDigits != DIGIT_COUNT)
			throw new IllegalArgumentException("Invalid course name.");

		this.name = name;
	}

	/**
	 * Returns the Course's section.
	 * 
	 * @return the section
	 */
	public String getSection() {
		return section;
	}

	/**
	 * Sets the Course's section after performing validity checks
	 * 
	 * @param section the section to set
	 *  @throws IllegalArgumentException for invalid parameter
	 */
	public void setSection(String section) {
		if (section == null || section.length() > SECTION_LENGTH) {
			throw new IllegalArgumentException("Invalid section.");
		}

		int numDigits = 0;
		for (int i = 0; i < section.length(); i++) {
			char current = section.charAt(i);
			if (Character.isDigit(current))
				numDigits++;
			else
				throw new IllegalArgumentException("Invalid section.");
		}

		if (numDigits != SECTION_LENGTH)
			throw new IllegalArgumentException("Invalid section.");

		this.section = section;
	}

	/**
	 * Returns the Course's credits.
	 * 
	 * @return the credits
	 */
	public int getCredits() {
		return credits;
	}

	/**
	 * Sets the Course's credits
	 * 
	 * @param credits the credits to set
	 *  @throws IllegalArgumentException for invalid parameter
	 */
	public void setCredits(int credits) {
		if (credits < MIN_CREDITS || credits > MAX_CREDITS)
			throw new IllegalArgumentException("Invalid credits.");
		this.credits = credits;
	}

	/**
	 * Returns the Course's Instructor ID.
	 * 
	 * @return the instructorId
	 */
	public String getInstructorId() {
		return instructorId;
	}

	/**
	 * Sets the Course's Instructor ID after making checks for invalid arguments
	 * 
	 * @param instructorId the instructorId to set
	 * @throws IllegalArgumentException for invalid parameter
	 */
	public void setInstructorId(String instructorId) {
		if (instructorId == null || "".equals(instructorId))
			throw new IllegalArgumentException("Invalid instructor id.");
		this.instructorId = instructorId;
	}	
	

	/**
	 * Constructs a course object with values for all fields
	 * 
	 * @param name         name of course
	 * @param title        name of title
	 * @param section      name of section
	 * @param credits      name of credits
	 * @param instructorId name of instructor ID
	 * @param meetingDays  meeting days for course
	 * @param startTime    start time for course
	 * @param endTime      end time for course
	 */
	public Course(String name, String title, String section, int credits, String instructorId, String meetingDays,
			int startTime, int endTime) {
		
		super(title, meetingDays, startTime, endTime);
		setName(name);
		
		setSection(section);
		setCredits(credits);
		setInstructorId(instructorId);
		

	}

	/**
	 * Creates a Course with the given name, title, section, credits, instructorId,
	 * and meetingDays for courses that are arranged.
	 * 
	 * @param name         name of course
	 * @param title        title of course
	 * @param section      section of course
	 * @param credits      credits of course
	 * @param instructorId instructor ID for course
	 * @param meetingDays  meeting days for course
	 */
	public Course(String name, String title, String section, int credits, String instructorId, String meetingDays) {
		this(name, title, section, credits, instructorId, meetingDays, 0, 0);
	}	
	
	/**
	 * Generates a hash code for Course
	 * @return hashCode for Course
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + credits;
		result = prime * result + ((instructorId == null) ? 0 : instructorId.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((section == null) ? 0 : section.hashCode());
		return result;
	}
	
	/**
	 * Compares a given object to this object for equality on all fields
	 * @param obj the Object to compare
	 * @return true if the objects are the same on all fields
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Course other = (Course) obj;
		if (credits != other.credits)
			return false;
		if (instructorId == null) {
			if (other.instructorId != null)
				return false;
		} else if (!instructorId.equals(other.instructorId))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (section == null) {
			if (other.section != null)
				return false;
		} else if (!section.equals(other.section))
			return false;
		return true;
	}

	/**
	 * Returns a comma separated value String of all Course fields.
	 * 
	 * @return String representation of Course
	 */
	@Override
	public String toString() {
		if ("A".equals(getMeetingDays())) {
			return name + "," + getTitle() + "," + section + "," + credits + "," + instructorId + "," + getMeetingDays();
		}
		return name + "," + getTitle() + "," + section + "," + credits + "," + instructorId + "," + getMeetingDays() + ","
				+ getStartTime() + "," + getEndTime();
	}
	
	/**
	 * Returns short display array to GUI
	 * @return shortDisplayArray
	 */
	@Override
	public String[] getShortDisplayArray() {
		String[] array = new String[4];
		String nameOfCourse = getName();
		String sectionCourse = getSection();
		String titleOfCourse = getTitle();
		String meetingDaysCourse = getMeetingString();
		
		array[0] = nameOfCourse;
		array[1] = sectionCourse;
		array[2] = titleOfCourse;
		array[3] = meetingDaysCourse;		
		return array;
	}
	
	/**
	 * Returns long display array to GUI
	 * @return longDisplayArray
	 */
	@Override
	public String[] getLongDisplayArray() {
		String[] array = new String[7];
		String nameOfCourse = getName();
		String sectionCourse = getSection();
		String titleOfCourse = getTitle();		
		String creditsForCourse = getCredits() + "";
		String instructorIdCourse = getInstructorId();
		String meetingDaysCourse = getMeetingString();
		String emptyForEvent = "";
		
		
		array[0] = nameOfCourse;
		array[1] = sectionCourse;
		array[2] = titleOfCourse;
		array[3] = creditsForCourse;
		array[4] = instructorIdCourse;
		array[5] = meetingDaysCourse;		
		array[6] = emptyForEvent;
		
		return array;
	}
	
	/**
	 * method to override setMeetingDaysAndTime so that Activity handles common checks only
	 * @param meetingDays for Course
	 * @param startTime for Course
	 * @param endTime for Course
	 *  @throws IllegalArgumentException for invalid parameters
	 */
	@Override
	public void setMeetingDaysAndTime(String meetingDays, int startTime, int endTime) {		
	
		// checking invalid meeting days
		if (meetingDays == null || "".equals(meetingDays))
			throw new IllegalArgumentException("Invalid meeting days and times.");
	
		// Arranged schedule
		if ("A".equals(meetingDays)) {
			if (startTime != 0 || endTime != 0) {
				throw new IllegalArgumentException("Invalid meeting days and times.");
			}
			super.setMeetingDaysAndTime(meetingDays, 0, 0);
		}
	
		else {
			
			// counters for days
			int countM = 0;
			int countT = 0;
			int countW = 0;
			int countH = 0;
			int countF = 0;
		
			
			//checking for invalid letters
			
			for (int i = 0; i < meetingDays.length(); i++) {
				char current = meetingDays.charAt(i);
				if (current == 'M' || current == 'T' || current == 'W' || current == 'H' || current == 'F' ) {

					// need to check for repeating letters
					if (current == 'M')
						countM++;
					if (current == 'T')
						countT++;
					if (current == 'W')
						countW++;
					if (current == 'H')
						countH++;
					if (current == 'F')
						countF++;
					

					if (countM > 1 || countT > 1 || countW > 1 || countH > 1 || countF > 1)
						throw new IllegalArgumentException("Invalid meeting days and times.");
				} else
					throw new IllegalArgumentException("Invalid meeting days and times.");
			} //end of for loop				
		
		}
		super.setMeetingDaysAndTime(meetingDays, startTime, endTime);
} //end of override method
	
	/**
	 * method to check for duplicate courses
	 * @param activity is course passed in
	 * @return boolean to know if duplicate course or not
	 */	
	public boolean isDuplicate(Product activity) {
		if(activity instanceof Course) {
			Course course = (Course) activity;
			return name.equals(course.getName());
		}
		else
			return false;		
	}
	
	

}
