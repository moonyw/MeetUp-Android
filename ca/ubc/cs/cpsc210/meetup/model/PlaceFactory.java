package ca.ubc.cs.cpsc210.meetup.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*
 * Provide a factory for places that have been "seen"
 */
public class PlaceFactory {

	private static PlaceFactory instance = null;
	
	private Map<String, Set<Place>> places;
	/**
	 * 
	 * @return an Singleton instance of the PlaceFactory
	 */

	public static PlaceFactory getInstance() {
		if (instance == null)
			instance = new PlaceFactory();
		return instance;
	}
	
	protected PlaceFactory() {
		places = new HashMap<String, Set<Place>>();
	}
	
	public void add(Place place) {
		String name = place.getName();

		if (places.containsKey(name)) {
			Set<Place> placesWithName = places.get(name);
			placesWithName.add(place);
		} else {
			Set<Place> placesWithName = new HashSet<Place>();
			placesWithName.add(place);
			places.put(name, placesWithName);
		}
	}
	
	public Set<Place> get(String name) {
		return places.get(name);
	}
	
	
}
