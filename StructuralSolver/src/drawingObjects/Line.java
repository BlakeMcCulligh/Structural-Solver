package drawingObjects;

import java.awt.Point;

public class Line {

	public Point Points[] = new Point[2];
	
	public Line(Point p1, Point p2) {
		Points[0] = p1;
		Points[1] = p2;
	}
	
}
