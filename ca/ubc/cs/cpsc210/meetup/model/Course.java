package ca.ubc.cs.cpsc210.meetup.model;

import java.util.HashSet;
import java.util.Set;

public class Course {
	private Set<Section> sections;
	private String code;
	private int number;
	
	
	
	public Set<Section> getSections() {
		return sections;
	}

	public String getCode() {
		return code;
	}

	public int getNumber() {
		return number;
	}

	public Course(String code, int number) {
		this.code = code;
		this.number = number;
		this.sections = new HashSet<Section>();
	}

	public void addSection(Section section) {
		sections.add(section);
	}
	
	public Section getSection(String sectionId) {
		for (Section s : sections) {
			if (s.getName().equals(sectionId))
				return s;
		}
		return null;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + number;
		result = prime * result
				+ ((sections == null) ? 0 : sections.hashCode());
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
		Course other = (Course) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		if (number != other.number)
			return false;
		if (sections == null) {
			if (other.sections != null)
				return false;
		} else if (!sections.equals(other.sections))
			return false;
		return true;
	}


	
	
	
}
