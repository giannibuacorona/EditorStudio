package geom;

import java.awt.geom.Point2D;

public class Geom {

	public static void add(Point2D p1, Point2D p2, Point2D result) {
		result.setLocation(p1.getX() + p2.getX(), p1.getY() + p2.getY());
	}

	public static Point2D add(Point2D p1, Point2D p2) {
		Point2D result = new Point2D.Double();
		add(p1, p2, result);
		return result;
	}

	public static void sub(Point2D p1, Point2D p2, Point2D result) {
		result.setLocation(p1.getX() - p2.getX(), p1.getY() - p2.getY());
	}

	public static Point2D sub(Point2D p1, Point2D p2) {
		Point2D result = new Point2D.Double();
		sub(p1, p2, result);
		return result;
	}
}
