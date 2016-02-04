package ca.ubc.cs.cpsc210.meetup.parser;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import ca.ubc.cs.cpsc210.meetup.exceptions.IllegalStudentException;
import ca.ubc.cs.cpsc210.meetup.model.StudentManager;

/**
 * Parse XML of a schedule (Sax parser)
 */

public class ScheduleParser extends DefaultHandler {

	private StringBuffer accumulator;
	private StudentManager manager;
	
	private String lastName;
	private String firstName;
	private int studentId;
	
	private int sectionStudentId;
	private String sectionName;
	private String courseCode;
	private int courseNumber;
	

	// TODO: Add necessary fields

	public ScheduleParser(StudentManager manager) {
		this.manager = manager;
	}

	/**
	 * Called at the start of the document (as the name suggests)
	 */
	@Override
	public void startDocument() {
		accumulator = new StringBuffer();
		
	}

	/**
	 * Called when the parsing of an element starts. (e.g., <book>)
	 * 
	 * Lookup documentation to learn meanings of parameters.
	 */
	@Override
	public void startElement(String namespaceURI, String localName,
			String qName, Attributes atts) {
		if (qName.toLowerCase().equals("student")) {
			lastName = null;
			firstName = null;
			studentId = 0;
			
			accumulator.setLength(0);
		} else if (qName.toLowerCase().equals("schedule")) {
		 	sectionStudentId = 0;
			sectionName = null;
			courseCode = null;
			courseNumber = 0;
			accumulator.setLength(0);
		} else if (qName.toLowerCase().equals("section")) {
			sectionName = atts.getValue("name");
			courseCode = atts.getValue("courseCode");
			courseNumber = Integer.parseInt(atts.getValue("courseNumber"));
			manager.addSectionToSchedule(sectionStudentId, courseCode, courseNumber, sectionName);
		}
	}

	/**
	 * Called for values of elements
	 * 
	 * Lookup documentation to learn meanings of parameters.
	 */
	public void characters(char[] temp, int start, int length) {
		// Remember the value parsed
		accumulator.append(temp, start, length);
	}

	/**
	 * Called when the end of an element is seen. (e.g., </title>)
	 * 
	 * Lookup documentation to learn meanings of parameters.
	 */
	public void endElement(String uri, String localName, String qName) {
		if (qName.toLowerCase().equals("lastname")) {
			lastName = accumulator.toString().trim();
		} else if (qName.toLowerCase().equals("firstname")) {
			firstName = accumulator.toString().trim();
		} else if (qName.toLowerCase().equals("id")) {
			studentId = Integer.parseInt(accumulator.toString().trim());
		} else if (qName.toLowerCase().equals("student")) {
			if (firstName.equals("") || lastName.equals(""))
				throw new IllegalStudentException();
			manager.addStudent(lastName,firstName,studentId);
			
		} else if (qName.toLowerCase().equals("studentid")) {
			sectionStudentId = Integer.parseInt(accumulator.toString().trim());
		}
		
		accumulator.setLength(0);
	}

}
