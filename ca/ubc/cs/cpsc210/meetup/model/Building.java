package ca.ubc.cs.cpsc210.meetup.model;

import ca.ubc.cs.cpsc210.meetup.util.LatLon;

public class Building extends Location {
	
	public Building(String name, LatLon latlon) {
		super(name, latlon);
	}
	public Building(String name) {
		super(name);
	}

}
