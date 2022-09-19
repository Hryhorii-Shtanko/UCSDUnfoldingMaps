package module3;

import java.util.ArrayList;
//import java.util.Collections;
//import java.util.Comparator;
import java.util.List;

//Unfolding libraries
import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.data.Feature;
import de.fhpotsdam.unfolding.data.PointFeature;
import de.fhpotsdam.unfolding.geo.Location;
import de.fhpotsdam.unfolding.marker.Marker;
import de.fhpotsdam.unfolding.marker.SimplePointMarker;
import de.fhpotsdam.unfolding.providers.Microsoft;
import de.fhpotsdam.unfolding.utils.MapUtils;
//Processing library
import processing.core.PApplet;

/**
 * EarthquakeCityMap An application with an interactive map displaying
 * earthquake data. Author: UC San Diego Intermediate Software Development MOOC
 * team
 * 
 * @author Hryhorii-Shtanko
 **/
public class EarthquakeCityMap extends PApplet {

	// You can ignore this. It's to keep eclipse from generating a warning.
	private static final long serialVersionUID = 1L;

	// IF YOU ARE WORKING OFFLINE, change the value of this variable to true
	private static final boolean offline = false;

	// Less than this threshold is a light earthquake
	public static final float THRESHOLD_MODERATE = 5;
	// Less than this threshold is a minor earthquake
	public static final float THRESHOLD_LIGHT = 4;

	/**
	 * This is where to find the local tiles, for working without an Internet
	 * connection
	 */
	public static String mbTilesString = "blankLight-1-3.mbtiles";

	// The map
	private UnfoldingMap map;

	// feed with magnitude 2.5+ Earthquakes
	private String earthquakesURL = "https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/2.5_week.atom";

