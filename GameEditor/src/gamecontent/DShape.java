package gamecontent;

import java.io.Serializable;

import org.jbox2d.collision.shapes.ShapeType;

public class DShape implements Serializable {

	public ShapeType type;

	public float radius;

	public float getRadius() {
		return radius;
	}

	public void setRadius(float radius) {
		this.radius = radius;
	}

	public ShapeType getType() {
		return type;
	}

	public void setType(ShapeType type) {
		this.type = type;
	}

}
