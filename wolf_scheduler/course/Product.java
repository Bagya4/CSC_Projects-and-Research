package edu.ncsu.csc216.wolf_scheduler.course;

/**
 * Super class Activity which handles common checks for child classes 
 * Courses and Events. Activity is an abstract class that implements Conflict
 * @author Bagya Maharajan
 *
 */

public abstract class Product implements Conflict {

	/** constant for upper hour */
	private static final int UPPER_HOUR = 24;
	/** constant for upper minute */
	private static final int UPPER_MINUTE = 60;
	/** Activity's title. */
	private String title;
	/** Activity's meeting days */
	private String meetingDays;
	/** Activity's starting time */
	private int startTime;
	/** Activity's ending time */
	private int endTime;
	
	/**
	 * constructor for Activity
	 * @param title is title of Activity
	 * @param meetingDays of Activity
	 * @param startTime of Activity
	 * @param endTime of Activity
	 */
	public Product(String title, String meetingDays, int startTime, int endTime) {
        super();
        setTitle(title);
        setMeetingDaysAndTime(meetingDays, startTime, endTime);
    }

	/**
	 * Returns the Activity's title.
	 * 
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets the Activity's title after 
	 * 
	 * @param title the title to set
	 * @throws IllegalArgumentException for invalid parameter
	 */
	public void setTitle(String title) {
	
		if (title == null || "".equals(title)) {
			throw new IllegalArgumentException("Invalid title.");
		}
		this.title = title;
	}

	/**
	 * Returns the Activity's meeting days.
	 * 
	 * @return the meetingDays
	 */
	public String getMeetingDays() {
		return meetingDays;
	}

	/**
	 * Returns the Activity's start time.
	 * 
	 * @return the startTime
	 */
	public int getStartTime() {
		return startTime;
	}

	/**
	 * Returns the Activity's end time
	 * 
	 * @return the endTime
	 */
	public int getEndTime() {
		return endTime;
	}

	/**
	 * method to set parameters after checking for validity
	 * 
	 * @param meetingDays is days of Activity meeting
	 * @param startTime   is start time of Activity
	 * @param endTime     is end time of Activity
	 * @throws IllegalArgumentException for invalid parameters
	 */
	public void setMeetingDaysAndTime(String meetingDays, int startTime, int endTime) {	

				
			// start time into military format
			int hourStartTime = startTime / 100;
			int minuteStartTime = startTime % 100;
	
			// checking for negative time
			if (startTime < 0 || endTime < 0)
				throw new IllegalArgumentException("Invalid meeting days and times.");
	
			// checking for invalid start time
			if (hourStartTime >= UPPER_HOUR || minuteStartTime >= UPPER_MINUTE) {
				throw new IllegalArgumentException("Invalid meeting days and times.");
			}
	
			// end time into military format
			int hourEndTime = endTime / 100;
			int minuteEndTime = endTime % 100;
	
			// checking for invalid end time
			if (hourEndTime >= UPPER_HOUR || minuteEndTime >= UPPER_MINUTE) {
				throw new IllegalArgumentException("Invalid meeting days and times.");
			}
	
			// checking if end time is greater than start time
			if (hourStartTime > hourEndTime)
				throw new IllegalArgumentException("Invalid meeting days and times.");
	
			// example: between 1300 to 1350
			if (hourStartTime == hourEndTime && minuteEndTime < minuteStartTime)
				throw new IllegalArgumentException("Invalid meeting days and times.");	
		
	
		this.meetingDays = meetingDays;
		this.startTime = startTime;
		this.endTime = endTime;
	
	}

	/**
	 * method to provide Activity meeting information as string
	 * 
	 * @return String return value
	 * @throws IllegalArgumentException for invalid values
	 */
	public String getMeetingString() {
	
		String normalStartTime = "";
		String normalEndTime = "";
	
		int hourStartTime = startTime / 100;
		int minuteStartTime = startTime % 100;
	
		// checking for invalid start time
		if (hourStartTime >= UPPER_HOUR || minuteStartTime >= UPPER_MINUTE) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}
	
		if (hourStartTime > (UPPER_HOUR / 2)) {
			normalStartTime = (hourStartTime - (UPPER_HOUR / 2)) + ":" + minuteStartTime + "PM";
			if (minuteStartTime < 10) {
				normalStartTime = (hourStartTime - (UPPER_HOUR / 2)) + ":" + "0" + minuteStartTime + "PM";
			}
		} else if (hourStartTime <= (UPPER_HOUR / 2)) {
			
			//changing to PM if 12 o'clock
			if(hourStartTime == UPPER_HOUR / 2)
				normalStartTime = hourStartTime + ":" + minuteStartTime + "PM";
			else
				normalStartTime = hourStartTime + ":" + minuteStartTime + "AM";
			
			if (minuteStartTime < 10) {
				//checking for 12 o'clock again
				if(hourStartTime == UPPER_HOUR / 2)
					normalStartTime = hourStartTime + ":" + "0" + minuteStartTime + "PM";
				else
					normalStartTime = hourStartTime + ":" + "0" + minuteStartTime + "AM";
			}
		}
	
