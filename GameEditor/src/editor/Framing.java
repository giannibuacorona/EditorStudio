package editor;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.Stack;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jbox2d.callbacks.TreeCallback;
import org.jbox2d.collision.AABB;
import org.jbox2d.collision.broadphase.DynamicTree;

import editor.function.Function;
import editor.function.Navigation;

public class Framing extends JPanel {

	//centro della telecamera in world space (varianile a seconda dell'inquadratura)
	public Point2D worldPosition = new Point2D.Double(0, 0);

	//centro della telecamera nel device space (non dipende dall'inquadratura)
	public Point2D devicePosition = new Point2D.Double(200, 200);

	public double zoom = 0.5f;
	public double alpha = Math.PI / 6;
	AffineTransform pvm;
	AffineTransform inv;
	//	Point2D clicked = new Point2D.Double();

	DynamicTree space = new DynamicTree();
	AABB aabb = new AABB();

	Stack<Oggetto> stack = new Stack<>();

	Function function;

	/*
	 * unitPerPixel: è la misura di un pixel in unità del world. Serve quando si
	 * vogliono disegnare pixel.
	 */
	double unitPerPixel; //misura di un pixel in unità world

	private TreeCallback callback = new TreeCallback() {

		@Override
		public boolean treeCallback(int proxyId) {
			Oggetto item = (Oggetto) space.getUserData(proxyId);
			stack.push(item);
			return true;
		}

	};

	public Function getFunction() {
		return function;
	}

	public void setFunction(Function function) {
		if (function == this.function)
			return;
		if (this.function != null) {
			this.function.removeFromEditor(this);
			this.function = null;
		}
		if (function != null) {
			function.addToEditor(this);
			this.function = function;
		}
	}

	void add(Oggetto oggetto) {

		if (oggetto.getId() != null) {
			throw new IllegalArgumentException();
		}
		int id = space.createProxy(oggetto.getAABB(), oggetto);
		oggetto.setId(id);

	}

	void remove(Oggetto oggetto) {

		space.destroyProxy(oggetto.getId());
		oggetto.setId(null);

	}

	public void updateMatrix() {

		pvm = new AffineTransform();

		//trasformazione del device (projection)

		double widthOverTwo = getWidth() * 0.5;
		double heightOverTwo = getHeight() * 0.5;

		pvm.translate(widthOverTwo, heightOverTwo);

		double ratio = ((double) getWidth()) / getHeight();
		float i;
		if (ratio > 1) {
			pvm.scale(widthOverTwo / ratio, -heightOverTwo);
			i = (float) (1 / heightOverTwo);
		} else {
			pvm.scale(widthOverTwo, -heightOverTwo * ratio);
			i = 1 / (float) widthOverTwo;
		}

		//trasformazione della telecamera (view)
		//pvm.translate(-worldPosition.getX(), -worldPosition.getY());
		pvm.scale(zoom, zoom);
		i /= zoom;
		unitPerPixel = i;
		pvm.rotate(-alpha);
		pvm.translate(-worldPosition.getX(), -worldPosition.getY());

		try {
			inv = pvm.createInverse();
		} catch (NoninvertibleTransformException e) {
			e.printStackTrace();
		}

	}

