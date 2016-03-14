package editor.function;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.geom.Point2D;

import editor.Framing;
import geom.Geom;

public class Navigation extends MouseAdapter implements Function {

	Point pressed; //device coords
	Point2D start, offset;

	@Override
	public void addToEditor(Framing framing) {

		framing.addMouseListener(this);
		framing.addMouseMotionListener(this);
		framing.addMouseWheelListener(this);

	}

	@Override
	public void removeFromEditor(Framing framing) {

		framing.removeMouseListener(this);
		framing.removeMouseMotionListener(this);
		framing.removeMouseWheelListener(this);

	}

	@Override
	public void mousePressed(MouseEvent e) {

		Framing framing = (Framing) e.getComponent();

		pressed = e.getPoint();
		start = framing.toWorldSpace(pressed);
		offset = new Point2D.Double(start.getX() - framing.worldPosition.getX(), start.getY() - framing.worldPosition.getY());
	}

	@Override
	public void mouseDragged(MouseEvent e) {

		Framing framing = (Framing) e.getComponent();

		//		double dx = e.getX() - pressed.getX();
		//		double dy = e.getY() - pressed.getY();
		//
		//		Point2D point2d = new Point2D.Double(dx, dy);
		//		Point2D vd = framing.toWorldSpace(point2d);
		//
		//		Point2D p2 = new Point2D.Double(start.getX() - offset.getX() - vd.getX(), start.getY() - offset.getY() - vd.getY());
		//		framing.worldPosition.setLocation(p2);

		spostaTelecameraSuMouse(start, e.getPoint(), framing);

		framing.repaint();
	}

	public void spostaTelecameraSuMouse(Point2D p, Point2D md, Framing framing) {

		//p è im world
		//md in device (mouse)

		Point2D mw = framing.toWorldSpace(md);
		Point2D dw = new Point2D.Double(mw.getX() - p.getX(), mw.getY() - p.getY());

		//		Point2D old = Geom.sub(start, offset);
		//		double fx = framing.worldPosition.getX();
		//		double fy = framing.worldPosition.getY();
		framing.worldPosition.setLocation(Geom.sub(framing.worldPosition, dw));

	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {

		Framing framing = (Framing) e.getComponent();

		Point2D fisso = framing.toWorldSpace(e.getPoint());

		if (e.getModifiers() != 0) {
			framing.alpha += e.getWheelRotation() * Math.PI / 10;
		} else {
			double f = framing.zoom * 30 / (30 + e.getWheelRotation() * 10);
			if (f < 0.5)
				f = 0.5;
			if (f > 32)
				f = 32;

			framing.zoom = f;

		}

		framing.updateMatrix();
		spostaTelecameraSuMouse(fisso, e.getPoint(), framing);

		framing.repaint();

	}

}
