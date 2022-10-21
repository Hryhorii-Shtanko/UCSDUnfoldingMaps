package module4;

import de.fhpotsdam.unfolding.data.PointFeature;
import processing.core.PConstants;
import processing.core.PGraphics;

/**
 * Implements a visual marker for land earthquakes on an earthquake map
 * @author Hryhorii-Shtanko
 **/
public class LandQuakeMarker extends EarthquakeMarker {

	public LandQuakeMarker(PointFeature quake) {

		// calling EarthquakeMarker constructor
		super(quake);

		// setting field in earthquake marker
		isOnLand = true;
	}

	@Override
	public void drawEarthquake(PGraphics pg, float x, float y) {

		// TODO: Implement this method
		pg.ellipseMode(PConstants.CENTER);
		pg.ellipse(x, y, setMarkerSize(), setMarkerSize());

	}

	// Get the country the earthquake is in
	public String getCountry() {
		return (String) getProperty("country");
	}

}