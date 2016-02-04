package ca.ubc.cs.cpsc210.meetup.model;
import ca.ubc.cs.cpsc210.meetup.exceptions.IllegalSectionInitialization;
import ca.ubc.cs.cpsc210.meetup.util.CourseTime;

/**
 * Represent a section for a course
 */
public class Section implements Comparable<Section> {

	// TODO: Add necessary fields
	private String name;
	private String day;
	private Building building;
	
	public Building getBuilding() {
		return building;
	}

	private Course course;
	
	
	// Time of course is provided to implement comparable
	private CourseTime timeOfCourse;

	public String getDay() {
		return day;
	}


	public CourseTime getTimeOfCourse() {
		return timeOfCourse;
	}


	/**
	 * Constructor 
	 * REQUIRES: name is not null, day is "MWF" or "TR", startTime
	 *   is before endTime and building is not null 
	 * EFFECTS: object is initialized
	 *   or the exception IllegalSectionInitialization has occurred
	 * @throws IllegalSectionInitialization 
	 */
	public Section(String name, String day, String startTime, String endTime,
			Building building) {
		this.name = name;
		this.day = day;
		this.building = building;
		
		timeOfCourse = new CourseTime(startTime, endTime);
	}

	
	public void setCourse(Course course) {
		this.course = course;
	}

	public Course getCourse() {
		return course;
	}

	public String getName() {
		return name;
	}
	
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((course == null) ? 0 : course.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Section other = (Section) obj;
		if (course == null) {
			if (other.course != null)
				return false;
		} else if (!course.equals(other.course))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}


	@Override
	public int compareTo(Section o) {
		return timeOfCourse.compareTo(o.timeOfCourse);
	}

}