		int hourEndTime = endTime / 100;
		int minuteEndTime = endTime % 100;
	
		if (hourEndTime > (UPPER_HOUR / 2)) {
			normalEndTime = (hourEndTime - (UPPER_HOUR / 2)) + ":" + minuteEndTime + "PM";
			if (minuteEndTime < 10) {
				normalEndTime = (hourEndTime - (UPPER_HOUR / 2)) + ":" + "0" + minuteEndTime + "PM";
			}
		} else if (hourEndTime <= (UPPER_HOUR / 2)) {
			
			//checking if 12 o'clock			
			if(hourEndTime == UPPER_HOUR / 2)
				normalEndTime = hourEndTime + ":" + minuteEndTime + "PM";
			else
				normalEndTime = hourEndTime + ":" + minuteEndTime + "AM";
			
			if (minuteEndTime < 10) {
				//checking for 12 o'clock again
				if(hourEndTime == UPPER_HOUR / 2) {
					normalEndTime = hourEndTime + ":" + "0" + minuteEndTime + "PM";
				}
				else
					normalEndTime = hourEndTime + ":" + "0" + minuteEndTime + "AM";
			}
		}
	
		if ("A".equals(meetingDays)) {
			return "Arranged";
		}
	
		return meetingDays + " " + normalStartTime + "-" + normalEndTime;
	}
	
	/**
	 * Generates a hash code for Activity 
	 * @return hashCode for Activity
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + endTime;
		result = prime * result + ((meetingDays == null) ? 0 : meetingDays.hashCode());
		result = prime * result + startTime;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
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
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (endTime != other.endTime)
			return false;
		if (meetingDays == null) {
			if (other.meetingDays != null)
				return false;
		} else if (!meetingDays.equals(other.meetingDays))
			return false;
		if (startTime != other.startTime)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}
	
	/**
	 * Provides short version of array to GUI which is constructed in Course
	 * @return String[] short version array to GUI
	 */	
	public abstract String[] getShortDisplayArray(); 
	
	/**
	 * Provides long version of array to GUI which is constructed to Course
	 * @return String[] long version array to GUI
	 */		
	public abstract String[] getLongDisplayArray();
	
	/**
	 * method to check for duplicate courses and events
	 * @param activity is the course or event passed
	 * @return boolean to see if duplicate or not
	 */	
	public abstract boolean isDuplicate(Product activity);
	
	/**
	 * Method to check for schedule conflict if there is an overlap of at least 1 day with an overlapping time.
	 * Overlapping time includes possibilities where an Activity starts or ends at the same minute.
	 * @param possibleConflictingActivity is activity to be checked for conflict
	 * @throws ConflictException when there is a schedule conflict 
	 */

	@Override
	public void checkConflict(Product possibleConflictingActivity) throws ConflictException {
		
		int startTimeToCheck = possibleConflictingActivity.getStartTime();
		int endTimeToCheck = possibleConflictingActivity.getEndTime();
		
		String daysOfActivity = possibleConflictingActivity.getMeetingDays();

			for(int i = 0; i < daysOfActivity.length(); i++) {
				//checking for arranged courses
				if("A".equals(daysOfActivity))
					break;
				
				char currentDay = daysOfActivity.charAt(i);
				for(int j = 0; j < this.meetingDays.length(); j++) {
					if(currentDay == this.meetingDays.charAt(j)) {
						//Assert: same day
						//check for same start and end time
						if(startTimeToCheck == this.startTime || endTimeToCheck == this.endTime)
							throw new ConflictException();
						//check for same startTime(parameter) and endTime(this) and vice versa
						if(startTimeToCheck == this.endTime || endTimeToCheck == this.startTime)
							throw new ConflictException();
						//check for startTime(this) before endTime(parameter)
						if(startTimeToCheck < this.endTime && startTimeToCheck > this.startTime)
							throw new ConflictException();
						
						if(endTimeToCheck < this.startTime && endTimeToCheck > this.endTime)
							throw new ConflictException();

						if(this.startTime < endTimeToCheck && this.startTime > startTimeToCheck)
							throw new ConflictException();
						
						if(this.endTime < startTimeToCheck && this.endTime > endTimeToCheck)
							throw new ConflictException();
						
					}
				}
			} //i for loop ends		
		
	}
	
	

}