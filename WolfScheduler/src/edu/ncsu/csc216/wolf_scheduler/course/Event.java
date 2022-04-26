/**
 * 
 */
package edu.ncsu.csc216.wolf_scheduler.course;

/**
 * Child class of Activity for handling operations on Events
 * @author Bagya Maharajan
 *
 */
public class Event extends Product {
	
	/** String for Event details*/
	private String eventDetails;
	
	

	/**
	 * constructor for Event
	 * @param title is title of Event
	 * @param meetingDays of Event
	 * @param startTime of Event
	 * @param endTime of Event
	 * @param eventDetails of Event
	 */
	public Event(String title, String meetingDays, int startTime, int endTime, String eventDetails) {
		super(title, meetingDays, startTime, endTime);
		setEventDetails(eventDetails);
	}

	/**
	 * method to get event details
	 * @return the eventDetails
	 */
	public String getEventDetails() {
		return eventDetails;
	}

	/**
	 * method to set event details
	 * @param eventDetails the eventDetails to set
	 * @throws IllegalArgumentException for invalid parameter
	 */
	public void setEventDetails(String eventDetails) {
		if(eventDetails == null)
			throw new IllegalArgumentException("Invalid event details.");
		
		this.eventDetails = eventDetails;
	}
	
	/**
	 * Returns short display array to GUI
	 * @return shortDisplayArray
	 */
	@Override
	public String[] getShortDisplayArray() {
		String[] shortArray = new String[4];
		
		String emptyString1 = "";
		String emptyString2 = "";
		String titleOfCourse = getTitle();
		String meetingDaysCourse = getMeetingString();
		
		shortArray[0] = emptyString1;
		shortArray[1] = emptyString2;
		shortArray[2] = titleOfCourse;
		shortArray[3] = meetingDaysCourse;		
		return shortArray;
	}
	
	/**
	 * Returns long display array to GUI with appropriate fields
	 * @return longDisplayArray
	 */
	@Override
	public String[] getLongDisplayArray() {
		String[] array = new String[7];
		String emptyString1 = "";
		String emptyString2 = "";
		String titleOfCourse = getTitle();		
		String emptyString3 = "";
		String emptyString4 = "";
		String meetingDaysCourse = getMeetingString();
		String detailsOfEvent = eventDetails;
		
		
		array[0] = emptyString1;
		array[1] = emptyString2;
		array[2] = titleOfCourse;
		array[3] = emptyString3;
		array[4] = emptyString4;
		array[5] = meetingDaysCourse;		
		array[6] = detailsOfEvent;
		
		return array;
	}
	
	/**
	 * Method to produce a comma separated string
	 * @return String of Event
	 */
	@Override
	public String toString() {		
		return getTitle() + "," + getMeetingDays() + "," + getStartTime() + "," + getEndTime() + "," + eventDetails;		
	}
	
	/**
	 * method to override setMeetingDaysAndTime so that super class Activity handles common checks only
	 * @param meetingDays of Event
	 * @param startTime of Event
	 * @param endTime of Event
	 * @throws IllegalArgumentException for invalid parameters
	 */
	@Override
	public void setMeetingDaysAndTime(String meetingDays, int startTime, int endTime) {
		
		// checking invalid meeting days
		if (meetingDays == null || "".equals(meetingDays))
			throw new IllegalArgumentException("Invalid meeting days and times.");
		if("A".equals(meetingDays))
			throw new IllegalArgumentException("Invalid meeting days and times.");		
	

		// counters for days
		int countM = 0;
		int countT = 0;
		int countW = 0;
		int countH = 0;
		int countF = 0;
		int countS = 0;
		int countU = 0;
		
		//checking for invalid letters
		
		for (int i = 0; i < meetingDays.length(); i++) {
			char current = meetingDays.charAt(i);
			if (current == 'M' || current == 'T' || current == 'W' || current == 'H' || current == 'F' || current == 'S' || current == 'U') {

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
				if (current == 'S')
					countS++;
				if (current == 'U')
					countU++;

				if (countM > 1 || countT > 1 || countW > 1 || countH > 1 || countF > 1 || countS > 1 || countU > 1)
					throw new IllegalArgumentException("Invalid meeting days and times.");
			} else
				throw new IllegalArgumentException("Invalid meeting days and times.");
		}
		
		super.setMeetingDaysAndTime(meetingDays, startTime, endTime);
	}
	
	/**
	 * method to check for duplicate events
	 * @param activity is event passed in
	 * @return boolean to know if duplicate event or not
	 */	
	public boolean isDuplicate(Product activity) {
		if(activity instanceof Event) {
			Event event = (Event) activity;
			return getTitle().equals(event.getTitle());
		}
		else
			return false;		
	}
	
	

}
