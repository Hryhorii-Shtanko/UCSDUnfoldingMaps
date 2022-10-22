package module4;

import de.fhpotsdam.unfolding.data.PointFeature;
import processing.core.PConstants;
import processing.core.PGraphics;

/**
 * Implements a visual marker for ocean earthquakes on an earthquake map
 * @author Hryhorii-Shtanko
 **/
public class OceanQuakeMarker extends EarthquakeMarker {

	public OceanQuakeMarker(PointFeature quake) {
		super(quake);

		// setting field in earthquake marker
		isOnLand = false;
	}

	@Override
	public void drawEarthquake(PGraphics pg, float x, float y) {
		// Drawing a centered square for Ocean earthquakes
		// DO NOT set the fill color. That will be set in the EarthquakeMarker
		// class to indicate the depth of the earthquake.
		// Simply draw a centered square.

		pg.rectMode(PConstants.CENTER);
		pg.rect(x, y, setMarkerSize(), setMarkerSize());
	}

}