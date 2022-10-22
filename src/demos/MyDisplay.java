package demos;

import processing.core.PApplet;

/**
 * A class to illustrate some use of the PApplet class Used in module 3 of the
 * UC San Diego MOOC Object Oriented Programming in Java
 * 
 * @author Hryhorii-Shtanko
 */
public class MyDisplay extends PApplet {

	@Override
	public void setup() {
		size(400, 400);
		background(200, 200, 200);

	}

	@Override
	public void draw() {
		fill(255, 255, 0);
		ellipse(200, 200, 390, 390);
		fill(0, 0, 0);
		ellipse(110, 130, 50, 70);
		ellipse(280, 130, 50, 70);

		fill(250, 0, 0);
		arc(200, 240, 75, 75, 0, PI);
	}

}