	public void setup() {
		size(950, 600, OPENGL);

		map = new UnfoldingMap(this, 200, 50, 700, 500, new Microsoft.AerialProvider());
////	map = new UnfoldingMap(this, 200, 50, 700, 500, new Google.GoogleMapProvider());

//		if (offline) {
//			map = new UnfoldingMap(this, 200, 50, 700, 500, new MBTilesMapProvider(mbTilesString));
//			// earthquakesURL = "2.5_week.atom";
//		} else {
////			map = new UnfoldingMap(this, 200, 50, 700, 500, new Google.GoogleMapProvider());
//			// IF YOU WANT TO TEST WITH A LOCAL FILE, uncomment the next line
//			// earthquakesURL = "2.5_week.atom";
//		}

		map.zoomToLevel(1);
		MapUtils.createDefaultEventDispatcher(this, map);

		Location valdiviaLoc = new Location(-38.14f, -73.03f);
		Feature valdiviaEq = new PointFeature(valdiviaLoc);
		valdiviaEq.addProperty("title", "Valdivia, Chile");
		valdiviaEq.addProperty("magnitude", "9.5");
		valdiviaEq.addProperty("date", "May 22, 1960");
		valdiviaEq.addProperty("year", "1960");

		Location alaskaLoc = new Location(61.02f, -147.65f);
		Feature alaskaEq = new PointFeature(alaskaLoc);
		alaskaEq.addProperty("title", "Great Alaska Earthquake");
		alaskaEq.addProperty("magnitude", "9.2");
		alaskaEq.addProperty("date", "28.03.1964");
	    alaskaEq.addProperty("year", 1964);


		Location sumatraLoc = new Location(3.30f, 95.78f);
		Feature sumatraEq = new PointFeature(sumatraLoc);
		sumatraEq.addProperty("title", "Off the West Coast of Northern Sumatra");
		sumatraEq.addProperty("magnitude", "9.1");
		sumatraEq.addProperty("date", "26.12.2004");
	    sumatraEq.addProperty("year", 2004);


		Location japanLoc = new Location(38.322f, 142.65f);
		Feature japanEq = new PointFeature(japanLoc);
		japanEq.addProperty("title", "Tohoku earthquake and tsunami");
		japanEq.addProperty("magnitude", "9.0");
		japanEq.addProperty("date", "11.05.2011");
	    japanEq.addProperty("year", 2011);


		Location kamchatkaLoc = new Location(52.76f, 160.06f);
		Feature kamchatkaEq = new PointFeature(kamchatkaLoc);
		kamchatkaEq.addProperty("title", "Severo-Kurilsk earthquake, Kamchatka");
		kamchatkaEq.addProperty("magnitude", "9.0");
		kamchatkaEq.addProperty("data", "04.11.1952");
	    kamchatkaEq.addProperty("year", 1952);


	    Marker valdiviaMk = new SimplePointMarker(valdiviaLoc, valdiviaEq.getProperties());
	    map.addMarker(valdiviaMk);
	    Marker alaskaMk = new SimplePointMarker(alaskaLoc, alaskaEq.getProperties());
	    map.addMarker(alaskaMk);
	    Marker sumatraMk = new SimplePointMarker(sumatraLoc, sumatraEq.getProperties());
	    map.addMarker(sumatraMk);
	    Marker japanMk = new SimplePointMarker(japanLoc, japanEq.getProperties());
	    map.addMarker(japanMk);
		Marker kamchatkaMk = new SimplePointMarker(kamchatkaLoc, kamchatkaEq.getProperties());
		map.addMarker(kamchatkaMk);
		
		SimplePointMarker valdivia = new SimplePointMarker(valdiviaLoc);
		map.addMarker(valdivia);
		SimplePointMarker alaska = new SimplePointMarker(alaskaLoc);
		map.addMarker(alaska);
		SimplePointMarker sumatra = new SimplePointMarker(sumatraLoc);
		map.addMarker(sumatra);
		SimplePointMarker japan = new SimplePointMarker(japanLoc);
		map.addMarker(japan);
		SimplePointMarker kamchatka = new SimplePointMarker(kamchatkaLoc);
		map.addMarker(kamchatka);
		
		// The List you will populate with new SimplePointMarkers
		List<PointFeature> bigEqs = new ArrayList<PointFeature>();
		bigEqs.add((PointFeature) valdiviaEq);
		 bigEqs.add((PointFeature)alaskaEq);
		 bigEqs.add((PointFeature)sumatraEq);
		 bigEqs.add((PointFeature)japanEq);
		 bigEqs.add((PointFeature)kamchatkaEq);
		// ..
//		List<Marker> markers = new ArrayList<Marker>();
//				for (PointFeature eq : bigEqs) {
//			markers.add(new SimplePointMarker(eq.getLocation(), eq.getProperties()));
//			}
		// List<PointFeature> earthquakes = ParseFeed.parseEarthquake(this,
		// earthquakesURL);
		// Use provided parser to collect properties for each earthquake
		// PointFeatures have a getLocation
		// map.addMarkers(markers);
		
		 List<Marker> markers = new ArrayList<Marker>();
		    for (PointFeature eq: bigEqs) {
		    	markers.add(new SimplePointMarker(eq.getLocation(), eq.getProperties()));
		    }
		    map.addMarkers(markers);
 
		    int yellow = color(255, 255, 0);
		    int gray = color(150,150,150);
		    
		    for (Marker mk :markers) {
		    	if ( (int) mk.getProperty("year") > 2006 ) {
		    		mk.setColor(yellow);
		    	}
		    	else {
		    		mk.setColor(gray);
		    	}
		    }
		    
				
//		 int yellow = color(255, 255, 0);
//		    int gray = color(150,150,150);
//		    
//		    for (Marker mk :markers) {
//		    	if ( (int) mk.getProperty("year") > 2006 ) {
//		    		mk.setColor(yellow);
//		    	}
//		    	else {
//		    		mk.setColor(gray);
//		    	}
//		    }
		
//public static 		List<PointFeature> parseEarthquake (PApplet p, String fileName){
	//p:"module3."
		//https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/2.5_week.atom


		


		
		// TODO (Step 3): Add a loop here that calls createMarker (see below)
		// to create a new SimplePointMarker for each PointFeature in
		// earthquakes. Then add each new SimplePointMarker to the
		// List markers (so that it will be added to the map in the line below)

		// Add the markers to the map so that they are displayed
		//map.addMarkers(markers);
		
	

	
	
	/*
	 * createMarker: A suggested helper method that takes in an earthquake feature
	 * and returns a SimplePointMarker for that earthquake
	 * 
	 * In step 3 You can use this method as-is. Call it from a loop in the setp
	 * method.
	 * 
	 * TODO (Step 4): Add code to this method so that it adds the proper styling to
	 * each marker based on the magnitude of the earthquake.
	 */
	}
	private SimplePointMarker createMarker(PointFeature feature) {
		// To print all of the features in a PointFeature (so you can see what they are)
		// uncomment the line below. Note this will only print if you call createMarker
		// from setup
		System.out.println(feature.getProperties());

		// Create a new SimplePointMarker at the location given by the PointFeature
		SimplePointMarker marker = new SimplePointMarker(feature.getLocation());

		Object magObj = feature.getProperty("magnitude");
		float mag = Float.parseFloat(magObj.toString());

		// Here is an example of how to use Processing's color method to generate
		// an int that represents the color yellow.
		int yellow = color(255, 255, 0);

		// TODO (Step 4): Add code below to style the marker's size and color
		// according to the magnitude of the earthquake.
		// Don't forget about the constants THRESHOLD_MODERATE and
		// THRESHOLD_LIGHT, which are declared above.
		// Rather than comparing the magnitude to a number directly, compare
		// the magnitude to these variables (and change their value in the code
		// above if you want to change what you mean by "moderate" and "light")
		// Finally return the marker

		
		
		return marker;
	}

	public void draw() {
		background(GRAY);
		map.draw();
		addKey();
	}

	// helper method to draw key in GUI
	// TODO: Implement this method to draw the key
	private void addKey() {
		// Remember you can use Processing's graphics methods here
		rect(40, 50, 150, 250);
		
		//colorMode(HSB,0,0,0);		
		
		
		String s="5.0+  Magnitude";
		fill(50);
		text(s,150,250);
		textSize(15);
		textAlign(RIGHT);
	
	}
}
