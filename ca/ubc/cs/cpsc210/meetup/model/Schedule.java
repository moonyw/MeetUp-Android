package ca.ubc.cs.cpsc210.meetup.model;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import ca.ubc.cs.cpsc210.meetup.exceptions.IllegalSectionInitialization;
import ca.ubc.cs.cpsc210.meetup.util.CourseTime;

/*
 * Represent a student's schedule consisting of all sections they must attend
 */
public class Schedule {

	private SortedSet<Section> sections;
	
	/**
	 * Constructor
	 */
	public Schedule() {
		sections = new TreeSet<Section>();
	}

	/**
	 * Add a section to the student's schedule 
	 * REQUIRES: section is not null
	 * MODIFIES: this 
	 * EFFECTS: if section is not well-formed, throws
	 * IllegalSectionInitialization otherwise, section is remembered in the
	 * schedule
	 */
	public void add(Section section) throws IllegalSectionInitialization {
		if (section == null)
			throw new IllegalSectionInitialization();
		if (section.getCourse() == null)
			throw new IllegalSectionInitialization();
		
		sections.add(section);
	}

	/**
	 * Retrieve the earliest start time in the schedule on a given day 
	 * REQUIRES: dayOfWeek is either "MWF" or "TR" 
	 * EFFECTS: Returns the start and end
	 *    times of the earliest section on that day or null
	 */
	public CourseTime startTime(String dayOfWeek) {
		SortedSet<Section> todaySections = new TreeSet<Section>();
		for (Section s : sections) {
			if (s.getDay().equals(dayOfWeek))
				todaySections.add(s);
		}
		if (todaySections.isEmpty())
			return null;

		return todaySections.first().getTimeOfCourse();
	}

	public SortedSet<Section> getSections() {
		return sections;
	}
	
	/**
	 * Retrieve the latest start time in the schedule on a given day 
	 * REQUIRES: dayOfWeek is either "MWF" or "TR" 
	 * EFFECTS: Returns the start and end
	 *    times of the latest section on that day or null
	 */
	public CourseTime endTime(String dayOfWeek) {
		SortedSet<Section> todaySections = new TreeSet<Section>();
		for (Section s : sections) {
			if (s.getDay().equals(dayOfWeek))
				todaySections.add(s);
		}
		if (todaySections.isEmpty())
			return null;

		return todaySections.last().getTimeOfCourse();
	}
	/**
	 * computes the minutes since 00:00 of the day
	 * 
	 */
	private static int toMinutes(String s) {
		String[] hourMin = s.split(":");
		int hour = Integer.parseInt(hourMin[0]);
		int min = Integer.parseInt(hourMin[1]);
		return hour * 60 + min;
	}
	
	/**
	 * Find the start time of all two hour breaks less than the end time
	 * REQUIRES: dayOfWeek is either "MWF" or "TR" 
	 * EFFECTS: Returns a set of the end time before any 2 hour break. The end time is in HH:MM format.
	 */
	public Set<String> getStartTimesOfBreaks(String dayOfWeek) {
		SortedSet<Section> todaySections = new TreeSet<Section>();
		for (Section s : sections) {
			if (s.getDay().equals(dayOfWeek))
				todaySections.add(s);
		}
		Iterator<Section> iterator = todaySections.iterator();
		Set<String> endTimes = new HashSet<String>();
		
		Section prev = iterator.next();
		while (iterator.hasNext()) {
			Section next = iterator.next();
			if (toMinutes(next.getTimeOfCourse().getStartTime()) 
					- toMinutes(prev.getTimeOfCourse().getEndTime()) >= 120) {
				endTimes.add(prev.getTimeOfCourse().getEndTime());
			}
			prev = next;
		}
		return endTimes;
	}

	/**
	 * In which building was I before the given timeOfDay on the given dayOfWeek
	 * REQUIRES: dayOfWeek is "MWF or "TR" and timeOfDay is non-null and of
	 * format HH:MM 
	 * EFFECTS: The Building in which the student last was before
	 * timeOfDay on dayOfWeek or null
	 */
	public Building whereAmI(String dayOfWeek, String timeOfDay) {
		SortedSet<Section> todaySections = new TreeSet<Section>();
		int minuteOfDay = toMinutes(timeOfDay);
		for (Section s : sections) {
			if (s.getDay().equals(dayOfWeek))
				todaySections.add(s);
		}
		if (todaySections.isEmpty())
			return null;
		Iterator<Section> iterator = todaySections.iterator();

		Section prev = iterator.next();
		Section next = null;
		while (iterator.hasNext()) {
			next = iterator.next();
			if (minuteOfDay >= toMinutes(prev.getTimeOfCourse().getStartTime()) &&
					toMinutes(next.getTimeOfCourse().getStartTime()) > minuteOfDay)
				return prev.getBuilding();
			prev = next;
		}
		return next.getBuilding();
	}


}
