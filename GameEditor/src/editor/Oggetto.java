package editor;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;

import org.jbox2d.collision.AABB;
import org.jbox2d.common.Vec2;

public class Oggetto {

	Integer id;

	Framing framing;

	Shape shape;

	public Oggetto(Framing framing, Shape shape) {
		super();
		this.framing = framing;
		this.shape = shape;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void draw(Graphics2D graphics2d) {

		graphics2d.setStroke(new BasicStroke(((float) framing.unitPerPixel)));
		graphics2d.setColor(Color.black);
		graphics2d.draw(shape);
		//		System.err.println("Oggetto draw: " + shape);

	}

	public AABB getAABB() {

		Rectangle rectangle = shape.getBounds();

		Vec2 lowerVertex = new Vec2((float) rectangle.getX(), (float) rectangle.getY());
		Vec2 upperVertex = new Vec2((float) (rectangle.getX() + rectangle.getWidth()), (float) (rectangle.getY() + rectangle.getHeight()));

		return new AABB(lowerVertex, upperVertex);
	}

	public Integer getId() {
		return id;
	}

}
