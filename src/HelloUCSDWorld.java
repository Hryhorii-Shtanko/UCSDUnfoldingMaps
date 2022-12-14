import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.geo.Location;
import de.fhpotsdam.unfolding.providers.Google;
import de.fhpotsdam.unfolding.utils.MapUtils;
import processing.core.PApplet;

/**
 * Hello World!
 * 
 * @author Hryhorii-Shtanko
 */
public class HelloUCSDWorld extends PApplet {

	UnfoldingMap map;

	@Override
	public void setup() {
		size(800, 600, OPENGL);

		map = new UnfoldingMap(this, new Google.GoogleTerrainProvider());
		map.zoomAndPanTo(14, new Location(32.881, -117.238)); // UCSD

		MapUtils.createDefaultEventDispatcher(this, map);
	}

	@Override
	public void draw() {
		background(0);
		map.draw();
	}

}
