package module4;

import java.util.ArrayList;
import java.util.List;

import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.data.Feature;
import de.fhpotsdam.unfolding.data.GeoJSONReader;
import de.fhpotsdam.unfolding.data.PointFeature;
import de.fhpotsdam.unfolding.geo.Location;
import de.fhpotsdam.unfolding.marker.AbstractShapeMarker;
import de.fhpotsdam.unfolding.marker.Marker;
import de.fhpotsdam.unfolding.marker.MultiMarker;
import de.fhpotsdam.unfolding.providers.Microsoft;
import de.fhpotsdam.unfolding.utils.MapUtils;
import parsing.ParseFeed;
import processing.core.PApplet;

/**
 * EarthquakeCityMap An application with an interactive map displaying
 * earthquake data.
 * 
 * @author UC San Diego Intermediate Software Development MOOC team
 * @author Hryhorii-Shtanko
 **/
public class EarthquakeCityMap extends PApplet {

	// We will use member variables, instead of local variables, to store the data
	// that the setUp and draw methods will need to access (as well as other
	// methods)
	// You will use many of these variables, but the only one you should need to add
	// code to modify is countryQuakes, where you will store the number of
	// earthquakes
	// per country.

	// You can ignore this. It's to get rid of eclipse warnings
	private static final long serialVersionUID = 1L;

	/**
	 * This is where to find the local tiles, for working without an Internet
	 * connection
	 */
	public static String mbTilesString = "blankLight-1-3.mbtiles";

	// feed with magnitude 2.5+ Earthquakes
	private String earthquakesURL = "https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/2.5_week.atom";

	// The files containing city names and info and country names and info
	private String cityFile = "city-data.json";
	private String countryFile = "countries.geo.json";

	// The map
	private UnfoldingMap map;

	// Markers for each city
	private List<Marker> cityMarkers;
	// Markers for each earthquake
	private List<Marker> quakeMarkers;

	// A List of country markers
	private List<Marker> countryMarkers;

	@Override
	public void setup() {
		// (1) Initializing canvas and map tiles
		size(900, 700, OPENGL);

		// map = new UnfoldingMap(this, 200, 50, 650, 600, new
		// Google.GoogleMapProvider());
		map = new UnfoldingMap(this, 200, 50, 700, 500, new Microsoft.AerialProvider());
		// IF YOU WANT TO TEST WITH A LOCAL FILE, uncomment the next line
		// earthquakesURL = "2.5_week.atom";

		MapUtils.createDefaultEventDispatcher(this, map);

		// FOR TESTING: Set earthquakesURL to be one of the testing files by
		// uncommenting
		// one of the lines below. This will work whether you are online or offline
		// earthquakesURL = "test1.atom";
		// earthquakesURL = "test2.atom";

		// WHEN TAKING THIS QUIZ: Uncomment the next line
		// earthquakesURL = "quiz1.atom";

		// (2) Reading in earthquake data and geometric properties
		// STEP 1: load country features and markers
		List<Feature> countries = GeoJSONReader.loadData(this, countryFile);
		countryMarkers = MapUtils.createSimpleMarkers(countries);

		// STEP 2: read in city data
		List<Feature> cities = GeoJSONReader.loadData(this, cityFile);
		cityMarkers = new ArrayList<Marker>();
		for (Feature city : cities) {
			cityMarkers.add(new CityMarker(city));
		}

		// STEP 3: read in earthquake RSS feed
		List<PointFeature> earthquakes = ParseFeed.parseEarthquake(this, earthquakesURL);
		quakeMarkers = new ArrayList<Marker>();

		for (PointFeature feature : earthquakes) {
			// check if LandQuake
			if (isLand(feature)) {
				quakeMarkers.add(new LandQuakeMarker(feature));
			}
			// OceanQuakes
			else {
				quakeMarkers.add(new OceanQuakeMarker(feature));
			}
		}

		// could be used for debugging
		// printQuakes();

		map.addMarkers(quakeMarkers);
		map.addMarkers(cityMarkers);

	}

	@Override
	public void draw() {
		background(0);
		map.draw();
		addKey();

	}

	// helper method to draw key in GUI
	private void addKey() {
		fill(255, 250, 240);
		rect(25, 50, 150, 250);

		fill(0);
		textAlign(LEFT, CENTER);
		textSize(13);
		text("Earthquake Key", 50, 75);
		text("Size - Magnitude", 50, 170);

		fill(color(139, 0, 0));
		triangle(55, 105, 65, 105, 60, 95); // city
		fill(color(255, 255, 255));
		ellipse(60, 120, 15, 15); // land quake
		fill(color(255, 255, 255));
		rect(53, 135, 15, 15); // ocean quake
		fill(color(255, 255, 0));
		ellipse(60, 190, 10, 10); // shallow
		fill(color(0, 0, 255));
		ellipse(60, 210, 10, 10); // intermediate
		fill(color(255, 0, 0));
		ellipse(60, 230, 10, 10); // deep
		fill(color(255));
		ellipse(60, 250, 10, 10); // past day
		line(51, 242, 70, 258); // line
		line(70, 242, 51, 258); // line
		strokeWeight(2);
		ellipseMode(CENTER);

		fill(0, 0, 0);
		text("City Marker", 75, 100);
		text("Land Quake", 75, 120);
		text("Ocean Quake", 75, 143);
		text("Shallow", 75, 190);
		text("Intermediate", 75, 210);
		text("Deep", 75, 230);
		text("Past Day", 75, 250);
	}

	// Checks whether this quake occurred on land. If it did, it sets the
	// "country" property of its PointFeature to the country where it occurred
	// and returns true. Notice that the helper method isInCountry will
	// set this "country" property already. Otherwise it returns false.
	private boolean isLand(PointFeature earthquake) {

		for (Marker country : countryMarkers) {
			if (isInCountry(earthquake, country)) {
				return true;
			}

		}

		// not inside any country
		return false;
	}

	private void printQuakes() {
		int waterQuakes = quakeMarkers.size();
		for (Marker country : countryMarkers) {
			String countryName = country.getStringProperty("name");
			int numOfQuakes = 0;
			for (Marker quake : quakeMarkers) {
				EarthquakeMarker landQuakes = (EarthquakeMarker) quake;
				if (landQuakes.isOnLand()) {
					if (countryName.equals(landQuakes.getStringProperty("country"))) {
						numOfQuakes++;
					}
				}
			}
			if (numOfQuakes > 0) {
				waterQuakes -= numOfQuakes;
				System.out.println(countryName + ": " + numOfQuakes);
			}
		}
		System.out.println("Ocean Quakes: " + waterQuakes);
	}

	private boolean isInCountry(PointFeature earthquake, Marker country) {
		Location checkLoc = earthquake.getLocation();

		if (country.getClass() == MultiMarker.class) {

			for (Marker marker : ((MultiMarker) country).getMarkers()) {

				if (((AbstractShapeMarker) marker).isInsideByLocation(checkLoc)) {
					earthquake.addProperty("country", country.getProperty("name"));

					return true;
				}
			}
		}

		else if (((AbstractShapeMarker) country).isInsideByLocation(checkLoc)) {
			earthquake.addProperty("country", country.getProperty("name"));

			return true;
		}
		return false;
	}

}
