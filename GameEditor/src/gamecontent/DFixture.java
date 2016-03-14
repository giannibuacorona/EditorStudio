package gamecontent;

import org.jbox2d.collision.shapes.Shape;
import org.jbox2d.dynamics.Filter;
import org.jbox2d.dynamics.FixtureDef;

public class DFixture {

	FixtureDef fixtureDef;

	@Override
	public int hashCode() {
		return fixtureDef.hashCode();
	}

	public Shape getShape() {
		return fixtureDef.getShape();
	}

	public void setShape(Shape shape) {
		fixtureDef.setShape(shape);
	}

	public Object getUserData() {
		return fixtureDef.getUserData();
	}

	public void setUserData(Object userData) {
		fixtureDef.setUserData(userData);
	}

	public float getFriction() {
		return fixtureDef.getFriction();
	}

	@Override
	public boolean equals(Object obj) {
		return fixtureDef.equals(obj);
	}

	public void setFriction(float friction) {
		fixtureDef.setFriction(friction);
	}

	public float getRestitution() {
		return fixtureDef.getRestitution();
	}

	public void setRestitution(float restitution) {
		fixtureDef.setRestitution(restitution);
	}

	public float getDensity() {
		return fixtureDef.getDensity();
	}

	public void setDensity(float density) {
		fixtureDef.setDensity(density);
	}

	public boolean isSensor() {
		return fixtureDef.isSensor();
	}

	public void setSensor(boolean isSensor) {
		fixtureDef.setSensor(isSensor);
	}

	public Filter getFilter() {
		return fixtureDef.getFilter();
	}

	public void setFilter(Filter filter) {
		fixtureDef.setFilter(filter);
	}

	@Override
	public String toString() {
		return fixtureDef.toString();
	}

}
