package ca.ubc.cs.cpsc210.meetup.parser;

import java.io.IOException;
import java.io.Reader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import ca.ubc.cs.cpsc210.meetup.model.EatingPlace;
import ca.ubc.cs.cpsc210.meetup.model.Place;
import ca.ubc.cs.cpsc210.meetup.model.PlaceFactory;
import ca.ubc.cs.cpsc210.meetup.util.LatLon;

/**
 * Foursquare location result parse (JSON)
 */
public class PlacesParser {
	
	private PlaceFactory factory;
	
	public PlacesParser() {
		factory = PlaceFactory.getInstance();
	}

	/**
	 * Parse JSON from Foursquare output stored into a file
	 * REQUIRES: input is a file with valid data
	 * EFFECTS: parsed data is put into PlaceFactory
	 */
	public void parse(Reader input) {
		
		int intValueOfChar;
	    String jsonString = "";
	    try {
			while ((intValueOfChar = input.read()) != -1) {
				jsonString += (char) intValueOfChar;
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	    
	
		try {
			JSONObject obj = new JSONObject(jsonString);
			JSONObject response = obj.getJSONObject("response");
			JSONArray venues = response.getJSONArray("venues");
			for (int i = 0; i < venues.length(); i++) {
				JSONObject venue = venues.getJSONObject(i);
				String name = venue.getString("name");
				JSONObject location = venue.getJSONObject("location");
				double lat = location.getDouble("lat");
				double lng = location.getDouble("lng");
				LatLon latlon = new LatLon(lat, lng);
				Place place = new EatingPlace(name, latlon);
				factory.add(place);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