	@Override
	protected void paintComponent(Graphics g) {

		super.paintComponent(g);

		Graphics2D graphics2d = (Graphics2D) g;

		updateMatrix();

		graphics2d.setTransform(pvm);
		graphics2d.scale(0.5, 0.5);

		// D R A W   H E R E ! !
		Path2D path = new Path2D.Double();
		Point2D sp = toWorldSpace(new Point2D.Double());
		path.moveTo(sp.getX(), sp.getY());
		sp = toWorldSpace(new Point2D.Double(getWidth(), 0));
		path.lineTo(sp.getX(), sp.getY());
		sp = toWorldSpace(new Point2D.Double(getWidth(), getHeight()));
		path.lineTo(sp.getX(), sp.getY());
		sp = toWorldSpace(new Point2D.Double(0, getHeight()));
		path.lineTo(sp.getX(), sp.getY());
		path.closePath();
		graphics2d.setColor(new Color(200, 200, 255));
		graphics2d.setStroke(new BasicStroke(((float) unitPerPixel)));
		graphics2d.fill(path);

		computeAABB();

		Rectangle2D r = new Rectangle2D.Double(aabb.lowerBound.x, aabb.lowerBound.y, aabb.upperBound.x - aabb.lowerBound.x, aabb.upperBound.y - aabb.lowerBound.y);
		graphics2d.setColor(Color.red);
		graphics2d.setStroke(new BasicStroke(((float) unitPerPixel)));
		graphics2d.draw(r);

		stack = new Stack<>();

		space.query(callback, aabb);

		//		System.err.println("stack : " + stack.size());

		for (Oggetto oggetto : stack) {

			oggetto.draw(graphics2d);

		}

		//		Ellipse2D ellipse2d = new Ellipse2D.Double(-1, -1, 2, 2);
		//		Rectangle2D rectangle2d = new Rectangle2D.Double(-2, -1, 2, 1);
		//

		//		graphics2d.draw(ellipse2d);
		//		graphics2d.draw(rectangle2d);
		//
		//		Ellipse2D punto = new Ellipse2D.Double(clicked.getX() - 3 * i, clicked.getY() - 3 * i, 6 * i, 6 * i);
		//		graphics2d.draw(punto);

	}

	/*
	 * 
	 */
	void computeAABB() {

		Point2D[] verteces = { new Point2D.Double(0, 0), new Point2D.Double(getWidth(), 0), new Point2D.Double(getWidth(), getHeight()), new Point2D.Double(0, getHeight()) };

		double minX = Double.MAX_VALUE, minY = Double.MAX_VALUE, maxX = -100000000, maxY = -10000000;

		for (int i = 0; i < verteces.length; i++) {

			Point2D point = toWorldSpace(verteces[i]);

			if (point.getX() < minX)
				minX = point.getX();
			if (point.getX() > maxX)
				maxX = point.getX();

			if (point.getY() < minY)
				minY = point.getY();
			if (point.getY() > maxY)
				maxY = point.getY();

		}

		aabb.lowerBound.x = (float) minX;
		aabb.lowerBound.y = (float) minY;
		aabb.upperBound.x = (float) maxX;
		aabb.upperBound.y = (float) maxY;

		System.err.println(String.format("lower bound: %.3f,%.3f  - upper bound: %.3f,%.3f", minX, minY, maxX, +maxY));

	}

	public void toWorldSpace(Point2D src, Point2D dst) {

		inv.transform(src, dst);

	}

	public Point2D toWorldSpace(Point2D point2d) {

		Point2D dst = new Point2D.Double();
		toWorldSpace(point2d, dst);
		return dst;
	}

	public void toDeviceSpace(Point2D src, Point2D dst) {

		pvm.transform(src, dst);

	}

	public Point2D toDeviceSpace(Point2D point2d) {

		Point2D dst = new Point2D.Double();
		toDeviceSpace(point2d, dst);
		return dst;
	}

	public static void main(String[] args) throws InterruptedException {

		Framing framing = new Framing();
		framing.zoom = 1;
		framing.alpha = 0;
		framing.worldPosition.setLocation(0, 0);
		//		framing.setFocusable(true);
		Navigation f = new Navigation();
		framing.setFunction(f);

		int n = 10;
		for (int i = 0; i < n; i++) {

			Oggetto oggetto = new Oggetto(framing, new Rectangle2D.Float(0, i * 1.1f, 1, 1));
			framing.add(oggetto);

		}

		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.add(framing);

		frame.setBounds(new Rectangle(200, 200, 400, 400));
		frame.setVisible(true);
		//		framing.requestFocus();

		//		Thread.sleep(2000);
		//		f.spostaTelecameraSuMouse(new Point2D.Double(), new Point2D.Double(0, 0), framing);
		//		//framing.worldPosition.setLocation(1, 1);
		//
		//		framing.repaint();

	}
}
