package ca.ubc.cs.cpsc210.meetup.model;

import ca.ubc.cs.cpsc210.meetup.util.LatLon;

public class Location {
	private LatLon latlon;
	private String displayText;
	
	public Location(String name) {
		this.displayText = name;
	}

	public Location(String name, LatLon latlon) {
		this.displayText = name;
		this.latlon = latlon;
	}
	
	public String getName() {
		return displayText;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((displayText == null) ? 0 : displayText.hashCode());
		result = prime * result + ((latlon == null) ? 0 : latlon.hashCode());
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
		Location other = (Location) obj;
		if (displayText == null) {
			if (other.displayText != null)
				return false;
		} else if (!displayText.equals(other.displayText))
			return false;
		if (latlon == null) {
			if (other.latlon != null)
				return false;
		} else if (!latlon.equals(other.latlon))
			return false;
		return true;
	}
	
	
}
