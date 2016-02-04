package ca.ubc.cs.cpsc210.meetup.model;

import java.util.HashSet;
import java.util.Set;

import ca.ubc.cs.cpsc210.meetup.util.LatLon;

public class Place extends Location {
	private Set<String> tags;
	
	public Place(String name) {
		super(name);
		tags = new HashSet<String>();
	}
	
	public Place(String name, LatLon latlon) {
		super(name, latlon);
		tags = new HashSet<String>();
	}
	
	public void addTag(String tag) {
		tags.add(tag);
	}

	public boolean containsTag(String tag) {
		return tags.contains(tag);
	}
	
	
	
}
